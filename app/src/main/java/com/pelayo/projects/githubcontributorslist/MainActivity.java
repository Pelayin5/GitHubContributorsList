package com.pelayo.projects.githubcontributorslist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> showNewRepositoryDialog());
    }

    private void showNewRepositoryDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View newRepositoryView = getLayoutInflater().inflate(R.layout.add_repository_alert,
                null);

        final EditText repositoryName = newRepositoryView.findViewById(R.id.repository_name);
        final EditText repositoryOwner = newRepositoryView.findViewById(R.id.repository_owner);

        builder.setView(newRepositoryView)
                .setTitle(getText(R.string.new_repository_title))
                .setPositiveButton(android.R.string.ok, (dialog, id) -> {
                    //Guardo el repositorio en la base de datos
                    DbAdapter db = new DbAdapter(this);

                    db.open();

                    db.createRepository(repositoryName.getText().toString(),
                            repositoryOwner.getText().toString());

                    db.close();

                    Fragment fragment = this.getSupportFragmentManager()
                            .findFragmentById(R.id.fragment);

                    if (fragment instanceof MainActivityFragment) {
                        ((MainActivityFragment) fragment).loadCursor();
                    }
                })
                .show();
    }
}