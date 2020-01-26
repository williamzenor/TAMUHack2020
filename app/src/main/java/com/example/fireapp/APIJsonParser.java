package com.example.fireapp;

import org.json.JSONObject;

public class APIJsonParser
{
    public APIData parse(String raw) throws Exception
    {
        JSONObject obj = new JSONObject(raw);
        APIData data = new APIData();

        data.speed = obj.getJSONObject("wind").getDouble("speed");
        data.deg = obj.getJSONObject("deg").getDouble("deg");

        data.dryness = 100 - obj.getJSONObject("main").getInt("humidity");

        return data;
    }
}
