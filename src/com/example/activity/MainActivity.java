package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends BaseActivity implements OnClickListener {

	private Spinner mMathSpinner,mRoleSpinner,mNumSpinner,mModeSpinner;
	private Button mConfirmButton,mCacelButton;

	private int[] maths={32,52,72,92};
	private String[] roles={"抽奖者","主持人"};
	private int[] nums={1,2,3};
	private String[] modes={"翻转","旋转"};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
    }

	private void initView() {
		mMathSpinner=(Spinner) findViewById(R.id.sp_math);
		mRoleSpinner=(Spinner) findViewById(R.id.sp_role);
		mNumSpinner=(Spinner) findViewById(R.id.sp_num);
		mModeSpinner=(Spinner) findViewById(R.id.sp_mode);
		mConfirmButton=(Button) findViewById(R.id.btn_confirm);
		mCacelButton=(Button) findViewById(R.id.btn_cancel);
		mConfirmButton.setOnClickListener(this);
		mCacelButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_confirm:
			//抽奖者
			if (mRoleSpinner.getSelectedItemId()==0) {
				Intent intent=new Intent(MainActivity.this,DrawingActivity.class);
				getInfo(intent);
				startActivity(intent);
			}else if (mRoleSpinner.getSelectedItemId()==1) {
				if (mModeSpinner.getSelectedItemId()==0) {
					Intent intent2=new Intent(MainActivity.this,HostActivity.class);
					getInfo(intent2);
					startActivity(intent2);
				}else {
					Intent intent2=new Intent(MainActivity.this,Host2Activity.class);
					getInfo(intent2);
					startActivity(intent2);
				}
			}
			break;
		case R.id.btn_cancel:
			finish();
			break;
		default:
			break;
		}
	}
	
	private void getInfo(Intent intent){
		intent.putExtra("num", nums[(int) mNumSpinner.getSelectedItemId()]);
		intent.putExtra("math", maths[(int) mMathSpinner.getSelectedItemId()]);
		intent.putExtra("mode", modes[(int) mModeSpinner.getSelectedItemId()]);
	}
}
