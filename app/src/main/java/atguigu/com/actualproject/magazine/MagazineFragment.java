package atguigu.com.actualproject.magazine;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.magazine.bean.MagazineBean;
import atguigu.com.actualproject.magazine.bean.SecondBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

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
    private String url;


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
        getNetData();

    }

    private void getNetData() {
        OkHttpUtils.get()
                .url(url)
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
    }

    private void processData(String json) {
        MagazineBean magazineBean = new Gson().fromJson(json, MagazineBean.class);
        List<SecondBean> jul0838 = (List<SecondBean>) magazineBean.getData().getItems().getInfos().get_$_2017JUL0838();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
