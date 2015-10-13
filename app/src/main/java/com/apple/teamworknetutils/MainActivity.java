package com.apple.teamworknetutils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apple.teamworknetutils.net.ConnectionMethod;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends Activity {
    @ViewInject(R.id.button)
    private Button mButton;
    @ViewInject(R.id.textview)
    private TextView mTextView;
    @ViewInject(R.id.edittext_name)
    private EditText mEditTextName;
    @ViewInject(R.id.edittext_password)
    private EditText mEditTextPassword;

    private boolean isRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.inject(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionMethod method = ConnectionMethod.newInstance();
                method.register(mEditTextName.getText().toString(),mEditTextPassword.getText().toString());
            }
        });
    }
}
