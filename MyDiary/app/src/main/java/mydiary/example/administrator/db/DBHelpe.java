package mydiary.example.administrator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class DBHelpe extends SQLiteOpenHelper {

	private static final String DB_NAME = "dialy.db";
	private static final String TBL_NAME = "Dialy";
	private static final String CREATE_TBL = " create table " + " Dialy(_id integer primary key autoincrement,content text,data text,days text,winder text) ";

	static String temp0 = "";
	private SQLiteDatabase db;
	public DBHelpe(Context c) {
		super(c, DB_NAME, null, 2);
	}

	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		db.execSQL(CREATE_TBL);

	}
	public void insert(ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TBL_NAME, null, values);
		db.close();
	}
	public Cursor query() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TBL_NAME, null, null, null, null, null, null);
	    return c;
	}
	
	public void update(String _id,String content) {
		SQLiteDatabase db = getWritableDatabase();
		String sql = "update Dialy  " + "set content='" + content +"'"+"where _id='"+ _id +"'";
		db.execSQL(sql);
		Log.d("==update content==", sql);
		db.close();
	}
	public ArrayList<Mode> fetchValue(String id) {
		ArrayList<Mode> shouCang = new ArrayList<Mode>();
		String sql="select * from Dialy " +
				"where _id='"+id+"'";
		Log.d("==sql==", sql);
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(sql,null);
		if (cursor != null && cursor.getCount() >= 0) {
			if (cursor.moveToFirst()) {
				do {
					Mode wt = new Mode();
					wt.setCONTENT(cursor.getString(1));
					wt.setDATA(cursor.getString(2));
					wt.setDAYS(cursor.getString(3));
					wt.setWINDER(cursor.getString(4));
				    shouCang.add(wt);
				} while (cursor.moveToNext());
			}
		}
		if (cursor != null) {
			cursor.close();
			db.close();
		}
		return shouCang;

	}
	public ArrayList<Mode> fetchValue() {
		ArrayList<Mode> shouCang = new ArrayList<Mode>();
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TBL_NAME, null, null, null, null, null, null);
		if (cursor != null && cursor.getCount() >= 0) {
			if (cursor.moveToFirst()) {
				do {
					Mode wt = new Mode();
					wt.setCONTENT(cursor.getString(1));
					wt.setDATA(cursor.getString(2));
					wt.setDAYS(cursor.getString(3));
					wt.setWINDER(cursor.getString(4));
					shouCang.add(wt);
				} while (cursor.moveToNext());
			}
		}
		if (cursor != null) {
			cursor.close();
			db.close();
		}
		return shouCang;

	}
	public String selectall() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TBL_NAME, null, null, null, null, null, null);
		if (cursor != null) {
			String temp = "";
			int i = 0;
			while (cursor.moveToNext()) {
				temp += cursor.getString(1);
				temp0 = temp;
			}
			cursor.close();
			db.close();
		}
		return temp0;
	}

	public void del(int id) {
		db = getWritableDatabase();
		db.delete(TBL_NAME, "_id=?", new String[]{String.valueOf(id)});
		db.close();
	}
	public void close() {
		if (db != null)
			db.close();
	}

	public void clean() {

		this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
		this.onCreate(this.getWritableDatabase());
		this.getWritableDatabase().close();

	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
