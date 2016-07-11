package com.example.ebook.entity;

import java.util.BitSet;

import android.graphics.Bitmap;

public class book {
	private int bookid;

	private String bookname;

	private String bookurl;

	private String picurl;

	public  Bitmap pic;
	
	public book(int bookid, String bookname, String bookurl, String picurl) {
		this.bookid = bookid;
		this.bookname = bookname;
		this.bookurl = bookurl;
		this.picurl = picurl;
	}

	public String getbookname() {
		return bookname;
	}

	public String getbookurl() {
		return bookurl;
	}

	public String getpicurl() {
		return picurl;
	}

	public int getbookid() {
		return bookid;
	}
	
	public void setpic(Bitmap pic){
		this.pic=pic;
	}
	
	public Bitmap getpic(){
		return pic;
	}
}
