package com.example.instagram.MainActivity.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagram.Common.Models.Post;
import com.example.instagram.MainActivity.Adapter.PostsAdapter;
import com.example.instagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class TimelineFragment extends Fragment {

    private static final String TAG = "TimelineFragment";

    private RecyclerView rvTimeline;
    private PostsAdapter postsAdapter;
    private List<Post> posts;

    public TimelineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timeline, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTimeline = view.findViewById(R.id.rvTimeline);
        posts = new ArrayList<>();
        postsAdapter = new PostsAdapter(getContext(), posts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTimeline.setAdapter(postsAdapter);
        rvTimeline.setLayoutManager(layoutManager);
        refreshPosts();

    }

    private void refreshPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.setLimit(20);
        query.orderByDescending("createdAt");
        query.include("user");
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                //Found posts successfully
                if (e == null){
                    posts.clear();
                    posts.addAll(objects);
                    postsAdapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "Failed fething posts from Parse", e);
                }
            }
        });
    }
}