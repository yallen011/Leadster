package com.ubcma.leadster.adapter;

import android.graphics.Movie;
import android.graphics.drawable.Icon;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ubcma.leadster.R;
import com.ubcma.leadster.vo.Lead;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvonne on 6/27/2016.
 */
public class LeadRecyclerViewAdapter extends RecyclerView.Adapter<LeadRecyclerViewAdapter.MyViewHolder> {

    public List<Lead> leads;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView leadName;
        public TextView leadNumber;
        public TextView followUp;
        public TextView leadType;
        public ImageView leadImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            leadImage = (ImageView) itemView.findViewById(R.id.lead_image);
            leadName = (TextView) itemView.findViewById(R.id.lead_name);
            leadNumber = (TextView) itemView.findViewById(R.id.lead_number);
            followUp = (TextView) itemView.findViewById(R.id.follow_up);
            leadType = (TextView) itemView.findViewById(R.id.lead_type);
        }
    }

    public LeadRecyclerViewAdapter(List<Lead> leads) {
        this.leads = leads;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lead_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Lead lead = leads.get(position);
        holder.leadName.setText(lead.getName());
        holder.leadNumber.setText(lead.getNumber());
        holder.leadImage.setImageResource(R.drawable.account_circle);
        holder.leadType.setText(lead.getType());
        holder.followUp.setText("1st Follow Up");
    }

    @Override
    public int getItemCount() {
        return leads.size();
    }


}
