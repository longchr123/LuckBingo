package com.example.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Toast;

import com.example.activity.DrawingActivity;
import com.example.activity.R;
import com.example.adapter.GridViewAdapter;
import com.shizhefei.fragment.LazyFragment;

public class DrawingFragment extends LazyFragment implements OnItemClickListener{
	public static final String INTENT_INT_INDEX = "intent_int_index";
	private List<String> mDatas;
	private int math;
	private GridView mGridView;
	private List<String> selectedPositions;
	private List<List<String>> awards;
	private boolean isAward1;
	private boolean isAward2;
	private boolean isAward3;
	private boolean contains12;
	private List<String> award1;
	private List<String> content;
	private boolean[] isAward={isAward1,isAward2,isAward3};
	int j=0;
	
	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_drawing);
		mDatas=new ArrayList<String>();
		selectedPositions=new ArrayList<String>();
		content=new ArrayList<String>();
		initAwards();
		mGridView=(GridView) findViewById(R.id.gv);
		math=((DrawingActivity)getActivity()).getMath();
		getRandomNumber();
		mGridView.setAdapter(new GridViewAdapter(getActivity(), mDatas, R.layout.gd_item));
		mGridView.setOnItemClickListener(this);
	}
	
	private void getRandomNumber(){
		for (int i = 0; i <25; i++) {
			java.util.Random random=new java.util.Random();// 定义随机类
			String result=random.nextInt(math+1)+"";// 返回[0,10)集合中的整数，注意不包括10
			if (mDatas.contains(result)) {
				i--;
				continue;
			}
			if (i==12) {
				result="鸿鹄";
			}
			mDatas.add(result);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (!contains12) {
			selectedPositions.add("12");
			contains12=true;
		}
		if (position!=12) {
			((CheckBox)view).setChecked(!((CheckBox)view).isChecked());
		}else {
			return;
		}
		if(((CheckBox)view).isChecked()){
			if (position<10) {
				selectedPositions.add("0"+position);
			}else {
				selectedPositions.add(""+position);
			}
			content.add(((CheckBox)view).getText().toString().trim());
		}else {
			if (position<10) {
				selectedPositions.remove("0"+position);
			}else {
				selectedPositions.remove(position+"");
			}
			content.remove(((CheckBox)view).getText().toString().trim());
		}
		Collections.sort(selectedPositions);
		for (int i = 0; i < selectedPositions.size(); i++) {
			System.out.println(selectedPositions.get(i));
		}
		
			for (int i = 0; i < awards.size(); i++) {
				if (selectedPositions.equals(awards.get(i))) {
					isAward[j]=true;
					j++;
					Toast.makeText(getActivity(), "皇上您中奖了！！！", Toast.LENGTH_SHORT).show();
					i=awards.size();
				}else  {
				}
			}	
	}
	
	/**
	 * 12种中奖情况
	 */
	private void initAwards() {
		// TODO Auto-generated method stub
		awards=new ArrayList<List<String>>();
		award1=new ArrayList<String>();
		award1.add("00");
		award1.add("01");
		award1.add("02");
		award1.add("03");
		award1.add("04");
		award1.add("12");
		awards.add(award1);
		List<String> award2=new ArrayList<String>();
		award2.add("05");
		award2.add("06");
		award2.add("07");
		award2.add("08");
		award2.add("09");
		award2.add("12");
		awards.add(award2);
//		String[] award3={"10","11","12","13","14"};
		List<String> award3=new ArrayList<String>();
		award3.add("10");
		award3.add("11");
		award3.add("12");
		award3.add("13");
		award3.add("14");
		awards.add(award3);
//		String[] award4={"15","16","17","18","19"};
		List<String> award4=new ArrayList<String>();
		award4.add("12");
		award4.add("15");
		award4.add("16");
		award4.add("17");
		award4.add("18");
		award4.add("19");
		awards.add(award4);
//		String[] award5={"20","21","22","23","24"};
		List<String> award5=new ArrayList<String>();
		award5.add("12");
		award5.add("20");
		award5.add("21");
		award5.add("22");
		award5.add("23");
		award5.add("24");
		awards.add(award5);
//		String[] award6={"00","05","10","15","20"};
		List<String> award6=new ArrayList<String>();
		award6.add("00");
		award6.add("05");
		award6.add("10");
		award6.add("12");
		award6.add("15");
		award6.add("20");
		awards.add(award6);
//		String[] award7={"01","06","11","16","21"};
		List<String> award7=new ArrayList<String>();
		award7.add("01");
		award7.add("06");
		award7.add("11");
		award7.add("12");
		award7.add("16");
		award7.add("21");
		awards.add(award7);
//		String[] award8={"02","07","12","17","22"};
		List<String> award8=new ArrayList<String>();
		award8.add("02");
		award8.add("07");
		award8.add("12");
		award8.add("17");
		award8.add("22");
		awards.add(award8);
//		String[] award9={"03","08","13","18","23"};
		List<String> award9=new ArrayList<String>();
		award9.add("03");
		award9.add("08");
		award9.add("12");
		award9.add("13");
		award9.add("18");
		award9.add("23");
		awards.add(award9);
//		String[] award10={"04","09","14","19","24"};
		List<String> award10=new ArrayList<String>();
		award10.add("04");
		award10.add("09");
		award10.add("12");
		award10.add("14");
		award10.add("19");
		award10.add("24");
		awards.add(award10);
//		String[] award11={"00","06","12","18","24"};
		List<String> award11=new ArrayList<String>();
		award11.add("00");
		award11.add("06");
		award11.add("12");
		award11.add("18");
		award11.add("24");
		awards.add(award11);
//		String[] award12={"04","08","12","16","20"};
		List<String> award12=new ArrayList<String>();
		award12.add("04");
		award12.add("08");
		award12.add("12");
		award12.add("16");
		award12.add("20");
		awards.add(award12);
	}

	public boolean[] getIsAward() {
		return isAward;
	}
	
	public String getContent() {
		String string="";
		for (int i = 0; i < content.size(); i++) {
			string+=content.get(i)+",";
		}
		return "您的中奖号码为："+string+"请及时去领奖";
		
	}
	
	public void notifyAdapter() {
		mGridView.setAdapter(new GridViewAdapter(getActivity(), mDatas, R.layout.gd_item,((DrawingActivity)getActivity()).getIsSecondAward()));
		
	}
}
