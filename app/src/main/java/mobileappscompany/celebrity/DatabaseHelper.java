package mobileappscompany.celebrity;

import android.app.admin.DeviceAdminInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import static mobileappscompany.celebrity.DatabaseContract.CelebrityEntry.TABLE_NAME;

/**
 * Created by fallaye on 12/4/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String name = "myOb";
    public static final int version = 1;

    //Create statements
    public static final String CREATE_CELEBRITY_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" + DatabaseContract.CelebrityEntry.ID + " TEXT PRIMARY KEY, " +
                    DatabaseContract.CelebrityEntry.NAME + " TEXT, " +
                    DatabaseContract.CelebrityEntry.AGE + " TEXT, " +
                    DatabaseContract.CelebrityEntry.GENDER + " TEXT ) ";


    public DatabaseHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CELEBRITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addCelebrity(Celebrity celebrity){
        SQLiteDatabase database = this.getWritableDatabase();
        //Create content values for adding celebrity
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.CelebrityEntry.ID, celebrity.getId());
        contentValues.put(DatabaseContract.CelebrityEntry.NAME, celebrity.getName());
        contentValues.put(DatabaseContract.CelebrityEntry.AGE, celebrity.getAge());
        contentValues.put(DatabaseContract.CelebrityEntry.GENDER, celebrity.getGender());

        long rowId = database.insert(TABLE_NAME, null, contentValues);
        return rowId;
    }

    public long updateCelebrity(Celebrity celebrity){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", celebrity.getId());
        contentValues.put("name", celebrity.getName());
        contentValues.put("age", celebrity.getAge());
        contentValues.put("gender", celebrity.getGender());

        return db.update(TABLE_NAME, contentValues, "id = " + celebrity.getId(), null);
    }

    public ArrayList<Celebrity> getAllCelebrities(){
        ArrayList<Celebrity> celebrityList = new ArrayList<Celebrity>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor celebrityCursor = db.rawQuery(
                "SELECT * FROM " +
                        TABLE_NAME, null);

        if(celebrityCursor.moveToFirst()) {
            do {
                Celebrity celebrity = new Celebrity(
                        celebrityCursor.getString(0),
                        celebrityCursor.getString(1),
                        celebrityCursor.getString(2),
                        celebrityCursor.getString(3)
                );
                celebrityList.add(celebrity);
            } while (celebrityCursor.moveToNext());
        }
        return  celebrityList;
    }

    public boolean deleteCelebrity(String pId){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(TABLE_NAME, "id = ?", new String[]{ pId });
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return result;
    }

}
