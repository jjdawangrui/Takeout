package com.itheima.takeout.model.dao;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;


import com.itheima.takeout.model.dao.bean.UserBean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Teacher on 2016/9/2.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASENAME = "itheima.db";
    private static final int DATABASEVERSION = 1;

    private DBHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    private static DBHelper instance;

    public static DBHelper getInstance(Context context) {

        if (instance == null) {
            instance = new DBHelper(context);
        }

        return instance;

    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // TODO 表的创建
        try {
            TableUtils.createTable(connectionSource, UserBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // TODO 表的更新
    }
}
