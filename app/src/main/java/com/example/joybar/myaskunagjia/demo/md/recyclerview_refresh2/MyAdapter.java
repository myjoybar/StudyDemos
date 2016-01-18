package com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh2;

/**
 * Created by joybar on 15/11/26.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh2.ViewHolder.MViewHolder;
import com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh2.ViewHolder.FooterViewHolder;

import java.util.List;

/**
 * TODO<>
 *
 * @author wen_er
 * @data: 2015年6月5日 下午2:57:16
 * @version: V1.0
 */
public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private List<User> listData;
    private static ItemClickListener mItemClickListener;

    //上拉加载
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public MyAdapter(Context context, List<User> mList) {
        super();
        this.context = context;
        this.listData = mList;
    }

    /**
     * TODO<添加数据，指定其位置>
     */

    public void addData(User info, int position) {
        listData.add(position, info);
        notifyItemInserted(position);
        //	notifyDataSetChanged(); //不会触发Item的动画效果，告知数据改变，刷新UI

    }

    /**
     * TODO<添加数据到最后面添加>
     */

    public void addData(User info) {
        // listData.add(position, info);
        // notifyItemInserted(position);
        listData.add(info);
        notifyDataSetChanged();
    }

    /**
     * TODO<删除数据，指定其位置>
     */
    public void daleteData(int position) {
        listData.remove(position);
        notifyItemRemoved(position);

    }

    /**
     * TODO<某一位置开始，有itemCount个Item的数据删除>
     */
    public void itemRangeRemoved(int positionStart, int itemCount) {
        for (int i = positionStart; i < itemCount; i++) {
            listData.remove(positionStart);
        }
        notifyItemRangeRemoved(positionStart, itemCount);
        //	notifyDataSetChanged(); //不会触发Item的动画效果，告知数据改变，刷新UI
    }

    /**
     * TODO<某一位置开始，有itemCount个Item的数据插入>
     */
    public void itemRangeInserted(User info, int positionStart, int itemCount) {
        for (int i = positionStart; i < itemCount; i++) {
            listData.add(i, info);
        }
        //	notifyItemRangeInserted(positionStart, itemCount);
        notifyDataSetChanged();
    }

    public void dataSetChanged(){

        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {

        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return listData.size()+1; //为了有加载更多
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
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
        if (arg1 == TYPE_ITEM) {
            View view = View.inflate(viewGroup.getContext(),
                    R.layout.item_user_friend_nod, null);
            // 创建一个ViewHolder
            MViewHolder holder = new MViewHolder(view,mItemClickListener);
            return holder;
        }else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footerview_rr, null);
            view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }

    }



    @Override
    public void onBindViewHolder(final ViewHolder mViewHolder,
                                 final int postion) {
        if (mViewHolder instanceof MViewHolder) {


            ((MViewHolder) mViewHolder).mTextView.setText(listData.get(postion).getUsername());
            ((MViewHolder) mViewHolder).image.setBackgroundResource(R.drawable.head);
            // 为image添加监听回调
            ((MViewHolder) mViewHolder).image.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (null != mItemClickListener) {
                        mItemClickListener.onItemSubViewClick(((MViewHolder) mViewHolder).image,
                                postion);
                    }

                }

            });

        }

    }


}
