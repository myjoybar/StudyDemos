package com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh3.recyclerview_refresh2.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh3.recyclerview_refresh2.ItemClickListener;

/**
 * Created by joybar on 15/12/3.
 */
public   class MViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextView;
    public ImageView image;

    public MViewHolder(final View view,final ItemClickListener mItemClickListener) {
        super(view);
        this.mTextView = (TextView) view.findViewById(R.id.tv_friend_name);
        this.image = (ImageView) itemView
                .findViewById(R.id.img_friend_avatar);
        // 为item添加普通点击回调
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (null != mItemClickListener) {
                    mItemClickListener.onItemClick(view, getPosition());
                }

            }
        });

        // 为item添加长按回调
        view.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                if (null != mItemClickListener) {
                    mItemClickListener.onItemLongClick(view, getPosition());
                }
                return true;
            }
        });

    }
}