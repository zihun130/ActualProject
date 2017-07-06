package atguigu.com.actualproject.personal_center;

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

public class PersonalCenterFragment extends BaseFragment {
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

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_personal_center, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initTitle() {
       titleText.setText("个人中心");
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
