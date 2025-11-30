package com.example.projetandroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.R;
import com.example.projetandroid.models.Property;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {

    private List<Property> propertyList;

    public PropertyAdapter(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_property, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        Property property = propertyList.get(position);
        holder.propertyTitle.setText(property.getTitle());
        holder.propertyAddress.setText(property.getAddress());
        holder.propertyPrice.setText(String.format("%.2f / day", property.getPricePerDay()));
        // TODO: Load image using a library like Glide or Picasso
        // holder.propertyImage.setImageResource(R.drawable.placeholder_image);
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    static class PropertyViewHolder extends RecyclerView.ViewHolder {
        ImageView propertyImage;
        TextView propertyTitle, propertyAddress, propertyPrice;

        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyImage = itemView.findViewById(R.id.propertyImage);
            propertyTitle = itemView.findViewById(R.id.propertyTitle);
            propertyAddress = itemView.findViewById(R.id.propertyAddress);
            propertyPrice = itemView.findViewById(R.id.propertyPrice);
        }
    }
}
