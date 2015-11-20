package com.truong.handle;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.e_learnning.AllCategory;
import com.example.e_learnning.Home;
import com.example.e_learnning.WordList;
import com.truong.http.UserFunctions;
import com.truong.modle.Category;
import com.truong.modle.Lesson;
import com.truong.modle.User;

public class LoadCategory extends AsyncTask<UserFunctions, Void, JSONArray> {
	@Override
	protected JSONArray doInBackground(UserFunctions... params) {
		// TODO Auto-generated method stub
		// Category.data = new ArrayList<Category>();
		return params[0].loadCategorys();
	}

	@Override
	protected void onPostExecute(JSONArray result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (Category.data == null)
			Category.data = new ArrayList<Category>();
		Category.data.clear();
		if (result != null) {
			try {
				JSONObject object = null;
				for (int i = 0; i < result.length(); i++) {
					object = result.getJSONObject(i);
					Category.data.add(new Category(Integer.parseInt(object
							.getString("id")), object.getString("name"), object
							.getString("created_at")));
				}

				if (User.Action.equals("Lesson")) {
					Intent intent = new Intent(Category.activity,
							AllCategory.class);
					Category.activity.startActivity(intent);
				}
				if (User.Action.equals("WordList")) {
					Intent intent = new Intent(Category.activity,
							WordList.class);
					Category.activity.startActivity(intent);
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.d("Truong", "Loi load Category " + e);
			}
		}
	}
}
