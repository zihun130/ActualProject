package atguigu.com.actualproject.shopping;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.base.BaseFragment;
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
                break;
            case R.id.title_shoppingcart:
                break;
        }
    }
}
