package com.example.instagram.MainActivity.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instagram.Common.Models.Post;
import com.example.instagram.MainActivity.Adapter.ProfileAdapter;
import com.example.instagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class DetailsUserFragment extends TimelineFragment {

    private ParseUser user;
    private String TAG = "DetailsUserFragment";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        user = ParseUser.getCurrentUser();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void getAdapter() {
        postsAdapter = new ProfileAdapter(getContext(), fragmentManager, posts, user);
    }

    @Override
    protected void refreshPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.setLimit(20);
        query.orderByDescending("createdAt");
        query.include("user");
        query.whereEqualTo(Post.KEY_USER, user);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                //Found posts successfully
                if (e == null){
                    posts.clear();
                    posts.addAll(objects);
                    postsAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    Log.e(TAG, "Failed fething posts from Parse", e);
                }
            }
        });
    }
}
