package com.example.societyguru.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.societyguru.admin.NoticeListActivity;
import com.example.societyguru.databinding.NoticeListViewBinding;
import com.example.societyguru.model.NoticeModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoticeListAdapter extends FirestoreRecyclerAdapter<NoticeModel,NoticeListAdapter.AdapterViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public NoticeListAdapter(@NonNull FirestoreRecyclerOptions<NoticeModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterViewHolder holder, int position, @NonNull NoticeModel model) {
        holder.setData(model);
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(NoticeListViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        NoticeListViewBinding viewBinding;
        public AdapterViewHolder(@NonNull NoticeListViewBinding itemView) {
            super(itemView.getRoot());
            viewBinding = itemView;
        }

        public void setData(NoticeModel model) {
            viewBinding.setModel(model);
        }
    }
}
