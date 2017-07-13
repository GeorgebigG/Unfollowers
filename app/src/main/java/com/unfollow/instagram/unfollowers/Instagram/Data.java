package com.unfollow.instagram.unfollowers.Instagram;

/**
 * Created by George on 7/10/2017.
 */

public class Data {

    public static final String AUTHURL = "https://api.instagram.com/oauth/authorize/";
    public static final String TOKENURL = "https://api.instagram.com/oauth/access_token";
    public static final String APIURL =  "https://api.instagram.com/v1";
    public static final String CALLBACKURL = "https://www.emoney.ge/login/callback";

    public static final String CLIENT_ID = "4cd77a289bf546b896d08981ad6df629";
    public static final String CLIENT_SECRET = "50d56444c1744962831c2af296382aab ";

    public static final String AUTH_URl_STRING = AUTHURL + "?client_id=" + CLIENT_ID + "&redirect-uri="
            + CALLBACKURL + "&response_type=code"
            + "&display=touch&scope=likes+comments+relationships"
            ;b

    public static final String TOKEN_URL_STRING = TOKENURL + "?client_id=" + CLIENT_ID + "&client_secret="
            + CLIENT_SECRET + "&redirect_uri=" + CALLBACKURL + "&grant_type=authorization_code";

    public static String ACCER_TOKEN;
    public static String ID;
    public static String USERNAME;
}
