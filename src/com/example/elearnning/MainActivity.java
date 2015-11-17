package com.example.elearnning;

import org.json.JSONObject;

import com.framgia.truong.handler.LoadLessonById;
import com.truong.http.UserFunctions;
import com.truong.modle.Category;
import com.truong.modle.User;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btn_login;
	TextView txt_create, toast;
	EditText email, password;

	String mail, pass;
	Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		// Tham chieu den cac view tuong ung
		btn_login = (Button) findViewById(R.id.btn_login);
		txt_create = (TextView) findViewById(R.id.create_new_account);
		email = (EditText) findViewById(R.id.edit_email);
		password = (EditText) findViewById(R.id.edit_password);
		toast = (TextView) findViewById(R.id.toast);

		// Set su kien cac view
		btn_login.setOnClickListener(loginOnClickListener());
		btn_login.setOnTouchListener(loginOnTouchListener());
		txt_create.setOnClickListener(newAcountListener());

		// Khoi tao bien
		Category.functions = new UserFunctions();
	}

	public View.OnClickListener newAcountListener() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						Register.class);
				startActivity(intent);
			}
		};
	}

	public View.OnTouchListener loginOnTouchListener() {
		return new View.OnTouchListener() {
			int xdau, xcuoi;
			int ydau, ycuoi;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					xdau = (int) event.getX();
					ydau = (int) event.getY();
					btn_login.setTextColor(Color.parseColor("#347C17"));
				}
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					xcuoi = (int) event.getX();
					ycuoi = (int) event.getY();
					if (Math.abs(xdau - xcuoi) > 70
							|| Math.abs(ydau - ycuoi) > 30)
						btn_login.setTextColor(Color.parseColor("#ffffff"));
					else
						btn_login.setTextColor(Color.parseColor("#347C17"));
				}
				if (event.getAction() == MotionEvent.ACTION_UP)
					btn_login.setTextColor(Color.parseColor("#ffffff"));
				return false;
			}
		};
	}

	public View.OnClickListener loginOnClickListener() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (email.getText().toString().equals("")
						|| password.getText().toString().equals("")) {
					toast.setVisibility(View.VISIBLE);
					toast.setText("Invalid informations");
				} else {
					mail = email.getText().toString();
					pass = password.getText().toString();
					new Login().execute(Category.functions);
				}
			}
		};
	}

	private class Login extends AsyncTask<UserFunctions, Void, JSONObject> {
		JSONObject object = null;

		Login() {
		}

		@Override
		protected JSONObject doInBackground(UserFunctions... params) {
			// TODO Auto-generated method stub
			Log.d("Truong", "doInBackground");
			return params[0].loginUser(mail, pass);
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result == null) {
				toast.setText("Connection errors");
				toast.setVisibility(View.VISIBLE);
			} else {
				try {
					if (result.getString("success") != null) {
						String res = result.getString("success");
						if (Integer.parseInt(res) == 1) {
							object = result.getJSONObject("user");
							User.id = Integer.parseInt(object.getString("id"));
							User.email = object.getString("email");
							User.password = object.getString("password");
							User.fullname = object.getString("fullname");
							Intent intent = new Intent(getApplicationContext(),
									Home.class);
							startActivity(intent);
							finish();
						}
					} else {
						toast.setText("Inconnect Email or Password");
						toast.setVisibility(View.VISIBLE);
					}
				} catch (Exception e) {
					e.printStackTrace();
					Log.d("Truong", "Loi Login " + e + "");
				}
			}
			Log.d("Truong", "onPostExecute");
		}
	}
}
