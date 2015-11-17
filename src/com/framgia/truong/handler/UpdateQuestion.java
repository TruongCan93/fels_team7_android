package com.framgia.truong.handler;

import org.json.JSONArray;
import org.json.JSONObject;

import com.truong.http.UserFunctions;
import com.truong.modle.Category;
import com.truong.modle.Lesson;

import android.os.AsyncTask;
import android.util.Log;

public class UpdateQuestion extends AsyncTask<UserFunctions, Void, JSONObject> {
	@Override
	protected JSONObject doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		return params[0].updateQuestion(Lesson.allWord.get(Lesson.index)
				.getId(), Lesson.perent_id);
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result != null) {
			com.example.elearnning.Lesson.A.setEnabled(true);
			com.example.elearnning.Lesson.B.setEnabled(true);
			com.example.elearnning.Lesson.C.setEnabled(true);
			com.example.elearnning.Lesson.D.setEnabled(true);
			com.truong.modle.Lesson.index++;
			new LoadQuestion().execute(Category.functions);
		}
	}

}
