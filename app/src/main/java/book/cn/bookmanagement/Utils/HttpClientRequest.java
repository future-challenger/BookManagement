package book.cn.bookmanagement.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by uncle_charlie on 21/12/15.
 */
public class HttpClientRequest {
    private static HttpClientRequest _instance;
    private static Context _context;
    private RequestQueue _requestQueue;

    private HttpClientRequest(Context context) {
        _context = context;
        _requestQueue = getRequestQueue();
    }

    public static synchronized HttpClientRequest getInstance(Context context) {
        if (_instance == null) {
            _instance = new HttpClientRequest(context);
        }
        return _instance;
    }

    public RequestQueue getRequestQueue() {
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(_context.getApplicationContext(), new OkHttpStack());
        }
        return _requestQueue;
    }

    public <T> void addRequest(Request<T> request) {
        getRequestQueue().add(request);
    }

    private class OkHttpStack extends HurlStack {
        private final OkUrlFactory okUrlFactory;
        public OkHttpStack() {
            this(new OkUrlFactory(new OkHttpClient()));
        }
        public OkHttpStack(OkUrlFactory okUrlFactory) {
            if (okUrlFactory == null) {
                throw new NullPointerException("Client must not be null.");
            }
            this.okUrlFactory = okUrlFactory;
        }
        @Override
        protected HttpURLConnection createConnection(URL url) throws IOException {
            return okUrlFactory.open(url);
        }
    }
}
