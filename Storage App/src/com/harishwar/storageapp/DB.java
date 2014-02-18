package com.harishwar.storageapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "sqlite.db";
	private static final String KEY_ID = "id";
	public static final String TABLE_DATA = "data";
	private static final String KEY_MESSAGE = "message";
	private Context context;
	private SQLiteDatabase m_db;

	public DB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_DATA_TABLE = "CREATE TABLE " + TABLE_DATA + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MESSAGE + " TEXT)";
		db.execSQL(CREATE_DATA_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
		onCreate(db);
	}
	
	public void openInternalDB() {
		if ((m_db == null) || (m_db.isOpen() == false)) {
			m_db = this.getWritableDatabase();
		}
	}

	public void closeDB() {
		if (m_db != null) {
			m_db.close();
			m_db = null;
		}
	}

	public void deleteRow(String tableName, String whereConition,
			String[] values) {
		SQLiteDatabase db = this.getWritableDatabase();
		int deletedItems = 0;
		deletedItems = db.delete(tableName, whereConition, values);
		Log.d("", "Deleted Items" + deletedItems);
		db.close();
	}

	public void deleteTableEntries(String TABLE_NAME) {
		int rows = m_db.delete(TABLE_NAME, null, null);
	}

	public void addMessage(String message) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_MESSAGE, message);
		db.insert(TABLE_DATA, null, values);
		db.close();
	}

	public int getCursorCount() {
		String selectQuery = "SELECT  * FROM " + TABLE_DATA;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		int count = cursor.getCount();
		cursor.close();
		db.close();
		return count;
	}

	public List<String> getMessageList() {

		List<String> messageList = new ArrayList<String>();
		String selectQuery = "SELECT  * FROM " + TABLE_DATA;
		SQLiteDatabase db = this.getWritableDatabase();
		String message = "";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				message = cursor.getString(1);
				messageList.add(message);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return messageList;
	}

}
