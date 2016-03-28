package com.example.joybar.myaskunagjia.demo.Adapter.MultiType;

/**
 * Created by joybar on 3/11/16.
 */
public interface MultiItemTypeSupport<T> {


        int getLayoutId(int position, T t);

        int getViewTypeCount();

        int getItemViewType(int postion, T t);
    
}
