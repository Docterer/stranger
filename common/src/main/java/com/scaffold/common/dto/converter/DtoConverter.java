package com.scaffold.common.dto.converter;

/**
 * @Author danyiran
 * @create 2020/7/1 23:39
 */
public interface DtoConverter<S, D> {
    boolean support(Object object, Class... destinationClass);

    D convert(S dto);
}
