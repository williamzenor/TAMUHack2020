package com.example.fireapp;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestManager
{
    private static final int SCALAR = 100000;
    public void get(String urlS, MainActivity a)
    {
        RequestQueue queue = Volley.newRequestQueue(a);
        final StringBuilder sb;
        StringRequest sr = new StringRequest(Request.Method.GET, urlS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Msg:", response);
            }}, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError e){
                Log.e("Error: ", e.getMessage());
            }
        });
        queue.add(sr);
    }

    public void get(String urlS, MainActivity a, double lon, double lat)
    {
        String lons = "/"+(int)(lon * SCALAR);
        String lats = "/"+(int)(lat * SCALAR);
        urlS += lons + lats;
        RequestQueue queue = Volley.newRequestQueue(a);
        final StringBuilder sb;
        StringRequest sr = new StringRequest(Request.Method.GET, urlS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Msg:", response);
            }}, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError e){
                Log.e("Error: ", e.getMessage());
            }
        });
        queue.add(sr);
    }
}