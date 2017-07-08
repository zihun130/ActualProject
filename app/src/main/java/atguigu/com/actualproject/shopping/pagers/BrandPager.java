package atguigu.com.actualproject.shopping.pagers;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.shopping.adapter.BrandAdapter;
import atguigu.com.actualproject.shopping.pagers.bean.BrandBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by sun on 2017/7/6.
 */

public class BrandPager extends BaseFragment {
    @InjectView(R.id.brand_recyclerview)
    RecyclerView brandRecyclerview;
    private String url="http://mobile.iliangcang.com/brand/brandList?app_key=Android&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    private String purl="http://mobile.iliangcang.com/brand/brandList?app_key=Android&count=20&page=";
    private String ourl="&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    int number=1;
    private BrandAdapter adapter;
    private boolean isloadMore = false;
    private MaterialRefreshLayout materialRefreshLayout;
    private List<BrandBean.DataBean.ItemsBean> items;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.pager_brand, null);
        ButterKnife.inject(this, view);

        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isloadMore = false;
                getDatas();

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                isloadMore = true;
                number++;
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
        getDatas();

    }

    private void getDatas() {

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
                        materialRefreshLayout.finishRefresh();

                    }
                });
    }

    private void precessData(String json) {
        BrandBean brandBean = new Gson().fromJson(json, BrandBean.class);

        if(!isloadMore){
            items = brandBean.getData().getItems();
            if(items!=null&&items.size()>0){
                adapter=new BrandAdapter(context,items);
                brandRecyclerview.setAdapter(adapter);
            }
        }else {
            List<BrandBean.DataBean.ItemsBean> data = brandBean.getData().getItems();
                if(data!=null && data.size()>0){

                    items.addAll(0,data);
                    adapter.notifyDataSetChanged();
            }
        }
        brandRecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
    }

    private void getLoadMore() {

        OkHttpUtils.get()
                .url(purl+number+ourl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        UIUtils.showToast(e.getMessage());
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
}
