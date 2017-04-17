package Public;

import com.example.maximtian.myapptask.R;

/**
 * Created by MaximTian on 2017/4/15.
 */

public class Public_Value {

    private static String user = "Maxim"; // 当前用户

    private static int User_ID = 0; // 用户ID
    private static int[] Image_ID = {R.drawable.animal1, R.drawable.animal2, R.drawable.animal3, R.drawable.animal4, R.drawable.animal5}; // 用户头像
    public static int[] Project_Img = {R.drawable.project_img1,
            R.drawable.project_img2,
            R.drawable.project_img3,
            R.drawable.project_img4,
            R.drawable.project_img5,
            R.drawable.project_img6,
            R.drawable.project_img7,
            R.drawable.project_img8
    };

    private static String current_proj_name;

    private static int Project_ID = 0; // 项目ID
    public static int getUser_ID() {
        User_ID++;
        return User_ID - 1;
    }

    public static int getImage_ID() {
        return Image_ID[User_ID - 1];
    }

    public static int getProject_ID() {
        Project_ID++;
        return Project_ID - 1;
    }

    public static void setCurrent_User(String str) {
        user = str;
    }

    public static String getCurrent_User() {
        return user;
    }

    public static void setCurrent_proj_name(String name) {
        current_proj_name = name;
    }

    public static String getCurrent_proj_name() {
        return current_proj_name;
    }

}
