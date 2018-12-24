package com.pelayo.projects.githubcontributorslist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private Context mContext;
    private CursorAdapter mCursorAdapter;
    private MainActivityFragment mFragmentInstance;

    public RepositoryAdapter(Cursor cursor, Context context, MainActivityFragment fragment) {
        mFragmentInstance = fragment;
        mContext = context;
        mCursorAdapter = new CursorAdapter(mContext, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                //Inflate the view
                return LayoutInflater.from(mContext)
                        .inflate(R.layout.repository_row, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                String name = cursor.getString(DbAdapter.INDEX_NAME);
                String owner = cursor.getString(DbAdapter.INDEX_OWNER);

                TextView nameTv = view.findViewById(R.id.name_textview);
                TextView ownerTv = view.findViewById(R.id.owner_textview);
                TextView apiCallTv = view.findViewById(R.id.api_call_textview);

                nameTv.setText(name);
                ownerTv.setText(owner);
                apiCallTv.setText(Repository.generateApiCall(owner, name));

                Intent intent = new Intent(mContext, ContributorsActivity.class);
                intent.putExtra("id", cursor.getInt(DbAdapter.INDEX_ID));

                view.setOnClickListener(v -> mContext.startActivity(intent));
                view.setOnLongClickListener(v -> showDeleteAlertDialog(cursor.getInt(
                        DbAdapter.INDEX_ID)));
            }
        };
    }

    private boolean showDeleteAlertDialog(int id) {
        new AlertDialog.Builder(mContext)
                .setTitle(R.string.delete_dialog_title)
                .setMessage(R.string.delete_dialog_message)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    DbAdapter dbAdapter = new DbAdapter(mContext);
                    dbAdapter.open();
                    dbAdapter.deleteRepositoryById(id);
                    dbAdapter.close();

                    //Informar del cambio
                    if (mFragmentInstance != null)
                        mFragmentInstance.loadCursor();
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
        return true;
    }

    public void changeCursor(Cursor cursor) {
        mCursorAdapter.changeCursor(cursor);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        mCursorAdapter.getCursor().moveToPosition(i);
        mCursorAdapter.bindView(viewHolder.itemView, mContext, mCursorAdapter.getCursor());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(mCursorAdapter.newView(mContext, mCursorAdapter.getCursor(), parent));
    }

    @Override
    public int getItemCount() {
        return mCursorAdapter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
