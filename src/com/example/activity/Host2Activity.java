package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Host2Activity extends BaseActivity implements OnClickListener {

	private TextView mNumberTextView;
	private TextView mLuckNumberTextView;
	private Button mRestartButton;
	//判断点击事件是否停止动画
	private boolean stop = false;
	//随机数取值范围
	private int math;
	//保存已被选中的数值
	private List<String> mDatas;
	//用于旋转显示数值
	private String num;
	//旋转增量
	private int increment = 0;
	//确保旋转达到一定的速度时才能点击停止旋转
	private boolean canClick = false;
	private long exitTime = 0;
	private Animator anim;
	//设置动画播放一次时间
	int duration=1500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_host);
		initView();
	}

	private void initView() {
		Intent intent = getIntent();
		math = intent.getIntExtra("math", 0);
		mDatas = new ArrayList<String>();
		mNumberTextView = (TextView) findViewById(R.id.tv_number);
		mLuckNumberTextView = (TextView) findViewById(R.id.tv_Luck_number);
		mNumberTextView.setOnClickListener(this);
		mRestartButton=(Button) findViewById(R.id.btn_restart);
		mRestartButton.setOnClickListener(this);

		anim =  AnimatorInflater.loadAnimator(this, R.animator.animation3d);
		anim.setTarget(mNumberTextView);
		anim.setInterpolator(new LinearInterpolator());
	}

	private void initObjectAnimator() {
		anim.start();
		anim.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				if (duration>500) {
					duration=duration-200;
				}
				if (duration<=500) {
					canClick=true;
				}
				anim.setDuration(duration);
				//产生随机数
				getRandomNumber();
				//num是一直有值的，防止生成相同的随机数时num为空；
				mNumberTextView.setText(num);
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_number:
			if (!stop) {
				initObjectAnimator();
				// anim.start();
			} else {
				if (!canClick) {
					Toast.makeText(Host2Activity.this, "客官您慢点，臣妾做不到！",
							Toast.LENGTH_SHORT).show();
					return;
				}
					anim.cancel();
					mNumberTextView.setRotationY(0);
					mDatas.add(mNumberTextView.getText().toString());
					String string = null;
					for (int i = 0; i < mDatas.size(); i++) {
						if (i == 0) {
							string = mDatas.get(i) + ",";
						} else {
							string += mDatas.get(i) + ",";
						}
					}
					if (string != null) {
						mLuckNumberTextView.setText(string);
					}
					increment=0;
					duration=1000;
			}
			stop = !stop;
			break;

			case R.id.btn_restart:
				restart();
				break;
		default:
			break;
		}
	}

	/**
	 * 产生随机数
	 */
	private void getRandomNumber() {
		java.util.Random random = new java.util.Random();// 定义随机类
		String result = random.nextInt(math + 1) + "";// 返回[0,10)集合中的整数，注意不包括10
		if (!mDatas.contains(result)) {
			//num是一直有值的，防止生成相同的随机数时num为空；
			num = result;
		}
	}

	/**
	 * 连续点击两次才可返回主页面
	 */
	public void restart() {
		
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(Host2Activity.this, "再按一次进入设置画面",
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			Host2Activity.this.finish();
			Intent intent = new Intent(Host2Activity.this, MainActivity.class);
			startActivity(intent);
		}

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (KeyEvent.KEYCODE_BACK==keyCode) {
			restart();
		}
		//当为false时才有效
		return false;
	}
	

}
