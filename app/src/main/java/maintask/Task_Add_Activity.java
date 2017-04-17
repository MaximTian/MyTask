package maintask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.maximtian.myapptask.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by MaximTian on 2017/4/14.
 */

public class Task_Add_Activity extends Activity {

    private TextView people_tv;
    private LinearLayout peoplepicker_ll; // 选择人员s

    private TextView time_tv;
    private LinearLayout timepicker_ll; // 选择时间
    private ArrayList<String> peopleItem;

    private Button bt_confirm;
    private Button bt_cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_add_layout);

        init_peoplepicker();
        init_timepicker();

        bt_confirm = (Button) findViewById(R.id.bt_confirm);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_cancel = (Button) findViewById(R.id.bt_confirm);
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
        for (int i = 0; i < 5; i++) {
            peopleItem.add(new String("No.ABC12345 " + String.valueOf(i) ));
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

}
