package atguigu.com.actualproject.recommend;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.activity.CaptureActivity;
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
    @InjectView(R.id.sao_sao)
    ImageButton saoSao;
    @InjectView(R.id.title_forward)
    ImageView titleForward;
    @InjectView(R.id.edit_deleter)
    TextView editDeleter;
    @InjectView(R.id.title_favor_select)
    CheckBox titleFavorSelect;
    @InjectView(R.id.title_bar)
    RelativeLayout titleBar;
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
        saoSao.setVisibility(View.VISIBLE);
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


        adapter = new RecommendAdapter(getActivity().getSupportFragmentManager(), pagers, titleList);
        viewpageRecommend.setAdapter(adapter);

        viewpageRecommend.setCurrentItem(0);
        //关联viewpager
        tablayoutRecommend.setupWithViewPager(viewpageRecommend);


        saoSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,CaptureActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
