package com.scaffold.common.dto.converter;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

/**
 * @Author danyiran
 * @create 2020/7/1 23:39
 */
@SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD", "UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD"})
public abstract class AbstractDtoConverter<S, D> implements DtoConverter<S, D>  {
    protected ModelMapper modelMapper = new ModelMapper();
    protected PropertyMap<S, D> dtoMap;
    protected TypeMap<S, D> typeMap;

    public AbstractDtoConverter() {
    }
}
