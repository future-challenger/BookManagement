package book.cn.bookmanagement.Utils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by uncle_charlie on 21/12/15.
 */
public class GsonRequest<T> extends Request<T> {
    private final Gson _gson = new Gson();
    private final Class<T> _clazz;
    private final Map<String, String> _headers;
    private final Map<String, String> _params;
    private final Response.Listener<T> _listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(String url, Class<T> clazz, Map<String, String> headers,
                       Map<String, String> params,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Request.Method.GET, url, errorListener);
        this._clazz = clazz;
        this._headers = headers;
        this._params = params;
        this._listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return _headers != null ? _headers : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return _params != null ? _params : super.getParams();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            return Response.success(_gson.fromJson(json, _clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        _listener.onResponse(response);
    }

    public static class RequestBuilder {
        private int _method = Method.GET;
        private String _url;
        private Class _clazz;
        private Response.Listener _successListener;
        private Response.ErrorListener _errorListener;
        private Map<String, String> _header;
        private Map<String, String> _params;

        public RequestBuilder url(String url) {
            this._url = url;
            return this;
        }
    }
}
