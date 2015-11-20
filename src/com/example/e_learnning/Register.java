package com.example.e_learnning;

import org.json.JSONObject;

import com.truong.http.UserFunctions;
import com.truong.modle.Category;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends Activity {
	Button previuous_re, done;
	EditText email, password, retype_password, fullname;
	TextView toast;

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
