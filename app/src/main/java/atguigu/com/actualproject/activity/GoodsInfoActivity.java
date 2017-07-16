package atguigu.com.actualproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.adapter.GoodsInfoAdapter;
import atguigu.com.actualproject.shopping.pagers.bean.GoodsBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class GoodsInfoActivity extends AppCompatActivity {

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
    @InjectView(R.id.goods_text)
    TextView goodsText;
    @InjectView(R.id.goods_recyclerview)
    RecyclerView goodsRecyclerview;
    @InjectView(R.id.activity_goods_info)
    LinearLayout activityGoodsInfo;
    private GoodsInfoAdapter adapter;
    private GridLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.inject(this);
        titleBack.setVisibility(View.VISIBLE);
        titleShoppingcart.setVisibility(View.VISIBLE);
        initData();
    }

    @OnClick({R.id.title_back, R.id.title_shoppingcart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
                break;
            case R.id.title_shoppingcart:
                startActivity(new Intent(this,CartShoppingActivity.class));
                break;
        }
    }

    private void initData() {
        String url = getIntent().getStringExtra("gift_image");
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
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
        GoodsBean goodsBean = new Gson().fromJson(json, GoodsBean.class);
        List<GoodsBean.DataBean.ItemsBean> items = goodsBean.getData().getItems();

        manager = new GridLayoutManager(this, 2);
        goodsRecyclerview.setLayoutManager(manager);

        adapter=new GoodsInfoAdapter(this,items);
        goodsRecyclerview.setAdapter(adapter);


    }
}
