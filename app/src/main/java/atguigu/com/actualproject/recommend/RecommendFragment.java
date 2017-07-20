package atguigu.com.actualproject.recommend;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.recommend.adapter.RecommendAdapter;
import atguigu.com.actualproject.recommend.pager.DuanZiPager;
import atguigu.com.actualproject.recommend.pager.RecommendPager;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/6.
 */

public class RecommendFragment extends BaseFragment {
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
    @InjectView(R.id.tablayout_recommend)
    TabLayout tablayoutRecommend;
    @InjectView(R.id.viewpage_recommend)
    ViewPager viewpageRecommend;
    private List<BaseFragment> pagers;
    private List<String> titleList;
    private RecommendAdapter adapter;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_allgoods, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initTitle() {
        titleText.setText("百思");
    }

    @Override
    public void initData() {
        pagers = new ArrayList<>();
        pagers.add(new RecommendPager());
        pagers.add(new DuanZiPager());

        titleList = new ArrayList<>();
        titleList.add("推荐");
        titleList.add("段子");


        tablayoutRecommend.addTab(tablayoutRecommend.newTab().setText(titleList.get(0)));
        tablayoutRecommend.addTab(tablayoutRecommend.newTab().setText(titleList.get(1)));


        adapter=new RecommendAdapter(getActivity().getSupportFragmentManager(),pagers,titleList);
        viewpageRecommend.setAdapter(adapter);

        viewpageRecommend.setCurrentItem(0);
        //关联viewpager
        tablayoutRecommend.setupWithViewPager(viewpageRecommend);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
