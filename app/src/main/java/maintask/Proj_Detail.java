package maintask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maximtian.myapptask.R;

/**
 * Created by MaximTian on 2017/4/11.
 */

public class Proj_Detail extends Activity {

    private Context mContext;
    private LinearLayout mLinearLayout;
    private LinearLayout.LayoutParams mLayoutParams;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proj_detail_layout);

        init_number();
    }

    private void init_number() {
        mLinearLayout = (LinearLayout)findViewById(R.id.linearlay_1);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.width = 80;
        lp.height = 80;
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.animal1);
        iv.setLayoutParams(lp);
        mLinearLayout.addView(iv);

        iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.animal2);
        iv.setLayoutParams(lp);
        mLinearLayout.addView(iv);
    }

}
