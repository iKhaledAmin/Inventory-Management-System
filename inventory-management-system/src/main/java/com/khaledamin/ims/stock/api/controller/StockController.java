package com.khaledamin.ims.stock.api.controller;


import com.khaledamin.ims.core.api.pagination.PageMapper;
import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.core.api.response.ApiActionResponse;
import com.khaledamin.ims.core.api.response.ApiPageResponse;
import com.khaledamin.ims.core.api.response.ApiResponse;
import com.khaledamin.ims.core.api.response.ApiResponseFactory;
import com.khaledamin.ims.stock.api.documentation.annotations.*;
import com.khaledamin.ims.stock.api.dto.*;
import com.khaledamin.ims.stock.api.mapper.StockBatchMapper;
import com.khaledamin.ims.stock.api.mapper.StockDetailsMapper;
import com.khaledamin.ims.stock.api.mapper.StockSummaryMapper;
import com.khaledamin.ims.stock.application.service.StockManagementService;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import com.khaledamin.ims.stock.domain.model.Stock;
import com.khaledamin.ims.stock.domain.value.StockCode;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Stock Management",
        description = """
                APIs for managing inventory stock.

                Features:
                - Create stock item
                - Update stock item
                - Restock inventory
                - View stock item
                - List stock items
                - List stock batches
                """
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

    private final StockManagementService stockManagementService;
    private final StockDetailsMapper stockDetailsMapper;
    private final StockSummaryMapper stockSummaryMapper;
    private final StockBatchMapper stockBatchMapper;



    @StockCreateApiDocs
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('stock_create')")
    public ResponseEntity<ApiResponse<StockDetailedResponse>> create(

            @Valid
            @ModelAttribute
            StockCreateRequest request
    ) {

        Stock created = stockManagementService.create(request);

        StockDetailedResponse response = stockDetailsMapper.toResponse(created);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponseFactory.success(response)
                );
    }


    @PatchMapping(
            value = "/{code}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @StockUpdateApiDocs
    @PreAuthorize("hasAuthority('stock_update')")
    public ResponseEntity<ApiResponse<StockDetailedResponse>> update(

            @Parameter(
                    description = "Stock unique business identifier",
                    example = "STK-01KABC123DEF456GHI789JKL",
                    required = true
            )
            @PathVariable
            String code,

            @Valid
            @ModelAttribute
            StockUpdateRequest request
    ) {

        Stock updated = stockManagementService.update(
                StockCode.of(code),
                request
        );

        StockDetailedResponse response = stockDetailsMapper.toResponse(updated);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }


    @DeleteMapping("/{code}")
    @StockDeleteApiDocs
    @PreAuthorize("hasAuthority('stock_delete')")
    public ResponseEntity<ApiResponse<ApiActionResponse>> delete(

            @Parameter(
                    description = "Stock unique business identifier",
                    example = "STK-01KABC123DEF456GHI789JKL",
                    required = true
            )
            @PathVariable
            String code
    ) {

        stockManagementService.delete(
                StockCode.of(code)
        );

        return ResponseEntity.ok(
                ApiResponseFactory.success(
                        ApiActionResponse.builder()
                                .message("Stock deleted successfully")
                                .build()
                )
        );
    }


    @StockRestockApiDocs
    @PostMapping("/{code}")
    @PreAuthorize("hasAuthority('stock_restock')")
    public ResponseEntity<ApiResponse<StockDetailedResponse>> restock(

            @Parameter(
                    description = "Stock unique business identifier",
                    example = "STK-01KABC123DEF456GHI789JKL",
                    required = true
            )
            @PathVariable
            String code,

            @Valid
            @RequestBody
            RestockRequest request
    ) {

        Stock updated = stockManagementService.restock(
                StockCode.of(code),
                request
        );

        StockDetailedResponse response = stockDetailsMapper.toResponse(updated);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }



    @GetMapping("/{code}")
    @StockViewApiDocs
    @PreAuthorize("hasAuthority('stock_read')")
    public ResponseEntity<ApiResponse<StockDetailedResponse>> view(

            @Parameter(
                    description = "Stock unique business identifier",
                    example = "STK-01KABC123DEF456GHI789JKL",
                    required = true
            )
            @PathVariable
            String code
    ) {

        Stock stock = stockManagementService.view(
                StockCode.of(code)
        );

        StockDetailedResponse response = stockDetailsMapper.toResponse(stock);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }


    @GetMapping
    @StockListApiDocs
    @PreAuthorize("hasAuthority('stock_read')")
    public ResponseEntity<ApiPageResponse<StockSummaryResponse>> list(

            @Valid
            @ParameterObject
            StockPageRequest request
    ) {

        PageResult<Stock> stocks =stockManagementService.list(request);

        PageResult<StockSummaryResponse> response = PageMapper.map(stocks, stockSummaryMapper::toResponse);

        return ResponseEntity.ok(
                ApiResponseFactory.page(response)
        );
    }




    @StockListBatchesApiDocs
    @GetMapping("/{code}/batches")
    @PreAuthorize("hasAuthority('stock_read_batches')")
    public ResponseEntity<ApiPageResponse<StockBatchResponse>> listBatches(

            @Parameter(
                    description = "Stock unique business identifier",
                    example = "STK-01KABC123DEF456GHI789JKL",
                    required = true
            )
            @PathVariable
            String code,

            @Valid
            @ParameterObject
            StockBatchPageRequest request
    ) {

        PageResult<StockBatch> batches = stockManagementService.listBatches(
                StockCode.of(code),
                request
        );

        PageResult<StockBatchResponse> response = PageMapper.map(batches, stockBatchMapper::toResponse);

        return ResponseEntity.ok(
                ApiResponseFactory.page(response)
        );
    }



    @StockExistenceApiDocs
    @GetMapping("/{code}/exists")
    @PreAuthorize("hasAuthority('stock_validate_existence')")
    public ResponseEntity<ApiResponse<StockExistenceResponse>> exists(
            @Parameter(
                    description = "Stock unique business identifier",
                    example = "STK-01KABC123DEF456GHI789JKL",
                    required = true
            )
            @PathVariable
            String code
    ) {

        boolean exists = stockManagementService.checkStockExistence(
                StockCode.of(code)
        );

        StockExistenceResponse response = StockExistenceResponse.builder()
                .stockCode(code)
                .exists(exists)
                .build();

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }
}