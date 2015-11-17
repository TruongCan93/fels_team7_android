package com.framgia.truong.handler;

import org.json.JSONObject;

import com.truong.http.UserFunctions;
import com.truong.modle.Category;
import com.truong.modle.Lesson;

import android.os.AsyncTask;
import android.util.Log;

public class DeleteLessonById extends
		AsyncTask<UserFunctions, Void, JSONObject> {
	@Override
	protected JSONObject doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		return params[0].deleteLesson(Lesson.delete_id);
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result != null)
			Log.d("Truong", "delete ok");
		else
			Log.d("Truong", "delete not ok");
		new LoadLessonById().execute(Category.functions);
	}

}
