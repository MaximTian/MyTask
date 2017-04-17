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

/**
 * Created by MaximTian on 2017/4/7.
 */

public class Login_Activity extends Activity {

    private EditText ed_account;
    private EditText ed_password;
    private String account;
    private String password;
    private Button loginBtn;
    private Button queryBtn;
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
        queryBtn = (Button) findViewById(R.id.query);
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

        queryBtn.setOnClickListener(new Button.OnClickListener() { // 登录
            public void onClick(View v) {
                String ac = ed_account.getText().toString();
                String ps = ed_password.getText().toString();
                if (ac.isEmpty() || ps.isEmpty()) {
                    Toast.makeText(Login_Activity.this, "输入不合法", Toast.LENGTH_SHORT).show();
                } else {
                    User_Item user = dbManager.QueryUser(ac);
                    Toast.makeText(Login_Activity.this, String.valueOf(dbManager.getAllUser().size()), Toast.LENGTH_SHORT).show();
                    if (dbManager.QueryUser(ac) == null) {
                        Toast.makeText(Login_Activity.this, "该用户不存在", Toast.LENGTH_SHORT).show();
                    } else if (true == ps.equals(user.getPassword()) ) {
                        Toast.makeText(Login_Activity.this, "用户存在", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login_Activity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}
