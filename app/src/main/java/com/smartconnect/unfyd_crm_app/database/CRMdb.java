package com.smartconnect.unfyd_crm_app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import androidx.annotation.Nullable;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

public class CRMdb extends SQLiteOpenHelper {
    private static String DBNAME = "unfydCrmdatabase.db";
    private static int VERSION = 7;
    public static final String ROW_ID = "_id";
    public static final String CONTACTNAME = "Contact_Name";
    public static final String OPPORTUNITY = "Opportunity";
    public static final String FLAG = "Flag";
    public static final String REMINDERDATE = "ReminderDate";
    public static final String NEXTSTEP = "NextStep";

    public static final String TABLE_CONTACTS = "Contact_list";
    public static final String TABLE_CONTACTS_ID = "contacts_id";
    public static final String  FULLNAME = "fullname";
    public static final String MOBILENUMBER = "mobilenumber";

    public static final String TABLE_TASK = "task_list";
    public static final String TABLE_TASK_ID = "task_id";
    public static final String  SUBJECT = "subject";
    public static final String DESCRIPION = "description";
    public static final String DATE = "taskdate";
    public static final String ASSIGN = "assign";
    public static final String STATUS = "status";
    private static CRMdb instance;



    long row;
    private SQLiteDatabase mDB;
    public static final String DATABASE_TABLE = "opportunity";


    public CRMdb( Context context) {
        super(context, DBNAME, null, VERSION);
        this.mDB = getWritableDatabase("Smart@123");
    }
    static public synchronized CRMdb getInstance(Context context) {
        if (instance == null) {
            instance = new CRMdb(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + DATABASE_TABLE + " ( " +
                ROW_ID + " integer primary key autoincrement not null, " +
                CONTACTNAME + " text , " +
                OPPORTUNITY + " text , " +
                FLAG + " text , " +
                REMINDERDATE + " text , " +
                NEXTSTEP + " text " +
                " ) ";

        String CREATE_TABLE_CONTACTS = "CREATE TABLE " + TABLE_CONTACTS + "("
                + TABLE_CONTACTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +  FULLNAME + " text , " +
                MOBILENUMBER + " text " +
                " ) ";
        String CREATE_TABLE_TASKS = "CREATE TABLE " + TABLE_TASK + "("
                +  TABLE_TASK_ID + " integer primary key autoincrement not null, " +
                SUBJECT + " text , " +
                DESCRIPION + " text , " +
                DATE + " text , " +
                ASSIGN + " text , " +
                STATUS + " text " +
                " ) ";


        db.execSQL(sql);
        db.execSQL(CREATE_TABLE_CONTACTS);
        db.execSQL(CREATE_TABLE_TASKS);


    }
    public long insert( String contactname,String oppo,String flag,String reminder,String nextstep){

        ContentValues contentValue = new ContentValues();
        contentValue.put(CONTACTNAME, contactname);
        contentValue.put(OPPORTUNITY, oppo);
        contentValue.put(FLAG, flag);
        contentValue.put(REMINDERDATE, reminder);
        contentValue.put(NEXTSTEP, nextstep);
        row= mDB.insert(DATABASE_TABLE, null, contentValue);
        mDB.close();

        return row;

    }

    public long addcontactItem(String fullname,String mobilenumber) {

        ContentValues values = new ContentValues();
        values.put(FULLNAME, fullname);
        values.put(MOBILENUMBER, mobilenumber);

        long childrow =mDB.insert(TABLE_CONTACTS, null, values);
        mDB.close();
        return childrow;
    }

    public long addtaskItem(String subject,String description,String date,String assign,String status) {

        ContentValues values = new ContentValues();
        values.put(SUBJECT, subject);
        values.put(DESCRIPION, description);
        values.put(DATE, date);
        values.put(ASSIGN, assign);
        values.put(STATUS, status);

        long taskdrow =mDB.insert(TABLE_TASK, null, values);
        mDB.close();
        return taskdrow;
    }
//    public int del(){
//        int cnt = mDB.delete(DATABASE_TABLE, null , null);
//        return cnt;
//    }
//
//    public Cursor getAppDetails(){
//        return mDB.query(DATABASE_TABLE, new String[] { ROW_ID, CONTACTNAME } , null, null, null, null, null);
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
