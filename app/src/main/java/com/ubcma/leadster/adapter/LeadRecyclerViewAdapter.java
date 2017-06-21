package com.ubcma.leadster.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ubcma.leadster.R;
import com.ubcma.leadster.activity.LeadDetailsActivity;
import com.ubcma.leadster.vo.Lead;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Yvonne on 6/27/2016.
 */
public class LeadRecyclerViewAdapter extends RecyclerView.Adapter<LeadRecyclerViewAdapter.MyViewHolder> {

    public List<Lead> mLeads;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView leadName;
        public TextView leadNumber;
        public TextView followUp;
        public TextView leadType;
        public ImageView leadImage;

        public Context context;

        public MyViewHolder(Context context, View itemView) {
            super(itemView);
            leadImage = (ImageView) itemView.findViewById(R.id.lead_image);
            leadName = (TextView) itemView.findViewById(R.id.lead_name);
            leadNumber = (TextView) itemView.findViewById(R.id.lead_number);
            followUp = (TextView) itemView.findViewById(R.id.follow_up);
            leadType = (TextView) itemView.findViewById(R.id.lead_type);

            itemView.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Intent intent = new Intent(v.getContext(), LeadDetailsActivity.class);
                v.getContext().startActivity(intent);
            }
        }
    }

    public LeadRecyclerViewAdapter(List<Lead> leads) {
        this.mLeads = leads;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.lead_list_row, parent, false);

        return new MyViewHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Lead lead = mLeads.get(position);
        holder.leadName.setText(lead.getName());
        holder.leadNumber.setText(lead.getNumber());
        holder.leadImage.setImageResource(R.drawable.ic_account_circle_black_48dp);
        holder.leadType.setText(lead.getType());
        holder.followUp.setText("1st Follow Up");
    }

    @Override
    public int getItemCount() {
        return mLeads.size();
    }


}
