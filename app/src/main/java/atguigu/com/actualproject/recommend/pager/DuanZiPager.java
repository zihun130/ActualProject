package atguigu.com.actualproject.recommend.pager;

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
import atguigu.com.actualproject.recommend.adapter.DuanZiAdapter;
import atguigu.com.actualproject.recommend.bean.RecomBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by sun on 2017/7/18.
 */

public class DuanZiPager extends BaseFragment {
    @InjectView(R.id.duanzi_recyclerview)
    RecyclerView duanziRecyclerview;
    private String url="http://s.budejie.com/topic/tag-topic/64/hot/budejie-android-6.6.3/0-20.json";
    private DuanZiAdapter adapter;
    private MaterialRefreshLayout materialRefreshLayout;
    private boolean isloadMore=false;
    private List<RecomBean.ListBean> lists;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.item_duanzi, null);
        ButterKnife.inject(this, view);


        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isloadMore = false;
                getDataFromNet();

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
       getDataFromNet();
    }

    private void getDataFromNet() {
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
                        materialRefreshLayout.finishRefresh();
                    }
                });
    }

    private void processData(String json) {
        RecomBean duanziBean = new Gson().fromJson(json, RecomBean.class);
        if(!isloadMore) {
            lists = duanziBean.getList();
            if(lists!=null && lists.size()>0) {
                adapter=new DuanZiAdapter(context,lists);
                duanziRecyclerview.setAdapter(adapter);
            }
        }else {
            List<RecomBean.ListBean> data = duanziBean.getList();
            if(data!=null && data.size()>0) {
                lists.addAll(data);
                adapter.notifyDataSetChanged();
            }
        }

        duanziRecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
    }



    private void getLoadMore() {
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
