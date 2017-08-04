package com.vwaber.udacity.crusty.data;

import android.net.Uri;
import android.text.Html;

import com.google.common.base.CharMatcher;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    public static List<Recipe> getRecipes(String urlString){

        URL url;

        try {
            url = new URL(urlString);
            String json = captureStringFromUrl(url);
            json = CharMatcher.ascii().retainFrom(json);
            return convertJSONtoRecipes(json);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;

    }

    private static List<Recipe> convertJSONtoRecipes(String json){
        Gson gson = new Gson();
        ArrayList<Recipe> recipes;
        recipes = gson.fromJson(json, new TypeToken<ArrayList<Recipe>>(){}.getType());
        return recipes;
    }

    private static String captureStringFromUrl(URL url) {

        if(url == null) return null;

        HttpURLConnection connection = null;
        InputStream stream = null;

        try {
            //url = new URL(uri.toString());
            connection = (HttpURLConnection) url.openConnection();
            stream = connection.getInputStream();
            return convertStreamToString(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection != null) connection.disconnect();
            if(stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private static String convertStreamToString(InputStream stream){

        if(stream == null) return null;

        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;

        try {
            while((line = reader.readLine()) != null) builder.append(line.trim());
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
