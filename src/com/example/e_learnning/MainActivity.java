package com.example.e_learnning;

import org.json.JSONObject;

import truong.shafre.SharePreUtils;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.truong.http.UserFunctions;
import com.truong.modle.Category;
import com.truong.modle.User;

public class MainActivity extends Activity {
	Button btn_login;
	TextView txt_create, toast, login, email_icon, password_icon;
	EditText email, password;

	String mail, pass;
	Bundle bundle;

	public static Typeface tf1, tf3, tf4, tf5, tf6, tf7;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		btn_login = (Button) findViewById(R.id.btn_login);
		txt_create = (TextView) findViewById(R.id.create_new_account);
		email = (EditText) findViewById(R.id.edit_email);
		password = (EditText) findViewById(R.id.edit_password);
		toast = (TextView) findViewById(R.id.toast);
		login = (TextView) findViewById(R.id.login);
		email_icon = (TextView) findViewById(R.id.email_tt);
		password_icon = (TextView) findViewById(R.id.pass_tt);

		loadTypeface();

		login.setTypeface(tf1);
		email_icon.setTypeface(tf3);
		password_icon.setTypeface(tf3);
		email.setTypeface(tf4);
		password.setTypeface(tf4);

		btn_login.setOnClickListener(loginOnClickListener());
		btn_login.setOnTouchListener(loginOnTouchListener());
		txt_create.setOnClickListener(newAcountListener());

		// Khoi tao bien
		Category.functions = new UserFunctions();
	}

	public void loadTypeface() {
		tf1 = Typeface.createFromAsset(getAssets(), "fonts/AGENTRED.TTF");

		tf3 = Typeface.createFromAsset(getAssets(), "fonts/grease__.ttf");
		tf4 = Typeface.createFromAsset(getAssets(), "fonts/UVF LH Line1 Sans Thin.ttf");
		tf5 = Typeface.createFromAsset(getAssets(), "fonts/PotLand.ttf");
		tf6 = Typeface.createFromAsset(getAssets(), "fonts/architep.ttf");
		tf7=Typeface.createFromAsset(getAssets(), "fonts/Pony.ttf");
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
			super.onPostExecute(result);
			if (result == null) {
				toast.setText("Connection errors");
				toast.setVisibility(View.VISIBLE);
			} else {
				try {
					User.id = Integer.parseInt(result.getString("id"));
					User.email = result.getString("email");
					User.fullname = result.getString("name");

					SharePreUtils.setString(MainActivity.this,
							SharePreUtils.EMAIL, User.email + "");
					SharePreUtils.setString(MainActivity.this,
							SharePreUtils.PASSWORD, pass);
					SharePreUtils.setString(MainActivity.this,
							SharePreUtils.FULL_NAME, User.fullname);
					SharePreUtils.setString(MainActivity.this,
							SharePreUtils.USER_ID, User.id + "");

					Intent intent = new Intent(MainActivity.this, Home.class);
					startActivity(intent);
					finish();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					Log.d("Truong", "Loi" + e);
					toast.setText("Not found User");
					toast.setVisibility(View.VISIBLE);
				}
			}
			Log.d("Truong", "onPostExecute");
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		String reUserid = SharePreUtils.getString(MainActivity.this,
				SharePreUtils.USER_ID, "");
		String reEmail = SharePreUtils.getString(MainActivity.this,
				SharePreUtils.EMAIL, "");
		String rePassword = SharePreUtils.getString(MainActivity.this,
				SharePreUtils.PASSWORD, "");
		String reFullname = SharePreUtils.getString(MainActivity.this,
				SharePreUtils.FULL_NAME, "");

		if (reUserid != "") {
			User.id = Integer.parseInt(reUserid);
			User.email = reEmail;
			User.fullname = reFullname;
			Log.d("Truong", "Chan " + reEmail + "---" + rePassword);
			mail = User.email;
			pass = rePassword;

			new Login().execute(new UserFunctions());
		}

		super.onResume();
	}
}
