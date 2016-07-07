package com.ebook.app.entity;



import com.ebook.app.R;
import android.util.Log;

public class bookShelfItem{
	
	private String[] nameStrings=new String[3];
	
	private int[] imageId=new int[3];
	
	public bookShelfItem(){
		for(int i=0;i<3;++i){
			this.nameStrings[i]="add a new book";
			this.imageId[i]=R.drawable.default_cover;
		}	
	}
	
	public bookShelfItem(int position,String name, int imageId ) {
		Log.d("bookShelfItem", "2");
		if(position>2){
			Log.e("bookShelfItem", "position greater than 2");
		}
		this.nameStrings[position]=name;
		this.imageId[position]=imageId;
	}
	
	public void setName(int position,String name){
		this.nameStrings[position]=name;
	}

	public void setImage(int position,int id){
		this.imageId[position]=id;
	}
	
	public String getName(int position) {
		return nameStrings[position];
	}
	
	public int getId(int position) {
		return imageId[position];
	}
}
