package com.scaffold.common.dto.converter;

import com.scaffold.common.exception.ServerException;
import com.scaffold.common.util.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author danyiran
 * @create 2020/7/1 23:40
 */
@Component
public class DtoConverterHandler {

    @Autowired(required = false)
    private List<DtoConverter> converterList;

    public DtoConverterHandler() {
    }

    public Object convertSingle(Object srcObj, Class... destinationClass) {
        Iterator i$ = this.converterList.iterator();

        DtoConverter converter;
        if (srcObj != null) {
            do {
                if (!i$.hasNext()) {
                    String msg = MessageHelper.getMessage("MSG999996", new Object[]{srcObj.getClass().getCanonicalName()});
                    throw new ServerException(msg);
                }

                converter = (DtoConverter) i$.next();
            } while (!converter.support(srcObj, destinationClass));

            return converter.convert(srcObj);
        } else {
            return null;
        }
    }

    public List convertList(List srcList, Class... destinationClass) {
        if (srcList != null) {
            List result = new ArrayList(srcList.size());
            Iterator i$ = srcList.iterator();

            while (i$.hasNext()) {
                Object obj = i$.next();
                result.add(this.convertSingle(obj, destinationClass));
            }

            return result;
        } else {
            return null;
        }

    }
}
