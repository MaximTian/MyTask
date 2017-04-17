package maintask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maximtian.myapptask.R;

import java.util.ArrayList;
import java.util.List;

import DataBase.DBManager;
import DataBase.Dynamic_Item;
import DataBase.Project_Item;
import datamodel.MyDynamicAdapter;
import datamodel.MyUserAdapter;
import datamodel.MyTaskAdapter;
import DataBase.User_Item;
import DataBase.Task_Item;

/**
 * Created by MaximTian on 2017/4/11.
 */

public class Proj_Detail extends Activity {

    private Button number_button;
    private Button dynamic_button;
    private Button task_button;
    private View select_number_View;
    private View select_dynamic_View;

    private Project_Item current_proj;
    private TextView project_users;
    private ArrayList<User_Item> user_items;
    private ArrayList<User_Item> iuser;

    private DBManager dbManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proj_detail_layout);

        dbManager = new DBManager(this);

        getCurrent_Project();
        init_view();
        select_Button();
        init_project_task();
    }

    private void getCurrent_Project() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("name");
        current_proj = dbManager.QueryProject(title);

        String[] user_names = current_proj.getUser_gather().split(" ");
        user_items = new ArrayList<User_Item>();
        for (int i = 0; i < user_names.length; i++) {
            user_items.add(dbManager.QueryUser(user_names[i]));
        }
    }

    private void init_view() {
        TextView project_title = (TextView) findViewById(R.id.proj_detail_title);
        project_title.setText(current_proj.getName()); // 设置标题

        LinearLayout mLinearLayout = (LinearLayout)findViewById(R.id.linearlay_1);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.width = 80;
        lp.height = 80;

        // 设置头像
        for (int i = 0; i < user_items.size(); i++) {
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(user_items.get(i).getImage());
            iv.setLayoutParams(lp);
            mLinearLayout.addView(iv);
        }

        project_users = (TextView) findViewById(R.id.proj_detail_number);
        project_users.setText(String.valueOf(user_items.size()) + "成员"); // 设置成员数量
    }

    private void select_Button() {
        number_button = (Button) findViewById(R.id.selectNumber);
        number_button.setOnClickListener(new ClickEvent());

        dynamic_button = (Button) findViewById(R.id.selectDynnamic);
        dynamic_button.setOnClickListener(new ClickEvent());

        task_button = (Button) findViewById(R.id.selectTask);
        task_button.setOnClickListener(new ClickEvent());
    }

    class ClickEvent implements View.OnClickListener {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.selectNumber: {
                    showNumberDialog(Proj_Detail.this);
                    break;
                }

                case R.id.selectDynnamic: {
                    showDynamicDialog(Proj_Detail.this);
                    break;
                }

                case R.id.selectTask: {
                    showTaskDialog(Proj_Detail.this);
                    break;
                }

            }
        }
    }

    private void showNumberDialog(Context context) { // 显示成员信息
        LayoutInflater inflater = LayoutInflater.from(this);
        select_number_View = inflater.inflate(R.layout.dialog_layout, null);
        ListView lv = (ListView) select_number_View.findViewById(R.id.dialog_listview);
        final MyUserAdapter people_data = get_people_data();
        lv.setAdapter(people_data);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.teammate);
        builder.setTitle("选择成员");
        builder.setView(select_number_View);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ArrayList<Integer> states = people_data.getItemState(); // 获取状态
                        String proj_user = current_proj.getCreator();
                        for (int i = 1; i < states.size(); i++) {
                            if (states.get(i) == 1) {
                                proj_user += " " + iuser.get(i).getName();
                            }
                        }
                        current_proj.setUser_gather(proj_user);
                        // updateProjectData(int id, String name, String creator, String time, String users)
                        dbManager.updateProjectData(current_proj.getId(), current_proj.getName(),
                                current_proj.getCreator(), current_proj.getTime(), current_proj.getUser_gather());
                    }
                });
        builder.show();
    }

    private MyUserAdapter get_people_data() { // 获取成员信息
        iuser = new ArrayList<User_Item>();
        String current_user = current_proj.getUser_gather();
        Toast.makeText(Proj_Detail.this, current_user, Toast.LENGTH_SHORT).show();
        List<User_Item> users = dbManager.getAllUser();
        for (int i = 0; i < users.size(); i++) {
/*            if (current_user.indexOf(users.get(i).getName()) == -1) {
                users.get(i).setState(1);
            } */
            iuser.add(users.get(i));
        }
        MyUserAdapter myAdapter = new MyUserAdapter(iuser, Proj_Detail.this);
        return myAdapter;
    }

    private void showDynamicDialog(Context context) { // 显示动态信息
        LayoutInflater inflater = LayoutInflater.from(this);
        select_dynamic_View = inflater.inflate(R.layout.dialog_layout, null);
        ListView lv = (ListView) select_dynamic_View.findViewById(R.id.dialog_listview);
        lv.setAdapter(get_dynamic_data());

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.dynamic);
        builder.setTitle("动态信息");
        builder.setView(select_dynamic_View);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        builder.show();
    }

    private MyDynamicAdapter get_dynamic_data() { // 获取动态信息
        ArrayList<Dynamic_Item> data = new ArrayList<Dynamic_Item>();
        data.add(new Dynamic_Item(R.drawable.animal2, "Jack", "创建了项目", "洗澡", "04-04 12:54"));
        data.add(new Dynamic_Item(R.drawable.animal2, "Jack", "创建了任务", "睡觉", "04-04 13:06"));
        data.add(new Dynamic_Item(R.drawable.animal2, "Jack", "修改了任务", "睡觉", "04-05 13:06"));
        data.add(new Dynamic_Item(R.drawable.animal1, "Maxim", "完成了任务", "睡觉", "04-06 17:28"));

        MyDynamicAdapter myAdapter = new MyDynamicAdapter(data, Proj_Detail.this);
        return myAdapter;
    }

    private void init_project_task() { // 加载任务清单
        ListView lv = (ListView) findViewById(R.id.proj_task_listview);
        lv.setBackgroundColor(getResources().getColor(R.color.white));
        lv.setAdapter(get_task_listview());
    }

    private MyTaskAdapter get_task_listview() { // 获取任务清单
        ArrayList<Task_Item> data = new ArrayList<Task_Item>();
        data.add(new Task_Item("嘉文", "Jack", "04-04 12:54", "皇子", false));
        data.add(new Task_Item("刀锋", "Jack", "04-04 12:54", "刷碗", false));
        data.add(new Task_Item("德玛西亚", "Jack", "04-04 12:54", "盖伦", false));
        data.add(new Task_Item("提莫", "Jack", "04-04 12:54", "法师", false));

        MyTaskAdapter myAdapter = new MyTaskAdapter(data, Proj_Detail.this);
        return myAdapter;
    }

/*    private void showTaskDialog(final Context context) { // 添加任务信息
        final MyDialog dialog = new MyDialog(context);
      // dialog.setClicklistener(new MyDialog.ClickListenerInterface() {
      //     @Override
      //     public void doConfirm() {
      //         Toast.makeText(context,"点击了--确定--按钮",Toast.LENGTH_LONG).show();
      //     }

      //     @Override
      //     public void doCancel() {
      //         Toast.makeText(context,"点击了--取消--按钮",Toast.LENGTH_LONG).show();
      //     }
      // });
        dialog.setOnConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Proj_Detail.this,"点击了--确定--按钮",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog.setOnCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Proj_Detail.this,"点击了--取消--按钮",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog.setOnTimeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerView.Builder(Proj_Detail.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        dialog.setTime(getTime(date));
                    }
                }).setType(TimePickerView.Type.MONTH_DAY_HOUR_MIN)
                        .build();
                pvTime.show();
            }
        });
        dialog.show();
    } */

    private void showTaskDialog(Context context) { // 添加任务信息
        Intent start_main = new Intent(context, Task_Add_Activity.class);
        startActivity(start_main);
    }

}
