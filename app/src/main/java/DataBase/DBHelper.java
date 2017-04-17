package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by MaximTian on 2016/7/14.
 */
public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context) {
        super(context, "task.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建用户数据库
        String sql1 = "Create Table If Not Exists users(userId integer primary key,"
                + "userName varchar(100),"
                + "userPassword varchar(100),"
                + "userState integer,"
                + "userImage integer)";
        db.execSQL(sql1);

        // 创建项目数据库
        String sql2 = "Create Table If Not Exists projects(projectId integer primary key,"
                + "projectName varchar(100),"
                + "projectCreator varchar(100),"
                + "projectTime varchar(100),"
                + "projectUsers varchar(200))";
        db.execSQL(sql2);

        // 创建任务数据库
        String sql3 = "Create Table If Not Exists tasks(taskId integer primary key,"
                + "taskName varchar(100),"
                + "taskResponser varchar(100),"
                + "taskTime varchar(100),"
                + "taskBelong varchar(200),"
                + "taskState integer)";
        db.execSQL(sql3);

        // 创建动态信息数据库
        String sql4 = "Create Table If Not Exists dynamics(dynamicId integer primary key,"
                + "dynamicCreator varchar(100),"
                + "dynamicOption varchar(100),"
                + "dynamicAim varchar(100),"
                + "dynamicBelong varchar(100),"
                + "dynamicTime varchar(100))";
        db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql1 = "DROP TABLE IF EXISTS users";
        db.execSQL(sql1);

        String sql2 = "DROP TABLE IF EXISTS projects";
        db.execSQL(sql2);

        String sql3 = "DROP TABLE IF EXISTS tasks";
        db.execSQL(sql3);

        String sql4 = "DROP TABLE IF EXISTS dynamics";
        db.execSQL(sql4);
        onCreate(db);
    }
}
