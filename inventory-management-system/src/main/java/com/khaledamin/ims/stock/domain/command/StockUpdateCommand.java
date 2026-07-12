package com.khaledamin.ims.stock.domain.command;

import com.khaledamin.ims.stock.api.dto.StockUpdateRequest;
import com.khaledamin.ims.stock.domain.value.StockDescription;
import com.khaledamin.ims.stock.domain.value.StockName;

import java.util.Optional;

public record StockUpdateCommand(Optional<StockName> name, Optional<StockDescription> description) {

    public static StockUpdateCommand of(String name, String description ){
        return new StockUpdateCommand(
                Optional.ofNullable(name).map(StockName::of),
                Optional.ofNullable(description).map(StockDescription::of)
        );
    }

    public static StockUpdateCommand of(StockUpdateRequest request) {
        return of(
                request.getName(),
                request.getDescription()
        );
    }
}
