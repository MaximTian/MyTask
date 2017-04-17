package DataBase;

/**
 * Created by MaximTian on 2017/4/15.
 */

public class Project_Item {
    private int id;
    private String name; // 名称
    private String creator; // 创建人
    private String time; // 创建时间
    private String user_gather;

    public Project_Item(int id, String name, String creator, String time, String users) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.time = time;
        this.user_gather = users;
    }

    public Project_Item() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public String getTime() {
        return time;
    }

    public String getUser_gather() {
        return user_gather;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUser_gather(String str) {
        this.user_gather = str;
    }
}
