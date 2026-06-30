package com.khaledamin.ims.core.api.response;


import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.core.logging.core.RequestContext;

import java.time.LocalDateTime;

public final class ApiResponseFactory {

    private ApiResponseFactory() {
    }



    public static <T> ApiResponse<T> success(T data) {

        return ApiResponse.<T>builder()
                .meta(buildMeta())
                .data(data)
                .build();
    }

    public static ApiErrorResponse error(ErrorResponse error) {

        return ApiErrorResponse.builder()
                .meta(buildMeta())
                .error(error)
                .build();
    }


    public static <T> ApiPageResponse<T> page(PageResult<T> pageResult){
        return ApiPageResponse.<T>builder()
                .meta(buildMeta())
                .data(pageResult.getContent())
                .pageInfo(
                        PageInfoResponse.builder()
                                .page(pageResult.getPage())
                                .size(pageResult.getSize())
                                .totalElements(pageResult.getTotalElements())
                                .totalPages(pageResult.getTotalPages())
                                .first(pageResult.isFirst())
                                .last(pageResult.isLast())
                                .hasNext(pageResult.isHasNext())
                                .hasPrevious(pageResult.isHasPrevious())
                                .build()
                )
                .build();
    }

    private static Meta buildMeta() {

        return Meta.builder()
                .timestamp(LocalDateTime.now())
                .requestId(RequestContext.getRequestId())
                .build();
    }
}