package mydiary.example.administrator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB_PASSWORD extends SQLiteOpenHelper {

	private static final String DB_NAME = "password.db";
	private static final String TBL_NAME = "PASSWORD";
	private static final String CREATE_TBL = " create table " + " PASSWORD(_id integer primary key autoincrement,password text) ";

	static String temp0 = "";
	private SQLiteDatabase db;
	public DB_PASSWORD(Context c) {
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

	public void update(String password) {
		SQLiteDatabase db = getWritableDatabase();
		String sql = "update PASSWORD  " + "set password='" + password + "'";
		db.execSQL(sql);
		Log.d("==sql==", sql);
		db.close();
	}

	public String selectall() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TBL_NAME, null, null, null, null, null, null);
		if (cursor != null) {
			String temp = "";
			int i = 0;
			while (cursor.moveToNext()) {
				
				temp += cursor.getString(1);
				System.out.println("+======="+temp);
				temp0 = temp;
			}
			cursor.close();
			db.close();
		}
		return temp0;
	}

	public void del(int id) {
		// if (db == null)
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