package maintask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maximtian.myapptask.R;

import java.text.SimpleDateFormat;

import DataBase.DBManager;
import DataBase.Project_Item;
import Public.Public_Value;

/**
 * Created by MaximTian on 2017/4/5.
 */

public class Proj_Add extends Activity {

    private ImageButton back_act;
    private Button confirm_act;
    private TextView s_time;
    private EditText s_title;
    private TextView s_creator;

    private DBManager dbManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proj_add_layout);
        Create_Time();
        s_title = (EditText) findViewById(R.id.set_title);
        s_creator = (TextView) findViewById(R.id.set_creator);

        dbManager = new DBManager(this);

        back_act = (ImageButton) findViewById(R.id.back_arrow);
        back_act.setOnClickListener(new Button.OnClickListener() { // 创建监听
            public void onClick(View v) {
                finish();
            }
        });

        confirm_act = (Button) findViewById(R.id.confirm);
        confirm_act.setOnClickListener(new Button.OnClickListener() { // 创建监听
            public void onClick(View v) {
                String title = s_title.getText().toString();
                String time = s_time.getText().toString();
                String creator = s_creator.getText().toString();
                init_project_id(); // 设置当前id;
                Project_Item proj = new Project_Item(Public_Value.getProject_ID(), title, creator, time, creator);
                if (title.isEmpty()) {
                    Toast.makeText(Proj_Add.this, "项目名为空", Toast.LENGTH_SHORT).show();
                } else {
                    dbManager.addProjectSQL(proj);
                }

                // public static void AddDynamic_Option(Context context, int opt, String aim, String belong)
                Public_Value.AddDynamic_Option(Proj_Add.this, 0, title, title);
                finish();
            }
        });
    }

    private void Create_Time() {
        s_time = (TextView) findViewById(R.id.set_time);

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
        s_time.setText(date);
    }

    private void init_project_id() { // 获取当前项目ID
        Public_Value.setProject_ID(dbManager.getAllProject().size());
    }

}
