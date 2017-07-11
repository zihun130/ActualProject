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
import atguigu.com.actualproject.expert.adapter.AttentionAdapter;
import atguigu.com.actualproject.expert.bean.AttentionBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by sun on 2017/7/10.
 */

public class AttentionFragment extends BaseFragment {
    @InjectView(R.id.recyclerview_item)
    RecyclerView recyclerviewItem;
    private View inflate;
    private String attentionurl1 = "http://mobile.iliangcang.com/user/masterFollow?app_key=Android&count=12&owner_id=";
    private String attentionurl2 = "&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";

    private String AttentionURL1="http://mobile.iliangcang.com/user/masterFollow?app_key=Android&count=12&owner_id=";
    private String AttentionURL2="&page=";
    private int    number=1;
    private String AttentionURL3="&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
    private AttentionAdapter adapter;
    private GridLayoutManager manager;
    private String uid;
    private MaterialRefreshLayout materialRefreshLayout;
    private boolean isloadMore=false;
    private List<AttentionBean.DataBean.ItemsBean.UsersBean> users;

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
                    .url(attentionurl1 + uid + attentionurl2)
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
        AttentionBean attentionBean = new Gson().fromJson(json, AttentionBean.class);

        if(!isloadMore){
            users = attentionBean.getData().getItems().getUsers();
            if(users!=null && users.size()>0){
                adapter=new AttentionAdapter(context,users);
                recyclerviewItem.setAdapter(adapter);
            }
        }else {
            List<AttentionBean.DataBean.ItemsBean.UsersBean> data = attentionBean.getData().getItems().getUsers();
            if(data!=null && data.size()>0){
                users.addAll(0,data);
                adapter.notifyDataSetChanged();
            }
        }

        manager = new GridLayoutManager(context, 3);
        recyclerviewItem.setLayoutManager(manager);
    }

    private void getLoadMore() {
        if (uid != null) {
            OkHttpUtils.get()
                    .url(AttentionURL1 + uid + AttentionURL2+number+AttentionURL3)
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
