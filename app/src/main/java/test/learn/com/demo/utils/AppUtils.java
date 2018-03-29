package test.learn.com.demo.utils;

/**
 * Contains app specific util methods.
 */
public class AppUtils {

    private AppUtils() {
        //cannot be instantiated
    }

    /**
     * Util method to make http url to https.
     *
     * @param url -url with just http
     * @return url with https
     */
    public static String appendHttps(String url) {
        String urlWithHttps;
        urlWithHttps = url.substring(0, 4) + "s" + url.substring(4, url.length());
        return urlWithHttps;
    }
}