package com.vwaber.udacity.crusty.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Step;

import java.util.ArrayList;
import java.util.List;

class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepViewHolder>{

    private final Context mContext;
    private final List<Step> mSteps;
    private final StepClickListener mClickListener;

    interface StepClickListener {
        void onStepClick(Step data);
    }

    StepListAdapter(Context context, @Nullable Fragment fragment) {
        mContext = context;
        mSteps = new ArrayList<>();
        if(fragment != null) mClickListener = (StepClickListener) fragment;
        else mClickListener = (StepClickListener) context;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.step_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(layoutId, parent, false);
        return new StepListAdapter.StepViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    void swap(List<Step> data){
        if(data == null) return;
        mSteps.clear();
        mSteps.addAll(data);
        notifyDataSetChanged();
    }

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView stepName;

        StepViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            stepName = itemView.findViewById(R.id.tv_step_text);
        }

        void bind(final int position){
            Step step = mSteps.get(position);
            stepName.setText(step.getText());
        }

        @Override
        public void onClick(View view) {
            Step data = mSteps.get(getAdapterPosition());
            mClickListener.onStepClick(data);
        }
    }
}
