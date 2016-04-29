package com.example.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment.DrawingFragment;
import com.example.view.MyDialog;
import com.example.view.MyDialog.CloseDialogListener;
import com.shizhefei.view.indicator.FragmentListPageAdapter;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

/**
 * 抽奖者模式
 * 
 * @author Administrator
 */
public class DrawingActivity extends FragmentActivity implements OnClickListener,CloseDialogListener{

	private int num, math;
	private String mode;
	private boolean isSecondAward;

	private long exitTime = 0;
	private Button mRestartButton, mAwardButton;
	private ScrollIndicatorView indicator;
	private IndicatorViewPager indicatorViewPager;
	private LayoutInflater inflate;
	private ViewPager viewPager;
	private String[] names = { "卡片一", "卡片二", "卡片三"};
	private DrawingFragment fragment;
	private MyDialog myDialog;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_drawing);
		
		initView();
	}

	private void initView() {
		Intent intent = getIntent();
		math = intent.getIntExtra("math", 32);
		num = intent.getIntExtra("num", 1);
		mode = intent.getStringExtra("mode");
		mRestartButton = (Button) findViewById(R.id.btn_restart);
		mAwardButton = (Button) findViewById(R.id.btn_award);
		mRestartButton.setOnClickListener(this);
		mAwardButton.setOnClickListener(this);

		viewPager = (ViewPager) findViewById(R.id.drawing_viewPager);
		indicator = (ScrollIndicatorView) findViewById(R.id.drawing_indicator);
		indicator.setScrollBar(new ColorBar(this, Color.GREEN, 5));
		// 设置滚动监听
		int selectColorId = R.color.tab_top_text_2;
		int unSelectColorId = R.color.tab_top_text_1;
		indicator.setOnTransitionListener(new OnTransitionTextListener().setColorId(this, selectColorId, unSelectColorId));
		viewPager.setOffscreenPageLimit(2);
		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		inflate = LayoutInflater.from(getApplicationContext());
		indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_restart:
			restart();
			break;
		case R.id.btn_award:
//			for (int i = 0; i <num; i++) {
				if ((fragment.getIsAward()[0]||fragment.getIsAward()[1]||fragment.getIsAward()[2])&&!isSecondAward) {
					myDialog=new MyDialog(DrawingActivity.this,R.layout.awardtoast,getCurrentTime(),fragment.getContent(),this);
					myDialog.showDialog();
					isSecondAward=true;
					viewPager.setFocusable(true);
					fragment.notifyAdapter();
				}else {
					Toast.makeText(this, "您还没有中奖！", Toast.LENGTH_SHORT).show();
				}
//			}
			break;
		default:
			break;
		}
	}

	/**
	 * 获取当前时间
	 */
	@SuppressLint("SimpleDateFormat") 
	private String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return(df.format(new Date()));// new Date()为获取当前系统时间
	}

	public void restart() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(DrawingActivity.this, "再按一次进入设置画面",
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			DrawingActivity.this.finish();
			Intent intent = new Intent(DrawingActivity.this, MainActivity.class);
			startActivity(intent);
		}

	}
	
	private class MyAdapter extends IndicatorFragmentPagerAdapter {

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return num;
		}

		@Override
		public View getViewForTab(int position, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = inflate.inflate(R.layout.tab_top, container, false);
			}
			TextView textView = (TextView) convertView;
			textView.setText(names[position % names.length]);
			textView.setPadding(20, 0, 20, 0);
			return convertView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {
			fragment = new DrawingFragment();
			Bundle bundle = new Bundle();
			bundle.putInt(DrawingFragment.INTENT_INT_INDEX, position);
			fragment.setArguments(bundle);
			return fragment;
		}

		@Override
		public int getItemPosition(Object object) {
			return FragmentListPageAdapter.POSITION_NONE;
		}

	};
	/**
	 * control+1自动生成
	 * @return
	 */
	public int getNum() {
		return num;
	}

	public int getMath() {
		return math;
	}

	public String getMode() {
		return mode;
	}
	
	public boolean getIsSecondAward() {
		return isSecondAward;
	}

	@Override
	public void onCloseClick(View view) {
		// TODO Auto-generated method stub
		myDialog.dismiss();
	}

}
