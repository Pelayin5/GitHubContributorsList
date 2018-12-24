package com.pelayo.projects.githubcontributorslist;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class RetrieveContributors extends IntentService {
    public static final String ACTION_LOADING =
            "com.pelayo.projects.githubcontributorslist.action.LOADING";

    public static final String ACTION_LOADED =
            "com.pelayo.projects.githubcontributorslist.action.LOADED";

    public static final String ACTION_ERROR =
            "com.pelayo.projects.githubcontributorslist.action.ERROR";

    public static final String ACTION_REPO_ERROR =
            "com.pelayo.projects.githubcontributorslist.action.REPO_ERROR";

    public RetrieveContributors(){
        super("RetrieveContributors");
    }

    @Override
    protected void onHandleIntent(Intent workIntent){
        ArrayList<Contributor> contributors;
        StringBuilder response = new StringBuilder();
        Bundle dataFromIntent = workIntent.getBundleExtra("data");

        int repositoryId = dataFromIntent.getInt("repoId");

        //Indico que se está cargando
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_LOADING);
        sendBroadcast(broadcastIntent);

        try {
            URL url = new URL(dataFromIntent.getString("url"));

            URLConnection conn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));

            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null)
                response.append(inputLine);

        } catch (Exception e) {
            Log.e("Error:", e.getMessage());

            broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_ERROR);
            sendBroadcast(broadcastIntent);
        }

        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<Contributor>>() {}.getType();

        contributors = gson.fromJson(response.toString(), collectionType);

        DbAdapter dbAdapter = new DbAdapter(this);
        dbAdapter.open();

        dbAdapter.deleteContributorsByRepositoryId(repositoryId);

        if (contributors != null) {
            for (Contributor contributor : contributors) {
                contributor.setIdRepository(repositoryId);
                dbAdapter.createContributor(contributor);
            }

        } else {
            broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_REPO_ERROR);
            sendBroadcast(broadcastIntent);
        }


        dbAdapter.close();

        //Se ha terminado de cargar
        broadcastIntent.setAction(ACTION_LOADED);
        sendBroadcast(broadcastIntent);
    }
}
