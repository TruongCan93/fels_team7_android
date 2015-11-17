package com.example.elearnning;

import java.util.ArrayList;

import com.framgia.truong.handler.LoadCategory;
import com.framgia.truong.handler.LoadWordByCategoryId;
import com.truong.adapter.SpinnerAdapter;
import com.truong.modle.Category;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class WordList extends Activity {
	Spinner spin;
	ArrayList<String> dataSpinner;
	ListView list;
	public static String category_id;

	SpinnerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wordlist);
		spin = (Spinner) findViewById(R.id.spinner);
		list = (ListView) findViewById(R.id.wl_list);
		dataSpinner = new ArrayList<String>();
		dataSpinner.add("All");
		for (Category part : Category.data) {
			dataSpinner.add(part.getName());
		}
		category_id = 0 + "";
		adapter = new SpinnerAdapter(dataSpinner, this);
		// Thiết lập adapter cho Spinner
		spin.setAdapter(adapter);
		new LoadWordByCategoryId().execute(Category.functions);
		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView text = (TextView) ((RelativeLayout) parent
						.getChildAt(0)).findViewById(R.id.text_spinner_item);
				text.setTextColor(Color.RED);
				text.setTypeface(Typeface.DEFAULT_BOLD);
				((RelativeLayout) parent.getChildAt(0))
						.setBackgroundColor(Color.WHITE);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
	}
}
