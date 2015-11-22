package com.truong.handle;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.e_learnning.AllCategory;
import com.truong.http.UserFunctions;
import com.truong.modle.Answer;
import com.truong.modle.Category;
import com.truong.modle.Data;
import com.truong.modle.Lesson;
import com.truong.modle.User;

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
		if (Data.data == null)
			Data.data = new ArrayList<Data>();
		Data.data.clear();

		if (result != null) {
			try {
				Lesson.Lesson_id = result.getString("id");
				Lesson.created_at_le=result.getString("created_at");

				JSONArray arrayWord = result.getJSONArray("words");
				for (int i = 0; i < arrayWord.length(); i++) {
					JSONObject object = arrayWord.getJSONObject(i);

					String content = object.getString("content");
					JSONArray arrayAnswer = object.getJSONArray("word_answers");

					ArrayList<Answer> data = new ArrayList<Answer>();

					for (int j = 0; j < arrayAnswer.length(); j++) {
						JSONObject part = arrayAnswer.getJSONObject(j);
						data.add(new Answer(part.getString("content"), part
								.getBoolean("correct")));
					}

					Data.data.add(new Data(content, data));
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Log.d("Truong", "Loi --e" + e);
			}

			Toast.makeText(Category.activity, "Create Lesson Ok",
					Toast.LENGTH_LONG).show();
			new LoadLesson().execute(Category.functions);

			AllCategory.viewFlipper
					.setInAnimation(com.truong.modle.Lesson.go_next_in);
			AllCategory.viewFlipper
					.setOutAnimation(com.truong.modle.Lesson.go_next_out);
			AllCategory.viewFlipper.showNext();

			AllCategory.layout13.setVisibility(View.VISIBLE);
		}

	}
}
