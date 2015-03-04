package com.shemeshapps.drexelstudybuddies.NetworkingServices;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tomershemesh on 8/9/14.
 */

//this is to parse the json into java object in background thread
public class JacksonRequest<T> extends JsonRequest<T> {
    private Class<T> responseType;
    private int method;

    public JacksonRequest(int method, String url, Object requestData, Class<T> responseType, Response.Listener<T> listener, Response.ErrorListener errorListener)
    {
        super((method==Method.PUT)?Method.POST:method, url, (requestData == null) ? null : Mapper.string(requestData), listener, errorListener);
        this.responseType = responseType;
        this.method = method;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response)
    {
        try
        {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(Mapper.objectOrThrow(jsonString, responseType), HttpHeaderParser.parseCacheHeaders(response));
        }
        catch (Exception e)
        {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    public Map<String, String> getHeaders()
    {
        Map<String, String> params = new HashMap<>();
        if(method == Method.PUT)
        {
            params.put("X-Http-Method-Override","PUT");
        }

        return params;
    }

}
