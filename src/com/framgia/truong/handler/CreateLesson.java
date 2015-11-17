package com.framgia.truong.handler;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.elearnning.Home;
import com.example.elearnning.R;
import com.truong.http.UserFunctions;
import com.truong.modle.Category;
import com.truong.modle.Lesson;
import com.truong.modle.User;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

public class CreateLesson extends AsyncTask<UserFunctions, Void, JSONObject> {
	public static JSONObject object;

	@Override
	protected JSONObject doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		object = null;
		return params[0].createLesson(Lesson.User_id + "", Lesson.Category_id
				+ "");
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		object = result;
		if (result != null) {
			com.example.elearnning.Lesson.viewFlipper
					.setInAnimation(com.truong.modle.Lesson.go_next_in);
			com.example.elearnning.Lesson.viewFlipper
					.setOutAnimation(com.truong.modle.Lesson.go_next_out);
			com.example.elearnning.Lesson.viewFlipper.showNext();
			new CreateAllQuestions().execute(Category.functions);

		}
	}

}
