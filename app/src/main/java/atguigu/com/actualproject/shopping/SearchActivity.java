package atguigu.com.actualproject.shopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

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
    @InjectView(R.id.et_search)
    EditText etSearch;
    @InjectView(R.id.image_search)
    ImageView imageSearch;
    @InjectView(R.id.activity_search)
    LinearLayout activitySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);

        initTitle();


    }

    private void initTitle() {
        titleBack.setVisibility(View.VISIBLE);
        titleText.setText("搜索商品");

    }

    @OnClick({R.id.title_back, R.id.image_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
                break;
            case R.id.image_search:
                UIUtils.showToast("搜搜哦");
                break;
        }
    }
}
