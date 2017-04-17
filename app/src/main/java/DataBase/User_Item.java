package DataBase;

/**
 * Created by MaximTian on 2017/4/12.
 */
public class User_Item {

    private int user_id;
    private int image; // 头像
    private String name; // 名称
    private String password; // 密码
    private int state; // 是否选中

    public User_Item() {
    }

    public User_Item(int id, String name, String password, int state, int image) {
        this.user_id = id;
        this.name = name;
        this.image = image;
        this.state = state;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getState() {
        return state;
    }

    public void setUser_id(int id) {
        user_id = id;
    }

    public void setPassword(String str) {
        password = str;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setState(int s) {
        this.state = s;
    }
}
