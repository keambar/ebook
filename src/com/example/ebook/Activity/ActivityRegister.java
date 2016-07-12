package com.example.ebook.Activity;

import org.json.JSONException;

import sun.util.logging.resources.logging;

import com.example.ebook.R;
import com.example.ebook.until.HttpRequest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityRegister extends Activity {
	private ProgressDialog progressDialog;
	private Button ZcButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
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
				final String inputText1=editText1.getText().toString();
				
				editText2=(EditText) findViewById(R.id.EditView_MM);
				final String inputText2=editText2.getText().toString();

				editText3=(EditText) findViewById(R.id.EditView_QQ);
				final String inputText3=editText3.getText().toString();
				
				editText4=(EditText) findViewById(R.id.EditView_WX);
				final String inputText4=editText4.getText().toString();
				
				progressDialog.show();
				
				
				new Thread(new Runnable() {  
					  
		            @Override  
		            public void run() {  
		                // TODO Auto-generated method stub  
		                try {
		                		HttpRequest httpRequest=new HttpRequest();
		                		
		                		String s=httpRequest.sendPost("http://192.168.1.156:8082/ebook/register.do",
		                				"user="+inputText1+"&pwd="+inputText2+"&qq="+inputText3+"&weixin="+inputText4);
		                		
//		        
		                		Message msg = new Message();  
		         		        Bundle data = new Bundle();  
		         		        data.putString("value",s);  
		         		        msg.setData(data);  
		         		        handler.sendMessage(msg);
//		                			Looper.prepare();
//		                		    Toast.makeText(ActivityRegister.this,"服务器响应超时",Toast.LENGTH_LONG).show();  
//		                		    progressDialog.cancel();
//		                		    Looper.loop();
		                		
		                	}catch (Exception e) {  
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
				
//				// TODO Auto-generated method stub
//				Intent intent=new Intent(ActivityRegister.this,ActivitySetting.class);
//				startActivity(intent);
				ActivityRegister.this.finish();
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
				if(s.equals("{\"code\":\"success\"}")){
					//弹出
					SuccessShow("注册成功");
					
					//关闭
					progressDialog.cancel();
				
					finish();
					
				}else{
					
					SuccessShow("注册失败");
					progressDialog.cancel();
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
