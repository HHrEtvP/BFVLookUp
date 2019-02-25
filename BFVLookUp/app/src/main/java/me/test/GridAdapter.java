package me.test;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    Typeface futura;
    Context mContext;
    List<Model> modelList;

    public GridAdapter(List<Model>modelList,Context mContext,Typeface futura){
        this.mContext=mContext;
        this.modelList=modelList;
        this.futura=futura;
    }

    @Override
    public Object getItem(int i) {
        return modelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=LayoutInflater.from(mContext).inflate(R.layout.grid_recycler_layout,viewGroup,false);
            ViewHolder vh=new ViewHolder(view);
            view.setTag(vh);
        }
        ((ViewHolder)view.getTag()).name.setTypeface(futura);
        ((ViewHolder)view.getTag()).name.setText(modelList.get(i).name);
        ((ViewHolder)view.getTag()).value.setTypeface(futura);
        ((ViewHolder)view.getTag()).value.setText(modelList.get(i).value);
        return view;
    }
    class ViewHolder{
        public TextView name;
        public TextView value;
        public ViewHolder(View view){
            name=view.findViewById(R.id.name);
            value=view.findViewById(R.id.value);
        }
    }
}
