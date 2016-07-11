package com.example.ebook.Activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.PUBLIC_MEMBER;

import sun.util.logging.resources.logging;

import com.example.ebook.R;
import com.example.ebook.entity.Model;
import com.example.ebook.entity.book;
import com.example.ebook.until.Url;
import com.example.ebook.until.bookstoreimp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class TestActivity extends Activity {
	List<book> booklist = new ArrayList<>();
	
	Model temModel;
	Url temUrl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.testlayout);
		
		Button button=(Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
	}
		
	public void buildbooklist(){
		temModel=new Model();
		temUrl= new Url();
	
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String aString = null;
		    	try {
		    		Log.d("TestActivity", "1");
					aString=Url.doGet("http://192.168.1.156:8082/ebook/findbooklist.do");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Log.d("TestActivity", "2");
		        Message msg = new Message();  
		        Bundle data = new Bundle();  
		        data.putString("value",aString);  
		        msg.setData(data);  
		        handler.sendMessage(msg);
		        Log.d("TestActivity", "3");
			}
		}).start();

	}
	private Handler handler =new Handler(){
		@Override
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			Bundle data = msg.getData();
			try {
				explanation(data.getString("value"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	};
	
	public void  explanation(String ansString) throws JSONException{
		booklist.clear();
		JSONObject json = new JSONObject(ansString);
		String message = json.getString("code");
		
		Log.d("1", message);

		int count = json.getInt("count");
		JSONArray jsonArray = json.getJSONArray("list");
		for (int i = 0; i < jsonArray.length(); ++i) {

			JSONObject book = (JSONObject) jsonArray.get(i);

			int id = book.getInt("book_id");
			
			String name = book.getString("book_name");
			Log.d("1", name);
			String book_pic = book.getString("book_pic");

			String book_path = book.getString("book_path");

			booklist.add(new book(id, name, book_path, book_pic));
		}
	}
	
}
