package com.example.instagram.MainActivity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instagram.Common.Models.Post;
import com.example.instagram.MainActivity.Fragments.DetailsPostFragment;
import com.example.instagram.MainActivity.Fragments.TimelineFragment;
import com.example.instagram.R;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<Post> posts;
    private Context context;
    private FragmentManager fragmentManager;

    public PostsAdapter(Context context, FragmentManager fragmentManager, List<Post> posts) {
        this.posts = posts;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View postItemView = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(postItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public void onViewRecycled(@NonNull PostsAdapter.ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.recycle();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivUserPicture;
        private TextView tvUsername;
        private TextView tvRelativeTime;
        private TextView tvCaption;
        private ImageView ivPostPicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUserPicture = itemView.findViewById(R.id.ivUserPicture);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvRelativeTime = itemView.findViewById(R.id.tvRelativeTime);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            ivPostPicture = itemView.findViewById(R.id.ivPostPicture);
            itemView.setOnClickListener(this);
        }

        public void bind(Post post) {
            //TODO: Set up the User profile picture
            tvUsername.setText(post.getUser().getUsername());
            tvRelativeTime.setText(post.getRelativeTime());
            tvCaption.setText(post.getDescription());
            String postPictureUrl = post.getImage().getUrl();
            Glide.with(context).load(postPictureUrl).into(ivPostPicture);

        }

        public void recycle() {
            ivUserPicture.setImageResource(0);
            tvUsername.setText("");
            tvRelativeTime.setText("");
            tvCaption.setText("");
            ivPostPicture.setImageResource(0);

        }

        @Override
        public void onClick(View v) {
            DetailsPostFragment fragment = new DetailsPostFragment();
            Post post = posts.get(getAdapterPosition());
            fragmentManager.beginTransaction().replace(R.id.flContainer, fragment.newInstance(post)).commit();
        }
    }
}
