package DataBase;

/**
 * Created by MaximTian on 2017/4/12.
 */

public class Task_Item {

    private int id; // 任务id
    private String name; // 任务名称
    private String responser; // 执行人
    private String time; // 截止时间
    private String project; // 归属
    private int state; // 状态

    public Task_Item() {
    }

    public Task_Item(int id, String name, String responser, String time, String project, int state) {
        this.id = id;
        this.name = name;
        this.responser = responser;
        this.time = time;
        this.project = project;
        this.state = state;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getResponser() {
        return responser;
    }

    public String getTime() {
        return time;
    }

    public String getProject_Belong() {
        return project;
    }

    public int getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
         this.name = name;
    }

    public void setResponser(String responser) {
        this.responser = responser;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setProject_Belong(String project) {
        this.project = project;
    }

    public void setState(int state) {
        this.state = state;
    }




}
