package com.truong.modle;

import java.util.ArrayList;

import android.app.Activity;

import com.truong.http.UserFunctions;

public class Category {
	public static UserFunctions functions;
	public static ArrayList<Category> data;
	public static Activity activity;

	int id;
	String name;
	String created_at;

	

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public Category(int id, String name, String created_at) {
		super();
		this.id = id;
		this.name = name;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
