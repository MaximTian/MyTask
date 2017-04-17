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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql1 = "DROP TABLE IF EXISTS users";
        db.execSQL(sql1);

        String sql2 = "DROP TABLE IF EXISTS projects";
        db.execSQL(sql2);
        onCreate(db);
    }
}
