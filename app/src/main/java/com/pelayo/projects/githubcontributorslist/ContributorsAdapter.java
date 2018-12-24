package com.pelayo.projects.githubcontributorslist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContributorsAdapter extends RecyclerView.Adapter<ContributorsAdapter.ViewHolder> {

    private Context mContext;
    private CursorAdapter mCursorAdapter;

    public ContributorsAdapter(Cursor cursor, Context context) {
        mContext = context;
        mCursorAdapter = new CursorAdapter(mContext, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                //Inflate the view
                return LayoutInflater.from(mContext)
                        .inflate(R.layout.contributors_row, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                String imageUrl = cursor.getString(DbAdapter.INDEX_AVATAR_URL);
                TextView tv = view.findViewById(R.id.contributors_login_textview);
                ImageView iv = view.findViewById(R.id.contributors_profile_imageview);

                if (imageUrl != null)
                    GlideApp.with(mContext).load(imageUrl).into(iv);

                tv.setText(cursor.getString(DbAdapter.INDEX_LOGIN));

                Intent intent = new Intent(mContext, ContributorDetailsActivity.class);
                intent.putExtra("id", cursor.getInt(DbAdapter.INDEX_ID));

                view.setOnClickListener(v -> mContext.startActivity(intent));
            }
        };
    }

    public void changeCursor(Cursor cursor) {
        mCursorAdapter.changeCursor(cursor);
    }

    @Override
    public void onBindViewHolder(@NonNull ContributorsAdapter.ViewHolder viewHolder, int i) {
        mCursorAdapter.getCursor().moveToPosition(i);
        mCursorAdapter.bindView(viewHolder.itemView, mContext, mCursorAdapter.getCursor());
    }

    @NonNull
    @Override
    public ContributorsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ContributorsAdapter.ViewHolder(mCursorAdapter.newView(mContext,
                mCursorAdapter.getCursor(), parent));
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
