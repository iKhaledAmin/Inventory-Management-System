package com.khaledamin.ims.core.mapper;


public interface BaseMapper<RESPONSE, ENTITY> {

    // Response
    RESPONSE toResponse(ENTITY entity);
}