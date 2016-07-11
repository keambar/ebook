package com.example.ebook.entity;

import com.example.ebook.db.sql;
import com.example.ebook.until.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.omg.CORBA.portable.InputStream;

import android.R.string;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
/**
 *@author keambar
 *2016/7/8 
 */
public class Model {
	private String picpath=Environment.getExternalStorageDirectory()+ "/ebook/pic/";
	
	private String bookpath=Environment.getExternalStorageDirectory()+ "/ebook/txt/";
	
	private sql temSql;
	/**
	 * 从本地载入图片
	 * 
	 * @param url
	 * @return bitmap
	 */
	public Bitmap getLoaclBitmap(String url){
		return BitmapFactory.decodeFile(url);
//		BitmapFactory.Options op = new BitmapFactory.Options();  
//		op.inJustDecodeBounds = true;
//		Bitmap bmp = BitmapFactory.decodeFile(url, op); //获取尺寸信息
////		Log.d("size ", op.outWidth+"");
////		Log.d("size ", op.outHeight+"");
//		//获取比例大小
//		int wRatio = (int)Math.ceil(op.outWidth/90);
//		int hRatio = (int)Math.ceil(op.outHeight/120);
//		//如果超出指定大小，则缩小相应的比例
//		if(wRatio > 1 && hRatio > 1){
//			if(wRatio > hRatio){
//				op.inSampleSize = wRatio;
//		}else{
//			op.inSampleSize = hRatio;
//	    }
//	  }
//	  op.inJustDecodeBounds = false;
//	  bmp = BitmapFactory.decodeFile(url, op);
//	  return bmp;	
	}
	
	public String downpiclocal(Context context,String url,int num){
		sql dbSql= new sql(context);
		Url downUrl = new Url();
		//int num=dbSql.getBooksnum(dbSql.DATABASE_TABLE2);
		String picp=picpath+ "pic_"  +num+ ".jpg";
		//Log.d("down",picp);
		downUrl.Download(url, picp);
		return picp;
	}
	
	public String downbooklocal(Context context,String url,int num){
		sql dbSql= new sql(context);
		Url downUrl = new Url();
		//int num=dbSql.getBooksnum(dbSql.DATABASE_TABLE2);
		String bookp=bookpath + "book_" +num+".txt";
		//Log.d("down",bookp);
		downUrl.Download(url, bookp);
		
		return bookp;
	}
}
