package maintask;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maximtian.myapptask.R;

import DataBase.DBManager;
import DataBase.User_Item;
import Public.Public_Value;

/**
 * Created by MaximTian on 2017/4/17.
 */

public class Personal_Activity extends Activity {

    private TextView name_tv;
    private ImageView img_iv;

    private DBManager dbManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_layout);

        dbManager = new DBManager(this);
        init_view();
    }

    private void init_view() {
        img_iv = (ImageView) findViewById(R.id.personal_img);
        name_tv = (TextView) findViewById(R.id.personal_name);

        User_Item user = dbManager.QueryUser(Public_Value.getCurrent_User());
        img_iv.setImageResource(user.getImage());
        name_tv.setText(user.getName());
    }

}
