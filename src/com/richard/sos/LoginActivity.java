package com.richard.sos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private EditText mEditTel, mEditPasswd;
	private Button mBtnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void findView() {
		setContentView(R.layout.activity_login);
		mEditTel = (EditText) findViewById(R.id.edit_tel);
		mEditPasswd = (EditText) findViewById(R.id.edit_passwd);
		mBtnLogin = (Button) findViewById(R.id.btn_login);
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
			m("�û��������벻��Ϊ�գ�");
			return;
		}
		
		if (!tel.equals("123456") || !passwd.equals("888888")) {
			m("�û������������");
			return;
		}
		
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}