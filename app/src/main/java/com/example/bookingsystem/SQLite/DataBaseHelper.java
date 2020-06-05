package com.example.bookingsystem.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingsystem.MainActivity;
import com.example.bookingsystem.Trip.Trip;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 99;
    private static String DATABASE_NAME = "FavTrips";

    public DataBaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Trip.QueryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean InsertTrip(Trip obj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID",obj.getID());
        values.put("NAME",obj.getName());
        values.put("Date",obj.getDate());
        values.put("Description",obj.getDescription());
        values.put("Photo",obj.getPhoto());
        values.put("Price",obj.getPrice());
        values.put("UserEmail", MainActivity.email);
        long id = db.insert("Trips",null,values);
        return id > 0;
    }

    public List<Trip> ShowFavoritesTrip(){
        List<Trip> trips = new ArrayList<>();
        String query = "Select * From" + " Trips";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Trip obj = new Trip();
                obj.setID(cursor.getInt(cursor.getColumnIndex("ID")));
                obj.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                obj.setDate(cursor.getString(cursor.getColumnIndex("Date")));
                obj.setPhoto(cursor.getString(cursor.getColumnIndex("Photo")));
                trips.add(obj);
            }while (cursor.moveToNext());
        }
        db.close();
        return  trips;
    }

    public boolean DeleteTrip(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int a = db.delete("Trips","ID" + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return a > 0;
    }
}
