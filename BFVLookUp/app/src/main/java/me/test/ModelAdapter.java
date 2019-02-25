package me.test;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder>{

    List<Model>modelList;
    Typeface futura;
    int layoutResId;

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView value;

        public ViewHolder(View view){
            super(view);
            name=view.findViewById(R.id.name);
            value=view.findViewById(R.id.value);
            name.setTypeface(futura);//设置字体
            value.setTypeface(futura);
        }
    }

    public ModelAdapter(List<Model>modelList, Typeface futura,int layoutResId){
        this.modelList=modelList;
        this.futura=futura;
        this.layoutResId=layoutResId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        View view=LayoutInflater.from(context).inflate(layoutResId,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(modelList.get(position).name);
        holder.value.setText(modelList.get(position).value);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}