package com.example.ebook.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.ebook.R;
import com.example.ebook.db.sql;
import com.example.ebook.entity.Model;
import com.example.ebook.entity.book;
import com.example.ebook.entity.bookAdapter;
import com.example.ebook.until.Url;
import com.lidroid.xutils.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

import android.R.string;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
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

	public sql temSql;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activityshop_main);
		loaddown = false;
		temModel = new Model();
		temUrl = new Url();
		temSql = new sql(ActivityShop.this);
		buildbookList thread = new buildbookList();
		thread.start();
		try {
			// 执行子进程，在子进程执行完后才能执行mainthread
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		bookAdapter adapter = new bookAdapter(ActivityShop.this, bookList);
		final ListView listview = (ListView) findViewById(R.id.ListView);
		listview.setAdapter(adapter);

		/*
		 * 对搜索按键进行监听触发，将文本框中的内容读取到s, 并在booklist里进行遍历搜索。
		 * 如果有相匹配的书名或者输入的字符串为空则通过适配器将结果显示出来
		 */

		ImageButton buttonSeek = (ImageButton) findViewById(R.id.imageButton2);
		buttonSeek.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText editText = (EditText) findViewById(R.id.search);
				String s = editText.getText().toString();
				List<book> tempList = new ArrayList<>();
				for (book e : bookList) {
					if (e.getbookname().indexOf(s) != -1 || s.equals("")) {
						tempList.add(e);
					}
				}
				bookAdapter tempAdapter = new bookAdapter(ActivityShop.this,
						tempList);
				listview.setAdapter(tempAdapter);
			}

		});

		/**
		 * 监听对书本点击事件，在点击书本后进行下载提示， 如果选择“是”，就将书本下载本地书架显示
		 */

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				final book tbook = bookList.get(position);
				// 下载对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ActivityShop.this);

				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("你确定要下载" + tbook.getbookname() + "吗?");

				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								String picp = temModel.downpiclocal(
										ActivityShop.this, tbook.getpicurl(),
										tbook.getbookid());
								String bookp = temModel.downbooklocal(
										ActivityShop.this, tbook.getbookurl(),
										tbook.getbookid());
								book temBook = tbook;
								temBook.setpic(picp);
								temBook.setbook(bookp);
								temSql.insertDataBy(temBook);

							}
						});

				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						});

				builder.create().show();
			}

		});
	}

	public void download(View v) {
		String path;
		HttpUtils http = new HttpUtils();
	}

	class buildbookList extends Thread {
		@Override
		public void run() {
			// 将URL中用doget访问服务端返回的数据放到aString中，用handler机制进行处理
			String aString = null;
			try {
				// Log.d("TestActivity", "1");

				aString = Url
						.doGet("http://192.168.1.156:8082/ebook/findbooklist.do");
			} catch (Exception e) {

				e.printStackTrace();
			}
			// Log.d("TestActivity", "2");
			Message msg = new Message();
			Bundle data = new Bundle();
			data.putString("value", aString);
			msg.setData(data);
			handler.sendMessage(msg);
			// Log.d("TestActivity", "3");
		}
	}

	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle data = msg.getData();
			try {
				explanation(data.getString("value"));
				loaddown = true;
			} catch (JSONException e) {

				e.printStackTrace();
			}

		}
	};

	/*
	 * 使用Json对编码数据进行解析，并将解析后的书id，书名等存放在booklist里
	 */
	public void explanation(String ansString) throws JSONException {
		bookList.clear();
		JSONObject json = new JSONObject(ansString);
		String message = json.getString("code");

		// Log.d("1", message);

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

	public interface Callback {
		public void Callme();
	}

}