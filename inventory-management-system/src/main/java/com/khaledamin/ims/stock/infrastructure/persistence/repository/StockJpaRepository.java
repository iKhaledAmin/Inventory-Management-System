package com.khaledamin.ims.stock.infrastructure.persistence.repository;

import com.khaledamin.ims.core.persistence.BaseRepository;
import com.khaledamin.ims.stock.domain.model.StockItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface StockJpaRepository extends BaseRepository<StockItem, Long> {
    Optional<StockItem> getByCode(String itemCode);

    Page<StockItem> findAllByOrganizationCode(String organizationCode, PageRequest request);

    boolean existsBySkuAndOrganizationCode(String itemSku, String organizationCode);

    boolean existsByCodeAndOrganizationCode(String stockCode, String organizationCode);
}
