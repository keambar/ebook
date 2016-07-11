package com.example.ebook.entity;

import com.example.ebook.R;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class bookShelfAdapter extends BaseAdapter {
	private List<book> bookList;
	private Context context;
	public Model temModel;
	

	public bookShelfAdapter(Context context, List<book> list) {
		this.context=context;
		this.bookList=list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		temModel=new Model();
		ViewHolder holder = null;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.bookshelfitem, null);
			holder.img =(ImageView) convertView.findViewById(R.id.pic_1);
			holder.bookname =(TextView) convertView.findViewById(R.id.text_1);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		final String imgUrl = bookList.get(position).getpicurl();
		
		holder.img.setImageBitmap(temModel.getLoaclBitmap(imgUrl));
		holder.bookname.setText(bookList.get(position).getbookname());	
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
		ImageView img;
		TextView bookname;
	}

}