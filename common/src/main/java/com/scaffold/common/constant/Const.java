package com.scaffold.common.constant;

import com.scaffold.common.constant.Enum.DeleteFlagEnum;
import sun.management.resources.agent;

/**
 * @Author danyiran
 * @create 2020/7/1 22:10
 */
public class Const {

    public static final String HEADER_USER_NAME = "User-Name";
    public static final String HEADER_AUTH_TOKEN = "Auth-Token";
    public static final String HEADER_DEVICE = "Device";
    public static final String DATE_FORMAT_FULL = "yyyyMMddHHmmss";
    public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 文件路径值
     */
    public static final String UPLOAD_FILE_ROOT_PATH = "upload_file_root_path";
    public static final String APP_FILE_PATH = "app_file_path";
    public static final String IMAGE_FILE_PATH = "image_file_path";

    /**
     * 删除标识常量，0：正常；1：删除
     */
    public static char DELETE_FLAG_SIMPLE = DeleteFlagEnum.SIMPLE.deleteFlag();
    public static char DELETE_FLAG_DELETED = DeleteFlagEnum.DELETED.deleteFlag();

    public static final String UNKNOWN = "unknown";
    public static final String X_FORWARDED_FOR = "x-forwarded-for";
    public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    public static final String REAL_ADDRESS_IPV6 = "0:0:0:0:0:0:0:1";
    public static final String REAL_ADDRESS_LOCALHOST = "127.0.0.1";
    public static final String USER_AGENT = "user-agent";
}
