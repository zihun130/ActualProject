package atguigu.com.actualproject.shopping.pagers;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/6.
 */

public class FirstPager extends BaseFragment {
    @InjectView(R.id.first_recycleview)
    RecyclerView firstRecycleview;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.pager_first, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initTitle() {



    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
