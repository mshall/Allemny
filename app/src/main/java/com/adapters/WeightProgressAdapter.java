package com.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allemny.R;
import com.communicators.IWeightHistoryCommunicator;
import com.pojo.Weight;
import com.util.ImageLoader;
import com.util.ImageUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by elsaidel on 1/21/2017.
 */

public class WeightProgressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    View view;
    ArrayList<Weight> weights;
    Context context;
    IWeightHistoryCommunicator communicator;

    public WeightProgressAdapter(Context context, IWeightHistoryCommunicator communicator, ArrayList<Weight> list) {
        this.context = context;
        this.communicator = communicator;
        this.weights = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weight_history_item_details, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Holder viewHolder = (Holder) holder;
        Weight weight = weights.get(position);
        ImageLoader.loadImageFromURL(context, ImageUtils.getImageUri(context, ImageUtils.getImage(weight.getUserImage())).toString(), viewHolder.ivUserImage, R.drawable.add_image);
        viewHolder.tvDate.setText(weight.getDate());
        viewHolder.tvWeight.setText(weight.getWeight() + " K.G");
    }

    @Override
    public int getItemCount() {
        if (weights != null) {
            Log.e("HSITORY SIZE: ", weights.size() + "");
            return weights.size();
        } else {
            return 0;
        }

    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvWeightHistoryItemDetailsWeight)
        TextView tvWeight;
        @BindView(R.id.tvWeightHistoryItemDetailsDate)
        TextView tvDate;
        @BindView(R.id.ivFragmentWeightHistoryItemDetailsUserImage)
        ImageView ivUserImage;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
