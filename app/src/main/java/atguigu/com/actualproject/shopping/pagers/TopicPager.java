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
import atguigu.com.actualproject.shopping.adapter.TopicAdapter;
import atguigu.com.actualproject.shopping.pagers.bean.TopicBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by sun on 2017/7/6.
 */

public class TopicPager extends BaseFragment {
    @InjectView(R.id.topic_recyclerview)
    RecyclerView topicRecyclerview;
    private String url="http://mobile.iliangcang.com/goods/shopSpecial?app_key=Android&count=10&page=1&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";
    private TopicAdapter adapter;
    private MaterialRefreshLayout materialRefreshLayout;
    private boolean isloadMore=false;
    private int number=1;
    private String purl="http://mobile.iliangcang.com/goods/shopSpecial?app_key=Android&count=10&page=";
    private String ourl="&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";
    private List<TopicBean.DataBean.ItemsBean> items;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.pager_topic, null);
        ButterKnife.inject(this, view);


        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isloadMore = false;
                getfromNet();

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
       getfromNet();
    }

    private void getfromNet() {
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
        TopicBean topicBean = new Gson().fromJson(json, TopicBean.class);
        if(!isloadMore){
            items = topicBean.getData().getItems();
            adapter=new TopicAdapter(context,items);
            topicRecyclerview.setAdapter(adapter);
        }else {
            List<TopicBean.DataBean.ItemsBean> data = topicBean.getData().getItems();
            items.addAll(0,data);
            adapter.notifyDataSetChanged();
        }


        topicRecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
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
