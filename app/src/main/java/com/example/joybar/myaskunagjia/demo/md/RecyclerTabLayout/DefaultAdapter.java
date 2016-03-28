package com.example.joybar.myaskunagjia.demo.md.RecyclerTabLayout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.utils.ScreenUtils;

import java.util.List;

/**
 * Created by joybar on 3/8/16.
 */
public class DefaultAdapter  extends RecyclerTabLayout.Adapter<DefaultAdapter.ViewHolder> {

    private List<Model> mListData ;

    public DefaultAdapter(List<Model> mListData) {
        this.mListData = mListData;
    }


    @Override
    public DefaultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       TextView tabTextView = new TextView(parent.getContext());


        tabTextView.setPadding(ScreenUtils.convertDipOrPx(parent.getContext(),10),
                ScreenUtils.convertDipOrPx(parent.getContext(),10),
                ScreenUtils.convertDipOrPx(parent.getContext(),10),
                ScreenUtils.convertDipOrPx(parent.getContext(),10));

        return new ViewHolder(tabTextView);
    }

    @Override
    public void onBindViewHolder(DefaultAdapter.ViewHolder holder, final int position) {

        holder.title.setText(mListData.get(position).getStr());


    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }




    protected RecyclerView.LayoutParams createLayoutParamsForTabs() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.MATCH_PARENT);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        View  itemView;

        public View getItemView() {
            return itemView;
        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public void setItemView(View itemView) {
            this.itemView = itemView;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView;
            this.itemView = itemView;

        }
    }
}