package com.example.joybar.myaskunagjia.demo.md.recyclerview_refresh4;

/**
 * Created by joybar on 15/11/26.
 */

import android.view.View;

/**
 * item点击回调接口
 *
 * @author wen_er
 *
 */
public interface ItemClickListener {

    /**
     * Item 普通点击
     */

    public void onItemClick(View view, int postion);

    /**
     * Item 长按
     */

    public void onItemLongClick(View view, int postion);

    /**
     * Item 内部View点击
     */

    public void onItemSubViewClick(View view, int postion);
}
