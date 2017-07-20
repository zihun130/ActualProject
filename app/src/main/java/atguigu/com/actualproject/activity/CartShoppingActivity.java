package atguigu.com.actualproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.adapter.CartRecyclerAdapter;
import atguigu.com.actualproject.database.HelperManager;
import atguigu.com.actualproject.database.InfoBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CartShoppingActivity extends AppCompatActivity {

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
    @InjectView(R.id.title_forward)
    ImageView titleForward;
    @InjectView(R.id.edit_deleter)
    TextView editDeleter;
    @InjectView(R.id.title_favor_select)
    CheckBox titleFavorSelect;
    @InjectView(R.id.title_bar)
    RelativeLayout titleBar;
    @InjectView(R.id.cart_recyclerview)
    RecyclerView cartRecyclerview;
    @InjectView(R.id.reach_discount)
    ImageView reachDiscount;
    @InjectView(R.id.discount_discount)
    ImageView discountDiscount;
    @InjectView(R.id.pack_bottom)
    ImageView packBottom;
    @InjectView(R.id.ship_bottom)
    ImageView shipBottom;
    @InjectView(R.id.reach_discount_text)
    TextView reachDiscountText;
    @InjectView(R.id.reach_discount_sub)
    LinearLayout reachDiscountSub;
    @InjectView(R.id.discount_discount_text)
    TextView discountDiscountText;
    @InjectView(R.id.discount_discount_price)
    LinearLayout discountDiscountPrice;
    @InjectView(R.id.pack_bottom_text)
    TextView packBottomText;
    @InjectView(R.id.pack_bottom_packaging)
    LinearLayout packBottomPackaging;
    @InjectView(R.id.ship_bottom_text)
    TextView shipBottomText;
    @InjectView(R.id.ship_bottom_postage)
    LinearLayout shipBottomPostage;
    @InjectView(R.id.sub)
    TextView sub;
    @InjectView(R.id.discount)
    TextView discount;
    @InjectView(R.id.pack)
    TextView pack;
    @InjectView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @InjectView(R.id.all_price_text)
    TextView allPriceText;
    @InjectView(R.id.all_price)
    LinearLayout allPrice;
    @InjectView(R.id.save_price_text)
    TextView savePriceText;
    @InjectView(R.id.save_price)
    LinearLayout savePrice;
    @InjectView(R.id.activity_cart_shopping)
    LinearLayout activityCartShopping;
    @InjectView(R.id.cart_btn)
    Button cartBtn;

    private List<InfoBean> allGoodsContent;
    private CartRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_shopping);
        ButterKnife.inject(this);
        inittitle();
        initData();

    }

    private void inittitle() {
        titleBack.setVisibility(View.VISIBLE);
        editDeleter.setVisibility(View.VISIBLE);
        titleText.setText("购物车");
    }

    private void initData() {

        allGoodsContent = HelperManager.getInstance().getGoodsInfoDAO().getAllGoodsContent();

        adapter = new CartRecyclerAdapter(this, allGoodsContent, allPriceText, checkboxAll, editDeleter);
        cartRecyclerview.setAdapter(adapter);
        cartRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }


    @OnClick({R.id.title_back, R.id.cart_btn,R.id.checkbox_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.cart_btn:
                break;
            case R.id.checkbox_all:
                boolean checked = checkboxAll.isChecked();
                adapter.checkAll_none(checked);
                adapter.showTotalPrice();
                break;

        }
    }

}
