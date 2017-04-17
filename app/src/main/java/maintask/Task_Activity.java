package maintask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.maximtian.myapptask.R;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataBase.DBManager;
import DataBase.Task_Item;
import DataBase.User_Item;
import Public.Public_Value;
import datamodel.Group;
import datamodel.Item;
import datamodel.MyExpandAdapter;
import datamodel.MyTaskAdapter;

/**
 * Created by MaximTian on 2017/3/26.
 */

public class Task_Activity extends Activity {

    private ListView task_lv;
    private List<Task_Item> tasks; // 系统任务总数

    private ExpandableListView expand_lv;
    private MyExpandAdapter myexp_ada;
    private Context mContext;
    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<Item>> iData = null;
    private ArrayList<Item> lsData;

    private DBManager dbManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_layout);

        dbManager = new DBManager(this);

        init_listview();

        expand_lv = (ExpandableListView) findViewById(R.id.expand_listview);
        expand_lv.setGroupIndicator(null);
        getExpand();
    }

    private void init_listview() {
        task_lv = (ListView) findViewById(R.id.task_listview); // 加载项目列表
        final MyTaskAdapter adapter = getData();

        task_lv.setAdapter(adapter); // 显示项目名单
        // 给Item添加点击事件
        task_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent start_main = new Intent(Task_Activity.this, Task_Detail.class);
                start_main.putExtra("title", adapter.getData().get(pos).getName());
                startActivity(start_main);
            }
        });
    }

    private void getExpand() {
        mContext = Task_Activity.this;
        gData = new ArrayList<Group>();
        iData = new ArrayList<ArrayList<Item>>();
        gData.add(new Group("已完成"));

        lsData = new ArrayList<Item>();
        tasks = dbManager.getAllTask();
        for (int i = 0; i < tasks.size(); i++) {
            User_Item myUser = dbManager.QueryUser(Public_Value.getCurrent_User());
            Task_Item myTask = tasks.get(i);
            if (myTask.getState() == 1
                    && myTask.getResponser().equals(myUser.getName()) ) { // 如果任务负责人是当前用户才显示
                lsData.add(new Item(myTask.getName(), myUser.getImage(),
                        myUser.getName(), myTask.getTime(), myTask.getProject_Belong()));
            }
        }
        iData.add(lsData);

        myexp_ada = new MyExpandAdapter(gData,iData,mContext);
        expand_lv.setAdapter(myexp_ada);

        expand_lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext, "你点击了：" + iData.get(groupPosition).get(childPosition).getiName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private MyTaskAdapter getData() { // 获取当前任务列表
        ArrayList<Task_Item> data = new ArrayList<Task_Item>();
        tasks = dbManager.getAllTask();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getState() == 0) {
                if (Public_Value.getCurrent_User()
                        .equals(tasks.get(i).getResponser()) ) {
                    data.add(tasks.get(i));
                }
            }
        }
        MyTaskAdapter myAdapter = new MyTaskAdapter(data, Task_Activity.this);
        return myAdapter;
    }
}
