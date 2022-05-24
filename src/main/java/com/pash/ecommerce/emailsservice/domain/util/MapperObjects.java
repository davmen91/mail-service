package com.pash.ecommerce.emailsservice.domain.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.pash.ecommerce.emailsservice.domain.exception.ProcessException;

import java.util.ArrayList;
import java.util.List;

public class MapperObjects {

    public static <E> E _mapper(Class<E> clazz, Object data) {

        JsonMapper mapper = JsonMapper.builder()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, Boolean.TRUE)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, Boolean.TRUE)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, Boolean.TRUE)
                .build();

        try {
            String json = data.toString();
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            String message = "The data model could not be mapped :: " + data;
            throw new ProcessException(MapperObjects.class, message, e);
        }
    }

    public static <E> List<E> _mapperList(Class<E> clazz, Object dataList) {
        try {
            List<E> result = new ArrayList<>();
            if (dataList instanceof List<?>)
                for (Object data : (List<?>) dataList) result.add(_mapper(clazz, data));
            return result;
        } catch (Exception e) {
            String message = "The dataList list model could not be mapped :: " + dataList;
            throw new ProcessException(MapperObjects.class, message, e);
        }
    }
}
