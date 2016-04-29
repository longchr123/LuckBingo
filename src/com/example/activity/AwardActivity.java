package com.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class AwardActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.awardtoast);
	}
}
