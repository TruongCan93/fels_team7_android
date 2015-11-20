package com.example.e_learnning;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.adapter.MyAdapter;
import com.truong.handle.CreateLesson;
import com.truong.modle.Category;
import com.truong.modle.Lesson;
import com.truong.modle.User;

public class AllCategory extends Activity {
	ListView list;
	Button previous, previous_le, start_le;
	public static ViewFlipper viewFlipper;
	TextView title_lesson;
	ImageView img;
	TextView toast;

	public static RelativeLayout layout13, layout14;
	public static Button A, B, C, D;
	public static TextView an, number;
	public static Dialog dialog;

	MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lessonslide);
		// Tham chieu den cac view tuong ung
		list = (ListView) findViewById(R.id.list_category);
		previous = (Button) findViewById(R.id.previous_ca);
		previous_le = (Button) findViewById(R.id.previous_ca_le);
		viewFlipper = (ViewFlipper) findViewById(R.id.vfMainFliper2);
		title_lesson = (TextView) findViewById(R.id.title_le);
		img = (ImageView) findViewById(R.id.img_le);
		toast = (TextView) findViewById(R.id.lesson_toast);
		layout13 = (RelativeLayout) findViewById(R.id.layout13);
		layout14 = (RelativeLayout) findViewById(R.id.layout14);
		start_le = (Button) findViewById(R.id.start_le);
		A = (Button) findViewById(R.id.A_le);
		B = (Button) findViewById(R.id.B_le);
		C = (Button) findViewById(R.id.C_le);
		D = (Button) findViewById(R.id.D_le);
		an = (TextView) findViewById(R.id.answer_le);
		number = (TextView) findViewById(R.id.socau);

		// Set cac su kien
		previous.setOnClickListener(backHome());
		list.setOnItemClickListener(onClickItem());

		adapter = new MyAdapter(this, Category.data);

		list.setAdapter(adapter);
	}

	public AdapterView.OnItemClickListener onClickItem() {
		return new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final int index=position;
				AlertDialog.Builder builder = new AlertDialog.Builder(
						AllCategory.this);
				builder.setTitle("Are you start lesson: "
						+ Category.data.get(position).getName());
				builder.setCancelable(false);
				builder.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
							}
						});
				builder.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								User.category_id=Category.data.get(index).getId()+"";
								new CreateLesson().execute(Category.functions);
							}
						});
				builder.show();
			}
		};
	}

	public View.OnClickListener backHome() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		};
	}

}
