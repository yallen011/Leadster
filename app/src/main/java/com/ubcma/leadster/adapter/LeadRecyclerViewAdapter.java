package com.ubcma.leadster.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ubcma.leadster.R;
import com.ubcma.leadster.activity.LeadDetailsActivity;
import com.ubcma.leadster.entity.Lead;
import com.ubcma.leadster.viewmodel.LeadViewModel;

import java.util.List;

/**
 * Created by Yvonne on 6/27/2016.
 */
public class LeadRecyclerViewAdapter extends RecyclerView.Adapter<LeadRecyclerViewAdapter.LeadViewHolder> {

    public List<Lead> mLeads;
    public LeadViewModel mLeadViewModel;

    public LeadRecyclerViewAdapter(List<Lead> leads, LeadViewModel leadViewModel) {
        this.mLeads = leads;
        this.mLeadViewModel = leadViewModel;
    }

    @Override
    public LeadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.lead_list_row, parent, false);

        return new LeadViewHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(LeadViewHolder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mLeads.size();
    }

    public List<Lead> getLeadsList(){
        return mLeads;
    }

    public void setLeadsList(final List<Lead> leads){
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {

            @Override
            public int getOldListSize() {
                return mLeads.size();
            }

            @Override
            public int getNewListSize() {
                return leads.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return mLeads.get(oldItemPosition).getId() ==
                        leads.get(newItemPosition).getId();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                Lead oldLead = mLeads.get(oldItemPosition);
                Lead newLead = leads.get(newItemPosition);
                return oldLead.equals(newLead);
            }

        });
        result.dispatchUpdatesTo(this);
        mLeads = leads;
    }

    public class LeadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView leadName;
        public TextView leadNumber;
        public TextView followUp;
        public TextView leadType;
        public ImageView leadImage;

        public Context mContext;

        public LeadViewHolder(Context mContext, View itemView) {
            super(itemView);
            leadImage = itemView.findViewById(R.id.lead_image);
            leadName = itemView.findViewById(R.id.lead_name);
            leadNumber = itemView.findViewById(R.id.lead_number);
            followUp = itemView.findViewById(R.id.follow_up);
            leadType = itemView.findViewById(R.id.lead_type);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void bind(int position) {

            Lead lead = mLeads.get(position);
            leadName.setText(lead.getName());
            leadNumber.setText(lead.getNumber());
            leadImage.setImageResource(R.drawable.ic_tm_indicator_48dp);
            leadType.setText(lead.getType());
            followUp.setText("1st Follow Up");
            itemView.setTag(lead);
        }

        // Handles the row being clicked
        @Override
        public void onClick(View v) {
            Log.d("LeadAdapter", "onClick: entered");
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Lead lead = (Lead) v.getTag();
                Intent intent = new Intent(v.getContext(), LeadDetailsActivity.class);
                intent.putExtra("leadId", lead.getId());
                v.getContext().startActivity(intent);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            Log.d("LeadAdapter", "onLongClick: entered");
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Lead lead = (Lead) view.getTag();
                mLeadViewModel.deleteLead(lead);
                Toast.makeText(view.getContext(), "Lead deleted", Toast.LENGTH_SHORT).show();
            }
            //return true to indicate the click was handled
            return true;
        }
    }
}
