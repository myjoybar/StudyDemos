package com.example.joybar.myaskunagjia.demo.md.layoutmanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by joybar on 15/11/26.
 */
public class SampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private List<Integer> list;

	private static final int TYPE_ITEM = 0;
	private static final int TYPE_FOOTER = 1;

	private String mNotificationNum;

	public static final int VERTICAL = 1;
	public static final int HORIZONAL = 0;
	int direction = VERTICAL;

	public List<Integer> getList() {
		return list;
	}

	public SampleAdapter(int direction) {
		list = new ArrayList<Integer>();
		this.direction = direction;
	}

	@Override
	public int getItemCount() {
		return list.size();
	}


	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
		if (holder instanceof ItemViewHolder) {
			String str = String.valueOf(list.get(position));
			((ItemViewHolder) holder).textView.setText(str);
			holder.itemView.setBackgroundColor(getRandomColor());
		}
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// list_item_text_hori
		//      list_item_text_rr
		View view = null;
		if (direction == VERTICAL) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_text_rr, parent,false);
		} else {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_text_hori, parent,false);

		}
		//view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		return new ItemViewHolder(view);

	}

	public static class FooterViewHolder extends RecyclerView.ViewHolder {

		public FooterViewHolder(View view) {
			super(view);
		}

	}

	public static class ItemViewHolder extends RecyclerView.ViewHolder {
		TextView textView;

		public ItemViewHolder(View view) {
			super(view);
			textView = (TextView) view.findViewById(R.id.text);
		}

	}

	public static int getRandomColor() {
		// 随机x颜色  
		// 随机颜色  
		Random random = new Random();
		int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
		return ranColor;
	}

}
