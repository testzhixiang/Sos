package com.richard.sos;

import com.richard.sos.views.DelBtnEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private DelBtnEditText mEditTel, mEditPasswd;
	private Button mBtnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void findView() {
		setContentView(R.layout.activity_login);
		mEditTel = (DelBtnEditText) findViewById(R.id.edit_tel);
		mEditPasswd = (DelBtnEditText) findViewById(R.id.edit_passwd);
		mBtnLogin = (Button) findViewById(R.id.btn_login);
		mBtnLogin.setOnClickListener(this);
	}

	@Override
	public void setupView() {
		mBtnLogin.setOnClickListener(this);
	}

	@Override
	public void initDatas() {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			handleLogin();
			break;
		}
	}

	private void handleLogin() {
		String tel = mEditTel.getText().toString();
		String passwd = mEditPasswd.getText().toString();
		if (tel.equals("") || passwd.equals("")) {
			m("用户名或密码不能为空！");
//			return;
		}
		
		if (!tel.equals("123456") || !passwd.equals("888888")) {
			m("用户名或密码错误！");
//			return;
		}
		
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}
