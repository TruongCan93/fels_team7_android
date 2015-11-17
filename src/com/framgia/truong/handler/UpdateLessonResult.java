package com.framgia.truong.handler;

import org.json.JSONObject;

import com.truong.http.UserFunctions;
import com.truong.modle.Lesson;

import android.os.AsyncTask;

public class UpdateLessonResult extends
		AsyncTask<UserFunctions, Void, JSONObject> {
	@Override
	protected JSONObject doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		return params[0].updateLessonResult(Lesson.result + "",
				Lesson.Lesson_id);
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
