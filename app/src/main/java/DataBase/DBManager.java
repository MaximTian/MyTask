package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaximTian on 2016/7/14.
 */
public class DBManager {
    DBHelper dbHelper;
    Context context;

    public DBManager(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public void addUserSQL(User_Item user) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("userId", user.getUser_id());
            values.put("userName", user.getName());
            values.put("userPassword", user.getPassword());
            values.put("userState", user.getState());
            values.put("userImage", user.getImage());
            db.insert("users", null, values);
            Log.v("tmx", "succeed");
            db.close();
        } catch (Exception e) {
        }
    }

    public List<User_Item> getAllUser() {
        List<User_Item> list = new ArrayList<User_Item>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query("users", null, null, null, null, null, null);
            User_Item user = null;
            while (cursor.moveToNext()) {
                user = new User_Item();
                user.setUser_id(cursor.getInt(cursor.getColumnIndex("userId")));
                user.setName(cursor.getString(cursor.getColumnIndex("userName")));
                user.setPassword(cursor.getString(cursor.getColumnIndex("userPassword")));
                user.setImage(cursor.getInt(cursor.getColumnIndex("userImage")));
                user.setState(cursor.getInt(cursor.getColumnIndex("userState")));
                list.add(user);
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
        }
        return list;
    }

    public void updateUserData(int id, String name, String password, int state, int image) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("userName", name);
            values.put("userPassword", password);
            values.put("userState", state);
            values.put("userImage", image);
            db.update("users", values, "userId=" + id, null);
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public User_Item QueryUser(String name) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        User_Item user = null;
        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.rawQuery(
                    "select userId,userName,userPassword,userState,userImage from users where userName=?",
                    new String[] { name });
            if (cursor.moveToNext()) {
                user = new User_Item();
                user.setUser_id(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setState(cursor.getInt(3));
                user.setImage(cursor.getInt(4));
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
        }
        return user;
    }

    /* String sql2 = "Create Table If Not Exists projects(projectId integer primary key,"
                + "projectName varchar(100),"
                        + "projectCreator varchar(100),"
                        + "projectTime varchar(100))";
        db.execSQL(sql2); */

    public void addProjectSQL(Project_Item proj) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("projectId", proj.getId());
            values.put("projectName", proj.getName());
            values.put("projectCreator", proj.getCreator());
            values.put("projectTime", proj.getTime());
            values.put("projectUsers", proj.getUser_gather());
            db.insert("projects", null, values);
            Log.v("tmx", "succeed_proj");
            db.close();
        } catch (Exception e) {
        }
    }

    public List<Project_Item> getAllProject() {
        List<Project_Item> list = new ArrayList<Project_Item>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query("projects", null, null, null, null, null, null);
            Project_Item proj = null;
            while (cursor.moveToNext()) {
                proj = new Project_Item();
                proj.setId(cursor.getInt(cursor.getColumnIndex("projectId")));
                proj.setName(cursor.getString(cursor.getColumnIndex("projectName")));
                proj.setCreator(cursor.getString(cursor.getColumnIndex("projectCreator")));
                proj.setTime(cursor.getString(cursor.getColumnIndex("projectTime")));
                proj.setUser_gather(cursor.getString(cursor.getColumnIndex("projectUsers")));
                list.add(proj);
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
        }
        return list;
    }

    public void updateProjectData(int id, String name, String creator, String time, String users) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("projectName", name);
            values.put("projectCreator", creator);
            values.put("projectTime", time);
            values.put("projectUsers", users);
            db.update("projects", values, "projectId=" + id, null);
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public Project_Item QueryProject(String name) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        Project_Item proj = null;
        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.rawQuery(
                    "select projectId,projectName,projectCreator,projectTime,projectUsers" +
                            " from projects where projectName=?",
                    new String[] { name });
            if (cursor.moveToNext()) {
                proj = new Project_Item();
                proj.setId(cursor.getInt(0));
                proj.setName(cursor.getString(1));
                proj.setCreator(cursor.getString(2));
                proj.setTime(cursor.getString(3));
                proj.setUser_gather(cursor.getString(4));
                Log.v("tmx", "succeed1111");
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
        }
        return proj;
    }

}
