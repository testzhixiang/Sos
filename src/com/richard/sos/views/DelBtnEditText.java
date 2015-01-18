package com.richard.sos.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.richard.sos.R;

public class DelBtnEditText extends FrameLayout implements TextWatcher {
	private EditText mEditText;
	private ImageButton mBtnCross;

	@SuppressLint("NewApi")
	public DelBtnEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.del_btn_edittext, this);
		if (!v.isInEditMode()) {
			mEditText = (EditText) v.findViewById(R.id.edit);

			TypedArray a = context.obtainStyledAttributes(attrs,
					R.styleable.DelBtnEditText);
			int inputType = a.getInteger(
					R.styleable.DelBtnEditText_android_inputType, 0);
			String hint = a.getString(R.styleable.DelBtnEditText_android_hint);
			Drawable icon = a
					.getDrawable(R.styleable.DelBtnEditText_android_drawableLeft);
			Drawable bkColor = a
					.getDrawable(R.styleable.DelBtnEditText_android_background);

			mEditText.setHint(hint);
			mEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(icon,
					null, null, null);
			mEditText.setBackground(bkColor);
			mEditText.setInputType(inputType);
			a.recycle();

			mBtnCross = (ImageButton) v.findViewById(R.id.btn_cross);
			mBtnCross.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mEditText.setText("");
				}
			});

			mEditText.addTextChangedListener(this);
		}

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		if (s.toString().equals("")) {
			mBtnCross.setVisibility(View.INVISIBLE);
		} else {
			mBtnCross.setVisibility(View.VISIBLE);
		}
	}

	public void setText(String text) {
		mEditText.setText(text);
	}

	public String getText() {
		return mEditText.getText().toString();
	}
}
