package com.example.joybar.myaskunagjia.demo.md.CoordinatorLayout2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 15/11/26.
 */
public class SampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Integer> list;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public List<Integer> getList() {
        return list;
    }

    public SampleAdapter() {
        list = new ArrayList<Integer>();
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).textView.setText(String.valueOf(list.get(position)));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_text_rr, null);
            view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footerview_rr, null);
            view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }

        return null;
    }

    public static  class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }

    public static  class  ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ItemViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text);
        }

    }

}
