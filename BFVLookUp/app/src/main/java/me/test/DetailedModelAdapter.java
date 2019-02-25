package me.test;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class DetailedModelAdapter extends RecyclerView.Adapter<DetailedModelAdapter.ViewHolder> {
    List<DetailedModel>modelList;
    Typeface futura;
    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView detail1_name;
        TextView detail2_name;
        TextView detail1_value;
        TextView detail2_value;

        public ViewHolder(View view){
            super(view);
            image=view.findViewById(R.id.image);
            name=view.findViewById(R.id.name);
            detail1_name=view.findViewById(R.id.detail_1_name);
            detail2_name=view.findViewById(R.id.detail_2_name);
            detail1_value=view.findViewById(R.id.detail_1_value);
            detail2_value=view.findViewById(R.id.detail_2_value);
        }
    }

    public DetailedModelAdapter(List<DetailedModel>modelList,Typeface futura){
        this.modelList=modelList;
        this.futura=futura;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycler_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageResource(modelList.get(position).imageId);
        holder.name.setTypeface(futura);
        holder.name.setText(modelList.get(position).name);
        holder.detail1_name.setTypeface(futura);
        holder.detail1_name.setText(modelList.get(position).detail1.name);
        holder.detail2_name.setTypeface(futura);
        holder.detail2_name.setText(modelList.get(position).detail2.name);
        holder.detail1_value.setTypeface(futura);
        holder.detail1_value.setText(modelList.get(position).detail1.value);
        holder.detail2_value.setTypeface(futura);
        holder.detail2_value.setText(modelList.get(position).detail2.value);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
