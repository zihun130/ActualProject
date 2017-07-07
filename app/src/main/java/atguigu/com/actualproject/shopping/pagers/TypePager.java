package atguigu.com.actualproject.shopping.pagers;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.shopping.adapter.TypeAdapter;
import atguigu.com.actualproject.shopping.pagers.bean.TypeBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by sun on 2017/7/6.
 */

public class TypePager extends BaseFragment {
    @InjectView(R.id.type_recyclerview)
    RecyclerView typeRecyclerview;
    private String url="http://mobile.iliangcang.com/goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    private TypeAdapter adapter;
    private GridLayoutManager manager;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.pager_type, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {
        fromNetData();
    }

    private void fromNetData() {
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
                         precessData(response);
                    }
                });
    }

    private void precessData(String json) {
        TypeBean typeBean = new Gson().fromJson(json, TypeBean.class);
        List<TypeBean.DataBean.ItemsBean> items = typeBean.getData().getItems();
        adapter=new TypeAdapter(context,items);
        typeRecyclerview.setAdapter(adapter);

        manager = new GridLayoutManager(context, 2);
        typeRecyclerview.setLayoutManager(manager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
