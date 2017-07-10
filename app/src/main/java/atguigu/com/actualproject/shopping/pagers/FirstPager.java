package atguigu.com.actualproject.shopping.pagers;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.shopping.adapter.FirstAdapter;
import atguigu.com.actualproject.shopping.pagers.bean.FirstBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by sun on 2017/7/6.
 */

public class FirstPager extends BaseFragment {
    @InjectView(R.id.first_recycleview)
    RecyclerView firstRecycleview;
    @InjectView(R.id.top_image)
    ImageView topImage;
    private String url = "http://mobile.iliangcang.com/goods/newShopHome?app_key=Android&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";
    private FirstAdapter adapter;
    private boolean isloadMore = false;
    private MaterialRefreshLayout materialRefreshLayout;
    private List<FirstBean.DataBean.ItemsBean.ListBean> list;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.pager_first, null);
        ButterKnife.inject(this, view);

        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isloadMore = false;
                getDataNet();

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                isloadMore = true;
                getLoadMore();

            }
        });
        return view;
    }


    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {
        getDataNet();
    }

    private void getDataNet() {
        OkHttpUtils.get()
                .url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                UIUtils.showToast("联网失败,请检查网络");
            }

            @Override
            public void onResponse(String response, int id) {
                precessData(response);
                materialRefreshLayout.finishRefresh();

            }
        });
    }

    private void precessData(String json) {
        Gson gson = new Gson();
        FirstBean firstBean = gson.fromJson(json, FirstBean.class);

        if (!isloadMore) {
            list = firstBean.getData().getItems().getList();
            if (list != null && list.size() > 0) {
                adapter = new FirstAdapter(context, list);
                firstRecycleview.setAdapter(adapter);
            }
        } else {
            List<FirstBean.DataBean.ItemsBean.ListBean> data = firstBean.getData().getItems().getList();
            if (data != null && data.size() > 0) {
                list.addAll(0, data);
                adapter.notifyDataSetChanged();
            }
        }

        firstRecycleview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }


    private void getLoadMore() {
        OkHttpUtils.get()
                .url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                UIUtils.showToast("联网失败,请检查网络");
            }

            @Override
            public void onResponse(String response, int id) {
                precessData(response);
                materialRefreshLayout.finishRefreshLoadMore();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.top_image)
    public void onViewClicked() {
        firstRecycleview.scrollToPosition(0);
    }
}
