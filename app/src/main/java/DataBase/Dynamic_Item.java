package DataBase;

/**
 * Created by MaximTian on 2017/4/12.
 */

public class Dynamic_Item {

    private int id;
    private String creator; // 名称
    private String opt; // 操作
    private String aim; // 内容
    private String time; // 时间
    private String belong; // 归属

    public Dynamic_Item() {
    }

    public Dynamic_Item(int id, String creator, String opt, String aim, String time, String belong) {
        this.id = id;
        this.creator = creator;
        this.opt = opt;
        this.aim = aim;
        this.time = time;
        this.belong = belong;
    }

    public int getId() {
        return id;
    }

    public String getCreator() {
        return creator;
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

    public String getBelong() {
        return belong;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public void setBelong(String belong) {
        this.belong = belong;
    }
}
