package atguigu.com.actualproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.adapter.BrandGoodsAdapter;
import atguigu.com.actualproject.bean.BrandGoodsBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class BrandGoodsActivity extends AppCompatActivity {

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
    @InjectView(R.id.brand_goods_image)
    ImageView brandGoodsImage;
    @InjectView(R.id.brand_goods_text)
    TextView brandGoodsText;
    @InjectView(R.id.brand_goods_textview)
    TextView brandGoodsTextview;
    @InjectView(R.id.brand_goods_recyclerView)
    RecyclerView brandGoodsRecyclerView;
    @InjectView(R.id.activity_brand_goods)
    LinearLayout activityBrandGoods;
    @InjectView(R.id.brand_goods_btn1)
    RadioButton brandGoodsBtn1;
    @InjectView(R.id.brand_goods_btn2)
    RadioButton brandGoodsBtn2;
    @InjectView(R.id.rg_brand_goods)
    RadioGroup rgBrandGoods;
    private BrandGoodsAdapter adapter;
    private GridLayoutManager manager;
    private String purl = "http://mobile.iliangcang.com/brand/brandShopList?app_key=Android&brand_id=";
    private String surl = "&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_goods);
        ButterKnife.inject(this);
        inittitle();
        initData();
    }

    private void inittitle() {
        titleBack.setVisibility(View.VISIBLE);
        titleText.setText("品牌产品");
    }

    private void initData() {
        String brandid = getIntent().getStringExtra("brandid");
        String brand_name = getIntent().getStringExtra("brand_name");
        String brand_logo = getIntent().getStringExtra("brand_logo");
        String url = purl + brandid + surl;
        getNetData(url);
        brandGoodsRecyclerView.setVisibility(View.VISIBLE);
        rgBrandGoods.check(R.id.brand_goods_btn2);
        brandGoodsText.setText(brand_name);
        Glide.with(this)
                .load(brand_logo)
                .into(brandGoodsImage);

    }

    private void getNetData(String url) {
        OkHttpUtils.get()
                .url(url)
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
    }

    private void processData(String json) {
        BrandGoodsBean brandGoodsBean = new Gson().fromJson(json, BrandGoodsBean.class);
        List<BrandGoodsBean.DataBean.ItemsBean> items = brandGoodsBean.getData().getItems();
        adapter = new BrandGoodsAdapter(this, items, brandGoodsTextview);
        brandGoodsRecyclerView.setAdapter(adapter);


        manager = new GridLayoutManager(this, 2);
        brandGoodsRecyclerView.setLayoutManager(manager);

    }

    @OnClick({R.id.title_back, R.id.title_shoppingcart, R.id.brand_goods_btn1, R.id.brand_goods_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
                break;
            case R.id.title_shoppingcart:
                UIUtils.showToast("购物车");
                break;
            case R.id.brand_goods_btn1:
                brandGoodsTextview.setVisibility(View.VISIBLE);
                brandGoodsRecyclerView.setVisibility(View.GONE);
                break;
            case R.id.brand_goods_btn2:
                brandGoodsTextview.setVisibility(View.GONE);
                brandGoodsRecyclerView.setVisibility(View.VISIBLE);
                break;
        }
    }
}
