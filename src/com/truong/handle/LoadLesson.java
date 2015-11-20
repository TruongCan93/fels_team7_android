package com.truong.handle;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.e_learnning.AllCategory;
import com.example.e_learnning.Home;
import com.truong.http.UserFunctions;
import com.truong.modle.Category;
import com.truong.modle.Lesson;
import com.truong.modle.User;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class LoadLesson extends AsyncTask<UserFunctions, Void, JSONArray> {
	@Override
	protected JSONArray doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		return params[0].getLessonById(User.id + "");
	}

	@Override
	protected void onPostExecute(JSONArray result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (Lesson.data == null)
			Lesson.data = new ArrayList<Lesson>();
		Lesson.data.clear();
		if (result != null) {
			try {
				JSONObject object = null;
				for (int i = 0; i < result.length(); i++) {
					object = result.getJSONObject(i);
					Lesson.data.add(
							0,
							new Lesson(object.getString("id"), object
									.getString("category_id"), object
									.getString("result"), object
									.getString("created_at")));
				}
				Home.adapter.notifyDataSetChanged();

			} catch (Exception e) {
				// TODO: handle exception
				Log.d("Truong", "Loi load Lesson " + e);
			}
		}
	}

}
