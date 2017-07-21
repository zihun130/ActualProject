package atguigu.com.actualproject.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.Constant;
import atguigu.com.actualproject.Utils.UIUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class RegisterActivity extends AppCompatActivity {

    @InjectView(R.id.et_register_number)
    EditText etRegisterNumber;
    @InjectView(R.id.et_register_name)
    EditText etRegisterName;
    @InjectView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @InjectView(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @InjectView(R.id.btn_register)
    Button btnRegister;
    @InjectView(R.id.activity_register)
    LinearLayout activityRegister;
    @InjectView(R.id.btn_back)
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
    }


    @OnClick({R.id.btn_register, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String name = etRegisterName.getText().toString().trim();
                String number = etRegisterNumber.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwdAgain = etRegisterPwdagain.getText().toString().trim();

                if(TextUtils.isEmpty(name)) {
                    UIUtils.showToast("用户名不能为空");
                    return;
                }
                if(TextUtils.isEmpty(number)) {
                    UIUtils.showToast("手机号不能为空");
                    return;
                }

                if(!TextUtils.isEmpty(pwd)) {
                    if(!pwd.equals(pwdAgain)) {
                        UIUtils.showToast("两次输入的密码不一致");
                        return;
                    }
                }else{
                    UIUtils.showToast("密码不能为空");
                    return;
                }

                OkHttpUtils.get()
                        .url(Constant.REGISTER)
                        .addParams("phone",number)
                        .addParams("name",name)
                        .addParams("password",pwd)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                UIUtils.showToast(e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                processData(response);
                            }
                        });


                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void processData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            boolean isExist = jsonObject.getBoolean("isExist");
            if(isExist) {
                UIUtils.showToast("用户已存在");
            }else {
                UIUtils.showToast("注册成功");
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
