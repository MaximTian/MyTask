package datamodel;

/**
 * Created by MaximTian on 2017/4/9.
 */

public class Item {

    private String iName; // 任务名称
    private int iPort; // 头像
    private String iPort_name; // 头像名称
    private String iTime; // 任务完成时间
    private String iProj; // 任务归属

    public Item() {
    }

    public Item(String iName, int iPort, String iPort_name, String iTime, String iProj) {
        this.iName = iName;
        this.iPort = iPort;
        this.iPort_name = iPort_name;
        this.iTime = iTime;
        this.iProj = iProj;
    }

    public String getiName() {
        return iName;
    }

    public int getiPort() {
        return iPort;
    }

    public String getiPort_name() {
        return iPort_name;
    }

    public String getiTime() {
        return iTime;
    }

    public String getiProj() {
        return iProj;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public void setiPort(int iPort) {
        this.iPort = iPort;
    }

    public void setiPort_name(String iPort_name) {
        this.iPort_name = iPort_name;
    }

    public void setiTime(String iTime) {
        this.iTime = iTime;
    }

    public void setiProj(String iProj) {
        this.iProj = iProj;
    }
}