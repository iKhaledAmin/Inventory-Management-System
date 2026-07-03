package com.khaledamin.ims.stock.domain.command;

import com.khaledamin.ims.stock.api.dto.StockUpdateRequest;
import com.khaledamin.ims.stock.domain.value.StockDescription;
import com.khaledamin.ims.stock.domain.value.StockName;

import java.util.Optional;

public record StockIUpdateCommand(Optional<StockName> name, Optional<StockDescription> description) {

    public static StockIUpdateCommand of(String name, String description ){
        return new StockIUpdateCommand(
                Optional.ofNullable(name).map(StockName::of),
                Optional.ofNullable(description).map(StockDescription::of)
        );
    }

    public static StockIUpdateCommand of(StockUpdateRequest request) {
        return of(
                request.getName(),
                request.getDescription()
        );
    }
}
