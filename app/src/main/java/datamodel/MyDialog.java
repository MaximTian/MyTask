package datamodel;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maximtian.myapptask.R;

/**
 * Created by MaximTian on 2017/4/13.
 */

public class MyDialog extends Dialog {

    private Context mContext;
    private Button confirmBt;
    private Button cancelBt;
    private EditText etTitle;
    private LinearLayout time_picker;
    private TextView task_time;

    public MyDialog(Context context) {
        super(context, R.style.mydialog_style);
        this.mContext = context;
//        initMyDialog();
    }
/*
    private void initMyDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.task_add_layout, null);
        etTitle = (EditText) view.findViewById(R.id.task_add_title);
        time_picker = (LinearLayout) view.findViewById(R.id.time_picker);
        confirmBt = (Button) view.findViewById(R.id.bt_confirm);
        cancelBt = (Button) view.findViewById(R.id.bt_cancel);
        task_time = (TextView) view.findViewById(R.id.dialog_task_time);

        super.setContentView(view);
    }

    public void setOnConfirmListener(View.OnClickListener listener){
        confirmBt.setOnClickListener(listener);
    }

    public void setOnCancelListener(View.OnClickListener listener){
        cancelBt.setOnClickListener(listener);
    }

    public void setOnTimeListener(View.OnClickListener listener) {
        time_picker.setOnClickListener(listener);
    }

    public void setTime(String date) { // 显示时间
        task_time.setText(date);
    }
*/
}
