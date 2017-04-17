package Public;

import android.content.Context;
import android.util.Log;

import com.example.maximtian.myapptask.R;

import java.text.SimpleDateFormat;

import DataBase.DBManager;
import DataBase.Dynamic_Item;
import maintask.Task_Detail;

/**
 * Created by MaximTian on 2017/4/15.
 */

public class Public_Value {

    private static String current_user = "Maxim"; // 当前用户

    private static int User_ID = 0; // 用户ID
    private static int[] Image_ID = { // 用户头像
            R.drawable.animal1,
            R.drawable.animal2,
            R.drawable.animal3,
            R.drawable.animal4,
            R.drawable.animal5,
            R.drawable.animal6,
            R.drawable.animal7,
            R.drawable.animal8,
            R.drawable.animal9,
    };
    public static int[] Project_Img = { // 项目图片
            R.drawable.project_img1,
            R.drawable.project_img2,
            R.drawable.project_img3,
            R.drawable.project_img4,
            R.drawable.project_img5,
            R.drawable.project_img6,
            R.drawable.project_img7,
            R.drawable.project_img8
    };

    private static String current_proj_name; // 当前项目

    private static int Project_ID = 0; // 项目ID

    private static int Task_ID = 0; // 任务ID

    private static int Dynamic_ID = 0; // 动态信息ID

    public static int getUser_ID() {
        User_ID++;
        return User_ID - 1;
    }

    public static int getImage_ID() {
        return Image_ID[User_ID - 1];
    }

    public static void setCurrent_User(String user) {
        current_user = user;
    }

    public static String getCurrent_User() {
        return current_user;
    }

    public static  void setUser_ID(int id) {
        User_ID = id;
    }

    // projectid信息
    public static  void setProject_ID(int id) {
        Project_ID = id;
    }

    public static int getProject_ID() {
        Project_ID++;
        return Project_ID - 1;
    }

    // taskid信息
    public static  void setTask_ID(int id) {
        Task_ID = id;
    }

    public static int getTask_ID() {
        Task_ID++;
        return Task_ID - 1;
    }

    // 当前项目名称信息
    public static void setCurrent_proj_name(String name) {
        current_proj_name = name;
    }

    public static String getCurrent_proj_name() {
        return current_proj_name;
    }

    // data.add(new Dynamic_Item(R.drawable.animal2, "Jack", "创建了项目", "洗澡", "04-04 12:54"));
    public static void AddDynamic_Option(Context context, int opt, String aim, String belong) {
        DBManager dbManager = new DBManager(context);
        Dynamic_Item dynamic = null;
        Dynamic_ID = dbManager.getAllDynamic().size();
        // Dynamic_Item(int id, String creator, String opt, String aim, String time)
        switch (opt) {
            case 0:
                dynamic = new Dynamic_Item(Dynamic_ID, current_user, "创建了项目", aim, get_time(), belong);
                break;
            case 1:
                dynamic = new Dynamic_Item(Dynamic_ID, current_user, "添加了任务", aim, get_time(), belong);
                break;
            case 2:
                dynamic = new Dynamic_Item(Dynamic_ID, current_user, "完成了任务", aim, get_time(), belong);
                break;
            case 3:
                dynamic = new Dynamic_Item(Dynamic_ID, current_user, "修改了成员名单", aim, get_time(), belong);
                break;
        }
        Log.v("tmx", "add_succeed");
        dbManager.addDynamicSQL(dynamic);
    }

    private static String get_time() { // 设置动态时间
        SimpleDateFormat sDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        return sDateFormat.format(new java.util.Date());
    }

}
