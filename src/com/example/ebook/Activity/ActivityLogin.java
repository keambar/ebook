package com.example.ebook.Activity;

import com.example.ebook.R;

import android.os.Bundle;
import android.os.Looper;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends Activity {
	
	private EditText editText1,editText2;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		final ProgressDialog dialog = new ProgressDialog(this);  
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条  
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消  
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条  
        dialog.setIcon(R.drawable.ic_launcher);//  
        // 设置提示的title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的  
        dialog.setTitle("提示");  
        // dismiss监听  
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {  
  
            @Override  
            public void onDismiss(DialogInterface dialog) {  
                // TODO Auto-generated method stub  
  
            }  
        });  
        // 监听Key事件被传递给dialog  
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {  
  
            @Override  
            public boolean onKey(DialogInterface dialog, int keyCode,  
                    KeyEvent event) {  
                // TODO Auto-generated method stub  
                return false;  
            }

        });  
        // 监听cancel事件  
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {  
  
            @Override  
            public void onCancel(DialogInterface dialog) {  
                // TODO Auto-generated method stub  
  
            }  
        });  
        
        dialog.setMessage("登录中......");  
        
		
		
		editText1=(EditText) findViewById(R.id.account);
		editText2=(EditText) findViewById(R.id.pwd);
		
		Button login=(Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String inputText1=editText1.getText().toString();
				String inputText2=editText2.getText().toString();
				
				dialog.show();  
		        new Thread(new Runnable() {  
		  
		            @Override  
		            public void run() {  
		                // TODO Auto-generated method stub  
		                try {  
		                	int i,n=10;
	                		for(i=0;i<n;i++){
	                			Thread.sleep(1000);
	                		}
	                		if(i==n){
	                			Looper.prepare();
	                		    Toast.makeText(ActivityLogin.this,"服务器响应超时",Toast.LENGTH_LONG).show();  
	                		    dialog.cancel();
	                		    Looper.loop();
	                		}  
		                } catch (InterruptedException e) {  
		                    // TODO Auto-generated catch block  
		                    e.printStackTrace();  
		                }  
		  
		            }  
		        }).start();
				
				// TODO Auto-generated method stub
				
			}
		});
		
		Button quit=(Button) findViewById(R.id.quit);
		quit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				Intent intent=new Intent(ActivityLogin.this,ActivitySetting.class);
				startActivity(intent);
				ActivityLogin.this.finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

}

