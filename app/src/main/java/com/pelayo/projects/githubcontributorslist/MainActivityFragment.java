package com.pelayo.projects.githubcontributorslist;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityFragment extends Fragment {
    private DbAdapter mDbAdapter;
    private RecyclerView mRecyclerView;
    private RepositoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View mView;
    private Cursor mCursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        mDbAdapter = new DbAdapter(mView.getContext());

        mLayoutManager = new LinearLayoutManager(mView.getContext());

        //Preparo el recyclerview
        mRecyclerView = mView.findViewById(R.id.repos_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mDbAdapter.open();

        mCursor = mDbAdapter.fetchAllRepositories();

        mAdapter = new RepositoryAdapter(mCursor, mView.getContext(), this);
        mRecyclerView.setAdapter(mAdapter);

        mDbAdapter.close();

        return mView;
    }

    public void loadCursor() {
        mDbAdapter.open();
        mCursor = mDbAdapter.fetchAllRepositories();
        mDbAdapter.close();

        mAdapter.changeCursor(mCursor);
        mAdapter.notifyDataSetChanged();
    }
}
