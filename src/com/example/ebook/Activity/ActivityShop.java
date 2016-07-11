package com.example.ebook.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.ebook.R;
import com.example.ebook.entity.Download;
import com.example.ebook.entity.Model;
import com.example.ebook.entity.book;
import com.example.ebook.entity.bookAdapter;
import com.example.ebook.until.Url;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ActivityShop extends Activity {
	private List<book> bookList = new ArrayList<>();
	
	private boolean loaddown;
	
	public Model temModel;
	
	public Url temUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityshop_main);
		loaddown = false;
		temModel=new Model();
		temUrl= new Url();
		buildbookList  thread= new buildbookList();
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		bookAdapter adapter = new bookAdapter(ActivityShop.this, bookList);
		ListView listview = (ListView) findViewById(R.id.ListView);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				final book tbook = bookList.get(position);
				AlertDialog.Builder builder = new AlertDialog.Builder(ActivityShop.this);

				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("你确定要下载"+tbook.getbookname()+"吗?");

				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								temModel.downpiclocal(ActivityShop.this,tbook.getpicurl());
								temModel.downbooklocal(ActivityShop.this,tbook.getbookurl());
							}
						}).start();
					}
				});

				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				});

				builder.create().show();
			}

		});
	}

	
	class buildbookList extends Thread{			
			@Override
			public void run() {

				String aString = null;
		    	try {
		    		//Log.d("TestActivity", "1");
					aString=Url.doGet("http://192.168.1.156:8082/ebook/findbooklist.do");
				} catch (Exception e) {

					e.printStackTrace();
				}
		    	//Log.d("TestActivity", "2");
		        Message msg = new Message();  
		        Bundle data = new Bundle();  
		        data.putString("value",aString);  
		        msg.setData(data);  
		        handler.sendMessage(msg);
		       // Log.d("TestActivity", "3");
			}
	}
	
	public Handler handler =new Handler(){
		@Override
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			Bundle data = msg.getData();
			try {
				explanation(data.getString("value"));
				loaddown=true;
			} catch (JSONException e) {

				e.printStackTrace();
			}
			
		}
	};
	
	public void  explanation(String ansString) throws JSONException{
		bookList.clear();
		JSONObject json = new JSONObject(ansString);
		String message = json.getString("code");
		
		Log.d("1", message);

		int count = json.getInt("count");
		
		JSONArray jsonArray = json.getJSONArray("list");
		for (int i = 0; i < jsonArray.length(); ++i) {

			JSONObject book = (JSONObject) jsonArray.get(i);

			int id = book.getInt("book_id");
			
			String name = book.getString("book_name");
	
			String book_pic = book.getString("book_pic");

			String book_path = book.getString("book_path");

			bookList.add(new book(id, name, book_path, book_pic));
		}
	}
	
	public interface Callback{
		public void Callme();
	}
}