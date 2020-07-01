package com.scaffold.common.util;

import com.github.pagehelper.PageHelper;
import com.scaffold.common.dto.PageDto;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author danyiran
 * @create 2020/7/1 23:35
 */
@Slf4j
public class PageUtil {
    public static void setupPageInfo(Integer pageNo, Integer pageSize) {
        log.debug("setupPageInfo,  pagination pageNo: {},pageSize:{}", pageNo, pageSize);

        if (pageNo == null) {
            pageNo = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        PageDto pageDto = new PageDto(pageNo, pageSize);
        if (pageDto.getPageNo() != null && pageDto.getPageSize() != null) {
            PageHelper.startPage(pageDto.getPageNo(), pageDto.getPageSize());
        } else {
            log.error("the pagination info was illegal, so will not set up the pagination info");
        }
    }

    public static void clearPageInfo() {
        PageHelper.clearPage();
    }
}
