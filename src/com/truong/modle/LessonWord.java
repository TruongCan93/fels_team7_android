package com.truong.modle;

public class LessonWord {
	public String id;
	public String lesson_id;
	public String word_id;
	public String word_answer_id;

	public LessonWord(String id, String lesson_id, String word_id,
			String word_answer_id) {
		super();
		this.id = id;
		this.lesson_id = lesson_id;
		this.word_id = word_id;
		this.word_answer_id = word_answer_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLesson_id() {
		return lesson_id;
	}

	public void setLesson_id(String lesson_id) {
		this.lesson_id = lesson_id;
	}

	public String getWord_id() {
		return word_id;
	}

	public void setWord_id(String word_id) {
		this.word_id = word_id;
	}

	public String getWord_answer_id() {
		return word_answer_id;
	}

	public void setWord_answer_id(String word_answer_id) {
		this.word_answer_id = word_answer_id;
	}

}
