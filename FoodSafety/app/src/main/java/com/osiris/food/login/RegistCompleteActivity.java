package com.osiris.food.login;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.view.dialog.RegistCompleteDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistCompleteActivity extends BaseActivity {


    @BindView(R.id.edt_industry)
    EditText edt_industry;
    @BindView(R.id.edt_post)
    EditText edt_post;
    @BindView(R.id.edt_company_name)
    EditText edt_company_name;
    @BindView(R.id.edt_company_phone)
    EditText edt_company_phone;
    @BindView(R.id.edt_company_location)
    EditText edt_company_location;
    @BindView(R.id.edt_company_detail_location)
    EditText edt_company_detail_location;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_complete_regist;
    }

    @Override
    public void init() {

    }

    @OnClick({R.id.btn_regist,R.id.rl_back})
    void onClick(View v){
        switch (v.getId()){
            case R.id.btn_regist:
                showRegistComplete();
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    private void showRegistComplete() {
        RegistCompleteDialog.Builder preventBuilder = new RegistCompleteDialog.Builder(this);
        preventBuilder.setPositiveButton(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        preventBuilder.create().show();
    }


}
