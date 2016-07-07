package com.ebook.app.Activity;

import com.ebook.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class searchbook extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.d("searchbook","run there");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView ( R.layout.booksearch );
		
	}
}
