package com.truong.handle;

import org.json.JSONObject;

import com.truong.http.UserFunctions;
import com.truong.modle.Category;
import com.truong.modle.User;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class CreateLesson extends AsyncTask<UserFunctions, Void, JSONObject> {
	@Override
	protected JSONObject doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		return params[0].createLesson(User.category_id);
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result != null) {
			Toast.makeText(Category.activity, "Create Lesson Ok",
					Toast.LENGTH_LONG).show();
			new LoadLesson().execute(Category.functions);
		}

	}

}
