package com.example.interviewtask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private  List<String> runningAppDataSet;
    private LayoutInflater mInflater;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView myTextView;
        MyViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAppName);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context,  List<String> myDataset) {
        this.mInflater = LayoutInflater.from(context);

        runningAppDataSet = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view

        View view = mInflater.inflate(R.layout.row_layout, parent, false);

        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String animal = runningAppDataSet.get(position);

        holder.myTextView.setText(animal);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return runningAppDataSet.size();
    }
}