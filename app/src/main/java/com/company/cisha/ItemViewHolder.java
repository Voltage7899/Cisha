package com.company.cisha;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView name,departure,landing,from,to;

    ImageView imageView;

    public ItemViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.name_element);
        departure=view.findViewById(R.id.departure_element);
        landing=view.findViewById(R.id.landing);
        from=view.findViewById(R.id.from);
        to=view.findViewById(R.id.to);
        imageView = view.findViewById(R.id.image_element);
    }
}
