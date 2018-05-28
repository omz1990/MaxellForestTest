package com.omar.maxellforesttest.Common.network;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by omz on 27/5/18
 */
public class ServerSettings {

    public static class Domain {
        public static final long OKHTTP_CLIENT_READ_TIMEOUT_SECONDS = 6;
        public static final long OKHTTP_CLIENT_CONNECT_TIMEOUT_SECONDS = 6;
        public static final HttpLoggingInterceptor.Level HTTP_LOG_LEVEL = HttpLoggingInterceptor.Level.BODY;

        public static final String BASE_URL = "https://api.foursquare.com/v2/";
        public static final String BASE_URL_CLIENT_ID = "0Q3WCEXHI130Z54K2VWIEA12LOVA4W3XYCIZOZSDTGBH3JJP";
        public static final String BASE_URL_CLIENT_SECRET = "X44GFRLGLCJ5RHFOEHEGQ2WMRK3AK0VPEK2INAMWS3KYKRVD";
        public static final String BASE_URL_API_VERSION = "20180425";

    }
}
