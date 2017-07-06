package atguigu.com.actualproject.magazine;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/6.
 */

public class MagazineFragment extends BaseFragment {
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
    @InjectView(R.id.recycleview_magazine)
    RecyclerView recycleviewMagazine;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_magazine, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initTitle() {
        titleDate.setVisibility(View.VISIBLE);
        titleText.setText("杂志");
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
