package atguigu.com.actualproject.shopping;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.activity.CartShoppingActivity;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.shopping.adapter.ShoppingAdapter;
import atguigu.com.actualproject.shopping.pagers.BrandPager;
import atguigu.com.actualproject.shopping.pagers.FirstPager;
import atguigu.com.actualproject.shopping.pagers.GiftPager;
import atguigu.com.actualproject.shopping.pagers.TopicPager;
import atguigu.com.actualproject.shopping.pagers.TypePager;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/7/6.
 */

public class ShoppingFragment extends BaseFragment {
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
    @InjectView(R.id.tablayout_shopping)
    TabLayout tablayoutShopping;
    @InjectView(R.id.viewpage_shopping)
    ViewPager viewpageShopping;

    private List<BaseFragment> pagers;
    private List<String> namelist;
    private ShoppingAdapter adapter;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_shopping, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initTitle() {
        titleImage.setVisibility(View.VISIBLE);
        titleText.setText("商 店");
        titleShoppingcart.setVisibility(View.VISIBLE);

    }

    @Override
    public void initData() {
         pagers = new ArrayList<>();
         pagers.add(new TypePager());
         pagers.add(new BrandPager());
         pagers.add(new FirstPager());
         pagers.add(new TopicPager());
         pagers.add(new GiftPager());

        namelist = new ArrayList<>();
        namelist.add("分类");
        namelist.add("品牌");
        namelist.add("首页");
        namelist.add("专题");
        namelist.add("礼物");

          tablayoutShopping.addTab(tablayoutShopping.newTab().setText(namelist.get(0)));
          tablayoutShopping.addTab(tablayoutShopping.newTab().setText(namelist.get(1)));
          tablayoutShopping.addTab(tablayoutShopping.newTab().setText(namelist.get(2)));
          tablayoutShopping.addTab(tablayoutShopping.newTab().setText(namelist.get(3)));
          tablayoutShopping.addTab(tablayoutShopping.newTab().setText(namelist.get(4)));


        adapter=new ShoppingAdapter(getActivity().getSupportFragmentManager(),pagers,namelist);

        viewpageShopping.setAdapter(adapter);
        viewpageShopping.setCurrentItem(2);

        tablayoutShopping.setupWithViewPager(viewpageShopping);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.title_image, R.id.title_shoppingcart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_image:
                startActivity(new Intent(context,SearchActivity.class));
                break;
            case R.id.title_shoppingcart:
                startActivity(new Intent(context, CartShoppingActivity.class));
                break;
        }
    }
}
