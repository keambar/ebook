package com.example.ebook.db;
 
import java.util.ArrayList;
import java.util.List;

import com.example.ebook.entity.book;
import com.sun.org.apache.regexp.internal.recompile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class sql {
	
	/**
	 * 数据库名
	 */
	private static final String DATABASE_NAME = "database_ebook.db";

	/**
	 * 数据表名
	 */
	public  static final String DATABASE_TABLE = "tb_ebook";
	
	//public  static final String DATABASE_TABLE2 = "tb_booknum";
	
	
	
	private SQLiteDatabase db;
	private DatabaseHelper DBHelper;
	private final Context context;
	
	public sql(Context ctx) {
		Log.d("sql","new sql");
		context = ctx;
		DBHelper = new DatabaseHelper(context);
		Log.d("sql","new sql finish");
	}
	

	/**
	 * 数据库的创建语句
	 */
	private static final String DATABASE_DEVICE_CREATE ="create table tb_ebook("
			+ "book_id integer primary key autoincrement,"
			+ "book_name  text,"
			+ "book_pic   text,"
			+ "book_path  text);";
	
//	private static final String DATABASE_DEVICE_CREATE2 ="create table tb_booknum("
//			+ "book_num  integer);";
	/**
	 * 数据库版本
	 */
	private static final int DATABASE_VERSION = 1;
	
	
	/**
	 * 插入一条书的信息
	 * @param info
	 * @return
	 */
	public boolean  insertDataBy(book info){
		
		try {
			db = DBHelper.getWritableDatabase();
			if (!tableIsExist(DATABASE_TABLE)) {
				db.execSQL(DATABASE_DEVICE_CREATE);
			}
			ContentValues initialValues = new ContentValues();

			initialValues.put("book_name",info.getbookname());
			initialValues.put("book_id",info.getbookid());
			
			initialValues.put("book_pic",info.getpicurl());
			initialValues.put("book_path",info.getbookurl());
			
			db.insert("tb_ebook", null, initialValues);
			
			return true;
		} catch (Exception e) {
			 
			e.printStackTrace();
		}finally{
			db.close();
		}
		
		
		return false;
	}
	
//	public boolean addnum(){
//		try {
//			db = DBHelper.getWritableDatabase();
//			if (!tableIsExist(DATABASE_TABLE2)) {
//				db.execSQL(DATABASE_DEVICE_CREATE2);
//				ContentValues initialValues = new ContentValues();
//				initialValues.put("book_num", 0);
//			}
//			db.execSQL("update tb_booknum set book_num=book_num+1");
//			return true;
//		} catch (Exception e) {
//			 
//			e.printStackTrace();
//		}finally{
//			db.close();
//		}		
//		return false;
//		
//	}
	
	/**
	 * 查询本地数据库书的信息
	 * @param tableName
	 */
	public List<book> getBooksInfo() {

		List<book> localbook=new ArrayList<book>();
		if (!tableIsExist(DATABASE_TABLE)) {
			Log.d("sql", "ok");
			return localbook;			
		}
		db = DBHelper.getReadableDatabase();
 
		Log.d("sql", "1");
		Cursor cursor = db.query(
				DATABASE_TABLE,
				new String[] { "book_name", "book_id" ,"book_pic","book_path"},
				null,
				null, null, null, null);
		if (cursor == null || cursor.getCount() < 0)
			return localbook;
		Log.d("sql", "2");  
		
		cursor.moveToFirst();
		
		Log.d("sql", "3");
		for (int j = 0; j < cursor.getCount(); j++) {

			int book_id = cursor.getInt(cursor.getColumnIndexOrThrow("book_id"));

			String book_name = cursor.getString(cursor.getColumnIndexOrThrow("book_name"));
			
			String book_pic =  cursor.getString(cursor.getColumnIndexOrThrow("book_pic"));
			
			String book_path =  cursor.getString(cursor.getColumnIndexOrThrow("book_path"));
			
			localbook.add(new book(book_id, book_name, book_path, book_pic));
			
			Log.i("DBAdapter",book_name+ "---->"+book_id);
			
			cursor.moveToNext();
		}
		return localbook;
	}

//	public int getBooksnum(String tableName) {
//		Log.d("sql", tableName);
//		db = DBHelper.getReadableDatabase();
//		Log.d("sql", "1");
//		if (!tableIsExist(DATABASE_TABLE2)) {
//			Log.d("sql", "ok");
//			return 0;			
//		}
//		Log.d("sql", "no");
//		Cursor cursor = db.query(tableName,	null ,null, null, null, null, null);
//		if (cursor == null || cursor.getCount() < 0)
//			return 0;
//		  
//		cursor.moveToFirst();
//		int res = cursor.getInt(cursor.getColumnIndexOrThrow("book_num"));
//		
//		Log.i("DBAdapter"," book_num + ---->"+res);
//		
//		return res;			
//	}
//	
	public boolean tableIsExist(String tableName) {
		Log.d("sql 2", tableName);
		boolean result = false;
		if (tableName == null) {
			return false;
		}

		db = DBHelper.getReadableDatabase();

		Cursor cursor = null;
		try {
			String sql = "select count(*) as c from Sqlite_master" +
					" where type ='table' and name ='"
					+ tableName.trim() + "' ";
			cursor = db.rawQuery(sql, null);
			
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}
			Log.d("sql2", result+"");
		} catch (Exception e) {

		}
		// db.close();
		return result;

	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
	 
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			try {
				db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			} catch (SQLException e) {

				e.printStackTrace();
			}
			onCreate(db);
		}

	}

}
