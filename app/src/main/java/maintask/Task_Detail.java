package maintask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maximtian.myapptask.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataBase.DBManager;
import DataBase.Task_Item;
import Public.Public_Value;
import datamodel.MyListView;

/**
 * Created by MaximTian on 2017/4/10.
 */

public class Task_Detail extends Activity {

    private MyListView remark_lv;
    private Task_Item current_task; // 当前任务
    private DBManager dbManager;

    private TextView task_Belong;
    private TextView task_name;
    private TextView responser;
    private ImageView responser_img;
    private TextView task_time;
    private CheckBox mycheckbox;

    private Button cancelBt;
    private Button confirmBt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_layout);

        dbManager = new DBManager(this);

        getCurrent_task();
        init_view();
        init_remark();
        init_button();
    }

    private void getCurrent_task() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
//        Toast.makeText(Task_Detail.this, title, Toast.LENGTH_SHORT).show();
        current_task = dbManager.QueryTask(title);
    }

    private void init_view() {
        task_Belong = (TextView) findViewById(R.id.task_belong);
        task_name = (TextView) findViewById(R.id.task_detail_name);
        responser = (TextView) findViewById(R.id.detail_responsor);
        responser_img = (ImageView) findViewById(R.id.detail_portrait);
        task_time = (TextView) findViewById(R.id.detail_time);
        mycheckbox = (CheckBox) findViewById(R.id.checkbox_touch);

       task_Belong.setText(current_task.getProject_Belong());
       task_name.setText(current_task.getName());
       responser.setText(current_task.getResponser());
       task_time.setText(current_task.getTime());
       responser_img.setImageResource(
               dbManager.QueryUser(current_task.getResponser()).getImage() );
    }

    // 让ListView随着整个界面滑动
    private void init_remark() {
        remark_lv = (MyListView) findViewById(R.id.remark_listview);
        SimpleAdapter adapter = new SimpleAdapter(this, get_remark_Data(),
                R.layout.remark_item, new String[]{"au_img", "au_name", "rm_time", "remark"},
                new int[]{R.id.author_image, R.id.author_name, R.id.remark_time, R.id.remark_info});
        remark_lv.setAdapter(adapter);

        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, remark_lv);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = remark_lv.getLayoutParams();
        params.height = totalHeight + (remark_lv.getDividerHeight() * (remark_lv.getCount() - 1));
        remark_lv.setLayoutParams(params);
        remark_lv.setEnabled(false);
    }

    private List<Map<String, Object>> get_remark_Data() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("au_img", R.drawable.animal2);
        map.put("au_name", "Joke");
        map.put("rm_time", "3天前");
        map.put("remark", "balalallallalalalasllalalslladakbsxajlalsdlalalallalalaaalallalalalalalalalallalalallalalalsallasxknasashd");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("au_img", R.drawable.animal3);
        map.put("au_name", "Mike");
        map.put("rm_time", "3天前");
        map.put("remark", "balalallallalalalasllalalslladakbsxajlalsdlalalallalalaaalallalalalalalalalallalalallalalalsallasxknasashd");
        list.add(map);

        return list;
    }

    private void init_button() {
        cancelBt = (Button) findViewById(R.id.task_detail_cancel);
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirmBt = (Button) findViewById(R.id.task_detail_confirm);
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // updateTaskData(int id, String name, String responser, String time, String project, int state)
                int id = current_task.getId();
                String name = current_task.getName();
                String res = current_task.getResponser();
                String time = getfinish_time();
                String pro = current_task.getProject_Belong();
                if(!mycheckbox.isChecked()) {
                    current_task.setState(1);
                }
                int sta = current_task.getState();
                dbManager.updateTaskData(id, name, res, time, pro, sta);

                Public_Value.AddDynamic_Option(Task_Detail.this, 2, name, pro); // 记录动态
                Toast.makeText(Task_Detail.this, "任务完成", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private String getfinish_time() { // 设置完成任务时间
        SimpleDateFormat sDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        return sDateFormat.format(new java.util.Date());
    }

}
