package com.pelayo.projects.githubcontributorslist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Objects;

public class ContributorsActivity extends AppCompatActivity {

    private int mRepoId;
    private Repository mRepository;
    private DbAdapter mDbAdapter;
    private RecyclerView mRecyclerView;
    private ContributorsAdapter mAdapter;
    private ProgressBar mProgressBar;
    private Cursor mCursor;

    private ContributorsReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributors);

        //Cargo las variables
        mRepoId = getIntent().getIntExtra("id", 0);
        mProgressBar = findViewById(R.id.contributors_progressbar);
        mDbAdapter = new DbAdapter(this);
        mDbAdapter.open();

        mRepository = mDbAdapter.fetchRepositoryById(mRepoId);

        mCursor = mDbAdapter.fetchAllContributorsByRepositoryId(mRepoId);

        mDbAdapter.close();

        this.setTitle(mRepository.getName());

        //Preparo el recyclerView
        mRecyclerView = findViewById(R.id.contributors_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ContributorsAdapter(mCursor, this);
        mRecyclerView.setAdapter(mAdapter);

        /* Una vez cargados y mostrados los contributors que hay actualmente en la base de datos
         * se eliminan y se hace la petición web para cargarlos actualizados, abajo está la clase
         * ContributorsReceiver que se encarga de actualizar el recyclerView con los nuevos
         * contributors una vez cargados
         */
        loadContributors();
    }

    private void loadContributors() {
        Intent intent = new Intent(this, RetrieveContributors.class);
        Bundle data = new Bundle();
        data.putInt("repoId", mRepoId);
        data.putString("url", mRepository.getUrl());
        intent.putExtra("data", data);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Preparo la actividad para recibir los mensajes de broadcast
        IntentFilter filter = new IntentFilter();
        filter.addAction(RetrieveContributors.ACTION_LOADED);
        filter.addAction(RetrieveContributors.ACTION_LOADING);

        mReceiver = new ContributorsReceiver();
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReceiver != null)
            unregisterReceiver(mReceiver);
    }

    public class ContributorsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.equals(intent.getAction(), RetrieveContributors.ACTION_LOADING)) {
                // Cargando
                if (mCursor.getCount() == 0)
                    mProgressBar.setVisibility(View.VISIBLE);

            } else if (Objects.equals(intent.getAction(), RetrieveContributors.ACTION_LOADED)) {
                // Cargado
                if (mProgressBar.getVisibility() == View.VISIBLE) {
                    mProgressBar.setVisibility(View.GONE);
                }

                mDbAdapter.open();
                mCursor = mDbAdapter.fetchAllContributorsByRepositoryId(mRepoId);
                mDbAdapter.close();

                mAdapter.changeCursor(mCursor);
                mAdapter.notifyDataSetChanged();
            } else if (Objects.equals(intent.getAction(), RetrieveContributors.ACTION_ERROR)) {
                Toast.makeText(context, R.string.network_error, Toast.LENGTH_LONG).show();

            } else if (Objects.equals(intent.getAction(), RetrieveContributors.ACTION_REPO_ERROR)) {
                Toast.makeText(context, R.string.repo_error, Toast.LENGTH_LONG).show();
            }
        }
    }
}
