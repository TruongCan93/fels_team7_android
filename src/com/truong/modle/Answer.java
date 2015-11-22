package com.truong.modle;

public class Answer {
	String content;
	boolean correct;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Answer(String content, boolean correct) {
		super();
		this.content = content;
		this.correct = correct;
	}

}
