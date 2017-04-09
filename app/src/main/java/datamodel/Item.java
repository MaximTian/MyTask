package datamodel;

/**
 * Created by MaximTian on 2017/4/9.
 */

public class Item {

    private int iId;
    private String iName;
    private int iPort;
    private String iPort_name;

    public Item() {
    }

    public Item(int iId, String iName, int iPort, String iPort_name) {
        this.iId = iId;
        this.iName = iName;
        this.iPort = iPort;
        this.iPort_name = iPort_name;
    }

    public int getiId() {
        return iId;
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

    public void setiId(int iId) {
        this.iId = iId;
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
}