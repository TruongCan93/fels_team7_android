package com.example.e_learnning;

import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.truong.http.UserFunctions;

public class Register extends Activity {
	Button previuous_re, done;
	EditText email, password, retype_password, fullname;
	TextView toast, emal_tt, pass_tt, retype_tt, fullname_tt, ava_tt, tt_re;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		// Tham chieu den cac View tuong ung
		previuous_re = (Button) findViewById(R.id.previous_re);
		done = (Button) findViewById(R.id.btn_done);
		email = (EditText) findViewById(R.id.edit_email_re);
		password = (EditText) findViewById(R.id.edit_pass_re);
		retype_password = (EditText) findViewById(R.id.edit_retype_pass_re);
		fullname = (EditText) findViewById(R.id.edit_full_name_re);
		toast = (TextView) findViewById(R.id.toast_re);
		emal_tt = (TextView) findViewById(R.id.email_tt_re);
		pass_tt = (TextView) findViewById(R.id.pass_tt_re);
		retype_tt = (TextView) findViewById(R.id.rety_tt_re);
		fullname_tt = (TextView) findViewById(R.id.fullname_tt_re);
		ava_tt = (TextView) findViewById(R.id.ava_tt_re);
		tt_re = (TextView) findViewById(R.id.tt_register_tt);

		email.setTypeface(MainActivity.tf4);
		password.setTypeface(MainActivity.tf4);
		retype_password.setTypeface(MainActivity.tf4);
		fullname.setTypeface(MainActivity.tf4);
		ava_tt.setTypeface(MainActivity.tf3);
		emal_tt.setTypeface(MainActivity.tf3);
		pass_tt.setTypeface(MainActivity.tf3);
		retype_tt.setTypeface(MainActivity.tf3);
		fullname_tt.setTypeface(MainActivity.tf3);
		tt_re.setTypeface(MainActivity.tf5);

		// Set su kien cac view
		previuous_re.setOnClickListener(onCloseListener());
		done.setOnClickListener(donOK());
	}

	String strEmail, strPassword, strRetypePassword, strFullname;

	public View.OnClickListener donOK() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				strEmail = email.getText().toString();
				strPassword = password.getText().toString();
				strRetypePassword = retype_password.getText().toString();
				strFullname = fullname.getText().toString();

				if (strEmail.equals("") || strPassword.equals("")
						|| strRetypePassword.equals("")
						|| strFullname.equals("")) {
					toast.setText("Invalid Informations");
					toast.setVisibility(View.VISIBLE);
				} else {
					new RegisterUser().execute(new UserFunctions());
				}

			}
		};
	}

	private class RegisterUser extends
			AsyncTask<UserFunctions, Void, JSONObject> {
		@Override
		protected JSONObject doInBackground(UserFunctions... params) {
			// TODO Auto-generated method stub
			return params[0].registerUser(strFullname, strEmail, strPassword,
					strRetypePassword);
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result == null) {
				toast.setText("Register Fail");
				toast.setVisibility(View.VISIBLE);
			} else {
				toast.setText("Register Success");
				toast.setVisibility(View.VISIBLE);
			}
		}
	}

	public View.OnClickListener onCloseListener() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		};
	}
}
