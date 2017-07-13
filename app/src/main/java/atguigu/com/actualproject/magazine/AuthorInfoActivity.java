package atguigu.com.actualproject.magazine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.magazine.adapter.AuthorInfoAdapter;
import atguigu.com.actualproject.magazine.bean.MagazineBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class AuthorInfoActivity extends AppCompatActivity {

    @InjectView(R.id.author_info_recuclerview)
    RecyclerView authorInfoRecuclerview;
    @InjectView(R.id.activity_author_info)
    LinearLayout activityAuthorInfo;
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
    @InjectView(R.id.title_bar)
    RelativeLayout titleBar;
    private String url;
    private String author_name;
    private AuthorInfoAdapter adapter;
    private ArrayList<MagazineBean.DataBean.ItemsBean.SecondBean> objects;
    private HashMap<String, List<MagazineBean.DataBean.ItemsBean.SecondBean>> map;
    private List<MagazineBean.DataBean.ItemsBean.SecondBean> secondBeen;
    private MagazineBean.DataBean.ItemsBean.SecondBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_info);
        ButterKnife.inject(this);

        url = getIntent().getStringExtra("URL");
        author_name = getIntent().getStringExtra("AUTHOR_NAME");

        inittitle();
        initData();
    }

    private void inittitle() {
        titleBack.setVisibility(View.VISIBLE);
        titleText.setText("杂志＊"+author_name);
        //代码中给TextView设置图片
        Drawable drawable = titleText.getResources().getDrawable(R.drawable.down);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        titleText.setCompoundDrawables(null,null,drawable,null);
        titleText.setCompoundDrawablePadding(5);

        titleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AuthorInfoActivity.this,MagazineInfoActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.up_in,0);
            }
        });

    }

    private void initData() {
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
                        Log.e("TAG","联网成功");
                    }
                });
    }

    private void processData(String json) {

        MagazineBean magazineBean = new Gson().fromJson(json, MagazineBean.class);
        List<String> keys = magazineBean.getData().getItems().getKeys();

        map = magazineBean.getData().getItems().getInfos();
        objects= new ArrayList<>();
        for(int i = 0; i <keys.size(); i++) {
            String s = keys.get(i);
             secondBeen = map.get(s);
            for(int j = 0; j < this.secondBeen.size(); j++) {
                bean = this.secondBeen.get(j);
                this.objects.add(bean);
            }

        }
        adapter=new AuthorInfoAdapter(this, this.objects);
        authorInfoRecuclerview.setAdapter(adapter);

        authorInfoRecuclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
}
