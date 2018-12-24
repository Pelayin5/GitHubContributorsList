package com.pelayo.projects.githubcontributorslist;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RetrieveUser extends IntentService {
    public static final String ACTION_LOADED =
            "com.pelayo.projects.githubcontributorslist.action.USER_LOADED";

    public static final String ACTION_ERROR =
            "com.pelayo.projects.githubcontributorslist.action.ERROR_LOADING_USER";

    public RetrieveUser(){
        super("RetrieveUser");
    }

    @Override
    protected void onHandleIntent(Intent workIntent){
        StringBuilder response = new StringBuilder();

        Bundle data = workIntent.getBundleExtra("data");
        String urlStr = data.getString("url");

        try {
            URL url = new URL(urlStr);

            URLConnection conn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));

            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null)
                response.append(inputLine);

        } catch (Exception e) {
            Log.e("Error:", e.getMessage());

            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_ERROR);
            sendBroadcast(broadcastIntent);
        }

        Gson gson = new Gson();

        User user = gson.fromJson(response.toString(), User.class);

        DbAdapter db = new DbAdapter(this);
        db.open();

        //Solamente creo el usuario si no está ya guardado en la base de datos
        if (db.fetchUserById(user.getId()) == null)
            db.createUser(user);

        db.close();

        //Se ha terminado de cargar
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_LOADED);
        sendBroadcast(broadcastIntent);
    }
}
