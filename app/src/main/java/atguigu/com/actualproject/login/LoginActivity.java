package atguigu.com.actualproject.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import atguigu.com.actualproject.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.back)
    ImageView back;
    @InjectView(R.id.ed_phone)
    EditText edPhone;
    @InjectView(R.id.ed_number)
    EditText edNumber;
    @InjectView(R.id.anth_code)
    Button anthCode;
    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.tv_more)
    TextView tvMore;
    @InjectView(R.id.activity_login)
    LinearLayout activityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.back, R.id.login, R.id.tv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.login:
                break;
            case R.id.tv_more:
                break;
        }
    }
}
