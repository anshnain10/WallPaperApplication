package com.example.wallpaperapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Model> localDataSet;
    private Context context;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView likes,downloads,user;
        private ImageView imageView;
        private CardView cardView;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            likes=(TextView) view.findViewById(R.id.likes);
            downloads=(TextView) view.findViewById(R.id.downloads);
            user=(TextView) view.findViewById(R.id.user);
            imageView=(ImageView) view.findViewById(R.id.imageView);
            cardView=(CardView) view.findViewById(R.id.cardView);
        }


    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(ArrayList<Model> dataSet,Context context) {
        this.context=context;
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.likes.setText("LIKES:"+localDataSet.get(position).getLikes());
        viewHolder.downloads.setText("DOWNLOADS:"+localDataSet.get(position).getDownloads());
        viewHolder.user.setText("USER:"+localDataSet.get(position).getUser());
        Glide.with(context).load(localDataSet.get(position).getWebformatURL()).into(viewHolder.imageView);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,webView.class);
                intent.putExtra("url",localDataSet.get(position).getWebformatURL());
                context.startActivity(intent);
            }
        });

//        viewHolder.linearLayout3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClicked(localDataSet.get(position));
//
//            }
//        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
