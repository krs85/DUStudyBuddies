package com.shemeshapps.drexelstudybuddies.NetworkingServices;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.shemeshapps.drexelstudybuddies.Models.LoginRequest;

import java.util.Arrays;



/**
 * Created by tomer on 8/9/14.
 */
public class RequestUtil {
    public static RequestQueue queue;
    public static ImageLoader imageLoader;
    private static Response.ErrorListener errorListener;
    private static Context context;

    public static void init(final Context context)
    {
        RequestUtil.context = context;
        queue = Volley.newRequestQueue(context);
        ImageLoader.ImageCache imageCache = new BitmapLruCache();
        imageLoader = new ImageLoader(Volley.newRequestQueue(context), imageCache);

        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                if(volleyError.networkResponse!=null)
                {
                    if(volleyError.networkResponse.statusCode == 401)
                    {

                    }
                    else
                    {
                        Log.e("Volley Error", Arrays.toString(volleyError.networkResponse.data));
                        Toast.makeText(context, "An error has occurred while making the request", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(context, "Please connect to the internet to use this app", Toast.LENGTH_LONG).show();
                }
            }
        };
    }


    public static void getAuthCode(String userid, String password, Response.Listener listener , Response.ErrorListener errorListener)
    {
        String url = "https://d1m.drexel.edu/API/v2.0/Authentication/";
        LoginRequest r = new LoginRequest();
        r.Password = password;
        r.UserId = userid;

        RequestUtil.queue.add(new JacksonRequest<>(Request.Method.PUT,url,r, Object.class,listener,errorListener));
    }

}
