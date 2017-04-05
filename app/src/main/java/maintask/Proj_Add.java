package maintask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maximtian.myapptask.R;

import java.text.SimpleDateFormat;

/**
 * Created by MaximTian on 2017/4/5.
 */

public class Proj_Add extends Activity {


    private ImageButton back_act;
    private Button confirm_act;
    private TextView s_time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proj_add_layout);

        back_act = (ImageButton) findViewById(R.id.back_arrow);
        back_act.setOnClickListener(new Button.OnClickListener() { // 创建监听
            public void onClick(View v) {
                finish();
            }
        });

        confirm_act = (Button) findViewById(R.id.confirm);
        confirm_act.setOnClickListener(new Button.OnClickListener() { // 创建监听
            public void onClick(View v) {
                finish();
            }
        });

        Create_Time();
    }

    private void Create_Time() {
        s_time = (TextView) findViewById(R.id.set_time);

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
        s_time.setText(date);
    }

}
