package datamodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maximtian.myapptask.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaximTian on 2017/4/8.
 */

public class MyExpandAdapter extends BaseExpandableListAdapter {

    private ArrayList<Group> gData;
    private ArrayList<ArrayList<Item>> iData;
    private Context mContext;

    public MyExpandAdapter(ArrayList<Group> gData, ArrayList<ArrayList<Item>> iData, Context mContext) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }

    @Override
    public Group getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Item getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.expandlist_group, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_group_name);
            groupHolder.arrow = (ImageView) convertView.findViewById(R.id.iv_arrow);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(gData.get(groupPosition).getgName());

        if(isExpanded){
            groupHolder.arrow.setBackgroundResource(R.drawable.arrow_down);
        }else{
            groupHolder.arrow.setBackgroundResource(R.drawable.arrow_right);
        }
        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.expandlist_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.img_icon = (ImageView) convertView.findViewById(R.id.check_box_c);
            itemHolder.tv_name = (TextView) convertView.findViewById(R.id.task_name_c);
            itemHolder.prot_bg = (ImageView) convertView.findViewById(R.id.portrait_c);
            itemHolder.prot_name = (TextView) convertView.findViewById(R.id.responsor_c);
            convertView.setTag(itemHolder);
        }else{
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.img_icon.setImageResource(iData.get(groupPosition).get(childPosition).getiId());
        itemHolder.tv_name.setText(iData.get(groupPosition).get(childPosition).getiName());

        itemHolder.prot_bg.setImageResource(iData.get(groupPosition).get(childPosition).getiPort());
        itemHolder.prot_name.setText(iData.get(groupPosition).get(childPosition).getiPort_name());
        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ViewHolderGroup{
        private TextView tv_group_name;
        private ImageView arrow;
    }

    private static class ViewHolderItem{
        private ImageView img_icon; // 完成状态
        private TextView tv_name; // 任务名称
        private ImageView prot_bg; // 头像
        private TextView prot_name; // 头像姓名
    }

}
