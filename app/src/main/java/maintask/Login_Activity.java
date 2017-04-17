package maintask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maximtian.myapptask.R;

import DataBase.DBManager;
import DataBase.User_Item;
import Public.Public_Value;

/**
 * Created by MaximTian on 2017/4/7.
 */

public class Login_Activity extends Activity {

    private EditText ed_account;
    private EditText ed_password;
    private Button loginBtn;
    private TextView registerBtn;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        dbManager = new DBManager(this);

        ed_account = (EditText) findViewById(R.id.account);
        ed_password = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.login);
        registerBtn = (TextView) findViewById(R.id.register);

        loginBtn.setOnClickListener(new Button.OnClickListener() { // 登录
            public void onClick(View v) {
                String ac = ed_account.getText().toString();
                String ps = ed_password.getText().toString();
                if (ac.isEmpty() || ps.isEmpty()) {
                    Toast.makeText(Login_Activity.this, "输入不合法", Toast.LENGTH_SHORT).show();
                } else {
                    User_Item user = dbManager.QueryUser(ac);
                    if (dbManager.QueryUser(ac) == null) {
                        Toast.makeText(Login_Activity.this, "该用户不存在", Toast.LENGTH_SHORT).show();
                    } else if (!user.getPassword().equals(ps)) {
                        Toast.makeText(Login_Activity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login_Activity.this, "成功登陆", Toast.LENGTH_SHORT).show();
                        Intent start_main = new Intent(Login_Activity.this, Main_Activity.class);
                        startActivity(start_main);
                        Public_Value.setCurrent_User(ac);
                        finish();
                    }
                }
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
