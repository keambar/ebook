package com.example.ebook.Activity;

import com.example.ebook.R;
import com.example.ebook.until.HttpRequest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
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
	private ProgressDialog dialog; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		dialog = new ProgressDialog(ActivityLogin.this); 
		
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
				
				final String inputText1=editText1.getText().toString();
				final String inputText2=editText2.getText().toString();
				
				dialog.show();  
		        new Thread(new Runnable() {  
		  
		            @Override  
		            public void run() {  
		                // TODO Auto-generated method stub  
		            	
		                try {
		                	
		                	String s=HttpRequest.sendPost("http://192.168.1.156:8082/ebook/login.do",
		                			"user="+inputText1+"&pwd="+inputText2);
		                	
		                	Message msg = new Message();  
	         		        Bundle data = new Bundle();  
	         		        data.putString("value",s);  
	         		        msg.setData(data);  
	         		        handler.sendMessage(msg);
	         		        
	         		        
		                } catch (Exception e) {  
		                    // TODO Auto-generated catch block  
		                    e.printStackTrace();  
		                }  
		  
		            }  
		        }).start();
				
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}

	public Handler handler =new Handler(){
		@Override
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			Bundle data = msg.getData();
			try {
				String s=data.getString("value");
				Log.d("hehe",s);
				if(!s.equals("{\"code\":\"fail\",\"ticket\":\"\"}")){
					//弹出
					SuccessShow("登录成功");
					
					//关闭
					dialog.cancel();
				
					finish();
					
				}else{
					
					SuccessShow("登录失败");
					dialog.cancel();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			
		}
	};
	
	
	public void SuccessShow(String s){
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}
	

}

