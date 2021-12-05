package com.example.sellhouse.buyfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sellhouse.R;
import com.example.sellhouse.model.House_Model;

import java.util.List;

public class BuyHouseAdapter extends RecyclerView.Adapter<BuyHouseAdapter.ViewHolder> {

    private List<House_Model> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    BuyHouseAdapter(Context context, List<House_Model> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.buy_house_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        House_Model house = mData.get(position);
        holder.price.setText(String.format("$%s", house.getPrice()));
        holder.mImageView.setImageResource(Integer.parseInt(String.valueOf(house.image)));
        holder.address.setText(house.getAddress1());
        holder.bedroomTextview.setText(String.format("%s BedRooms", house.getNoOfBedrooms()));
        holder.bathroomTextView.setText(String.format("%s Bathrooms", house.getNoOfWashrooms()));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView price, address, bedroomTextview, bathroomTextView;
        ImageView mImageView;

        ViewHolder(View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.price);
            mImageView = itemView.findViewById(R.id.mImageViewNotification);
            address = itemView.findViewById(R.id.address);
            bedroomTextview = itemView.findViewById(R.id.bedroomTextview);
            bathroomTextView = itemView.findViewById(R.id.bathroomTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}