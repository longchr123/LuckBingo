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
         window = getWindow(); //�õ��Ի���  
         window.setWindowAnimations(R.style.popup_anim_style); //���ô��ڵ�������  
         show();  
     }  
	
        
	 
	 public void showDialog(int layoutResID,int x,int y){  
            setContentView(layoutResID);  
            windowDeploy(x, y);  
            //���ô����Ի�������ĵط�ȡ���Ի���  
            setCanceledOnTouchOutside(true);  
            show();  
        }  
          
        //���ô�����ʾ  
        public void windowDeploy(int x, int y){  
            window = getWindow(); //�õ��Ի���  
            window.setWindowAnimations(R.style.popup_anim_style); //���ô��ڵ�������  
//            window.setBackgroundDrawableResource(R.color.vifrification); //���öԻ��򱳾�Ϊ͸��  
            WindowManager.LayoutParams wl = window.getAttributes();  
            //����x��y�������ô�����Ҫ��ʾ��λ��  
            wl.x = x; //xС��0���ƣ�����0����  
            wl.y = y; //yС��0���ƣ�����0����    
//            wl.alpha = 0.6f; //����͸����  
//            wl.gravity = Gravity.BOTTOM; //��������  
            window.setAttributes(wl);  
        }

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			listener.onCloseClick(v);
		}  
}  
