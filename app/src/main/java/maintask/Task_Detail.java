package maintask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.maximtian.myapptask.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datamodel.MyListView;

/**
 * Created by MaximTian on 2017/4/10.
 */

public class Task_Detail extends Activity {

    private MyListView remark_lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_layout);

        init_remark();
    }

    // 让ListView随着整个界面滑动
    private void init_remark() {
        remark_lv = (MyListView) findViewById(R.id.remark_listview);
        SimpleAdapter adapter = new SimpleAdapter(this, get_remark_Data(),
                R.layout.remark_item, new String[]{"au_img", "au_name", "rm_time", "remark"},
                new int[]{R.id.author_image, R.id.author_name, R.id.remark_time, R.id.remark_info});
        remark_lv.setAdapter(adapter);

        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, remark_lv);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = remark_lv.getLayoutParams();
        params.height = totalHeight + (remark_lv.getDividerHeight() * (remark_lv.getCount() - 1));
        remark_lv.setLayoutParams(params);
        remark_lv.setEnabled(false);
    }

    private List<Map<String, Object>> get_remark_Data() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("au_img", R.drawable.animal2);
        map.put("au_name", "Joke");
        map.put("rm_time", "3天前");
        map.put("remark", "balalallallalalalasllalalslladakbsxajlalsdlalalallalalaaalallalalalalalalalallalalallalalalsallasxknasashd");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("au_img", R.drawable.animal3);
        map.put("au_name", "Mike");
        map.put("rm_time", "3天前");
        map.put("remark", "balalallallalalalasllalalslladakbsxajlalsdlalalallalalaaalallalalalalalalalallalalallalalalsallasxknasashd");
        list.add(map);

        return list;
    }

}
