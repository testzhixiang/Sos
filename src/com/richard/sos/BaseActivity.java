package com.richard.sos;

import com.richard.sos.contants.C;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

public class BaseActivity extends Activity {
	private String tag;
	protected int mScreenWidth, mScreenHeight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tag = this.getClass().getSimpleName();
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		Point outSize = new Point();
		wm.getDefaultDisplay().getSize(outSize);
		mScreenWidth = outSize.x;
		mScreenHeight = outSize.y;
		findView();
		setupView();
		initDatas();
	}
	
	public void findView() {
		
	}
	
	public void setupView() {
		
	}
	
	public void initDatas() {
		
	}
	
	public void d(String msg) {
		if (C.DEBUG) {
		Log.d(tag, msg);
		}
	}
	
	public void e(String msg) {
		if (C.DEBUG) {
		Log.e(tag, msg);
		}
	}
	
	public void m(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	
}
