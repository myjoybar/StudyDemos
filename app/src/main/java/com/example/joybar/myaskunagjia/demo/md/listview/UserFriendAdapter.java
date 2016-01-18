package com.example.joybar.myaskunagjia.demo.md.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joybar.library_listview.bean.User;
import com.example.joybar.myaskunagjia.R;

import java.util.List;

/**
 * Created by joybar on 15/11/26.
 */
public class UserFriendAdapter extends BaseAdapter {
    private Context ct;
    private List<User> data;

    public UserFriendAdapter(Context ct, List<User> datas) {
        this.ct = ct;
        this.data = datas;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<User> list) {
        this.data = list;
        notifyDataSetChanged();
    }

    public void remove(User user){
        this.data.remove(user);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(ct).inflate(
                    R.layout.item_user_friend, null);
            viewHolder = new ViewHolder();

            viewHolder.name = (TextView) convertView
                    .findViewById(R.id.tv_friend_name);
            viewHolder.avatar = (ImageView) convertView
                    .findViewById(R.id.img_friend_avatar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        User friend = data.get(position);
        final String name = friend.getUsername();


	/*	if (!TextUtils.isEmpty(avatar)) {
			ImageLoader.getInstance().displayImage(avatar, viewHolder.avatar, ImageLoadOptions.getOptions());
		} else {
			viewHolder.avatar.setImageDrawable(ct.getResources().getDrawable(R.drawable.head));
		}
	*/
        viewHolder.avatar.setImageDrawable(ct.getResources().getDrawable(R.drawable.head));
        viewHolder.name.setText(name);


        return convertView;
    }

    static class ViewHolder {

        ImageView avatar;
        TextView name;
    }





}