package com.khaledamin.ims.stock.exception;

import com.khaledamin.ims.core.exception.technical.TechnicalError;
import com.khaledamin.ims.core.exception.technical.TechnicalException;

public class StockTechnicalException extends TechnicalException {

    // ---------------------------------------------------- Constructors ---------------------------------------------------- //

    protected StockTechnicalException(TechnicalError error) {
        super(error);
    }

    // ---------------------------------------------------- End Constructors -------------------------------------------------- //

    // ---------------------------------------------------- Methods -------------------------------------------------------- //
    public static StockTechnicalException nullImage() {
        return new StockTechnicalException(StockTechnicalError.IMAGE_NULL);
    }

    public static StockTechnicalException nullOrganization() {
        return new StockTechnicalException(StockTechnicalError.ORGANIZATION_NULL);
    }

    public static StockTechnicalException nullCreateCommand() {
        return new StockTechnicalException(StockTechnicalError.CREATE_COMMAND_NULL);
    }

    public static StockTechnicalException nullUpdateCommand() {
        return new StockTechnicalException(StockTechnicalError.UPDATE_COMMAND_NULL);
    }

    public static StockTechnicalException nullRestockCommand() {
        return new StockTechnicalException(StockTechnicalError.RESTOCK_COMMAND_NULL);
    }

    public static StockTechnicalException nullStockItem() {
        return new StockTechnicalException(StockTechnicalError.ITEM_NULL);
    }

    public static StockTechnicalException imageUploadFailed() {
        return new StockTechnicalException(StockTechnicalError.IMAGE_UPLOAD_FAILED);
    }
    // ---------------------------------------------------- End Methods ------------------------------------------------------ //
}
