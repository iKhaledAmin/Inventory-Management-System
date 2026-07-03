package com.khaledamin.ims.stock.infrastructure.persistence.repository;

import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.core.api.pagination.PageResultFactory;
import com.khaledamin.ims.core.api.pagination.PageableFactory;
import com.khaledamin.ims.stock.api.dto.StockPageRequest;
import com.khaledamin.ims.stock.domain.model.StockItem;
import com.khaledamin.ims.stock.domain.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class StockRepositoryImpl implements StockRepository {
    private final StockJpaRepository jpaRepository;

    @Override
    public StockItem save(StockItem stockItem) {
        return jpaRepository.save(stockItem);
    }

    @Override
    public Optional<StockItem> findByCode(String itemCode) {
        return jpaRepository.getByCode(itemCode);
    }

    @Override
    public PageResult<StockItem> findAllByOrganizationCode(String organizationCode, StockPageRequest request) {
        Page<StockItem> page = jpaRepository.findAllByOrganizationCode(
                organizationCode,
                PageableFactory.from(request)
        );

        return PageResultFactory.from(page);
    }

    @Override
    public boolean existsBySkuAndOrganizationCode(String itemSku, String organizationCode) {
        return jpaRepository.existsBySkuAndOrganizationCode(itemSku,organizationCode);
    }

}
