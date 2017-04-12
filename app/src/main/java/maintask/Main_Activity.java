package maintask;

import android.os.Bundle;

import android.app.TabActivity;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.example.maximtian.myapptask.R;

/**
 * Created by MaximTian on 2016/5/22.
 */
public class Main_Activity extends TabActivity {

    private RadioGroup main_rg;
    private RadioButton tab_task, tab_project, tab_dynamic, tab_personal;

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        main_rg = (RadioGroup) findViewById(R.id.main_radiogroup);
        tab_task = (RadioButton) findViewById(R.id.tab_task);
        tab_project = (RadioButton) findViewById(R.id.tab_project);
        tab_dynamic = (RadioButton) findViewById(R.id.tab_dynamic);
        tab_personal = (RadioButton) findViewById(R.id.tab_personal);

        tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("tag1").setIndicator("0").setContent(new Intent(this, Task_Activity.class))); // 设置任务界面标签
        tabHost.addTab(tabHost.newTabSpec("tag2").setIndicator("1").setContent(new Intent(this, Proj_Activity.class))); // 设置项目界面标签
        tabHost.addTab(tabHost.newTabSpec("tag3").setIndicator("2").setContent(new Intent(this, Dynamic_Activity.class))); // 设置动态界面标签
        tabHost.addTab(tabHost.newTabSpec("tag4").setIndicator("3").setContent(new Intent(this, Task_Activity.class))); // 设置个人界面标签

        checkListener checkradio = new checkListener();
        main_rg.setOnCheckedChangeListener(checkradio);
    }

    public class checkListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int flagId) {
            switch (flagId) {
                case R.id.tab_task:
                    tabHost.setCurrentTab(0);  // 任务界面
                    break;
                case R.id.tab_project:
                    tabHost.setCurrentTab(1);  // 项目界面
                    break;
                case R.id.tab_dynamic:
                    tabHost.setCurrentTab(2);  // 动态界面
                    break;
                case R.id.tab_personal:
                    tabHost.setCurrentTab(3);  // 个人界面
                    break;
            }
        }

    }

}