package com.richard.sos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.richard.sos.utils.UnitConvert;


public class MainActivity extends Activity implements View.OnClickListener,
		OnCheckedChangeListener,
		android.widget.RadioGroup.OnCheckedChangeListener {
	private Point mScreenSize;

	private ImageButton mIbSos, mIbRailway, mIbShare, mIbLocation;
	private CheckBox mCb110, mCb120, mCb119, mCb122, mCbFriends;
	private RelativeLayout mRlContainer;
	private Button mBtnContinue, mBtnCancel;
	private Button mBtnStartRecord, mBtnCancelRecord;
	private RadioGroup mRgAlarmMethod;

	private Dialog mAlarmConfirmDialog;
	private PopupWindow mAlarmTypeSelectDialog;

	private double SQRT_2 = Math.sqrt(2);

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	private void basicSetup() {
		mScreenSize = new Point();
		int curVersion = android.os.Build.VERSION.SDK_INT;
		if (curVersion > android.os.Build.VERSION_CODES.HONEYCOMB_MR1) {
			getWindowManager().getDefaultDisplay().getSize(mScreenSize);
		} else {
			Display d = getWindowManager().getDefaultDisplay();
			mScreenSize.x = d.getWidth();
			mScreenSize.y = d.getHeight();
		}
	}

	private void findView() {
		mIbSos = (ImageButton) findViewById(R.id.ib_sos);
		mIbLocation = (ImageButton) findViewById(R.id.ib_location);
		mIbShare = (ImageButton) findViewById(R.id.ib_share);
		mIbRailway = (ImageButton) findViewById(R.id.ib_railway);

		mCb110 = (CheckBox) findViewById(R.id.cb_110);
		mCb119 = (CheckBox) findViewById(R.id.cb_119);
		mCb120 = (CheckBox) findViewById(R.id.cb_120);
		mCb122 = (CheckBox) findViewById(R.id.cb_122);
		mCbFriends = (CheckBox) findViewById(R.id.cb_friends);

		mRlContainer = (RelativeLayout) findViewById(R.id.rl_container);
	}

	private void setListener() {
		mIbSos.setOnClickListener(this);
		mIbLocation.setOnClickListener(this);
		mIbShare.setOnClickListener(this);
		mIbRailway.setOnClickListener(this);

		mCb110.setOnCheckedChangeListener(this);
		mCb119.setOnCheckedChangeListener(this);
		mCb120.setOnCheckedChangeListener(this);
		mCb122.setOnCheckedChangeListener(this);
		mCbFriends.setOnCheckedChangeListener(this);
	}

	private void adjustView() {
		View parent = (View) mRlContainer.getParent();
		int padding = parent.getPaddingLeft();
		int Radius = mScreenSize.x / 2 - padding;

		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRlContainer
				.getLayoutParams();
		params.width = Radius * 2;
		params.height = params.width;
		mRlContainer.setLayoutParams(params);

		Bitmap bmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.location_pressed);
		int radius = bmp.getWidth() / 2;
		int radiusCenter = Radius - 2 * radius - 2 * UnitConvert.dp2Px(6, this);
		bmp.recycle();

		params = (RelativeLayout.LayoutParams) mIbSos.getLayoutParams();
		params.width = radiusCenter * 2;
		params.height = params.width;
		mIbSos.setLayoutParams(params);

		double margin = (SQRT_2 - 1) * Radius;
		margin += UnitConvert.dp2Px(6, this) + radius;
		margin /= SQRT_2;
		margin -= radius;

		params = (RelativeLayout.LayoutParams) mIbShare.getLayoutParams();
		params.setMargins(0, (int) margin, (int) margin, 0);
		mIbShare.setLayoutParams(params);

		params = (RelativeLayout.LayoutParams) mCb119.getLayoutParams();
		params.setMargins(0, 0, (int) margin, (int) margin);
		mCb119.setLayoutParams(params);

		params = (RelativeLayout.LayoutParams) mCb110.getLayoutParams();
		params.setMargins((int) margin, 0, 0, (int) margin);
		mCb110.setLayoutParams(params);

		params = (RelativeLayout.LayoutParams) mIbRailway.getLayoutParams();
		params.setMargins((int) margin, (int) margin, 0, 0);
		mIbRailway.setLayoutParams(params);
	}

	private void initDialog() {
		// 方案1
		mAlarmConfirmDialog = new Dialog(this);
		mAlarmConfirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		LayoutInflater inflater = getLayoutInflater();
		View view = inflater.inflate(R.layout.dialog_alarm_confirm, null);
		mAlarmConfirmDialog.setContentView(view);
		mBtnContinue = (Button) view.findViewById(R.id.btn_conti);
		mBtnCancel = (Button) view.findViewById(R.id.btn_cancel);
		mBtnContinue.setOnClickListener(this);
		mBtnCancel.setOnClickListener(this);

		// 方案2
		mAlarmTypeSelectDialog = new PopupWindow();
		mAlarmTypeSelectDialog.setWidth(LayoutParams.WRAP_CONTENT);
		mAlarmTypeSelectDialog.setHeight(LayoutParams.WRAP_CONTENT);
		mAlarmTypeSelectDialog.setFocusable(true);
		inflater = getLayoutInflater();
//		view = inflater.inflate(R.layout.dialog_alarm, null);
//		mAlarmTypeSelectDialog.setContentView(view);
//		mBtnStartRecord = (Button) view.findViewById(R.id.btn_start_recode);
//		mBtnCancelRecord = (Button) view.findViewById(R.id.btn_cancel);
//		mRgAlarmMethod = (RadioGroup) view.findViewById(R.id.rg_alarm_method);
//		mBtnStartRecord.setOnClickListener(this);
//		mBtnCancelRecord.setOnClickListener(this);
//		mRgAlarmMethod.setOnCheckedChangeListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		basicSetup();
		findView();
		setListener();
		adjustView();
		initDialog();
	}

	private void showMsg(String msg) {
		Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		if (v == mIbSos) {
			if (!mCbFriends.isChecked()) {
				mAlarmConfirmDialog.show();
			}
		} else if (v == mBtnContinue) {
			mAlarmConfirmDialog.dismiss();
			mAlarmTypeSelectDialog.showAtLocation(
					(View) mRlContainer.getParent(), Gravity.CENTER, 0, 0);
		} else if (v == mBtnCancel) {
			mAlarmConfirmDialog.dismiss();
		} else if (v == mBtnStartRecord) {
			mAlarmTypeSelectDialog.dismiss();
//			if (mRgAlarmMethod.getCheckedRadioButtonId() == R.id.rb_recode_sound) {
//				showMsg("开始录音");
////				startActivity(new Intent(this, SoundRecordActivity.class));
//			} else {
//				showMsg("开始录像");
//			}
		} else if (v == mBtnCancelRecord) {
			mAlarmTypeSelectDialog.dismiss();
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// 演示如何调试
		if (buttonView == mCbFriends) {
			if (isChecked) {
				mCb110.setChecked(false);
				mCb119.setChecked(false);
				mCb120.setChecked(false);
				mCb122.setChecked(false);
			}
		} else {
			if (isChecked && mCbFriends.isChecked()) {
				mCbFriends.setChecked(false);
			}
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
//		if (checkedId == R.id.rb_recode_sound) {
////			mBtnStartRecord.setCompoundDrawablesWithIntrinsicBounds(
////					R.drawable.record_audio, 0, 0, 0);
//		} else {
//			mBtnStartRecord.setCompoundDrawablesWithIntrinsicBounds(
//					R.drawable.record_video, 0, 0, 0);
//		}
	}
}
