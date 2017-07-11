package atguigu.com.actualproject.expert;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.expert.bean.PersonalInfo;
import atguigu.com.actualproject.expert.fagment.AttentionFragment;
import atguigu.com.actualproject.expert.fagment.FansFragment;
import atguigu.com.actualproject.expert.fagment.LikeFragment;
import atguigu.com.actualproject.expert.fagment.RecommendFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class ExpertInfoActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

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
    @InjectView(R.id.expert_image)
    ImageView expertImage;
    @InjectView(R.id.expert_nameinfo)
    TextView expertNameinfo;
    @InjectView(R.id.expert_dutyinfo)
    TextView expertDutyinfo;
    @InjectView(R.id.expert_btn1)
    Button expertBtn1;
    @InjectView(R.id.expert_btn2)
    Button expertBtn2;
    @InjectView(R.id.rg_expert_goods)
    RadioGroup rgExpertGoods;
    @InjectView(R.id.like)
    RadioButton like;
    @InjectView(R.id.recommend)
    RadioButton recommend;
    @InjectView(R.id.attention)
    RadioButton attention;
    @InjectView(R.id.fans)
    RadioButton fans;
    @InjectView(R.id.activity_expert_info)
    LinearLayout activityExpertInfo;
    @InjectView(R.id.fragment_fl)
    FrameLayout fragmentFl;
    private String uid;
    private String mid;
    private String names;
    private String duty;

    private String purl="http://mobile.iliangcang.com/user/masterFollowed?app_key=Android&count=12&owner_id=";
    private String surl="&page=1&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";

    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment tempfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_info);
        ButterKnife.inject(this);
        uid = getIntent().getStringExtra("UID");
        names = getIntent().getStringExtra("NAME");
        mid = getIntent().getStringExtra("MID");
        duty = getIntent().getStringExtra("DUTY");

        inittitle();
        initData();

        rgExpertGoods.setOnCheckedChangeListener(this);
        rgExpertGoods.check(R.id.attention);

    }

    private void inittitle() {
        titleBack.setVisibility(View.VISIBLE);
        titleText.setText(names);
    }

    private void initData() {
        Glide.with(this)
                .load(mid)
                .into(expertImage);
        expertNameinfo.setText(names);
        expertDutyinfo.setText(duty);
       //联网请求数据
        getDataNet();

        fragments = new ArrayList<>();

        fragments.add(new LikeFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new AttentionFragment());
        fragments.add(new FansFragment());

    }

    private void getDataNet() {
        OkHttpUtils.get()
                .url(purl+uid+surl)
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
        PersonalInfo personalInfo = new Gson().fromJson(json, PersonalInfo.class);
        String like_count = personalInfo.getData().getItems().getLike_count();
        String recommendation_count = personalInfo.getData().getItems().getRecommendation_count();
        String following_count = personalInfo.getData().getItems().getFollowing_count();
        String followed_count = personalInfo.getData().getItems().getFollowed_count();
        like.setText("喜欢\n"+like_count);
        recommend.setText("推荐\n"+recommendation_count);
        attention.setText("关注\n"+following_count);
        fans.setText("粉丝\n"+followed_count);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.like:
                position = 0;
                break;
            case R.id.recommend:
                position = 1;
                break;
            case R.id.attention:
                position = 2;
                break;
            case R.id.fans:
                position = 3;
                break;
        }
        BaseFragment currentfragment = fragments.get(position);
        addFragment(currentfragment);

    }

    private void addFragment(BaseFragment currentfragment) {
        if (tempfragment != currentfragment) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (!currentfragment.isAdded()) {
                if (tempfragment != null) {
                    ft.hide(tempfragment);
                }
                ft.add(R.id.fragment_fl, currentfragment);
            } else {
                if (tempfragment != null) {
                    ft.hide(tempfragment);
                }
                ft.show(currentfragment);
            }

            ft.commit();
            tempfragment = currentfragment;

        }
    }

    @OnClick({R.id.title_back, R.id.expert_btn1, R.id.expert_btn2, R.id.like, R.id.recommend, R.id.attention, R.id.fans})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.expert_btn1:
                UIUtils.showToast("关注");
                break;
            case R.id.expert_btn2:
                UIUtils.showToast("私信");
                break;
        }
    }

}
