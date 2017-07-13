package atguigu.com.actualproject.magazine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.Constant;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.magazine.adapter.MagazineAdapter;
import atguigu.com.actualproject.magazine.bean.MagazineBean;
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
    @InjectView(R.id.title_bar)
    RelativeLayout titleBar;
    private HashMap<String, List<MagazineBean.DataBean.ItemsBean.SecondBean>> map;
    private ArrayList<MagazineBean.DataBean.ItemsBean.SecondBean> objects;
    private List<MagazineBean.DataBean.ItemsBean.SecondBean> secondBeen;
    private MagazineBean.DataBean.ItemsBean.SecondBean bean;
    private MagazineAdapter adapter;
    private String s;


    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_magazine, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initTitle() {
        titleDate.setVisibility(View.VISIBLE);
        titleText.setText("杂 志");
        //代码中给TextView设置图片
        Drawable drawable = titleText.getResources().getDrawable(R.drawable.down);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        titleText.setCompoundDrawables(null,null,drawable,null);

        titleText.setCompoundDrawablePadding(5);

        titleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MagazineInfoActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.up_in,0);
            }
        });
    }

    @Override
    public void initData() {
        getNetData();

    }

    private void getNetData() {
        OkHttpUtils.get()
                .url(Constant.MAGAZINEONE)
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

        List<String> keys = magazineBean.getData().getItems().getKeys();

        map = magazineBean.getData().getItems().getInfos();
        objects= new ArrayList<>();
        for(int i = 0; i <keys.size(); i++) {
             s = keys.get(i);
            secondBeen = map.get(s);
            for(int j = 0; j < this.secondBeen.size(); j++) {
                bean = this.secondBeen.get(j);
                this.objects.add(bean);
            }

        }
        adapter=new MagazineAdapter(context,objects);

        recycleviewMagazine.setAdapter(adapter);

        recycleviewMagazine.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
