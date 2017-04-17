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

import DataBase.Project_Item;
import Public.Public_Value;

/**
 * Created by MaximTian on 2017/4/15.
 */

public class MyProjectAdapter extends BaseAdapter {
    private ArrayList<Project_Item> data;
    private Context mContext;

    public MyProjectAdapter(ArrayList<Project_Item> data, Context mContext) {
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
                    R.layout.proj_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.name = (TextView) convertView.findViewById(R.id.proj_name);
            itemHolder.image = (ImageView) convertView.findViewById(R.id.proj_image);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.name.setText(data.get(position).getName());
        itemHolder.image.setImageResource(Public_Value.Project_Img[position]);
        return convertView;
    }

    private class ViewHolderItem {
        private TextView name; // 名称
        private ImageView image; // 图片
    }
}
