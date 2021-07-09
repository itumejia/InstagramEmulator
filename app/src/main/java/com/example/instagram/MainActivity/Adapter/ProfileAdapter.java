package com.example.instagram.MainActivity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instagram.Common.Models.Post;
import com.example.instagram.MainActivity.Fragments.DetailsPostFragment;
import com.example.instagram.R;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.List;

public class ProfileAdapter extends PostsAdapter {

    private static final int TYPE_USER_INFO = 1;
    private static final int TYPE_POST = 2;

    ParseUser user;

    public ProfileAdapter(Context context, FragmentManager fragmentManager, List<Post> posts, ParseUser user) {
        super(context, fragmentManager, posts);
        this.user = user;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_USER_INFO){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_info, parent, false);
            return new UserInfoViewHolder(view);
        }

        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserInfoViewHolder) {
            ((UserInfoViewHolder) holder).bind();
            return;
        }
        super.onBindViewHolder(holder, position - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_USER_INFO;
        }
        else {
            return TYPE_POST;
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    public class UserInfoViewHolder extends RecyclerView.ViewHolder {

        ImageView ivUserPicture;
        TextView tvUsername;
        TextView tvUserCaption;

        public UserInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUserPicture = itemView.findViewById(R.id.ivUserPicture);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvUserCaption = itemView.findViewById(R.id.tvUserCaption);
        }


        public void bind() {
            //TODO set user picture
            tvUsername.setText(user.getUsername());
            //TODO set user caption
        }
    }
}