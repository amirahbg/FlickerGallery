package ir.ac.iut.amiiir.flickergallery;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by amiiir on 7/3/17.
 */

public class FlickerClient {
    private static final String BASE_URL = "https://api.flickr.com/services/rest/";
    private static final String PARAM_METHOD = "method";
    private static final String VALUE_METHOD = "flickr.interestingness.getList";
    private static final String PARAM_API_KEY = "api_key";
    private static final String VALUE_API_KEY = "c7f025137fd1aaaa21a30c8c6a9711ea";
    private static final String PARAM_EXTRAS = "extras";
    private static final String VALUE_EXTRAS = "url_m";
    private static final String PARAM_FORAMT = "format";
    private static final String VALUE_FORAMT = "json";
    private static final String PARAM_NO_JSON_CALLBACK = "nojsoncallback";
    private static final String VALUE_NO_JSON_CALLBACK = "1";

    public URL getUrl() {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, VALUE_API_KEY)
                .appendQueryParameter(PARAM_EXTRAS, VALUE_EXTRAS)
                .appendQueryParameter(PARAM_FORAMT, VALUE_FORAMT)
                .appendQueryParameter(PARAM_NO_JSON_CALLBACK, VALUE_NO_JSON_CALLBACK)
                .appendQueryParameter(PARAM_METHOD, VALUE_METHOD)
                .build();

        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getListOfPictures() {
        OkHttpClient client = new OkHttpClient();
        URL url = getUrl();
        if (url != null) {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                return new JSONObject(response.body().string());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
