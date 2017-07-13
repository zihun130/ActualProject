package atguigu.com.actualproject.magazine.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.magazine.adapter.AuthorAdapter;
import atguigu.com.actualproject.magazine.bean.AuthorBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by sun on 2017/7/12.
 */

public class AuthorFragment extends BaseFragment {
    @InjectView(R.id.author_recuclerview)
    RecyclerView authorRecuclerview;
    private String url="http://mobile.iliangcang.com/topic/magazineAuthorList?app_key=Android&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    private AuthorAdapter adapter;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_author, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initTitle() {

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
        AuthorBean authorBean = new Gson().fromJson(json, AuthorBean.class);
        List<AuthorBean.DataBean.ItemsBean> items = authorBean.getData().getItems();

        adapter=new AuthorAdapter(context,items);
        authorRecuclerview.setAdapter(adapter);

        authorRecuclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
