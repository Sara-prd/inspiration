package com.example.diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "APP_DataBase";
    private static final String albumTable="tbl_album2";

    public SQLiteHelper(@Nullable Context context) {
        super(context,
                DATABASE_NAME,
                null,
                1);
//        super(context, context.getString(R.string.dataBase), null, 1);
//        super(context, context.getResources().getString(R.string.dataBase), null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
//            sqLiteDatabase.execSQL("CREATE TABLE "+albumTable+" (id INTEGER PRIMARY KEY AUTOINCREMENT, uri NVARCHAR, title TEXT);");
            sqLiteDatabase.execSQL("CREATE TABLE " + albumTable +
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, photoAddress TEXT, title TEXT)");

            ContentValues defaultAlbum1 = new ContentValues();
            defaultAlbum1.put("title","سفر");
            defaultAlbum1.put("photoAddress", String.valueOf(R.drawable.safar));
            sqLiteDatabase.insert(albumTable,null, defaultAlbum1);


            sqLiteDatabase.close();

        } catch (SQLException e){
            Log.e("DataBase", e.toString());
        }
//        Log.i("dataBaseExample", "database is created and has "+String.valueOf(sqLiteDatabase.rawQuery("SELECT COUNT(*) FROM "+albumTable,null).getInt(0))+ "rows");
//        Log.i("dataBaseExample","the number of rows are: "+String.valueOf(DatabaseUtils.queryNumEntries(sqLiteDatabase,albumTable)));

        }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addAlbum(Album album){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("title", album.getTitle());
        values.put("photoAddress", album.getPhotoUri());
        long result= db.insert(albumTable,null,values);
        db.close();
        return result;

    }

    public List<Album> getAllAlbums(){
        List<Album> albumList= new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+albumTable,null);
        if(cursor.moveToFirst())
            do {
                Album album = new Album();
                album.setId((long) cursor.getInt(0));
                album.setTitle(cursor.getString(2));
//                album.setPhotos();
//                album.setPhotoCoverAddress(cursor.getInt(1));
                albumList.add(album);
            }while(cursor.moveToNext());
            db.close();
            return albumList;
    }

    public int updateAlbum(Album album){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("title", album.getTitle());
        values.put("photoAddress", album.getPhotoUri());
        int result= db.update(albumTable, values, "id=?",new String[]{String.valueOf(album.getId())});
        db.close();

        return result;

    }
    public void deleteAlbum(){

    }
}
