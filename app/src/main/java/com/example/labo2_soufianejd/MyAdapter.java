package com.example.labo2_soufianejd;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyAdapter {
    Context context;
    private String dbname="LABO2SQLITE.db";
    private MyDBHelper dbhelper;
    private int version=1;
    private SQLiteDatabase mySQLiteDatabase;



    public MyAdapter(Context context){
        this.context=context;
        dbhelper=new MyDBHelper(context,dbname,null,version);
    }


    public void open(){
        this.mySQLiteDatabase=dbhelper.getWritableDatabase();
    }

    public void insertclient(String name, String lastname,String password,double balance,double credit){

        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("lastname",lastname);
        cv.put("password",password);
        cv.put("balance",balance);
        cv.put("credit",credit);
        this.mySQLiteDatabase.insert("client",null,cv);
    }


    public ArrayList<ClassUsers> select(){

        ArrayList<ClassUsers> l=new ArrayList<ClassUsers>();
        Cursor cursor=mySQLiteDatabase.query("client",null,null,null,null,null,null);

        if(cursor!=null && cursor.moveToFirst()){

            do{

                l.add(new ClassUsers(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getDouble(4),cursor.getDouble(5)));

            }
            while(cursor.moveToNext());
        }


        return l;
    }

    public ArrayList<ClassUsers> selectpositif(){

        ArrayList<ClassUsers> l=new ArrayList<ClassUsers>();
        Cursor cursor=mySQLiteDatabase.query("client",null,null,null,null,null,null);

        if(cursor!=null && cursor.moveToFirst()){

            do{
                if(cursor.getDouble(4)>cursor.getDouble(5))
                    l.add(new ClassUsers(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getDouble(4),cursor.getDouble(5)));

            }
            while(cursor.moveToNext());
        }


        return l;
    }


    public  ClassUsers getclient(String name){

        ClassUsers c=null;
        Cursor cursor=mySQLiteDatabase.query("client",null,"name like ?",new String[]{name},null,null,null);

        if(cursor!=null && cursor.moveToFirst()){

            do{

                c=(new ClassUsers(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getDouble(4),cursor.getDouble(5)));

            }
            while(cursor.moveToNext());
        }


        return c;
    }


    public void deleteone(ClassUsers c){
        this.mySQLiteDatabase.delete("client","name=?",new String[]{c.name});
    }

    public void updateone(ClassUsers c){

        ContentValues cv=new ContentValues();
        cv.put("name",c.name);
        cv.put("lastname",c.lastname);
        cv.put("password",c.password);
        cv.put("balance",c.balance);
        cv.put("credit",c.credit);

        this.mySQLiteDatabase.update("client",cv,"name=?",new String[]{c.name});
    }

    private class MyDBHelper extends SQLiteOpenHelper {

        public MyDBHelper(Context context, String dbname, SQLiteDatabase.CursorFactory factory,int version){
            super(context,dbname,factory,version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {

            String querry="create table client(id int"+ "auto_increment"+", name varchar(50),lastname varchar(50),password varchar(50),balance decimal(12,2),credit  decimal(12,2),primary key(id));";
            db.execSQL(querry);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            String querry="drop table if exists client;";
            db.execSQL(querry);
            onCreate(db);
        }

    }
}
