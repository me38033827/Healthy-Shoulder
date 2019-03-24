package com.example.healthyshoulder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zh on 2017/3/5.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String CREATE_USER="create table user(" +
            "id integer primary key autoincrement," +
            "username text," +
            "password text," +
            "sex text," +
            "birth_year integer," +
            "birth_month integer," +
            "province text," +
            "nation text," +
            "career text)";

    public static final String CREATE_RECORD="create table record("+
            "id integer primary key autoincrement," +
            "user_id integer,"+
            "index1 real," +
            "index2 real," +
            "index3 real," +
            "index4 real," +
            "index5 real," +
            "index6 real," +
            "syc_date text)";



    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_RECORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
