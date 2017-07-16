package atguigu.com.actualproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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

import java.util.ArrayList;
import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.DensityUtil;
import atguigu.com.actualproject.Utils.GlidImageLoader;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.bean.AllGoodsInfoBean;
import atguigu.com.actualproject.database.InfoBean;
import atguigu.com.actualproject.database.dao.GoodsInfoDAO;
import atguigu.com.actualproject.view.AddSubView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;

import static atguigu.com.actualproject.R.id.count;
import static atguigu.com.actualproject.R.id.popu_btn;
import static atguigu.com.actualproject.R.id.shopping_count_content;

public class AllGoodsInfoActivity extends AppCompatActivity implements View.OnClickListener {
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
    private PopupWindow popupWindow;
    private View popupView;
    private TextView textview1;
    private TextView textview2;
    private TextView textview3;
    private TextView textview4;
    private TextView textview5;
    private View popuinflate;
    private PopupWindow window;
    private ImageView back;
    private TextView shoppingname;
    private TextView shoppingcontent;
    private TextView shoppingprice;
    private RadioGroup countcontent;
    private RadioGroup capacitycontent;
    private RadioGroup typecontent;
    private Button popubtn;
    private ImageView shoppingimage;
    private LinearLayout shoppingcount;
    private LinearLayout capacity;
    private LinearLayout shoppingtype;
    private LinearLayout shoppingcolors;
    private RadioGroup colorcontent;
    private LinearLayout shoppingsize;
    private RadioGroup sizecontent;
    private LinearLayout shoppingclothing;
    private RadioGroup clothingcontent;
    private LinearLayout shoppingshoes;
    private RadioGroup shoescontent;
    private LinearLayout counts;
    private AddSubView shoppingcountcontent;
    private CharSequence sizeText;
    private CharSequence countText;
    private CharSequence capacityText;
    private CharSequence typeText;
    private CharSequence shoesText;
    private CharSequence colorText;
    private CharSequence clothingText;
    private String string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_goods_info);
        ButterKnife.inject(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //设置分享的popuwindow页面
        popupView = getLayoutInflater().inflate(R.layout.popuwindow_item, null);

        popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        textview1 = (TextView) popupView.findViewById(R.id.textview1);
        textview2 = (TextView) popupView.findViewById(R.id.textview2);
        textview3 = (TextView) popupView.findViewById(R.id.textview3);
        textview4 = (TextView) popupView.findViewById(R.id.textview4);
        textview5 = (TextView) popupView.findViewById(R.id.textview5);

        textview1.setOnClickListener(this);
        textview2.setOnClickListener(this);
        textview3.setOnClickListener(this);
        textview4.setOnClickListener(this);
        textview5.setOnClickListener(this);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });


        //设置购物车的popuwindow页面

        popuinflate = getLayoutInflater().inflate(R.layout.popu_shoppingcar, null);
        window = new PopupWindow(popuinflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        window.setTouchable(true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

        //初始化各个控件
        back = (ImageView) popuinflate.findViewById(R.id.shopping_back);
        shoppingimage = (ImageView) popuinflate.findViewById(R.id.shopping_image);
        shoppingname = (TextView) popuinflate.findViewById(R.id.shopping_name);
        shoppingcontent = (TextView) popuinflate.findViewById(R.id.shopping_content);
        shoppingprice = (TextView) popuinflate.findViewById(R.id.shopping_price);
        //addsubview的初始化
        shoppingcountcontent = (AddSubView) popuinflate.findViewById(shopping_count_content);

        //数量
        counts = (LinearLayout) popuinflate.findViewById(count);
        countcontent = (RadioGroup) popuinflate.findViewById(R.id.count_content);
        //容量
        capacity = (LinearLayout) popuinflate.findViewById(R.id.capacity);
        capacitycontent = (RadioGroup) popuinflate.findViewById(R.id.capacity_content);
        //种类
        shoppingtype = (LinearLayout) popuinflate.findViewById(R.id.shopping_type);
        typecontent = (RadioGroup) popuinflate.findViewById(R.id.type_content);
        //颜色
        shoppingcolors = (LinearLayout) popuinflate.findViewById(R.id.shopping_colors);
        colorcontent = (RadioGroup) popuinflate.findViewById(R.id.color_content);
        //尺寸
        shoppingsize = (LinearLayout) popuinflate.findViewById(R.id.shopping_size);
        sizecontent = (RadioGroup) popuinflate.findViewById(R.id.size_content);
        //服装
        shoppingclothing = (LinearLayout) popuinflate.findViewById(R.id.shopping_clothing);
        clothingcontent = (RadioGroup) popuinflate.findViewById(R.id.clothing_content);
        //鞋
        shoppingshoes = (LinearLayout) popuinflate.findViewById(R.id.shopping_shoes);
        shoescontent = (RadioGroup) popuinflate.findViewById(R.id.shoes_content);

        //确认按钮
        popubtn = (Button) popuinflate.findViewById(popu_btn);


        back.setOnClickListener(this);


        //按键的监听
        window.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (window != null && window.isShowing()) {
                        window.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });

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
        final List<AllGoodsInfoBean.DataBean.ItemsBean.GoodsInfoBean> goods_info = items.getGoods_info();

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


        //设置添加购物车中popuwindow中的控件的数据
        Glide.with(this)
                .load(items.getGoods_image())
                .into(shoppingimage);
        shoppingname.setText(items.getBrand_info().getBrand_name());
        shoppingcontent.setText(items.getGoods_name());
        shoppingprice.setText(items.getPrice());


        List<AllGoodsInfoBean.DataBean.ItemsBean.SkuInfoBean> sku_info = items.getSku_info();


        final ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < sku_info.size(); i++) {
            AllGoodsInfoBean.DataBean.ItemsBean.SkuInfoBean skuInfoBean = sku_info.get(i);
            String type_name = skuInfoBean.getType_name();
            if ("数量".equals(type_name)) {
                counts.setVisibility(View.VISIBLE);
                List<AllGoodsInfoBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList = skuInfoBean.getAttrList();
                for (int j = 0; j < attrList.size(); j++) {
                    RadioButton radioButton = new RadioButton(getApplicationContext());
                    if (radioButton == null) {
                        return;
                    } else {

                        SetRadioButtonView(attrList, j, radioButton);
                        countcontent.addView(radioButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        //必须在添加进RadioGroup之后设置默认选中
                        if (j == 0) {
                            countcontent.check(radioButton.getId());
                        }
                        //设置RadioButton在RadioGroup中的位置
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) radioButton.getLayoutParams();

                        layoutParams.setMargins(0, 0, DensityUtil.dip2px(this,10), 0);

                        radioButton.setLayoutParams(layoutParams);


                    }
                }


                countcontent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton tempButton = (RadioButton) group.getChildAt(checkedId-1);
                        // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                        // 以下就可以对这个RadioButton进行处理了
                        countText = tempButton.getText().toString().trim();
                        arrayList.add("数量:"+countText);
                    }
                });

            }



            if ("容量".equals(type_name)) {
                capacity.setVisibility(View.VISIBLE);
                List<AllGoodsInfoBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList = skuInfoBean.getAttrList();
                for (int j = 0; j < attrList.size(); j++) {
                    RadioButton radioButton = new RadioButton(getApplicationContext());
                    if (radioButton == null) {
                        return;
                    } else {
                        SetRadioButtonView(attrList, j, radioButton);

                        capacitycontent.addView(radioButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        if (j == 0) {
                            capacitycontent.check(radioButton.getId());
                        }
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) radioButton.getLayoutParams();

                        layoutParams.setMargins(0, 0, DensityUtil.dip2px(this,10), 0);

                        radioButton.setLayoutParams(layoutParams);

                    }
                }


                capacitycontent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton tempButton = (RadioButton) group.getChildAt(checkedId-1);
                        // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                        // 以下就可以对这个RadioButton进行处理了
                        capacityText = tempButton.getText().toString().trim();

                        arrayList.add("容量:"+capacityText);

                    }
                });
            }



            if ("种类".equals(type_name)) {
                shoppingtype.setVisibility(View.VISIBLE);
                List<AllGoodsInfoBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList = skuInfoBean.getAttrList();
                for (int j = 0; j < attrList.size(); j++) {
                    RadioButton radioButton = new RadioButton(getApplicationContext());
                    if (radioButton == null) {
                        return;
                    } else {
                        SetRadioButtonView(attrList, j, radioButton);

                        typecontent.addView(radioButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        if (j == 0) {
                            typecontent.check(radioButton.getId());
                        }
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) radioButton.getLayoutParams();

                        layoutParams.setMargins(0, 0, DensityUtil.dip2px(this,10), 0);

                        radioButton.setLayoutParams(layoutParams);


                    }
                }

                typecontent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton tempButton = (RadioButton) group.getChildAt(checkedId-1);
                        // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                        // 以下就可以对这个RadioButton进行处理了
                        typeText = tempButton.getText().toString().trim();

                        arrayList.add("种类:"+typeText);

                    }
                });
            }




            if ("颜色".equals(type_name)) {
                shoppingcolors.setVisibility(View.VISIBLE);
                List<AllGoodsInfoBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList = skuInfoBean.getAttrList();
                for (int j = 0; j < attrList.size(); j++) {
                    RadioButton radioButton = new RadioButton(getApplicationContext());
                    if (radioButton == null) {
                        return;
                    } else {
                        SetRadioButtonView(attrList, j, radioButton);

                        colorcontent.addView(radioButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        if (j == 0) {
                            colorcontent.check(radioButton.getId());
                        }
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) radioButton.getLayoutParams();

                        layoutParams.setMargins(0, 0, DensityUtil.dip2px(this,10), 0);

                        radioButton.setLayoutParams(layoutParams);



                    }
                }


            }

            colorcontent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton tempButton = (RadioButton) group.getChildAt(checkedId-1);
                    Log.e("TAG",checkedId+"");
                    // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                    // 以下就可以对这个RadioButton进行处理了
                    colorText = tempButton.getText().toString().trim();
                    Log.e("TAG",colorText+"");
                    arrayList.add("颜色:"+colorText);

                }
            });




            if ("尺寸".equals(type_name)) {
                shoppingsize.setVisibility(View.VISIBLE);
                List<AllGoodsInfoBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList = skuInfoBean.getAttrList();
                for (int j = 0; j < attrList.size(); j++) {
                    RadioButton radioButton = new RadioButton(getApplicationContext());
                    if (radioButton == null) {
                        return;
                    } else {
                        SetRadioButtonView(attrList, j, radioButton);

                        sizecontent.addView(radioButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        if (j == 0) {
                            sizecontent.check(radioButton.getId());
                        }
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) radioButton.getLayoutParams();

                        layoutParams.setMargins(0, 0, DensityUtil.dip2px(this,10), 0);

                        radioButton.setLayoutParams(layoutParams);

                    }
                }

                sizecontent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton tempButton = (RadioButton) group.getChildAt(checkedId-1);
                        // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                        // 以下就可以对这个RadioButton进行处理了
                        sizeText = tempButton.getText().toString().trim();

                        arrayList.add("尺寸:"+sizeText);
                    }
                });
            }



            if ("服装".equals(type_name)) {
                shoppingclothing.setVisibility(View.VISIBLE);
                List<AllGoodsInfoBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList = skuInfoBean.getAttrList();
                for (int j = 0; j < attrList.size(); j++) {
                    RadioButton radioButton = new RadioButton(getApplicationContext());
                    if (radioButton == null) {
                        return;
                    } else {
                        SetRadioButtonView(attrList, j, radioButton);

                        clothingcontent.addView(radioButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        if (j == 0) {
                            clothingcontent.check(radioButton.getId());
                        }
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) radioButton.getLayoutParams();

                        layoutParams.setMargins(0, 0,DensityUtil.dip2px(this,10), 0);

                        radioButton.setLayoutParams(layoutParams);


                    }
                }

                clothingcontent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        RadioButton tempButton = (RadioButton) group.getChildAt(checkedId-1);
                        Log.e("TAG",checkedId+"");
                        // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                        // 以下就可以对这个RadioButton进行处理了
                        clothingText = tempButton.getText().toString().trim();

                        Log.e("TAG",clothingText+"");

                        arrayList.add("服装:"+clothingText);
                    }
                });
            }



            if ("鞋".equals(type_name)) {
                shoppingshoes.setVisibility(View.VISIBLE);
                List<AllGoodsInfoBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList = skuInfoBean.getAttrList();
                for (int j = 0; j < attrList.size(); j++) {
                    RadioButton radioButton = new RadioButton(getApplicationContext());
                    if (radioButton == null) {
                        return;
                    } else {
                        SetRadioButtonView(attrList, j, radioButton);


                        shoescontent.addView(radioButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        if (j == 0) {
                            shoescontent.check(radioButton.getId());
                        }

                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) radioButton.getLayoutParams();

                        layoutParams.setMargins(0, 0, DensityUtil.dip2px(this,10), 0);

                        radioButton.setLayoutParams(layoutParams);


                    }
                }

                shoescontent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        Log.e("TAG",checkedId+"");

                        RadioButton tempButton = (RadioButton) group.getChildAt(checkedId-1);


                        // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                        // 以下就可以对这个RadioButton进行处理了
                        shoesText = tempButton.getText().toString().trim();
                        Log.e("TAG",shoesText+"");
                        arrayList.add("鞋:"+shoesText);
                    }
                });


            }


        }

       final InfoBean infoBean=new InfoBean();


        for(int i = 0; i < arrayList.size(); i++) {

            string+=arrayList.get(i);
        }
       infoBean.setGoodsInfo(string);
        infoBean.setGoodsName(items.getGoods_name());
        infoBean.setGoodsDesc(items.getGoods_desc());
        infoBean.setImageUrl(items.getGoods_image());
        infoBean.setPrice(Double.parseDouble(items.getPrice()));

        shoppingcountcontent.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void numberChange(int value) {
                infoBean.setCount(value);
            }
        });


        popubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.showToast("已加入购物车");
                GoodsInfoDAO dao = new GoodsInfoDAO(AllGoodsInfoActivity.this);
                dao.AddGoods(infoBean);
            }
        });

    }

    private void SetRadioButtonView(List<AllGoodsInfoBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList, int j, final RadioButton radioButton) {
        //设置button为null
        radioButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
        radioButton.setGravity(Gravity.CENTER);
        //设置背景选择器
        radioButton.setBackgroundResource(R.drawable.radio_group_selector);

        radioButton.setWidth(DensityUtil.dip2px(this,100));
        radioButton.setHeight(DensityUtil.dip2px(this,100));

        radioButton.setText(attrList.get(j).getAttr_name());
        radioButton.setTextColor(Color.WHITE);
        radioButton.setPadding(2, 1, 2, 1);

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
                Intent intent=new Intent(this,CartShoppingActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_custom_service:
                break;
            case R.id.add_shoppingcart:
                window.showAtLocation(activityAllGoodsInfo, Gravity.CENTER, 0, 0);
                break;
            case R.id.buying:
                break;
            case R.id.shardSDK:
                popupWindow.showAtLocation(activityAllGoodsInfo, Gravity.CENTER, 0, 0);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textview1:
                showShare();
                break;
            case R.id.textview2:
                showShare();
                break;
            case R.id.textview3:
                showShare();
                break;
            case R.id.textview4:
                showShare();
                break;
            case R.id.textview5:
                popupWindow.dismiss();
                break;
            case R.id.shopping_back:
                window.dismiss();
                break;
        }
    }
}
