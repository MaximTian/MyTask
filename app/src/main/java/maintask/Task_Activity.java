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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datamodel.Group;
import datamodel.Item;
import datamodel.MyExpandAdapter;

/**
 * Created by MaximTian on 2017/3/26.
 */

public class Task_Activity extends Activity {

    private ListView task_lv;
    private SimpleAdapter simpleAda;
    private List<Map<String, Object>> list;

    private ExpandableListView expand_lv;
    private MyExpandAdapter myexp_ada;
    private Context mContext;
    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<Item>> iData = null;
    private ArrayList<Item> lsData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_layout);

        init_listview();

        expand_lv = (ExpandableListView) findViewById(R.id.expand_listview);
        expand_lv.setGroupIndicator(null);
        getExpand();
    }

    private void init_listview() {
        task_lv = (ListView) findViewById(R.id.task_listview); // 加载项目列表
        simpleAda = new SimpleAdapter(this, getData(),
                R.layout.task_item, new String[]{"name", "portrait", "responsor", "time", "proj_be"},
                new int[]{R.id.task_name, R.id.portrait, R.id.responsor, R.id.task_time, R.id.proj_belong});  // 设计项目适配器
        task_lv.setAdapter(simpleAda); // 显示项目名单
        // 给Item添加点击事件
        task_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent start_main = new Intent(Task_Activity.this, Task_Detail.class);
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
        lsData.add(new Item("剑圣", R.drawable.animal1, "Maxim", "2017-04-04 21:00", "Table"));
        lsData.add(new Item("德莱文", R.drawable.animal1, "Maxim", "2017-04-05 02:00", "Desk"));
        lsData.add(new Item("男枪", R.drawable.animal1, "Maxim", "2017-04-06 21:00", "Table"));
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

    private List<Map<String, Object>> getData() {
        list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "吃饭");
        map.put("portrait", R.drawable.animal1);
        map.put("responsor", "Maxim");
        map.put("time", "2017-04-07 21:00");
        map.put("proj_be", "Table");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "洗澡");
        map.put("portrait", R.drawable.animal2);
        map.put("responsor", "Jack");
        map.put("time", "2017-04-07 22:00");
        map.put("proj_be", "Desk");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "睡觉");
        map.put("portrait", R.drawable.animal3);
        map.put("responsor", "Rose");
        map.put("time", "2017-04-07 23:00");
        map.put("proj_be", "Homework");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "喝水");
        map.put("portrait", R.drawable.animal4);
        map.put("responsor", "Tom");
        map.put("time", "2017-04-08 1:00");
        map.put("proj_be", "Fire");
        list.add(map);

        return list;
    }
}
