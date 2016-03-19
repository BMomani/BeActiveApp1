package com.alcode.beactiveapp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alcode.beactiveapp.Activities.MainActivity;
import com.alcode.beactiveapp.Models.Item;
import com.alcode.beactiveapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by MOMANI on 2016/03/18.
 */
public class DummyAdapter extends RecyclerView.Adapter<DummyAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Item> mDataset;

    private static final String TAG = "MyAdpater";
    private ColorDrawable[] vibrantLightColorList =
            {
                    new ColorDrawable(Color.parseColor("#9ACCCD")), new ColorDrawable(Color.parseColor("#8FD8A0")),
                    new ColorDrawable(Color.parseColor("#CBD890")), new ColorDrawable(Color.parseColor("#DACC8F")),
                    new ColorDrawable(Color.parseColor("#D9A790")), new ColorDrawable(Color.parseColor("#D18FD9")),
                    new ColorDrawable(Color.parseColor("#FF6772")), new ColorDrawable(Color.parseColor("#DDFB5C"))
            };


    public DummyAdapter(Context context, ArrayList<Item> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    public DummyAdapter(Context context) {
        mContext = context;
        mDataset = new ArrayList<Item>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DummyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_row, null);
        // set the view's size, margins, paddings and layout parameters


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Item item = mDataset.get(position);
        Log.d(TAG, item.getUrl());
        Glide.with(holder.mImageView.getContext())
                .load(item.getUrl())
                .placeholder(getRandomDrawbleColor())//new ColorDrawable(0xCC9966)//vibrantLightColorList[2]//getRandomDrawbleColor()
                .into(holder.mImageView);

        holder.mImageTitle.setText(item.getName());



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();

    }

    public void addFirst(Item item) {
        mDataset.add(0, item);
        notifyItemInserted(0);
    }

    public ColorDrawable getRandomDrawbleColor() {
        int idx = new Random().nextInt(vibrantLightColorList.length);
        return vibrantLightColorList[idx];
    }

    // Provide a reference to the type of views that you are using
    // (custom viewholder)
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       // public LinearLayout placeNameHolder;
        public TextView mImageTitle;
        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageTitle = (TextView) itemView.findViewById(R.id.textView);
            //placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick " + getPosition() + " " + mImageTitle.getText().toString());
        }
    }

}