package com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.adapter;

import android.support.v7.widget.RecyclerView;

import com.example.joybar.myaskunagjia.demo.md.sortRecyclerView.model.MembersEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    private ArrayList<MembersEntity> items = new ArrayList<>();

    public BaseAdapter() {
        setHasStableIds(true);
    }

    public void add(MembersEntity object) {
        items.add(object);
        notifyDataSetChanged();
    }

    public void add(int index, MembersEntity object) {
        items.add(index, object);
        notifyDataSetChanged();
    }

    public void addAll(Collection< MembersEntity> collection) {
        if (collection != null) {
            items.clear();
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void addAll( MembersEntity... items) {
        addAll(Arrays.asList(items));
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void remove( MembersEntity object) {
        items.remove(object);
        notifyDataSetChanged();
    }

    public  MembersEntity getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
