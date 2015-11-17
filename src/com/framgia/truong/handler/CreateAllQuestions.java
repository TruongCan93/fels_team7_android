package com.framgia.truong.handler;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.truong.http.UserFunctions;
import com.truong.modle.Lesson;
import com.truong.modle.LessonWord;

public class CreateAllQuestions extends
		AsyncTask<UserFunctions, Void, JSONArray> {
	JSONArray lessonword;
	JSONObject object;

	@Override
	protected JSONArray doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		if (CreateLesson.object != null) {
			JSONObject lesson = null;
			try {
				String success = CreateLesson.object.getString("success");
				if (Integer.parseInt(success) == 1) {
					lesson = CreateLesson.object.getJSONObject("lesson");
					Lesson.Lesson_id = lesson.getString("id");
					Lesson.Category_id = lesson.getString("category_id");
					lessonword = params[0].createLessonWord(Lesson.Lesson_id,
							"1");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Log.d("Truong", "Loi load lesson" + e);
			}
		}
		return lessonword;
	}

	@Override
	protected void onPostExecute(JSONArray result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (Lesson.allWord == null)
			Lesson.allWord = new ArrayList<LessonWord>();
		Lesson.allWord.clear();
		if (lessonword != null) {
			try {
				for (int i = 0; i < lessonword.length(); i++) {
					object = lessonword.getJSONObject(i);
					Lesson.allWord.add(new LessonWord(object.getString("id"),
							object.getString("lesson_id"), object
									.getString("word_id"), object
									.getString("word_answer_id")));
					com.example.elearnning.Lesson.layout13
							.setVisibility(View.VISIBLE);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Log.d("Truong", "Loi load lesson word vao array " + e);
			}

		}
	}

}
