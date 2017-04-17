package maintask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.maximtian.myapptask.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataBase.DBManager;
import DataBase.Dynamic_Item;
import datamodel.MyDynamicActivityAdapter;
import datamodel.MyDynamicAdapter;

/**
 * Created by MaximTian on 2017/4/9.
 */

public class Dynamic_Activity extends Activity {

    private DBManager dbManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_layout);

        dbManager = new DBManager(this);
        init_view();
    }

    public void init_view() {
        ListView lv = (ListView) findViewById(R.id.dynamic_listview);
        lv.setAdapter(get_dynamic_data());
    }

    private MyDynamicActivityAdapter get_dynamic_data() { // 获取动态信息
        ArrayList<Dynamic_Item> data = new ArrayList<Dynamic_Item>();
        List<Dynamic_Item> dynamics = dbManager.getAllDynamic();
        for (int i = 0; i < dynamics.size(); i++) {
                data.add(dynamics.get(i));
        }

        MyDynamicActivityAdapter myAdapter = new MyDynamicActivityAdapter(data, Dynamic_Activity.this);
        return myAdapter;
    }
}
