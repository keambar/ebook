package com.example.ebook.Activity;

import com.example.ebook.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityRegister extends Activity {
	private ProgressDialog progressDialog;
	private Button ZcButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ZcButton=(Button)findViewById(R.id.ButtonZc);
		progressDialog=new ProgressDialog(this);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setIcon(R.drawable.ic_launcher);
		progressDialog.setTitle("提示");  
		progressDialog.setMessage("注册中…………");  
		ZcButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				EditText editText1,editText2,editText3,editText4;
				
				editText1=(EditText) findViewById(R.id.EditView_ZH);
				String inputText1=editText1.getText().toString();
				
				editText2=(EditText) findViewById(R.id.EditView_MM);
				String inputText2=editText2.getText().toString();

				editText3=(EditText) findViewById(R.id.EditView_QQ);
				String inputText3=editText3.getText().toString();
				
				editText4=(EditText) findViewById(R.id.EditView_WX);
				String inputText4=editText4.getText().toString();
				
				
				progressDialog.setMessage("注册中…………");
				progressDialog.show();
				
				
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
		                		    Toast.makeText(ActivityRegister.this,"服务器响应超时",Toast.LENGTH_LONG).show();  
		                		    progressDialog.cancel();
		                		    Looper.loop();
		                		}
		                	}catch (InterruptedException e) {  
		                    // TODO Auto-generated catch block  
		                    e.printStackTrace();  
		                }  
		  
		            }  
		        }).start();  
			}
		});
		
		Button QXButton=(Button) findViewById(R.id.ButtonQx);
		QXButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				Intent intent=new Intent(ActivityRegister.this,ActivitySetting.class);
				startActivity(intent);
				ActivityRegister.this.finish();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
