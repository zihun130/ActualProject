package atguigu.com.actualproject.expert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;

import java.util.ArrayList;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.GlidImageLoader;
import atguigu.com.actualproject.Utils.UIUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RecommendAndLikeActivity extends AppCompatActivity {

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
    @InjectView(R.id.goods_name)
    TextView goodsName;
    @InjectView(R.id.expert_textview)
    TextView expertTextview;
    @InjectView(R.id.expert_price)
    TextView expertPrice;
    @InjectView(R.id.expert_favor)
    ImageView expertFavor;
    @InjectView(R.id.expert_textview3)
    TextView expertTextview3;
    @InjectView(R.id.expert_link)
    Button expertLink;
    @InjectView(R.id.userImage)
    ImageView userImage;
    @InjectView(R.id.comment_recyclerview)
    RecyclerView commentRecyclerview;
    @InjectView(R.id.btn_writer)
    Button btnWriter;
    @InjectView(R.id.expert_ll)
    LinearLayout expertLl;
    @InjectView(R.id.expert_scroll)
    ScrollView expertScroll;
    @InjectView(R.id.activity_recommend_and_like)
    LinearLayout activityRecommendAndLike;
    @InjectView(R.id.exoert_banner)
    Banner exoertBanner;
    @InjectView(R.id.usename)
    TextView usename;
    private String ids;
    private String username;
    private String mid;
    private String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_and_like);
        ButterKnife.inject(this);
        inittitle();
        initData();
    }

    private void initData() {
        String image = getIntent().getStringExtra("IMAGE");
        String goodname = getIntent().getStringExtra("GOODNAME");
        String price = getIntent().getStringExtra("PRICE");
        username = getIntent().getStringExtra("USERNAME");
        mid = getIntent().getStringExtra("MID");
        String favor = getIntent().getStringExtra("FAVOR");
        ids = getIntent().getStringExtra("ID");
        desc = getIntent().getStringExtra("DESC");

        ArrayList<String> images = new ArrayList<>();
        images.add(image);
        exoertBanner.setImages(images)
                .setImageLoader(new GlidImageLoader())
                .isAutoPlay(false)
                .start();
        goodsName.setText(goodname);
        expertPrice.setText(price);
        Glide.with(this)
                .load(mid)
                .into(userImage);
        usename.setText(username);
        expertTextview3.setText(favor);

    }

    private void inittitle() {
        titleBack.setVisibility(View.VISIBLE);
        titleText.setText("良 品");
    }


    @OnClick({R.id.title_back, R.id.expert_favor, R.id.expert_link, R.id.userImage, R.id.btn_writer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.expert_favor:
                UIUtils.showToast("点点");
                break;
            case R.id.expert_link:
                break;
            case R.id.userImage:
                Intent intent=new Intent(RecommendAndLikeActivity.this,ExpertInfoActivity.class);
                intent.putExtra("UID",ids);
                intent.putExtra("NAME",username);
                intent.putExtra("MID",mid);
                intent.putExtra("DUTY",desc);
                startActivity(intent);
                break;
            case R.id.btn_writer:
                UIUtils.showToast("写写什么");
                break;
        }
    }
}
