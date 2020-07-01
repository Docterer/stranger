package com.scaffold.common.util;

import com.scaffold.common.constant.Const;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author danyiran
 * @create 2020/7/1 23:45
 */
public class IpUtil {

    /**
     * <p>
     * 获取Ip地址
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     * </p>
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader(Const.X_FORWARDED_FOR);
        if (ip == null || ip.length() == 0 || Const.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || Const.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(Const.WL_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || Const.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return Const.REAL_ADDRESS_IPV6.equals(ip) ? Const.REAL_ADDRESS_LOCALHOST : ip;
    }

    /**
     * <p>
     * 活用户请求头中的user-agent信息
     * </p>
     *
     * @param request
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader(Const.USER_AGENT);
        return userAgent;
    }

    /**
     * <p>
     * 获取请求类型
     * </p>
     *
     * @param request
     * @return
     */
    public static String getMethod(HttpServletRequest request) {
        String method = request.getMethod();
        return method;
    }

    /**
     * <p>
     * 获取请求url
     * </p>
     *
     * @param request
     * @return
     */
    public static String getUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return url;
    }
}
