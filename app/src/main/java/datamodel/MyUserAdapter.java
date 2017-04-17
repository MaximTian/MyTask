package datamodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maximtian.myapptask.R;

import java.util.ArrayList;

import DataBase.User_Item;

/**
 * Created by MaximTian on 2017/4/12.
 */

public class MyUserAdapter extends BaseAdapter {

    private ArrayList<User_Item> data;
    private Context mContext;

    public MyUserAdapter(ArrayList<User_Item> data, Context mContext) {
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
                    R.layout.number_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.image = (ImageView) convertView.findViewById(R.id.number_image);
            itemHolder.name = (TextView) convertView.findViewById(R.id.number_name);
            itemHolder.checkbox = (CheckBox) convertView.findViewById(R.id.number_checkbox);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.image.setImageResource(data.get(position).getImage());
        itemHolder.name.setText(data.get(position).getName());
        if(data.get(position).getState() == 1){
            itemHolder.checkbox.setChecked(true);
        }else{
            itemHolder.checkbox.setChecked(false);
        }

        // 设置点击事件
        final int index = position;
        itemHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    data.get(index).setState(1);
                }else{
                    data.get(index).setState(0);
                }
            }
        });
        return convertView;
    }

    public ArrayList<Integer> getItemState() { // 获取成员状态
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < data.size(); i++) {
            ans.add(data.get(i).getState());
        }
        return ans;
    }

    private class ViewHolderItem {
        private TextView name; // 名称
        private ImageView image; // 头像
        private CheckBox checkbox; // 状态
    }
}
