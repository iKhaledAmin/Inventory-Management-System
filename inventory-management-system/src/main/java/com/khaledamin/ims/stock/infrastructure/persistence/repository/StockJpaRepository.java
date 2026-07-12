package com.khaledamin.ims.stock.infrastructure.persistence.repository;

import com.khaledamin.ims.core.persistence.BaseRepository;
import com.khaledamin.ims.stock.domain.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface StockJpaRepository extends BaseRepository<Stock, Long> {
    Optional<Stock> getByCode(String itemCode);

    Page<Stock> findAllByOrganizationCode(String organizationCode, PageRequest request);

    boolean existsBySkuAndOrganizationCode(String itemSku, String organizationCode);

    boolean existsByCodeAndOrganizationCode(String stockCode, String organizationCode);
}
