package com.truong.modle;

import java.util.ArrayList;

public class Answer {
	public static ArrayList<Answer> data;

	String id;
	String content;
	String correct;

	public Answer(String id, String content, String correct) {
		super();
		this.id = id;
		this.content = content;
		this.correct = correct;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

}
