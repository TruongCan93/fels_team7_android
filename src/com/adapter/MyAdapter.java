package com.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_learnning.R;
import com.truong.modle.Category;

public class MyAdapter extends BaseAdapter {
	ArrayList<Category> data;
	Context context;

	public MyAdapter(Context context, ArrayList<Category> data) {
		this.data = data;
		this.context = context;
	}

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
		if (convertView != null) {
			hodle = (ViewHodle) convertView.getTag();
		} else {
			convertView = inflater.inflate(R.layout.category_item, parent,
					false);
			hodle = new ViewHodle();
			hodle.title = (TextView) convertView
					.findViewById(R.id.title_item_ca);
			hodle.body = (TextView) convertView.findViewById(R.id.body_ca);
			hodle.image = (ImageView) convertView.findViewById(R.id.img_ca);
			convertView.setTag(hodle);
		}
		hodle.title.setText(data.get(position).getName());
		hodle.body.setText(data.get(position).getCreated_at());
		if (position == 0)
			hodle.image.setImageDrawable(context.getResources().getDrawable(
					R.drawable.basic_img));
		else if (position == 1)
			hodle.image.setImageDrawable(context.getResources().getDrawable(
					R.drawable.advance_img));
		else
			hodle.image.setImageDrawable(context.getResources().getDrawable(
					R.drawable.expert_img));
		return convertView;
	}

	private class ViewHodle {
		TextView title;
		TextView body;
		ImageView image;
	}
}
