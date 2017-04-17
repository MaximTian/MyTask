package DataBase;

/**
 * Created by MaximTian on 2017/4/12.
 */

public class Dynamic_Item {

    private int image; // 头像
    private String name; // 名称
    private String opt; // 操作
    private String aim; // 内容
    private String time; // 时间

    public Dynamic_Item() {
    }

    public Dynamic_Item(int image, String name, String opt, String aim, String time) {
        this.name = name;
        this.image = image;
        this.opt = opt;
        this.aim = aim;
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getOpt() {
        return opt;
    }

    public String getAim() {
        return aim;
    }

    public String getTime() {
        return time;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
