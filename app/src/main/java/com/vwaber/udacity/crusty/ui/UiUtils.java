package com.vwaber.udacity.crusty.ui;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.vwaber.udacity.crusty.R;

import java.text.DecimalFormat;
import java.util.Objects;

public class UiUtils {

    static SimpleExoPlayer getDefaultExoVideoPlayer(Context context){

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        return ExoPlayerFactory.newSimpleInstance(context, trackSelector);
    }

    static MediaSource convertUriToDefaultMediaSource(Context context, Uri mediaUri){
        Resources resources = context.getResources();
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        String userAgent = Util.getUserAgent(context, resources.getString(R.string.app_name));
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, userAgent, bandwidthMeter);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        return new ExtractorMediaSource(mediaUri, dataSourceFactory, extractorsFactory, null, null);
    }

    static void loadImageInto(Context context, ImageView imageView, String imageUrl){
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
    }

    public static String formatUnit(Context context, String unit){

        Resources resources = context.getResources();

        String[] unitKeys = resources.getStringArray(R.array.unit_keys);
        String[] unitValues = resources.getStringArray(R.array.unit_values);

        String newUnit = null;

        for(int i = 0; i < unitKeys.length; i++){
            if(Objects.equals(unit, unitKeys[i])) newUnit = unitValues[i];
        }

        return newUnit;

    }

    public static String formatQuantity(Context context, float quantity){

        Resources resources = context.getResources();
        final DecimalFormat format = new DecimalFormat(resources.getString( R.string.ingredient_decimal_format));

        return format.format(quantity);

    }

}
