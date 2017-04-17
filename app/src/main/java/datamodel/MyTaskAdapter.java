package datamodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maximtian.myapptask.R;

import java.util.ArrayList;

import DataBase.Task_Item;

/**
 * Created by MaximTian on 2017/4/12.
 */

public class MyTaskAdapter extends BaseAdapter {

    private ArrayList<Task_Item> data;
    private Context mContext;

    public MyTaskAdapter(ArrayList<Task_Item> data, Context mContext) {
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
                    R.layout.task_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.name = (TextView) convertView.findViewById(R.id.task_name);
            itemHolder.responsor = (TextView) convertView.findViewById(R.id.responsor);
            itemHolder.time = (TextView) convertView.findViewById(R.id.task_time);
            itemHolder.project = (TextView) convertView.findViewById(R.id.proj_belong);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.name.setText(data.get(position).getName());
        itemHolder.responsor.setText(data.get(position).getResponser());
        itemHolder.time.setText(data.get(position).getTime());
        itemHolder.project.setText(data.get(position).getProject());
        return convertView;
    }

    private class ViewHolderItem {
        private TextView name; // 名称
        private TextView responsor; // 负责人
        private TextView time; // 时间
        private TextView project; // 归属
    }
}