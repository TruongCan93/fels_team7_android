package com.truong.adapter;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.example.elearnning.R;
import com.truong.modle.Category;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
		ImageView cur = hodle.image;
		new LoadImageCategory().execute(data.get(position).getImage() + ".png",
				cur);

		hodle.title.setText(data.get(position).getName());
		return convertView;
	}

	private class ViewHodle {
		TextView title;
		TextView body;
		ImageView image;
	}

	public class LoadImageCategory extends AsyncTask<Object, Void, Bitmap> {
		Bitmap bitmap;
		ImageView image;

		@Override
		protected Bitmap doInBackground(Object... params) {
			// TODO Auto-generated method stub
			if (params[1] != null)
				image = (ImageView) params[1];
			try {
				bitmap = BitmapFactory.decodeStream((InputStream) new URL(
						(String) params[0]).getContent());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Log.d("Truong", "Loi load image ca " + e);
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (image != null)
				image.setImageBitmap(result);
		}
	}

}
