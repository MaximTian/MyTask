package maintask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.maximtian.myapptask.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DataBase.DBManager;
import DataBase.Project_Item;
import DataBase.Task_Item;
import Public.Public_Value;

/**
 * Created by MaximTian on 2017/4/14.
 */

public class Task_Add_Activity extends Activity {

    private EditText title_ed; // 标题

    private TextView people_tv; // 任务负责人
    private LinearLayout peoplepicker_ll; // 选择人员s

    private TextView time_tv; // 任务时间
    private LinearLayout timepicker_ll; // 选择时间
    private ArrayList<String> peopleItem;

    private Button bt_confirm;
    private Button bt_cancel;

    private DBManager dbManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_add_layout);

        dbManager = new DBManager(this);

        title_ed = (EditText) findViewById(R.id.task_add_title);
        init_peoplepicker();
        init_timepicker();

        bt_confirm = (Button) findViewById(R.id.task_add_confirm);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_task_id(); // 设置当前id
                String title = title_ed.getText().toString();
                String responsor = people_tv.getText().toString();
                String time = time_tv.getText().toString();
                if (title.isEmpty() || responsor.isEmpty() || time.isEmpty()) {
                    Toast.makeText(Task_Add_Activity.this, "信息不全", Toast.LENGTH_SHORT).show();
                } else {
                    Task_Item task = new Task_Item(Public_Value.getTask_ID(), title, responsor, time, Public_Value.getCurrent_proj_name(), 0);
                    dbManager.addTaskSQL(task);

                    Public_Value.AddDynamic_Option(Task_Add_Activity.this, 1, title, Public_Value.getCurrent_proj_name()); // 记录动态信息
                    finish();
                }
            }
        });

        bt_cancel = (Button) findViewById(R.id.task_add_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init_peoplepicker() {
        people_tv = (TextView) findViewById(R.id.dialog_task_people);
        peoplepicker_ll = (LinearLayout) findViewById(R.id.people_picker);

        peopleItem = new ArrayList<String>();
        getPeopleData();

        peoplepicker_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(Task_Add_Activity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        String tx = peopleItem.get(options1);
                        people_tv.setText(tx);
                    }
                }).build();
                pvOptions.setPicker(peopleItem);
                pvOptions.show();
            }
        });
    }

    private void getPeopleData() { // 获取可选人员
        Project_Item currennt_item = dbManager.QueryProject(Public_Value.getCurrent_proj_name());
        String[] users = currennt_item.getUser_gather().split(" ");
        for (int i = 0; i < users.length; i++) {
            peopleItem.add(users[i]);
        }
    }

    private void init_timepicker() {
        time_tv = (TextView) findViewById(R.id.dialog_task_time);
        timepicker_ll = (LinearLayout) findViewById(R.id.time_picker);
        timepicker_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerView.Builder(Task_Add_Activity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        time_tv.setText(getTime(date));
                    }
                }).setType(TimePickerView.Type.MONTH_DAY_HOUR_MIN)
                        .build();
                pvTime.show();
            }
        });
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        return format.format(date);
    }

    private void init_task_id() { // 获取当前任务ID
        Public_Value.setTask_ID(dbManager.getAllTask().size());
    }

}
