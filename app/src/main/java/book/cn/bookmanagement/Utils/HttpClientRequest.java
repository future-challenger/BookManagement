package book.cn.bookmanagement.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by uncle_charlie on 21/12/15.
 */
public class HttpClientRequest {
    private static HttpClientRequest _instance;
    private static Context _context;
    public RequestQueue _requestQueue;

    private HttpClientRequest(Context context) {

    }

    public static synchronized HttpClientRequest getInstance(Context context) {
        if (_instance == null) {
            _instance = new HttpClientRequest(context);
        }
        return _instance;
    }

    public RequestQueue getRequestQueue() {
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(_context.getApplicationContext());
        }
        return _requestQueue;
    }

    public <T> void addRequest(Request<T> request) {
        getRequestQueue().add(request);
    }
}
