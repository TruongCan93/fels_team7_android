package com.framgia.truong.handler;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.elearnning.Home;
import com.truong.adapter.LessonAdapter;
import com.truong.http.UserFunctions;
import com.truong.modle.Lesson;
import com.truong.modle.User;

import android.os.AsyncTask;
import android.util.Log;

public class LoadLessonById extends AsyncTask<UserFunctions, Void, JSONArray> {
	@Override
	protected JSONArray doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		return params[0].loadLesson(User.id + "");
	}

	@Override
	protected void onPostExecute(JSONArray result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (User.allLesson == null)
			User.allLesson = new ArrayList<Lesson>();
		User.allLesson.clear();
		if (result != null) {
			try {
				for (int i = 0; i < result.length(); i++) {
					JSONObject object = result.getJSONObject(i);
					User.allLesson.add(
							0,
							new Lesson(object.getString("id"), object
									.getString("category_id"), object
									.getString("result"), object
									.getString("created_at")));
				}
				Home.adapter.notifyDataSetChanged();
				Log.d("Truong", User.allLesson.size() + "");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Log.d("Truong", "loi e" + e);
			}
		}
		Home.adapter.notifyDataSetChanged();
	}
}
