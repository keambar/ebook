package com.example.ebook.entity;


import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.ebook.R;
import com.example.ebook.until.BitmapCache;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class bookAdapter extends BaseAdapter {
	private List<book> bookList;
	private Context context;
	private RequestQueue queue;
	private ImageLoader imageLoader;

	

	public bookAdapter(Context context, List<book> list) {
		for(int i=0;i<list.size();++i){
			Log.d("111",list.get(i).getbookname());
		}
		this.context=context;
		this.bookList=list;
		queue =Volley.newRequestQueue(context);
		imageLoader = new ImageLoader(queue, new BitmapCache());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.activityshopitem, null);
			holder.img =(NetworkImageView) convertView.findViewById(R.id.image_item);
			holder.bookname =(TextView) convertView.findViewById(R.id.text_item);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		final String imgUrl = bookList.get(position).getpicurl();

		if(imgUrl != null && !imgUrl.equals("")){
			holder.img.setDefaultImageResId(R.drawable.default_cover);
			holder.img.setErrorImageResId(R.drawable.default_cover);
			holder.img.setImageUrl(imgUrl, imageLoader);
			holder.bookname.setText(bookList.get(position).getbookname());
		}	
		
		return convertView;
	}

	@Override
	public int getCount() {

		return bookList.size();
	}

	@Override
	public Object getItem(int arg0) {
		
		return bookList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {

		return arg0;
	}
	class ViewHolder{
		NetworkImageView img;
		TextView bookname;
	}

}