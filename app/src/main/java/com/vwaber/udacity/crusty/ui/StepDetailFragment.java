package com.vwaber.udacity.crusty.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Recipe;
import com.vwaber.udacity.crusty.data.Step;

import java.util.List;

public class StepDetailFragment extends Fragment {

    private static final String PLAYER_POSITION = "player_position";
    private static final String PLAYER_URL = "player_media_url";
    private long mPlayerPosition;
    private String mPlayerUrl;

    private Context mContext;

    private Recipe mRecipe;
    private Step mStep;

    private TextView mStepText;
    private ImageView mStepImage;

    private SimpleExoPlayerView mPlayerView;
    private SimpleExoPlayer mExoPlayer;

    private Button mNextButton;
    private Button mPrevButton;

    interface FragmentCreationListener{
        void onFragmentCreated(StepDetailFragment fragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong(PLAYER_POSITION, mPlayerPosition);
        outState.putString(PLAYER_URL, mPlayerUrl);
        outState.putParcelable(Step.PARCELABLE_EXTRA_KEY, mStep);
        outState.putParcelable(Recipe.PARCELABLE_EXTRA_KEY, mRecipe);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);

        mStepText = rootView.findViewById(R.id.tv_step_text);
        mStepImage = rootView.findViewById(R.id.iv_step_image);
        mPlayerView = rootView.findViewById(R.id.sepv_media_player);

        mNextButton = rootView.findViewById(R.id.btn_next_step);
        mPrevButton = rootView.findViewById(R.id.btn_prev_step);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextStep();
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPrevStep();
            }
        });

        if(savedInstanceState != null){
            mPlayerPosition = savedInstanceState.getLong(PLAYER_POSITION);
            mPlayerUrl = savedInstanceState.getString(PLAYER_URL);
            mStep = savedInstanceState.getParcelable(Step.PARCELABLE_EXTRA_KEY);
            mRecipe = savedInstanceState.getParcelable(Recipe.PARCELABLE_EXTRA_KEY);
            setStep(mStep);
        }

        initializePlayer();

        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState == null) ((FragmentCreationListener) mContext).onFragmentCreated(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        initializePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

    void setRecipe(Recipe recipe){
        mRecipe = recipe;
    }

    void setStep(Step data){

        pausePlayer();

        mStep = data;
        mStepText.setText(data.getText());

        if(!TextUtils.isEmpty(data.getVideoUrl())){
            mPlayerUrl = data.getVideoUrl();
            setPlayerMedia();
            mPlayerView.setVisibility(View.VISIBLE);
            mStepImage.setVisibility(View.GONE);
        }else if(!TextUtils.isEmpty(data.getImageUrl())){
            UiUtils.loadImageInto(mContext, mStepImage, data.getImageUrl());
            mStepImage.setVisibility(View.VISIBLE);
            mPlayerView.setVisibility(View.GONE);
        }else{
            mStepImage.setVisibility(View.GONE);
            mPlayerView.setVisibility(View.GONE);
        }

        List<Step> steps = mRecipe.getSteps();
        int index = steps.indexOf(mStep);

        if(index == 0){
            mNextButton.setText(R.string.step_detail_next);
            mPrevButton.setText(R.string.step_detail_last);
        }else if(index == steps.size()-1){
            mNextButton.setText(R.string.step_detail_first);
            mPrevButton.setText(R.string.step_detail_prev);
        }else{
            mNextButton.setText(R.string.step_detail_next);
            mPrevButton.setText(R.string.step_detail_prev);
        }

    }

    private void showNextStep(){
        List<Step> steps = mRecipe.getSteps();
        int index = (steps.indexOf(mStep) + 1) % steps.size();
        setStep(steps.get(index));
    }

    private void showPrevStep(){
        List<Step> steps = mRecipe.getSteps();
        int index = steps.indexOf(mStep) - 1;
        if(index < 0) index += steps.size();
        setStep(steps.get(index));
    }

    private void initializePlayer(){
        if(mExoPlayer != null) return;
        mExoPlayer = UiUtils.getDefaultExoVideoPlayer(mContext);
        mPlayerView.setPlayer(mExoPlayer);
        if(mPlayerUrl != null) setPlayerMedia();
        if(mPlayerPosition != 0) mExoPlayer.seekTo(mPlayerPosition);
    }

    private void releasePlayer(){
        if(mExoPlayer == null) return;
        mPlayerPosition = mExoPlayer.getCurrentPosition();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    private void pausePlayer(){
        if(mExoPlayer == null) return;
        mExoPlayer.setPlayWhenReady(false);
    }

    private void setPlayerMedia(){
        if(mExoPlayer == null) return;
        Uri videoUri = Uri.parse(mPlayerUrl);
        MediaSource mediaSource = UiUtils.convertUriToDefaultMediaSource(mContext , videoUri);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);
    }

}
