package maintask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maximtian.myapptask.R;

/**
 * Created by MaximTian on 2017/4/7.
 */

public class Login_Activity extends Activity {

    private EditText ed_account;
    private EditText ed_password;
    private String account;
    private String password;
    private Button loginBtn;
    private TextView registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        ed_account = (EditText) findViewById(R.id.account);
        ed_password = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.login);
        registerBtn = (TextView) findViewById(R.id.register);

        loginBtn.setOnClickListener(new Button.OnClickListener() { // 登录
            public void onClick(View v) {
                Intent start_main = new Intent(Login_Activity.this, Main_Activity.class);
                startActivity(start_main);
                finish();
            }
        });

        registerBtn.setOnClickListener(new Button.OnClickListener() { // 注册
            public void onClick(View v) {
                Intent start_main = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(start_main);
                finish();
            }
        });

    }

}
