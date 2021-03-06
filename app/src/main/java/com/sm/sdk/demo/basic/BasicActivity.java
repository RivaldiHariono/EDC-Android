package com.sm.sdk.demo.basic;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sm.sdk.demo.BaseAppCompatActivity;
import com.sm.sdk.demo.R;

public class BasicActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        initToolbarBringBack(R.string.basic);
        showToast("inside basic activity");
        initView();
    }

    private void initView() {
        View item = findViewById(R.id.get_sys_param);
        TextView leftText = item.findViewById(R.id.left_text);
        leftText.setText(R.string.basic_get_sys_param);
        item.setOnClickListener(this);

        item = findViewById(R.id.set_sys_param);
        leftText = item.findViewById(R.id.left_text);
        leftText.setText(R.string.basic_set_sys_param);
        item.setOnClickListener(this);

        item = findViewById(R.id.buzzer);
        leftText = item.findViewById(R.id.left_text);
        leftText.setText(R.string.basic_buzzer);
        item.setOnClickListener(this);

        item = findViewById(R.id.led);
        leftText = item.findViewById(R.id.left_text);
        leftText.setText(R.string.basic_led);
        item.setOnClickListener(this);

        item = findViewById(R.id.screen_mode);
        leftText = item.findViewById(R.id.left_text);
        leftText.setText(R.string.basic_screen_mode);
        item.setOnClickListener(this);

        item = findViewById(R.id.sale);
        leftText = item.findViewById(R.id.left_text);
        leftText.setText(R.string.sale);
        item.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.get_sys_param:
                openActivity(GetSysParamActivity.class);
                System.out.println("testing1");
                break;
            case R.id.set_sys_param:
                openActivity(SetSysParamActivity.class);
                System.out.println("testing2");
                break;
            case R.id.buzzer:
                openActivity(BuzzerActivity.class);
                System.out.println("testing2");
                break;
            case R.id.led:
                openActivity(LedActivity.class);
                System.out.println("testing3");
                break;
            case R.id.screen_mode:
                openActivity(ScreenModelActivity.class);
                System.out.println("testing4");
                break;
            case R.id.sale:
                openActivity(SaleActivity.class);
                break;
        }
    }


}
