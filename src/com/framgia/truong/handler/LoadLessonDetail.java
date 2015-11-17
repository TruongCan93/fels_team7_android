package com.framgia.truong.handler;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.elearnning.Lesson;
import com.truong.http.UserFunctions;
import com.truong.modle.Category;
import com.truong.modle.ResutlModle;
import com.truong.modle.User;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class LoadLessonDetail extends AsyncTask<UserFunctions, Void, JSONArray> {
	@Override
	protected JSONArray doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		return params[0].loadLessonDetail(User.lessn_id + "");
	}

	@Override
	protected void onPostExecute(JSONArray result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (User.result == null)
			User.result = new ArrayList<ResutlModle>();
		User.result.clear();
		if (result != null) {
			try {
				JSONObject object = null;
				for (int i = 0; i < result.length(); i++) {
					object = result.getJSONObject(i);
					User.result.add(new ResutlModle(
							object.getString("content"), object
									.getString("answer"), (object
									.getString("correct").equals("1")) ? true
									: false));
				}
				Lesson.createDialog(User.context);
				Log.d("Truong", User.result.size() + "");
			} catch (Exception e) {
				// TODO: handle exception
				Log.d("Truong", "Loi load Category " + "");
			}
		}
	}

}
