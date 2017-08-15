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

        if(mExoPlayer == null){
            mExoPlayer = UiUtils.getDefaultExoVideoPlayer(mContext);
            mPlayerView.setPlayer(mExoPlayer);
        }

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

        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((FragmentCreationListener) mContext).onFragmentCreated(this);
    }

    void setRecipe(Recipe recipe){
        mRecipe = recipe;
    }

    void setStep(Step data){

        mStep = data;
        mStepText.setText(data.getText());

        if(!TextUtils.isEmpty(data.getVideoUrl())){
            Uri videoUri = Uri.parse(data.getVideoUrl());
            MediaSource mediaSource = UiUtils.convertUriToDefaultMediaSource(mContext , videoUri);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
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

}
