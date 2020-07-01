package com.scaffold.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaffold.common.exception.ParamInvalidException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author danyiran
 * @create 2020/7/1 23:41
 */
@Slf4j
public class JsonUtil {

    /**
     * Parse json string to map for all object
     *
     * @param json the string to parse
     * @return the map
     */
    public static HashMap<String, Object> parseJson2ObjectMap(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            HashMap<String, Object> resultMap = (json == null) ? new HashMap<String, Object>()
                    : objectMapper.readValue(json, HashMap.class);
            return resultMap;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            String msg = MessageHelper.getMessage("MSG999991");
            throw new ParamInvalidException(msg);
        }
    }

    /**
     * Parse map to json string
     *
     * @param map the map to parse
     * @return the json string
     */
    public static String parseMap2Json(HashMap<String, Object> map) {
        try {
            String jsonResult = null;
            ObjectMapper objectMapper = new ObjectMapper();
            if (map != null) {
                jsonResult = objectMapper.writeValueAsString(map);
            }
            return jsonResult;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            String msg = MessageHelper.getMessage("MSG999991");
            throw new ParamInvalidException(msg);
        }
    }

    /**
     * Parse list to json string
     *
     * @param list the map to parse
     * @return the json string
     */
    public static String parseList2Json(List<?> list) {
        try {
            String jsonResult = null;
            ObjectMapper objectMapper = new ObjectMapper();
            if (list != null) {
                jsonResult = objectMapper.writeValueAsString(list);
            }
            return jsonResult;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            String msg = MessageHelper.getMessage("MSG999991");
            throw new ParamInvalidException(msg);
        }
    }

    /**
     * Parse json string to list for all object
     *
     * @param json the string to parse
     * @return the list
     */
    public static List<?> parseJson2List(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List resultList = (json == null) ? new ArrayList<>() : objectMapper.readValue(json, List.class);
            return resultList;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            String msg = MessageHelper.getMessage("MSG999991");
            throw new ParamInvalidException(msg);
        }
    }

    /**
     * Parse a object to json string.
     *
     * @param object the object to parse
     * @return the json string
     */
    public static String parseObject2Json(Object object) {
        try {
            String jsonResult = null;
            ObjectMapper objectMapper = new ObjectMapper();
            if (object != null) {
                jsonResult = objectMapper.writeValueAsString(object);
            }
            return jsonResult;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            String msg = MessageHelper.getMessage("MSG999991");
            throw new ParamInvalidException(msg);
        }
    }

    /**
     * Parse a json string to a specific object
     *
     * @param json  the json to parse
     * @param clazz the object type to parse into
     * @return
     */
    public static Object parseJson2Object(String json, Class clazz) {
        try {
            Object object;
            ObjectMapper objectMapper = new ObjectMapper();
            object = objectMapper.readValue(json, clazz);
            return object;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            String msg = MessageHelper.getMessage("MSG999991");
            throw new ParamInvalidException(msg);
        }
    }

    /**
     * @param json
     * @param valueTypeReference
     * @return
     */
    public static <T> T parseJson2List(String json, TypeReference<T> valueTypeReference) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, valueTypeReference);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            String msg = MessageHelper.getMessage("MSG999991");
            throw new ParamInvalidException(msg);
        }
    }
}
