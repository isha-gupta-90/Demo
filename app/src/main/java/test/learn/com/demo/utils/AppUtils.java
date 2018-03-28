package test.learn.com.demo.utils;

public class AppUtils {

    public static String appendHttps(String url) {
        String urlWithHttps;
        urlWithHttps = url.substring(0, 4) + "s" + url.substring(4, url.length());
        return urlWithHttps;
    }
}