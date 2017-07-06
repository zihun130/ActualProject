package atguigu.com.actualproject.expert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SearchPersonActivity extends AppCompatActivity {

    @InjectView(R.id.title_image)
    ImageView titleImage;
    @InjectView(R.id.title_back)
    ImageView titleBack;
    @InjectView(R.id.title_date)
    TextView titleDate;
    @InjectView(R.id.title_text)
    TextView titleText;
    @InjectView(R.id.title_shoppingcart)
    ImageView titleShoppingcart;
    @InjectView(R.id.title_select)
    ImageView titleSelect;
    @InjectView(R.id.et_searchperson)
    EditText etSearchperson;
    @InjectView(R.id.image_searchperson)
    ImageView imageSearchperson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_person);
        ButterKnife.inject(this);
        initTitle();
    }

    private void initTitle() {
        titleBack.setVisibility(View.VISIBLE);
        titleText.setText("搜索用户");
    }

    @OnClick({R.id.title_back, R.id.image_searchperson})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
                break;
            case R.id.image_searchperson:
                UIUtils.showToast("搜搜哦");
                break;
        }
    }
}
