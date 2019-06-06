package com.osiris.food.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity {

    @BindView(R.id.edt_name)
    EditText edt_name;
    @BindView(R.id.edt_sex)
    EditText edt_sex;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_document)
    EditText edt_document;
    @BindView(R.id.edt_document_num)
    EditText edt_document_num;
    @BindView(R.id.edt_mail)
    EditText edt_mail;
    @BindView(R.id.edt_eduction)
    EditText edt_eduction;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_regist;
    }

    @Override
    public void init() {

    }

    @OnClick({R.id.btn_next,R.id.rl_back})
    void onClick(View v){
        switch (v.getId()){
            case R.id.btn_next:
                Intent intent = new Intent(this,RegistCompleteActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
