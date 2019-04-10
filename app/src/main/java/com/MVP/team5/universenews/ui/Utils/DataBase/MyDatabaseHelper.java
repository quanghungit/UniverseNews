package com.MVP.team5.universenews.ui.Utils.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.MVP.team5.universenews.ui.model.NewsDetailModel;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "News";
    public static final int VERSION = 1;

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESC = "description";
    public static final String DATA = "data";

    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbQuery = "CREATE TABLE IF NOT EXISTS " + DB_NAME + " ("
                        + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + TITLE + " TEXT, "
                        + DESC + " TEXT, "
                        + DATA + " TEXT" + ")";
        db.execSQL(dbQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }

    public void addNews(NewsDetailModel news) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, news.getTilte());
        contentValues.put(DESC, news.getDesc());
        contentValues.put(DATA, news.getHtml());
        db.insert(DB_NAME, null, contentValues);
        db.close();
    }

    public List<NewsDetailModel> getNews() {
        List<NewsDetailModel> list = new ArrayList<>();

        String query = "SELECT * FROM " + DB_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                NewsDetailModel news = new NewsDetailModel();
                news.setId(cursor.getInt(0));
                news.setTilte(cursor.getString(1));
                news.setDesc(cursor.getString(2));
                news.setHtml(cursor.getString(3));
                list.add(news);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public int getCount() {
        String query = "SELECT * FROM " + DB_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public void updateNews(){}

    public void deleteNews(NewsDetailModel news){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_NAME, ID + " = ? ", new String[]{String.valueOf(news.getId())});
        db.close();
    }
}
