package com.truong.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.elearnning.R;
import com.truong.modle.Lesson;

public class LessonAdapter extends BaseAdapter {
	ArrayList<Lesson> data;
	Context context;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	public LessonAdapter(ArrayList<Lesson> data, Context context) {
		super();
		this.data = data;
		this.context = context;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewHodle hodle;
		if (convertView != null)
			hodle = (ViewHodle) convertView.getTag();
		else {
			hodle = new ViewHodle();
			convertView = inflater.inflate(R.layout.lesson_item, parent, false);
			hodle.category = (TextView) convertView.findViewById(R.id.le_ca);
			hodle.time = (TextView) convertView.findViewById(R.id.le_time);
			convertView.setTag(hodle);
		}
		hodle.time.setText(data.get(position).getCreated_at());
		if (data.get(position).getCategory_id().equals("1"))
			hodle.category.setText("Basic");
		else if (data.get(position).getCategory_id().equals("2"))
			hodle.category.setText("Advance");
		else if (data.get(position).getCategory_id().equals("3"))
			hodle.category.setText("Expert");
		return convertView;
	}

	private class ViewHodle {
		TextView category;
		TextView time;
	}

}
