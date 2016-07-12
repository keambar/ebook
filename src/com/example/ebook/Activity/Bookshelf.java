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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Bookshelf extends Activity {
	
	private List<book> bookShelfList = new ArrayList<book>();
	private sql dbsql;
	@Override
    protected void onCreate ( Bundle savedInstanceState ) {
		Log.d("Bookshelf", "onCreate");
        super.onCreate ( savedInstanceState );
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView (R.layout.bookshelf);
    }
	@Override
	protected void onStart(){
		super.onStart();
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
		shelf_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
					
					final book tbook = bookShelfList.get(position);
					String bookp =tbook.getbookurl();
					Log.d("Bookshelf", bookp);
					Intent intent = new Intent();
					intent.putExtra("bookname", tbook.getbookname());
					intent.putExtra("bookpath", bookp);
					intent.setClass(Bookshelf.this, BookPlayActivity.class);
					Bookshelf.this.startActivity(intent);
			}

		});
	}
	//从数据库获取图书
	private void init() {
		dbsql= new sql(Bookshelf.this);
		bookShelfList = dbsql.getBooksInfo();
		Log.d("init", ""+ bookShelfList.size());
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_bookshelf, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_login) {
			Intent intent=new Intent(this,ActivityLogin.class);
			startActivity(intent);
			return true;
		}
		if (id == R.id.action_register) {
			Intent intent=new Intent(this,ActivityRegister.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

