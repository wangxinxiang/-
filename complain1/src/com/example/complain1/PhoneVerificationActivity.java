package com.example.complain1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by wang on 2015/3/26.
 */
public class PhoneVerificationActivity extends Activity{
    private ImageButton back;
    private TextView phoneNum;
    private EditText verificationCode;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_verification);
        init();
        initListener();
    }

    private void init(){
        back = (ImageButton)this.findViewById(R.id.phoneVerification_back);
        phoneNum = (TextView)this.findViewById(R.id.phoneVerification_phoneNum);
        verificationCode = (EditText)this.findViewById(R.id.phoneVerification_code);
        send = (Button)this.findViewById(R.id.phoneVerification_sendBtn);

    }

    private void initListener(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhoneVerificationActivity.this,Tab5Activity.class);
                startActivity(intent);
            }
        });
    }
}
