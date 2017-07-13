package atguigu.com.actualproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.GlidImageLoader;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.bean.AllGoodsInfoBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;

public class AllGoodsInfoActivity extends AppCompatActivity {
    @InjectView(R.id.all_goods_banner)
    Banner allGoodsBanner;
    @InjectView(R.id.all_doods_goodsname1)
    TextView allDoodsGoodsname1;
    @InjectView(R.id.all_goods_image1)
    ImageView allGoodsImage1;
    @InjectView(R.id.all_goods_moneytext)
    TextView allGoodsMoneytext;
    @InjectView(R.id.all_goods_textview1)
    TextView allGoodsTextview1;
    @InjectView(R.id.all_goods_image2)
    ImageView allGoodsImage2;
    @InjectView(R.id.all_goods_textview2)
    TextView allGoodsTextview2;
    @InjectView(R.id.all_goods_image3)
    ImageView allGoodsImage3;
    @InjectView(R.id.all_doods_goodsinfo)
    TextView allDoodsGoodsinfo;
    @InjectView(R.id.all_goods_favor)
    ImageView allGoodsFavor;
    @InjectView(R.id.all_goods_textview3)
    TextView allGoodsTextview3;
    @InjectView(R.id.all_doods_goodsname2)
    TextView allDoodsGoodsname2;
    @InjectView(R.id.brand_goods_btn1)
    RadioButton brandGoodsBtn1;
    @InjectView(R.id.brand_goods_btn2)
    RadioButton brandGoodsBtn2;
    @InjectView(R.id.rg_brand_goods)
    RadioGroup rgBrandGoods;
    @InjectView(R.id.brand_goods_textview)
    TextView brandGoodsTextview;
    @InjectView(R.id.fram_shoppingnotice)
    RelativeLayout framShoppingnotice;
    @InjectView(R.id.all_doods_allgoodsinfo)
    LinearLayout allDoodsAllgoodsinfo;
    @InjectView(R.id.all_doods_goodsname3)
    TextView allDoodsGoodsname3;
    @InjectView(R.id.fram_goodsinfo)
    LinearLayout framGoodsinfo;
    @InjectView(R.id.brand_frame)
    FrameLayout brandFrame;
    @InjectView(R.id.navigation_back)
    ImageView navigationBack;
    @InjectView(R.id.navigation_cart)
    ImageView navigationCart;
    @InjectView(R.id.ic_custom_service)
    ImageView icCustomService;
    @InjectView(R.id.add_shoppingcart)
    Button addShoppingcart;
    @InjectView(R.id.buying)
    Button buying;
    @InjectView(R.id.activity_all_goods_info)
    RelativeLayout activityAllGoodsInfo;
    @InjectView(R.id.goods_price)
    TextView goodsPrice;
    @InjectView(R.id.ll_all)
    LinearLayout llAll;
    @InjectView(R.id.scrollview_all)
    ScrollView scrollviewAll;
    @InjectView(R.id.brand_goods_image2)
    ImageView brandGoodsImage2;
    @InjectView(R.id.three_day_send)
    TextView threeDaySend;
    @InjectView(R.id.all_doods_goodsname4)
    TextView allDoodsGoodsname4;
    @InjectView(R.id.goods_desc)
    TextView goodsDesc;
    @InjectView(R.id.shardSDK)
    ImageView shardSDK;
    private String purl = "http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id=";
    private String surl = "&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_goods_info);
        ButterKnife.inject(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();
    }

    private void initData() {
        String goods_id = getIntent().getStringExtra("GOODSINFO");
        OkHttpUtils.get()
                .url(purl + goods_id + surl)
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

        rgBrandGoods.check(R.id.brand_goods_btn1);
        framGoodsinfo.setVisibility(View.VISIBLE);

    }

    private void processData(String json) {
        //解析数据
        AllGoodsInfoBean bean = new Gson().fromJson(json, AllGoodsInfoBean.class);
        AllGoodsInfoBean.DataBean.ItemsBean items = bean.getData().getItems();

        setGoodsData(items);

    }

    private void setGoodsData(AllGoodsInfoBean.DataBean.ItemsBean items) {
        List<String> images_item = items.getImages_item();

        allGoodsBanner.setImages(images_item)
                .setImageLoader(new GlidImageLoader())
                .isAutoPlay(false)
                .start();
        allDoodsGoodsname1.setText(items.getBrand_info().getBrand_name());
        allDoodsGoodsinfo.setText(items.getGoods_name());
        goodsPrice.setText(items.getPrice());
        allGoodsTextview3.setText(items.getLike_count());
        threeDaySend.setText(items.getShipping_str());
        allDoodsGoodsname2.setText(items.getBrand_info().getBrand_name());
        Glide.with(this)
                .load(items.getBrand_info().getBrand_logo())
                .into(brandGoodsImage2);

        brandGoodsTextview.setText(items.getGood_guide().getContent());
        List<AllGoodsInfoBean.DataBean.ItemsBean.GoodsInfoBean> goods_info = items.getGoods_info();

        if (goods_info != null && goods_info.size() > 0) {
            for (int i = 0; i < goods_info.size(); i++) {
                ImageView image = new ImageView(this);
                String img = goods_info.get(i).getContent().getImg();
                Glide.with(this)
                        .load(img)
                        .into(image);
                allDoodsAllgoodsinfo.addView(image);
            }
        }

        if (items.getGoods_desc() != null) {
            goodsDesc.setText(items.getGoods_desc());
        }
        allDoodsGoodsname3.setText(items.getBrand_info().getBrand_name());
        allDoodsGoodsname4.setText(items.getBrand_info().getBrand_desc());

    }

    @OnClick({R.id.brand_goods_btn1, R.id.brand_goods_btn2, R.id.navigation_back, R.id.navigation_cart, R.id.ic_custom_service, R.id.add_shoppingcart, R.id.buying, R.id.shardSDK})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.brand_goods_btn1:
                framGoodsinfo.setVisibility(View.VISIBLE);
                framShoppingnotice.setVisibility(View.GONE);
                break;
            case R.id.brand_goods_btn2:
                framGoodsinfo.setVisibility(View.GONE);
                framShoppingnotice.setVisibility(View.VISIBLE);
                break;
            case R.id.navigation_back:
                finish();
                break;
            case R.id.navigation_cart:
                break;
            case R.id.ic_custom_service:
                break;
            case R.id.add_shoppingcart:
                break;
            case R.id.buying:
                break;
            case R.id.shardSDK:
               showShare();
                break;
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("我是中国人");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://www.atguigu.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是一个兵，来自老百姓！！");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496999731520&di=940086649468f3966ca0f4b317fd18ff&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F215%2F46%2FJYYMR4I452LO_1000x500.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496999731520&di=940086649468f3966ca0f4b317fd18ff&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F215%2F46%2FJYYMR4I452LO_1000x500.jpg");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("尚硅谷");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496999731520&di=940086649468f3966ca0f4b317fd18ff&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F215%2F46%2FJYYMR4I452LO_1000x500.jpg");

// 启动分享GUI
        oks.show(this);
    }
}