package atguigu.com.actualproject.expert.fagment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import atguigu.com.actualproject.expert.adapter.RecommendAdapter;
import atguigu.com.actualproject.expert.bean.RecommendBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by sun on 2017/7/10.
 */

public class RecommendFragment extends BaseFragment {
    @InjectView(R.id.recyclerview_item)
    RecyclerView recyclerviewItem;
    private View inflate;
    private String recommendurl1 = "http://mobile.iliangcang.com/user/masterListInfo?app_key=Android&count=10&owner_id=";
    private String recommendurl2 = "&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";

    private String RecommendURL1="http://mobile.iliangcang.com/user/masterListInfo?app_key=Android&count=10&owner_id=";
    private String RecommendURL2="&page=";
    private int    number=1;
    private String RecommendURL3="&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
    private RecommendAdapter adapter;
    private GridLayoutManager manager;
    private MaterialRefreshLayout materialRefreshLayout;
    private boolean isloadMore=false;
    private String uid;
    private List<RecommendBean.DataBean.ItemsBean.GoodsBean> goods;

    @Override
    protected View initView() {
        inflate = LayoutInflater.from(context).inflate(R.layout.like_item, null);
        ButterKnife.inject(this, inflate);

        materialRefreshLayout = (MaterialRefreshLayout) inflate.findViewById(R.id.refresh);

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
                number++;
                getLoadMore();

            }
        });


        return inflate;
    }



    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {
        uid = getActivity().getIntent().getStringExtra("UID");
        getDataFromNet();

    }

    private void getDataFromNet() {
        if (uid != null) {
            OkHttpUtils.get()
                    .url(recommendurl1 + uid + recommendurl2)
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
    }

    private void processData(String json) {
        RecommendBean recommendBean = new Gson().fromJson(json, RecommendBean.class);

        if(!isloadMore){
            goods = recommendBean.getData().getItems().getGoods();
            RecommendBean.DataBean.ItemsBean items = recommendBean.getData().getItems();

            if (goods != null && goods.size() > 0) {
                adapter = new RecommendAdapter(context, goods,items);
                recyclerviewItem.setAdapter(adapter);
            }
        }else {
            List<RecommendBean.DataBean.ItemsBean.GoodsBean> data = recommendBean.getData().getItems().getGoods();
            if(data!=null && data.size()>0){
                goods.addAll(data);
                adapter.notifyDataSetChanged();
            }
        }

        manager = new GridLayoutManager(context, 2);
        recyclerviewItem.setLayoutManager(manager);
    }

    private void getLoadMore() {
        if (uid != null) {
            OkHttpUtils.get()
                    .url(RecommendURL1 + uid + RecommendURL2+number+RecommendURL3)
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
