package com.truong.modle;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;

import com.truong.http.UserFunctions;

public class Category {
	public static UserFunctions functions;
	public static ArrayList<Category> data;
	public static Activity activity;

	int id;
	String name;
	String image;
	Bitmap bitmap;

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public Category(int id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
