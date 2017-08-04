package com.vwaber.udacity.crusty.ui;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.DataUtils;
import com.vwaber.udacity.crusty.data.Step;

import java.util.Objects;

public class StepDetailFragment extends Fragment {

    Context mContext;
    Resources mResources;

    Step mStep;

    TextView mStepText;
    ImageView mStepImage;

    SimpleExoPlayerView mPlayerView;
    SimpleExoPlayer mExoPlayer;

    interface FragmentCreationListener{
        void onFragmentCreated(StepDetailFragment fragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mResources = context.getResources();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);

        mStepText = rootView.findViewById(R.id.tv_step_text);
        mStepImage = rootView.findViewById(R.id.iv_step_image);
        mPlayerView = rootView.findViewById(R.id.sepv_media_player);

        if(mExoPlayer == null){
            mExoPlayer = MediaUtils.getDefaultExoVideoPlayer(mContext);
            mPlayerView.setPlayer(mExoPlayer);
        }

        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mExoPlayer.release();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((FragmentCreationListener) mContext).onFragmentCreated(this);
    }

    void setStep(Step data){
        mStep = data;
        mStepText.setText(data.getText());

        if(!TextUtils.isEmpty(data.getVideoUrl())){
            Uri videoUri = Uri.parse(data.getVideoUrl());
            MediaSource mediaSource = MediaUtils.convertUriToDefaultMediaSource(mContext , videoUri);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
            mPlayerView.setVisibility(View.VISIBLE);
            mStepImage.setVisibility(View.GONE);
        }else if(!TextUtils.isEmpty(data.getImageUrl())){
            MediaUtils.loadImageInto(mContext, mStepImage, data.getImageUrl());
            mStepImage.setVisibility(View.VISIBLE);
            mPlayerView.setVisibility(View.GONE);
        }else{
            mStepImage.setVisibility(View.GONE);
            mPlayerView.setVisibility(View.GONE);
        }

    }

}
