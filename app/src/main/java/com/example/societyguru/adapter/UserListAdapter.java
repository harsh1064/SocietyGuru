package com.example.societyguru.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.societyguru.R;
import com.example.societyguru.adapter.chairman.OnUserOptionClick;
import com.example.societyguru.admin.UserListActivity;
import com.example.societyguru.databinding.UserListViewBinding;
import com.example.societyguru.model.UserModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class UserListAdapter extends FirestoreRecyclerAdapter<UserModel,UserListAdapter.AdapterViewHolder> {

    private final OnUserOptionClick optionClick;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *  @param options
     * @param optionClick
     * @param userListActivity
     */
    Context context;
    private Object OnUserOptionClick;
    private Object UserModel;
    PopupMenu popupMenu;

    public UserListAdapter(@NonNull FirestoreRecyclerOptions<UserModel> options, OnUserOptionClick optionClick, Context context, com.example.societyguru.adapter.chairman.OnUserOptionClick click) {
        super(options);
        this.optionClick = optionClick;
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterViewHolder holder, int position, @NonNull UserModel model) {
        holder.setData(model,this.optionClick);

    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(UserListViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder implements OnUserOptionClick {

        public UserListViewBinding viewBinding;

        public AdapterViewHolder(@NonNull  UserListViewBinding itemView) {
            super(itemView.getRoot());
            viewBinding = itemView;
        }

        public void setData(UserModel model, OnUserOptionClick optionClick) {
            viewBinding.setModel(model);
            viewBinding.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(context,v);
                    popupMenu.inflate(R.menu.admin_user_options);

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.user_block:
                                    Toast.makeText(context, "user blocked", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.user_unblock:
                                    Toast.makeText(context, "user unblocked", Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }
            });
        }

        @Override
        public void showoptions(UserModel model, OnUserOptionClick view) {

        }

        @Override
        public void showSocietyInfo(int position) {

        }
    }
}
