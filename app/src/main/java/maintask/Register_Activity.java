package maintask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maximtian.myapptask.R;

/**
 * Created by MaximTian on 2017/4/7.
 */

public class Register_Activity extends Activity {
    private Button registerBtn;
    private EditText account;
    private EditText password;
    private EditText confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        registerBtn = (Button)findViewById(R.id.register_button);  // 注册确定按钮
        account = (EditText)findViewById(R.id.register_account);  // 获取账户名
        password = (EditText)findViewById(R.id.register_password); // 获取密码
        confirm_password = (EditText)findViewById(R.id.register_confirm); // 再次确认密码

        registerBtn.setOnClickListener(new Button.OnClickListener() { // 注册
            public void onClick(View v) {
                Intent start_main = new Intent(Register_Activity.this, Login_Activity.class);
                startActivity(start_main);
                finish();
            }
        });

    }

    public void listenerRegister(View v) {
        String username = account.getText().toString();
        String password1 = password.getText().toString();
        String confirm = confirm_password.getText().toString();

        if (username.isEmpty()) {
            Toast.makeText(Register_Activity.this, "请输入账户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password1.isEmpty()) {
            Toast.makeText(Register_Activity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirm.equals(password1) == false) {
            Toast.makeText(Register_Activity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}
