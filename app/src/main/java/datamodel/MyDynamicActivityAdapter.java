package datamodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maximtian.myapptask.R;

import java.util.ArrayList;

import DataBase.DBManager;
import DataBase.Dynamic_Item;
import DataBase.User_Item;

/**
 * Created by MaximTian on 2017/4/17.
 */

public class MyDynamicActivityAdapter extends BaseAdapter {

    private ArrayList<Dynamic_Item> data;
    private Context mContext;

    public MyDynamicActivityAdapter(ArrayList<Dynamic_Item> data, Context mContext) {
        this.data =data;
        this.mContext = mContext;
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
        ViewHolderItem itemHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.dynamic_activity_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.image = (ImageView) convertView.findViewById(R.id.dynamic_image);
            itemHolder.name = (TextView) convertView.findViewById(R.id.dynamic_name);
            itemHolder.opt = (TextView) convertView.findViewById(R.id.dynamic_option);
            itemHolder.aim = (TextView) convertView.findViewById(R.id.dynamic_aim);
            itemHolder.time = (TextView) convertView.findViewById(R.id.dynamic_time);
            itemHolder.belong = (TextView) convertView.findViewById(R.id.dynamic_belong);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        User_Item myUser = (new DBManager(mContext)).QueryUser(data.get(position).getCreator());
        itemHolder.image.setImageResource(myUser.getImage());
        itemHolder.name.setText(myUser.getName());
        itemHolder.opt.setText(data.get(position).getOpt());
        itemHolder.aim.setText(data.get(position).getAim());
        itemHolder.time.setText(data.get(position).getTime());
        itemHolder.belong.setText(data.get(position).getBelong());
        //       Toast.makeText(mContext, data.get(position).getCreator(), Toast.LENGTH_SHORT).show();
        return convertView;
    }

    private class ViewHolderItem {
        private ImageView image; // 头像
        private TextView name; // 名称
        private TextView opt; // 操作
        private TextView aim; // 内容
        private TextView time; // 时间
        private TextView belong; // 归属
    }
}
