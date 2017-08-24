package com.example.rshc4u.appv3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rshc4u.appv3.R;
import com.example.rshc4u.appv3.data_model.home_data.PullupContent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rshc4u on 8/22/17.
 */

public class PullMenuAdapter extends RecyclerView.Adapter<PullMenuAdapter.PullHolder> {


    private Context mContext;
    private ArrayList<PullupContent> listItems;


    public PullMenuAdapter(Context mContext, ArrayList<PullupContent> listItems) {
        this.mContext = mContext;
        this.listItems = listItems;
    }


    @Override
    public PullHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pull_adapter_layout, parent, false);
        PullHolder viewHolder = new PullHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PullHolder holder, final int position) {


        String text = listItems.get(position).getText();

        holder.text.setText(text);

        String title = listItems.get(position).getTitle();

        holder.title.setText(title);


        Picasso.with(mContext).load(listItems.get(position).getImg()).
                placeholder(R.drawable.place_holder).error(
                R.drawable.place_holder).into(holder.images);


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class PullHolder extends RecyclerView.ViewHolder {
        public TextView title, text;
        public ImageView images;
        public LinearLayout layout;

        public PullHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.pull_title);
            text = (TextView) itemView.findViewById(R.id.pull_text);
            layout = (LinearLayout) itemView.findViewById(R.id.liner_layout_pull);
            images = (ImageView) itemView.findViewById(R.id.imgPull);

        }
    }
}
