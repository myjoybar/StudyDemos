package com.example.joybar.myaskunagjia.demo.Adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by joybar on 3/9/16.
 */
public class ViewHolder {
    private final SparseArray<View> mViews;
    private View mConvertView;
    private int mPosition;


    private int mLayoutId;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mLayoutId = layoutId;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        //setTag
        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {

        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    public <T extends View> T getView(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text)
    {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }
    public ViewHolder setImageUrl(int viewId, String imageUrl) {
        final ImageView view = getView(viewId);
        Picasso.with(view.getContext()).load(imageUrl).into(view);

//        final  ImageView view  = (ImageView) mConvertView.findViewById(viewId);
//
//        Picasso.with(view.getContext()).load("http://i.imgur.com/DvpvklR.png").placeholder(R.drawable.head).error(R.mipmap.ic_launcher).resize(100, 100).into(view, new Callback() {
//            @Override
//            public void onSuccess() {
//                T.showShort(view.getContext(), "onSuccess");
//            }
//
//            @Override
//            public void onError() {
//                T.showShort(view.getContext(),"onError");
//            }
//        });
//
//
        return this;
    }

    public int getLayoutId(){
        return mLayoutId;
    }

    public int getmPosition() {
        return mPosition;
    }
}
