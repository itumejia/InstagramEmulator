package com.example.instagram.MainActivity.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.instagram.Common.Models.Post;
import com.example.instagram.R;

import org.parceler.Parcels;


public class DetailsPostFragment extends Fragment {

    private Post post;

    private ImageView ivUserPicture;
    private TextView tvUsername;
    private TextView tvRelativeTime;
    private TextView tvCaption;
    private ImageView ivPostPicture;


    public DetailsPostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        post = Parcels.unwrap(getArguments().getParcelable(Post.class.getSimpleName()));
        return inflater.inflate(R.layout.fragment_details_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);


    }

    public static DetailsPostFragment newInstance(Post post) {
        
        Bundle args = new Bundle();
        
        DetailsPostFragment fragment = new DetailsPostFragment();
        args.putParcelable(Post.class.getSimpleName(),Parcels.wrap(post));
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        ivUserPicture = view.findViewById(R.id.ivUserPicture);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvRelativeTime = view.findViewById(R.id.tvRelativeTime);
        tvCaption = view.findViewById(R.id.tvCaption);
        ivPostPicture = view.findViewById(R.id.ivPostPicture);

        //Set information to views
        //TODO: Set up the User profile picture
        tvUsername.setText(post.getUser().getUsername());
        tvRelativeTime.setText(post.getRelativeTime());
        tvCaption.setText(post.getDescription());
        String postPictureUrl = post.getImage().getUrl();
        Glide.with(getContext()).load(postPictureUrl).into(ivPostPicture);

    }
}