package com.khaledamin.ims.stock.infrastructure.persistence.repository;

import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.core.api.pagination.PageResultFactory;
import com.khaledamin.ims.core.api.pagination.PageableFactory;
import com.khaledamin.ims.stock.api.dto.StockBatchPageRequest;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import com.khaledamin.ims.stock.domain.repository.StockBatchRepository;
import com.khaledamin.ims.stock.infrastructure.persistence.specification.StockBatchSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class StockBatchRepositoryImpl implements StockBatchRepository {

    private final StockBatchJpaRepository jpaRepository;

    @Override
    public PageResult<StockBatch> findAllByStockCode(String stockCode, StockBatchPageRequest request) {

        Specification<StockBatch> spec = buildSpecification(
                stockCode,
                request
        );

        Page<StockBatch> page = jpaRepository.findAll(
                spec,
                PageableFactory.from(request)
        );

        return PageResultFactory.from(page);
    }



    private Specification<StockBatch> buildSpecification(
            String stockCode,
            StockBatchPageRequest request
    ) {

        Specification<StockBatch> spec =
                StockBatchSpecifications.stockCode(stockCode);

        if (Boolean.TRUE.equals(request.getHasStock())) {

            spec = spec.and(
                    StockBatchSpecifications.hasStock()
            );
        }

        if (Boolean.TRUE.equals(request.getExpired())) {

            spec = spec.and(
                    StockBatchSpecifications.expired()
            );
        }

        if (request.getExpirationBefore() != null) {

            spec = spec.and(
                    StockBatchSpecifications.expirationBefore(
                            request.getExpirationBefore()
                    )
            );
        }

        return spec;
    }
}