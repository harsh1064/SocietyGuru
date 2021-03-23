package com.example.societyguru.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.societyguru.R;
import com.example.societyguru.adapter.admin.OnSocietyOptionClick;
import com.example.societyguru.databinding.SocietyListViewBinding;
import com.example.societyguru.enums.SocietyStatus;
import com.example.societyguru.model.SocietyModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SocietyListAdapter extends FirestoreRecyclerAdapter<SocietyModel,SocietyListAdapter.AdapterViewHolder> {

    private final OnSocietyOptionClick optionClick;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *  @param options
     * @param optionClick
     * @param societyListActivity
     */
    Context context;
    private Object OnSocietyOptionClick;
    private Object SocietyModel;
    PopupMenu popupMenu;

    public SocietyListAdapter(@NonNull FirestoreRecyclerOptions<SocietyModel> options, OnSocietyOptionClick optionClick, Context context) {
        super(options);
        this.optionClick = optionClick;
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterViewHolder holder, int position, @NonNull SocietyModel model) {
        holder.setData(model,this.optionClick);
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(SocietyListViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder implements OnSocietyOptionClick{
        public SocietyListViewBinding viewBinding;

        public AdapterViewHolder(@NonNull SocietyListViewBinding itemView) {
            super(itemView.getRoot());
            viewBinding = itemView;
        }

        public void setData(SocietyModel model, OnSocietyOptionClick optionClick) {
            viewBinding.setModel(model);
            //viewBinding.setModel((com.example.societyguru.model.SocietyModel) optionClick);
            viewBinding.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(context,v);
                    popupMenu.inflate(R.menu.admin_society_options);

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.society_update:
                                    Toast.makeText(context, "society updated", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.society_delete:
                                    Toast.makeText(context, "society deleted", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.society_block:
                                    Toast.makeText(context, "society blocked", Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
//                    showoptions(model,v);
                   // OnSocietyOptionClick = optionClick;
                  //  optionClick.showoptions(model,viewBinding.btnMore);
//                    OnSocietyOptionClick = optionClick;
//                    SocietyModel = model;
//                    optionClick.showoptions(model,viewBinding.btnMore);
                }
            });
//            viewBinding.btnMore.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    OnSocietyOptionClick = optionClick;
//                    optionClick.showSocietyInfo(getAdapterPosition());
//                }
//            });
        }

        @Override
        public void showoptions(com.example.societyguru.model.SocietyModel model, com.example.societyguru.adapter.admin.OnSocietyOptionClick view) {

        }

        @Override
        public void showSocietyInfo(int position) {

        }
        }
    }

