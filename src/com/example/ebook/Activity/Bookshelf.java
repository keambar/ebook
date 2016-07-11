package com.example.ebook.Activity;

import java.util.ArrayList;
import java.util.List;

import com.example.ebook.R;


import com.example.ebook.db.sql;
import com.example.ebook.entity.book;
import com.example.ebook.entity.bookShelfAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class Bookshelf extends Activity {
	
	private List<book> bookShelfList = new ArrayList<book>();
	private String picpath=Environment.getExternalStorageDirectory()+ "";
	private sql dbsql;
	@Override
    protected void onCreate ( Bundle savedInstanceState ) {
		Log.d("Bookshelf", "onCreate");
        super.onCreate ( savedInstanceState );
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView (R.layout.bookshelf);
    }
	@Override
	protected void onStart(){
		super.onStart();
		
//		final AnimButtons animButtons;
//		animButtons = (AnimButtons) findViewById(R.id.animButtons);
//
//		animButtons
//				.setOnButtonClickListener(new AnimButtons.OnButtonClickListener() {
//					@Override
//					public void onButtonClick(View v, int id) {
//						if (id == 0) {
//							Intent intent0 = new Intent(
//									Bookshelf.this,
//									ActivityShop.class);
//							startActivity(intent0);
//						}
//						if (id == 1) {
//							Intent intent1 = new Intent(
//									Bookshelf.this,
//									Bookshelf.class);
//							startActivity(intent1);
//						}
//						if (id == 2) {
//							Intent intent2 = new Intent(
//									Bookshelf.this,
//									ActivitySetting.class);
//							startActivity(intent2);
//						}
//
//						animButtons.closeMenu();
//					}
//				});
		
		Log.d("Bookshelf", "onStart");
        init ();
        bookShelfAdapter mAdapter = new bookShelfAdapter(Bookshelf.this,bookShelfList);
        ListView shelf_list = (ListView) findViewById(R.id.shelf_list);
        Button   shelf_button = (Button) findViewById(R.id.shelf_image_button);
        shelf_list.setAdapter ( mAdapter );
		shelf_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Bookshelf.this,ActivityShop.class);
				startActivity(intent);
			}
		});
	}
	//从数据库获取图书
	private void init() {
		dbsql= new sql(Bookshelf.this);
		bookShelfList = dbsql.getBooksInfo();
		Log.d("init", ""+ bookShelfList.size());
	}

}

