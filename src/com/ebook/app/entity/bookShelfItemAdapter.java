package com.ebook.app.entity;

import java.util.List;

import com.ebook.app.R;
import com.example.testtxtbook.BookPlayActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;


public class bookShelfItemAdapter extends ArrayAdapter<bookShelfItem> {
	
	private int resourceId;
	
	private Context context;
	
	public bookShelfItemAdapter(Context context, int resource, List<bookShelfItem> objects) {
		super(context, resource,objects);
		this.resourceId=resource;
		this.context=context;
	}
	@Override
	public View getView(int position,View converView,ViewGroup parent) {
		bookShelfItem oneItem = getItem(position);
		View view;
		ViewHolder	viewHolder = null;
		if(converView == null){
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.bookself1 =(ImageButton) view.findViewById(R.id.button_1);
			viewHolder.bookself2 =(ImageButton) view.findViewById(R.id.button_2);
			viewHolder.bookself3 =(ImageButton) view.findViewById(R.id.button_3);
			view.setTag(viewHolder);
		}else{
			view = converView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.bookself1.setImageResource(oneItem.getId(0));
		viewHolder.bookself2.setImageResource(oneItem.getId(1));
		viewHolder.bookself3.setImageResource(oneItem.getId(2));
		
		final String path = Environment.getExternalStorageDirectory() + "/test1.txt";
		viewHolder.bookself1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("bookname", "testbookname");
				intent.putExtra("bookpath", path);
				intent.setClass(context, BookPlayActivity.class);
				context.startActivity(intent);
				android.util.Log.d("bookShelfItemAdapter","onClick1");
			}
		});
		
		viewHolder.bookself2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	
				android.util.Log.d("bookShelfItemAdapter","onClick");
			}
		});
		
		viewHolder.bookself3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				android.util.Log.d("bookShelfItemAdapter","onClick3");
			}
		});
		return view;
	}
	class ViewHolder{
		ImageButton bookself1,bookself2,bookself3;
	}
}
