package nguluvhe.pt.coffescup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String TABLE_NAME = "coffee_table";
    private static final String COL0 = "order_id";
    private static final String COL1 = "order_person_full_name";
    private static final String COL2 = "order_coffee_type";
    private static final String COL3 = "order_milk";
    private static final String COL4 = "order_cups_number";
    private static final String COL5 = "order_sugar_number";
    private static final String COL6 = "order_cost";

    public DatabaseHelper( Context context) {

        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sCreateTable = "CREATE TABLE " + TABLE_NAME + "( " + COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 + "TEXT"
                + COL2 + "TEXT" + COL3 + "TEXT" + COL4 + "INTEGER" + COL5 + "INTEGER" + COL6 + "DOUBLE)";
        db.execSQL(sCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP IF TABLE EXISTS");
        onCreate(db);
    }

    public boolean addData(String sItem)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, sItem);
        contentValues.put(COL2, sItem);
        contentValues.put(COL3, sItem);
        contentValues.put(COL4, sItem);
        contentValues.put(COL5, sItem);
        contentValues.put(COL6, sItem);

        long iResult = db.insert(TABLE_NAME, null, contentValues);

        if (iResult == -1)
        {
            return false;
        }else {
            return true;
        }
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(sQuery, null);

        return data;
    }

    public Cursor getItemId(String _sName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sQuery = "SELECT " + COL0 + "FROM " + TABLE_NAME
                + "WHERE " + COL1 + "= '" +_sName + "'";
        return db.rawQuery(sQuery, null);
    }

    public void updateName(String _sNewName, int _iId, String _sOldname)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sUpdateStatement = "UPDATE " + TABLE_NAME + " SET " + COL1 + " = '" + _sNewName + "' WHERE " + COL0 + " = '" + _iId + "' AND "
                + COL1 + " = '" + _sOldname + "'" ;
        db.execSQL(sUpdateStatement);
    }
    public void deleteName(int _iId, String _sName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sDeleteStatement = "DELETE FROM " + TABLE_NAME + " WHERE " + COL0 + " = '" + _iId + "' AND " + COL1 + " = '" + _sName +"'";
        db.execSQL(sDeleteStatement);
    }
}
