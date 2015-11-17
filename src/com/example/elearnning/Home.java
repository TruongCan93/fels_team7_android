package com.example.elearnning;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.framgia.truong.handler.DeleteLessonById;
import com.framgia.truong.handler.LoadCategory;
import com.framgia.truong.handler.LoadLessonById;
import com.framgia.truong.handler.LoadLessonDetail;
import com.truong.adapter.LessonAdapter;
import com.truong.modle.Category;
import com.truong.modle.Lesson;
import com.truong.modle.User;

public class Home extends Activity {
	Button btn_word_list, btn_lesson, previous_up;
	TextView fullname, email;
	ViewFlipper viewFlipper;
	ImageView edit_ho;
	ListView list;

	public static LessonAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainslide);

		// Tham chieu den cac view tuong unng
		btn_lesson = (Button) findViewById(R.id.lesson_ho);
		btn_word_list = (Button) findViewById(R.id.word_list_ho);
		viewFlipper = (ViewFlipper) findViewById(R.id.vfMainFliper1);
		edit_ho = (ImageView) findViewById(R.id.edit_ho);
		previous_up = (Button) findViewById(R.id.previous_up);
		fullname = (TextView) findViewById(R.id.full_name_ho);
		email = (TextView) findViewById(R.id.email_ho);
		list = (ListView) findViewById(R.id.list_le);

		// Set du lieu
		fullname.setText(User.fullname);
		email.setText(User.email);
		User.allLesson = new ArrayList<Lesson>();
		adapter = new LessonAdapter(User.allLesson, this);
		list.setAdapter(adapter);

		// Load file Animation
		Lesson a = new Lesson();
		Lesson.go_next_in = AnimationUtils.loadAnimation(this,
				R.anim.go_next_in);
		Lesson.go_next_out = AnimationUtils.loadAnimation(this,
				R.anim.go_next_out);
		Lesson.go_prev_in = AnimationUtils.loadAnimation(this,
				R.anim.go_prev_in);
		Lesson.go_prev_out = AnimationUtils.loadAnimation(this,
				R.anim.go_prev_out);

		// Set su kien cho cac view
		btn_lesson.setOnTouchListener(onRedBtnLessonClickListener());
		btn_lesson.setOnClickListener(goCategory());
		edit_ho.setOnClickListener(goUpdateProfiles());
		previous_up.setOnClickListener(backHome());
		// btn_word_list.setOnTouchListener(onRedBtnClickListener());
		// list.setOnItemLongClickListener(longItem());
		list.setOnItemClickListener(itemClick());
		btn_word_list.setOnClickListener(goWordList());

		// Load lesson
		new LoadLessonById().execute(Category.functions);

	}

	public View.OnClickListener goWordList() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				User.Action="WordList";
				Category.activity = Home.this;
				new LoadCategory().execute(Category.functions);
			}
		};
	}

	public AdapterView.OnItemClickListener itemClick() {
		return new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showPopupMenu(view, position);
			}
		};
	}

	public void showPopupMenu(View v, int position) {
		final int inext = position;
		PopupMenu popup = new PopupMenu(Home.this, v);
		popup.getMenuInflater().inflate(R.menu.sms_menu, popup.getMenu());
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				switch (item.getItemId()) {
				case R.id.show_result_home: {
					User.lessn_id = User.allLesson.get(inext).getId();
					Lesson.result = Integer.parseInt(User.allLesson.get(inext)
							.getLesson_result());
					User.context = Home.this;
					new LoadLessonDetail().execute(Category.functions);
					break;
				}
				case R.id.delete_lesson_home: {
					Lesson.delete_id = User.allLesson.get(inext).getId();
					new DeleteLessonById().execute(Category.functions);
					break;
				}
				}
				return false;
			}
		});
		popup.show();
	}

	public AdapterView.OnItemLongClickListener longItem() {
		return new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Lesson.delete_id = User.allLesson.get(position).getId();
				new DeleteLessonById().execute(Category.functions);
				return false;
			}
		};
	}

	public View.OnClickListener goCategory() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				User.Action="Lesson";
				Category.activity = Home.this;
				new LoadCategory().execute(Category.functions);
			}
		};
	}

	public View.OnClickListener backHome() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewFlipper.setInAnimation(Lesson.go_prev_in);
				viewFlipper.setOutAnimation(Lesson.go_prev_out);
				viewFlipper.showPrevious();
			}
		};
	}

	public View.OnClickListener goUpdateProfiles() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewFlipper.setInAnimation(Lesson.go_next_in);
				viewFlipper.setOutAnimation(Lesson.go_next_out);
				viewFlipper.showNext();
			}
		};
	}

	public View.OnTouchListener onRedBtnLessonClickListener() {
		return new View.OnTouchListener() {
			int xdau, xcuoi;
			int ydau, ycuoi;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					xdau = (int) event.getX();
					ydau = (int) event.getY();
					btn_lesson.setTextColor(Color.parseColor("#dbE42217"));
				}
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					xcuoi = (int) event.getX();
					ycuoi = (int) event.getY();
					if (Math.abs(xdau - xcuoi) > 70
							|| Math.abs(ydau - ycuoi) > 30)
						btn_lesson.setTextColor(Color.parseColor("#ffffff"));
					else
						btn_lesson.setTextColor(Color.parseColor("#dbE42217"));
				}
				if (event.getAction() == MotionEvent.ACTION_UP)
					btn_lesson.setTextColor(Color.parseColor("#ffffff"));
				return false;
			}
		};
	}

}
