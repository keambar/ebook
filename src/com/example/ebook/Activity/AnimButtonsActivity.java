package com.example.ebook.Activity;


import com.example.ebook.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 主界面
 * 
 * @author ZhaoKaiQiang
 * 
 *         Time:2014年3月11日
 */
public class AnimButtonsActivity extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final AnimButtons animButtons;
		animButtons = (AnimButtons) findViewById(R.id.animButtons);

		animButtons
				.setOnButtonClickListener(new AnimButtons.OnButtonClickListener() {
					@Override
					public void onButtonClick(View v, int id) {
						if (id == 0) {
							Intent intent0 = new Intent(
									AnimButtonsActivity.this,
									ActivityShop.class);
							startActivity(intent0);
						}
						if (id == 1) {
							Intent intent1 = new Intent(
									AnimButtonsActivity.this,
									Bookshelf.class);
							startActivity(intent1);
						}
						if (id == 2) {
							Intent intent2 = new Intent(
									AnimButtonsActivity.this,
									ActivitySetting.class);
							startActivity(intent2);
						}

						animButtons.closeMenu();
					}
				});
	}
}
