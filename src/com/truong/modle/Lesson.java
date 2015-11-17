package com.truong.modle;

import java.util.ArrayList;

import android.view.animation.Animation;

public class Lesson {
	public static Animation go_next_in, go_next_out, go_prev_in, go_prev_out,
			scale_toast;
	public static String Category_id, User_id, Lesson_id;
	public static ArrayList<LessonWord> allWord = new ArrayList<LessonWord>();
	public static int index;
	public static int result = 0;
	public static String perent_id;
	public static String delete_id;

	public String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Lesson(String id, String category_id, String lesson_result,
			String created_at) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.lesson_result = lesson_result;
		this.created_at = created_at;
	}

	public String category_id;
	public String lesson_result;
	public String created_at;

	public Lesson() {

	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getLesson_result() {
		return lesson_result;
	}

	public void setLesson_result(String lesso_result) {
		this.lesson_result = lesso_result;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
