package com.example.joybar.myaskunagjia.demo.stucture.mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.User;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.util.CheckUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 15/11/26.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ItemViewHolder> {


    private List<User> mListData = new ArrayList<>();


    public List<User> getList() {
        return mListData;
    }

    public void setListData(List<User> mListData) {
        this.mListData = mListData;
    }
    public void replaceData(List<User> tasks) {
        setList(tasks);
        notifyDataSetChanged();
    }

    private void setList(List<User> tasks) {
        mListData =  CheckUtils.checkNotNull(tasks);
    }
    public UsersAdapter() {
    }

    public UsersAdapter(List<User> mListData) {
        this.mListData = mListData;
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }


    @Override
    public void onBindViewHolder(UsersAdapter.ItemViewHolder holder, final int position) {
        holder.tv_id.setText(mListData.get(position).getUserId());
        holder.tv_name.setText(mListData.get(position).getName());
        holder.tv_des.setText(mListData.get(position).getDescription());

    }

    @Override
    public UsersAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_text_mvp_rv, null);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        return new ItemViewHolder(view);
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_des;
        View viewItem;

        public ItemViewHolder(View view) {
            super(view);
            viewItem = view;
            tv_des = (TextView) view.findViewById(R.id.tv_des);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_id = (TextView) view.findViewById(R.id.tv_id);
        }

        public View getViewItem() {
            return viewItem;
        }
    }

}
