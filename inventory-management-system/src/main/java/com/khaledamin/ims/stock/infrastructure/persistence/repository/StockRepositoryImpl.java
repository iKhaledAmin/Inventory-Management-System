package com.khaledamin.ims.stock.infrastructure.persistence.repository;

import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.core.api.pagination.PageResultFactory;
import com.khaledamin.ims.core.api.pagination.PageableFactory;
import com.khaledamin.ims.stock.api.dto.StockPageRequest;
import com.khaledamin.ims.stock.domain.model.Stock;
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
    public Stock save(Stock stock) {
        return jpaRepository.save(stock);
    }

    @Override
    public Optional<Stock> findByCode(String itemCode) {
        return jpaRepository.getByCode(itemCode);
    }

    @Override
    public PageResult<Stock> findAllByOrganizationCode(String organizationCode, StockPageRequest request) {
        Page<Stock> page = jpaRepository.findAllByOrganizationCode(
                organizationCode,
                PageableFactory.from(request)
        );

        return PageResultFactory.from(page);
    }

    @Override
    public boolean existsBySkuAndOrganizationCode(String itemSku, String organizationCode) {
        return jpaRepository.existsBySkuAndOrganizationCode(itemSku,organizationCode);
    }

    @Override
    public boolean existsByCodeAndOrganizationCode(String stockCode, String organizationCode) {
        return jpaRepository.existsByCodeAndOrganizationCode(stockCode,organizationCode);
    }

}
