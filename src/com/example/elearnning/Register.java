package com.example.elearnning;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends Activity{
	Button previuous_re;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
//		Tham chieu den cac View tuong ung
		previuous_re=(Button)findViewById(R.id.previous_re);
		
//		Set su kien cac view
		previuous_re.setOnClickListener(onCloseListener());
	}
	
	public View.OnClickListener onCloseListener(){
		return new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		};
	}
}
