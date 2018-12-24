package com.pelayo.projects.githubcontributorslist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ContributorDetailsActivity extends AppCompatActivity {
    private DbAdapter mDbAdapter;
    private Contributor mContributor;

    private TextView mLoginTextView, mNameTextView, mEmailTextView;
    private ImageView mProfileImageView;

    private UserReceiver mReceiver;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor_details);

        mLoginTextView = findViewById(R.id.contributor_login_textview);
        mNameTextView = findViewById(R.id.contributor_name_textview);
        mEmailTextView = findViewById(R.id.contributor_email_textview);

        mProfileImageView = findViewById(R.id.contributor_profile_imageview);

        mDbAdapter = new DbAdapter(this);
        int contributorId = getIntent().getIntExtra("id", 0);

        mDbAdapter.open();
        mContributor = mDbAdapter.fetchContributorById(contributorId);
        mUser = mDbAdapter.fetchUserById(contributorId);
        mDbAdapter.close();

        //Cargo los datos a la interfaz
        mLoginTextView.setText(mContributor.getLogin());

        if (mUser != null) {
            mNameTextView.setText(mUser.getName());
            mEmailTextView.setText(mUser.getEmail());
        } else {
            mNameTextView.setText("Loading");
            mEmailTextView.setText("Loading");
        }

        GlideApp.with(this).load(mContributor.getAvatarUrl()).into(mProfileImageView);
        LoadUser();
    }

    private void LoadUser() {
        Intent intent = new Intent(this, RetrieveUser.class);
        Bundle data = new Bundle();
        data.putString("url", mContributor.getUrl());
        intent.putExtra("data", data);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Preparo la actividad para recibir los mensajes de broadcast
        IntentFilter filter = new IntentFilter();
        filter.addAction(RetrieveUser.ACTION_LOADED);

        mReceiver = new UserReceiver();
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //"Desregistro" el receiver
        if (mReceiver != null)
            unregisterReceiver(mReceiver);
    }

    public class UserReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.equals(intent.getAction(), RetrieveUser.ACTION_LOADED)) {
                // Cargado
                mDbAdapter.open();
                mUser = mDbAdapter.fetchUserById(mContributor.getId());
                mDbAdapter.close();

                mNameTextView.setText(mUser.getName());
                mEmailTextView.setText(mUser.getEmail());
            } else if (Objects.equals(intent.getAction(), RetrieveUser.ACTION_ERROR)) {
                mNameTextView.setText(R.string.network_error);
                mNameTextView.setText(R.string.network_error);
            }
        }
    }
}
