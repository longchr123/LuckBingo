package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.widget.CheckBox;

import com.example.activity.R;

public class GridViewAdapter extends CommonAdapter<String>{
	
	private boolean isSecondAward;
	
	public GridViewAdapter(Context context, List<String> mDatas,
			int itemLayoutId) {
		super(context, mDatas, itemLayoutId);
	}
	
	public GridViewAdapter(Context context, List<String> mDatas,
			int itemLayoutId, boolean isSecondAward) {
		super(context, mDatas, itemLayoutId);
		this.isSecondAward=isSecondAward;
	}

	@Override
	public void convert(ViewHolder helper, String item, int position) {
		// TODO Auto-generated method stub
		CheckBox checkBox=((CheckBox)helper.getView(R.id.gv_item));
		checkBox.setText(item);
		if (position==12) {
			checkBox.setChecked(true);
			checkBox.setClickable(false);
		}else {
			checkBox.setClickable(false);
		}
		
		if (isSecondAward) {
			checkBox.setFocusable(true);
		}
		
	}

}
