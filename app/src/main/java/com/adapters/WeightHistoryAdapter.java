package com.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allemny.R;
import com.communicators.IWeightHistoryCommunicator;
import com.gaurav.cdsrecyclerview.CdsRecyclerViewAdapter;
import com.pojo.Weight;
import com.util.ImageLoader;
import com.util.ImageUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by elsaidel on 1/21/2017.
 */

public class WeightHistoryAdapter extends CdsRecyclerViewAdapter<Weight, WeightHistoryAdapter.ViewHolder> {

    View view;
    ArrayList<Weight> weights;
    Context context;
    IWeightHistoryCommunicator communicator;

    public WeightHistoryAdapter(Context context, IWeightHistoryCommunicator communicator, ArrayList<Weight> list) {
        super(context, list);
        this.context = context;
        this.communicator = communicator;
        this.weights = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weight_history_item_details, parent, false);
        WeightHistoryAdapter.ViewHolder holder = new WeightHistoryAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final WeightHistoryAdapter.ViewHolder viewHolder = (WeightHistoryAdapter.ViewHolder) holder;
        Weight weight = weights.get(position);
        ImageLoader.loadImageFromURL(context, ImageUtils.getImageUri(context, ImageUtils.getImage(getList().get(position).getUserImage())).toString(), viewHolder.ivUserImage, R.drawable.add_image);
        viewHolder.tvDate.setText(weight.getDate());
        viewHolder.tvWeight.setText(weight.getWeight() + " K.G");
    }

    @Override
    public int getItemCount() {
        if (weights != null)
            return weights.size();
        else
            return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refreshAdapter() {
        this.weights = communicator.getUserWeightsHistory();
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvWeightHistoryItemDetailsWeight)
        TextView tvWeight;
        @BindView(R.id.tvWeightHistoryItemDetailsDate)
        TextView tvDate;
        @BindView(R.id.ivFragmentWeightHistoryItemDetailsUserImage)
        ImageView ivUserImage;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
