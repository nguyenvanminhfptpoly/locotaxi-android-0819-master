package com.example.asignment.view;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asignment.R;
import com.example.asignment.model.Post;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ImageView mImgImageDetail;
    private FloatingActionButton mFbtnFavorites;
    private FloatingActionButton mFbtnShare;
    private FloatingActionButton mFbtnSaveImage;
    private FloatingActionButton mFbtnSetAs;
    private FloatingActionMenu mFab;
    private Post postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mImgImageDetail = findViewById(R.id.imageView2);
        mFbtnFavorites = findViewById(R.id.fbtnFavorites);
        mFbtnShare = findViewById(R.id.fbtnShare);
        mFbtnSaveImage = findViewById(R.id.fbtnSaveImage);
        mFbtnSetAs = findViewById(R.id.fbtnSetAs);
        mFab = findViewById(R.id.fab);

        GetItem();

    }
    private void GetItem(){
        postList = (Post) getIntent().getSerializableExtra("imageinfo");
        Picasso.with(getApplication()).load(postList.getEmbedded().getWpFeaturedmedia().get(0).getMediaDetails().getSizes().getMedium().getSourceUrl()).into(mImgImageDetail);
    }

}
