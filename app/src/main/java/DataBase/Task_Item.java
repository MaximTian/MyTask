package DataBase;

/**
 * Created by MaximTian on 2017/4/12.
 */

public class Task_Item {

    private String name; // 任务名称
    private String responser; // 执行人
    private String time; // 截止时间
    private String project; // 归属
    private boolean state; // 状态

    public Task_Item() {
    }

    public Task_Item(String name, String responser, String time, String project, boolean state) {
        this.name = name;
        this.responser = responser;
        this.time = time;
        this.project = project;
        this.state = state;
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

    public String getProject() {
        return project;
    }

    public boolean getState() {
        return state;
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

    public void setProject(String project) {
        this.project = project;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}
