package maintask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.maximtian.myapptask.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MaximTian on 2017/3/26.
 */

public class Proj_Activity extends Activity {

    private ListView proj_lv;
    private SimpleAdapter simpleAda;
    private List<Map<String, Object>> list;
    private Button AddProj;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proj_layout);

        proj_lv = (ListView) findViewById(R.id.proj_listview); // 加载项目列表
        AddProj = (Button) findViewById(R.id.addProj);

        simpleAda = new SimpleAdapter(this, getData(),
                R.layout.proj_listview_item, new String[]{"image", "name"},
                new int[]{R.id.proj_image, R.id.proj_name});  // 设计项目适配器

        proj_lv.setAdapter(simpleAda); // 显示项目名单
        // 给Item添加点击事件
        proj_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        AddProj.setOnClickListener(new Button.OnClickListener() { //创建监听
            public void onClick(View v) {
      //          Toast.makeText(getApplicationContext(), "click_button", Toast.LENGTH_SHORT).show();
                Intent change_act = new Intent(Proj_Activity.this, Proj_Add.class);
                startActivity(change_act);
            }
        });

    }

    private List<Map<String, Object>> getData() {
        list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("image", R.drawable.project_img);
        map.put("name", "吃饭");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.project_img);
        map.put("name", "洗澡");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.project_img);
        map.put("name", "睡觉");
        list.add(map);

        return list;
    }

}
