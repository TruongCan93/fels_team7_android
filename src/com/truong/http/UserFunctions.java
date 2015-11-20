package com.truong.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.e_learnning.WordList;
import com.truong.modle.Lesson;
import com.truong.modle.User;

public class UserFunctions {

	private static final String Url_login = "https://protected-earth-1676.herokuapp.com/login.json";

	private static final String Url_register = "https://protected-earth-1676.herokuapp.com/users.json";

	private static final String Url_load_categorys = "https://protected-earth-1676.herokuapp.com/categories.json";

	private static final String Url_lesson = "https://protected-earth-1676.herokuapp.com/users/5/lessons.json";

	private static final String Url_load_word = "https://protected-earth-1676.herokuapp.com/users/3/words.json";

	JSONParser parser;

	public UserFunctions() {
		parser = new JSONParser();
	}

	public JSONArray getListWord() {
		JSONArray jArr;
		Log.d("Truong", "Chan vai"+Lesson.action);
		if (Lesson.action.equals("Filter not learn"))
			jArr = parser.getJsonArrayFromUrl(Url_load_word
					+ "?utf8=✓&category=" + WordList.category_id
					+ "&commit=Filter", "GET", "");
		else if (Lesson.action.equals("Filter has learn"))
			jArr = parser.getJsonArrayFromUrl(Url_load_word
					+ "?utf8=✓&category=" + WordList.category_id + "&learn="
					+ WordList.learn + "&commit=Filter", "GET", "");
		else
			jArr = parser.getJsonArrayFromUrl(Url_load_word, "GET", "");

		if (null != jArr)
			return jArr;
		return null;
	}

	public JSONArray getLessonById(String user_id) {
		JSONArray jArr = parser.getJsonArrayFromUrl(
				"https://protected-earth-1676.herokuapp.com/users/" + user_id
						+ "/lessons.json", "GET", "");
		if (jArr != null)
			return jArr;
		return null;
	}

	public JSONObject createLesson(String category_id) {
		JSONObject object = new JSONObject();
		JSONObject lesson = new JSONObject();

		try {
			lesson.put("category_id", category_id);
			lesson.put("user_id", User.id + "");
			object.put("lesson", lesson);
			Log.d("Truong", object.toString());
		} catch (Exception e) {
			// TODO: handexception
			e.printStackTrace();
			Log.d("Truong", "Loi login of UserFunctions.class " + e);
		}
		JSONObject json = parser.makeHttpRequest(Url_lesson, "POST", object);

		if (json != null)
			return json;
		return null;
	}

	public JSONArray loadCategorys() {
		JSONArray jArr = parser.getJsonArrayFromUrl(Url_load_categorys, "GET",
				"");
		if (jArr != null)
			return jArr;
		return null;
	}

	public JSONObject registerUser(String name, String email, String password,
			String password_confirmation) {
		Log.d("Truong", name + email + password + password_confirmation);
		JSONObject object = new JSONObject();
		JSONObject user = new JSONObject();
		try {
			user.put("password_confirmation", password_confirmation);
			user.put("password", password);
			user.put("email", email);
			user.put("name", name);
			object.put("user", user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.d("Truong", "Loi register +" + e);
		}
		JSONObject json = parser.makeHttpRequest(Url_register, "POST", object);
		if (json != null)
			return json;
		return null;
	}

	public JSONObject loginUser(String email, String password) {
		JSONObject object = new JSONObject();
		JSONObject session = new JSONObject();

		try {
			session.put("email", email);
			session.put("password", password);
			object.put("session", session);
		} catch (Exception e) {
			// TODO: handexception
			e.printStackTrace();
			Log.d("Truong", "Loi login of UserFunctions.class " + e);
		}
		JSONObject json = parser.makeHttpRequest(Url_login, "POST", object);

		if (json != null)
			return json;
		return null;
	}

}
