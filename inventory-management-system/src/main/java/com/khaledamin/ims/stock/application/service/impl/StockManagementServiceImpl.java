package com.khaledamin.ims.stock.application.service.impl;

import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.core.exception.core.BaseException;
import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.provider.ActorProvider;
import com.khaledamin.ims.media.core.model.MediaOwnerType;
import com.khaledamin.ims.media.image.application.service.ImageService;
import com.khaledamin.ims.media.image.domain.model.Image;
import com.khaledamin.ims.organization.application.service.OrganizationQueryService;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.organization.domain.value.OrganizationCode;
import com.khaledamin.ims.stock.api.dto.*;
import com.khaledamin.ims.stock.application.service.StockManagementService;
import com.khaledamin.ims.stock.application.service.StockQueryService;
import com.khaledamin.ims.stock.domain.command.RestockCommand;
import com.khaledamin.ims.stock.domain.command.StockCreateCommand;
import com.khaledamin.ims.stock.domain.command.StockIUpdateCommand;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import com.khaledamin.ims.stock.domain.model.StockImagePreset;
import com.khaledamin.ims.stock.domain.model.StockItem;
import com.khaledamin.ims.stock.domain.policy.StockAccessPolicy;
import com.khaledamin.ims.stock.domain.repository.StockRepository;
import com.khaledamin.ims.stock.domain.value.StockCode;
import com.khaledamin.ims.stock.domain.value.StockSKU;
import com.khaledamin.ims.stock.exception.StockBusinessException;
import com.khaledamin.ims.stock.exception.StockTechnicalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@AllArgsConstructor
public class StockManagementServiceImpl implements StockManagementService {
    private final StockRepository stockRepository;
    private final StockQueryService stockQueryService;
    private final StockAccessPolicy stockAccessPolicy;
    private final ImageService imageService;
    private final ActorProvider actorProvider;
    private final OrganizationQueryService organizationQueryService;
    private final BusinessEventLogger businessEventLogger;


    @Transactional
    @Override
    public StockItem create(StockCreateRequest request) {

        Actor currentActor = actorProvider.getCurrent();
        Organization organization = organizationQueryService.getByOwnerIdentity(currentActor.getActorIdentity());

        ensureSkuUnique(
                request.getSku(),
                organization.getCode()
        );

        StockCreateCommand command = StockCreateCommand.of(request);

        // Domain logic
        StockItem newStockItem = StockItem.create(command);

        newStockItem.attachOrganization(organization);
        handleAddImage(
                newStockItem,
                request.getImage()
        );

        // Persist
        StockItem saved = stockRepository.save(newStockItem);

        // Log the business operation event
        businessEventLogger.stockCreated(
                saved.getCode()
        );

        return saved;
    }

    @Transactional
    @Override
    public StockItem update(StockCode code, StockUpdateRequest request) {

        StockItem existingStock = stockQueryService.getByCode(code);
        Actor actor = actorProvider.getCurrent();

        stockAccessPolicy.canUpdate(actor, existingStock);

        StockIUpdateCommand command = StockIUpdateCommand.of(request);

        if (request.getImage() != null) {
            handleUpdateImage(
                    existingStock,
                    request.getImage()
            );
        }

        // Domain logic
        existingStock.update(command);

        // Persist
        StockItem saved = stockRepository.save(existingStock);

        // Log the business operation event
        businessEventLogger.StockUpdated(
                saved.getCode()
        );

        return saved;

    }

    @Transactional
    @Override
    public void delete(StockCode stockCode) {

        StockItem stock = stockQueryService.getByCode(stockCode);

        Actor actor = actorProvider.getCurrent();

        stockAccessPolicy.canDelete(actor, stock);

        stock.delete(actor);

        stockRepository.save(stock);

        businessEventLogger.stockDeleted(
                stock.getCode()
        );
    }

    @Transactional
    @Override
    public StockItem restock(StockCode stockCode, RestockRequest request) {

        StockItem stock = stockQueryService.getByCode(stockCode);
        Actor actor = actorProvider.getCurrent();

        stockAccessPolicy.canRestock(actor, stock);

        RestockCommand command = RestockCommand.of(request);

        stock.restock(command);

        StockItem saved = stockRepository.save(stock);

        // Log the business operation event
        businessEventLogger.stockRestocked(
                stock.getCode()
        );

        return saved;
    }

    @Override
    public StockItem view(StockCode stockCode) {

        StockItem stock = stockQueryService.getByCode(stockCode);
        Actor actor = actorProvider.getCurrent();

        stockAccessPolicy.canView(actor, stock);

        // Log the business operation event
        businessEventLogger.stockViewed(
                stock.getCode()
        );

        return stock;
    }


    @Override
    public PageResult<StockItem> list(StockPageRequest request) {

        Actor currentActor = actorProvider.getCurrent();
        Organization organization = organizationQueryService.getByOwnerIdentity(currentActor.getActorIdentity());

        OrganizationCode organizationCode = OrganizationCode.of(
                organization.getCode()
        );
        PageResult<StockItem> stocks = stockQueryService.getAllByOrganizationCode(organizationCode, request);

        // Log the business operation event
        businessEventLogger.stockListed(
                organization.getCode(),
                request.getPage(),
                request.getSize(),
                request.getSortBy(),
                request.getDirection()
        );

        return stocks;
    }

    @Override
    public PageResult<StockBatch> listBatches(StockCode stockCode, StockBatchPageRequest request) {

        StockItem stock = stockQueryService.getByCode(stockCode);
        Actor actor = actorProvider.getCurrent();

        stockAccessPolicy.canListBatches(actor, stock);

        PageResult<StockBatch> batches = stockQueryService.getBatchesByStockCode(stockCode, request);

        businessEventLogger.stockBatchesListed(
                stockCode.toString(),
                request.getPage(),
                request.getSize(),
                request.getSortBy(),
                request.getDirection()
        );

        return batches;
    }


    // --------------------------------------------------- Helper methods ---------------------------------------------------

    private void handleAddImage(StockItem item, MultipartFile imageFile) {
        if (imageFile == null || imageFile.isEmpty()) {
            return;
        }

        Image newImage = uploadImageToStorage(imageFile);
        item.addImage(newImage);
    }

    private void handleUpdateImage(StockItem item, MultipartFile imageFile) {

        if (item.getImage() == null) {

            Image newImage = uploadImageToStorage(imageFile);
            item.addImage(newImage);


        } else {

            Image existingImage = item.getImage();

            Image updatedImage = updateImageInStorage(existingImage, imageFile);
            item.updateImage(updatedImage);
        }
    }


    private Image uploadImageToStorage(MultipartFile newImageFile) {
        try {
            return imageService.create(
                    newImageFile,
                    StockImagePreset.INSTANCE,
                    MediaOwnerType.STOCK
            );

        } catch (BaseException e) {

            throw StockTechnicalException.imageUploadFailed();
        }
    }

    private Image updateImageInStorage(Image existingImage, MultipartFile newImageFile) {
        try {
            return imageService.update(
                    existingImage,
                    newImageFile,
                    StockImagePreset.INSTANCE,
                    MediaOwnerType.STOCK
            );

        } catch (BaseException e) {

            throw StockTechnicalException.imageUploadFailed();
        }
    }

    private void ensureSkuUnique(String sku,String organizationCode) {

        StockSKU stockSKU = StockSKU.of(sku);
        OrganizationCode code = OrganizationCode.of(organizationCode);


        if (stockQueryService.existsBySkyAndOrganizationCode(stockSKU,code)) {
            throw StockBusinessException.SkuAlreadyExists();
        }
    }

}
