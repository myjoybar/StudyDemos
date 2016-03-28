package com.example.joybar.myaskunagjia.demo.Adapter.MultiType;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.joybar.myaskunagjia.demo.Adapter.CommonAdapter;
import com.example.joybar.myaskunagjia.demo.Adapter.ViewHolder;

import java.util.List;

/**
 * Created by joybar on 3/11/16.
 */
public class MultiItemCommonAdapter<T> extends CommonAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<T> datas,
                                  MultiItemTypeSupport<T> multiItemTypeSupport)
    {
        super(context, datas, -1);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }
    @Override
    public void convert(ViewHolder viewHolder, T item, int position) {

    }

    @Override
    public int getViewTypeCount() {

        if (mMultiItemTypeSupport != null){
            return mMultiItemTypeSupport.getViewTypeCount();
        }
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiItemTypeSupport != null){
            return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));

        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mMultiItemTypeSupport == null){
            return super.getView(position, convertView, parent);

        }

        int layoutId = mMultiItemTypeSupport.getLayoutId(position,
                getItem(position));
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        convert(viewHolder, getItem(position),position);
        return viewHolder.getConvertView();
    }
}
