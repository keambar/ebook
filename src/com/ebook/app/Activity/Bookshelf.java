package com.ebook.app.Activity;

import java.util.ArrayList;
import java.util.List;

import com.ebook.app.R;
import com.ebook.app.entity.bookShelfItemAdapter;
import com.ebook.app.entity.bookShelfItem;








import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Bookshelf extends Activity {

	private List<bookShelfItem> bookShelfItemList = new ArrayList<bookShelfItem>();

	@ Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView ( R.layout.bookshelf );
        init ();
        bookShelfItemAdapter mAdapter = new bookShelfItemAdapter(Bookshelf.this, R.layout.bookshelfitem, bookShelfItemList);
        ListView shelf_list = (ListView) findViewById(R.id.shelf_list);
        Button   shelf_button = (Button) findViewById(R.id.shelf_image_button);
        shelf_list.setAdapter ( mAdapter );        
        
        shelf_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {				
				Intent intent = new Intent(Bookshelf.this,searchbook.class);
				startActivity(intent);					
				Log.d("BookShelf","1");
			}
        });
    }

	private void init() {
		bookShelfItem[] item=new bookShelfItem[10];
		for(int i=0;i<10;++i){		
			item[i] =new bookShelfItem();
			bookShelfItemList.add(item[i]);
		}		
		item[0].setName(0, "ÖïÏÉ");
		item[0].setImage(0, R.drawable.cover_0);
	}

}

