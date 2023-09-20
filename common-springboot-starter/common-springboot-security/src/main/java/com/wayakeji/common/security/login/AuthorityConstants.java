package com.wayakeji.common.security.login;

public class AuthorityConstants {

    /**
     * 超级管理员的id
     */
    public static final long ADMIN_USER_ID = 888888;

    public static final boolean QUERY_USER_NEED_ADD_UID = true;

    /**
     * 消息头中token的key
     */
    public static final String HEADER_TOKEN = "Scpsat-Token";

    /**
     * 消息头中签名的key
     */
    public static final String HEADER_SIGN = "Scpsat-Sign";

    /**
     * 登陆时允许错误次数
     */
    public static final int LOGIN_ERROR_TIME = 5;

    /**
     * 登陆时达到错误次数后需要锁定账号多长时间
     * <p>单位秒
     */
    public static final int LOGIN_ERROR_LOCK_TIME = 600;

    /**
     * 储存缓存的根文件夹名称
     */
    public static final String ROOT_KEY = "serializer";

    /**
     * 储存用户缓存的文件夹名称
     */
    public static final String USERS_KEY = "user";

    /**
     * 储存用户权限的文件夹名称
     */
    public static final String AUTHORITY_KEY = "authority";

    /**
     * token的有效时间，单位毫秒
     */
    public static final long TOKEN_EFFECTIVE_TIME = 86400000;
}
