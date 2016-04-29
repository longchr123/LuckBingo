package com.example.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.example.activity.R;

public class MyDialog extends Dialog implements OnClickListener{

	 private Window window = null;  
	 private TextView mTimeTextView;
	 private TextView mContentTextView;
	private TextView mCloseButton;
	 private CloseDialogListener listener;   
	    
     public interface CloseDialogListener{   
         public void onCloseClick(View view);
     }  
	 
	public MyDialog(Context context,int layoutResID,String time,String content,CloseDialogListener listener) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(layoutResID); 
		 mTimeTextView=(TextView)findViewById(R.id.tv_time);
         mContentTextView=(TextView)findViewById(R.id.tv_content);
         mCloseButton=(TextView)findViewById(R.id.tv_close);
         mTimeTextView.setText(time);
         mContentTextView.setText(content);
         mCloseButton.setOnClickListener(this);
         this.listener=listener;
         
	}
       
	 public void showDialog(){  
         setCanceledOnTouchOutside(false);  
         window = getWindow(); //得到对话框  
         window.setWindowAnimations(R.style.popup_anim_style); //设置窗口弹出动画  
         show();  
     }  
	
        
	 
	 public void showDialog(int layoutResID,int x,int y){  
            setContentView(layoutResID);  
            windowDeploy(x, y);  
            //设置触摸对话框意外的地方取消对话框  
            setCanceledOnTouchOutside(true);  
            show();  
        }  
          
        //设置窗口显示  
        public void windowDeploy(int x, int y){  
            window = getWindow(); //得到对话框  
            window.setWindowAnimations(R.style.popup_anim_style); //设置窗口弹出动画  
//            window.setBackgroundDrawableResource(R.color.vifrification); //设置对话框背景为透明  
            WindowManager.LayoutParams wl = window.getAttributes();  
            //根据x，y坐标设置窗口需要显示的位置  
            wl.x = x; //x小于0左移，大于0右移  
            wl.y = y; //y小于0上移，大于0下移    
//            wl.alpha = 0.6f; //设置透明度  
//            wl.gravity = Gravity.BOTTOM; //设置重力  
            window.setAttributes(wl);  
        }

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			listener.onCloseClick(v);
		}  
}  
