package atguigu.com.actualproject.expert;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.expert.adapter.ExpertAdapter;
import atguigu.com.actualproject.expert.bean.ExpertBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by sun on 2017/7/6.
 */

public class ExpertFragment extends BaseFragment {
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
    @InjectView(R.id.recycleview_expert)
    RecyclerView recycleviewExpert;
    private String url="http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&page=1&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";
    private ExpertAdapter adapter;
    private GridLayoutManager manager;
    private boolean isloadMore=false;
    private String purl="http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&page=";
    private String surl="&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";
    private int  number=1;
    private MaterialRefreshLayout materialRefreshLayout;
    private List<ExpertBean.DataBean.ItemsBean> items;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_expert, null);
        ButterKnife.inject(this, view);

        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isloadMore = false;
                getNetData();

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
        titleImage.setVisibility(View.VISIBLE);
        titleText.setText("达 人");
        titleSelect.setVisibility(View.VISIBLE);
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
                        materialRefreshLayout.finishRefresh();
                    }
                });
    }

    private void processData(String json) {
        ExpertBean expertBean = new Gson().fromJson(json, ExpertBean.class);

        if(!isloadMore){
            items = expertBean.getData().getItems();
            if(items!=null && items.size()>0){
                adapter=new ExpertAdapter(context,items);
                recycleviewExpert.setAdapter(adapter);
            }

        }else {
            List<ExpertBean.DataBean.ItemsBean> data = expertBean.getData().getItems();
            if(data!=null && data.size()>0){
                items.addAll(0,data);
                adapter.notifyDataSetChanged();
            }
        }


        manager = new GridLayoutManager(context, 3);
        recycleviewExpert.setLayoutManager(manager);
    }

    private void getLoadMore() {
        OkHttpUtils.get()
                .url(purl+number+surl)
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

    @OnClick({R.id.title_image, R.id.title_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_image:
                startActivity(new Intent(context,SearchPersonActivity.class));
                break;
            case R.id.title_select:
                break;
        }
    }
}
