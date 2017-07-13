package com.unfollow.instagram.unfollowers.Instagram;

/**
 * Created by George on 7/10/2017.
 */

public class Data {

    public static final String AUTHURL = "https://api.instagram.com/oauth/authorize/";
    public static final String TOKENURL = "https://api.instagram.com/oauth/access_token";
    public static final String APIURL =  "https://api.instagram.com/v1";
    public static final String CALLBACKURL = "http://localhost/";

    public static final String CLIENT_ID = "ea93f31ba7a4456ca9a13f25c74083be";
    public static final String CLIENT_SECRET = "6d3839d680694bee80bd7dfd16ac7aa9";

    public static final String AUTH_URl_STRING = AUTHURL + "?client_id=" + CLIENT_ID + "&redirect-uri="
            + CALLBACKURL + "&response_type=code"
            + "&display=touch&scope=likes+comments+relationships"
            ;

    public static final String TOKEN_URL_STRING = TOKENURL + "?client_id=" + CLIENT_ID + "&client_secret="
            + CLIENT_SECRET + "&redirect_uri=" + CALLBACKURL + "&grant_type=authorization_code";

    public static String ACCER_TOKEN;
    public static String ID;
    public static String USERNAME;
}
