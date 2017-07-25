package atguigu.com.actualproject.personal_center;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.GlidImageLoader;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.personal_center.adapter.CommonalityAdapter;
import atguigu.com.actualproject.personal_center.bean.BilibiliBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by sun on 2017/7/6.
 */

public class PersonalCenterFragment extends BaseFragment {

    @InjectView(R.id.navigation_drawer)
    ImageView navigationDrawer;
    @InjectView(R.id.default_avatar)
    ImageView defaultAvatar;
    @InjectView(R.id.login_text)
    TextView loginText;
    @InjectView(R.id.login_home)
    ImageView loginHome;
    @InjectView(R.id.game_center)
    ImageView gameCenter;
    @InjectView(R.id.menu_download)
    ImageView menuDownload;
    @InjectView(R.id.download_search)
    ImageView downloadSearch;
    @InjectView(R.id.bilibili_banner)
    Banner bilibiliBanner;
    @InjectView(R.id.drawing_prefecture)
    ImageView drawingPrefecture;
    @InjectView(R.id.tv_drawing)
    TextView tvDrawing;
    @InjectView(R.id.drawing_recyclerview)
    RecyclerView drawingRecyclerview;
    @InjectView(R.id.drawing_more_btn)
    Button drawingMoreBtn;
    @InjectView(R.id.living_recreation)
    ImageView livingRecreation;
    @InjectView(R.id.tv_living)
    TextView tvLiving;
    @InjectView(R.id.living_recyclerview)
    RecyclerView livingRecyclerview;
    @InjectView(R.id.living_more_btn)
    Button livingMoreBtn;
    @InjectView(R.id.songing_dance)
    ImageView songingDance;
    @InjectView(R.id.tv_songing)
    TextView tvSonging;
    @InjectView(R.id.songing_dance_recyclerview)
    RecyclerView songingDanceRecyclerview;
    @InjectView(R.id.songing_more_btn)
    Button songingMoreBtn;
    @InjectView(R.id.phone_game)
    ImageView phoneGame;
    @InjectView(R.id.tv_phone_game)
    TextView tvPhoneGame;
    @InjectView(R.id.phone_game_recyclerview)
    RecyclerView phoneGameRecyclerview;
    @InjectView(R.id.phone_more_btn)
    Button phoneMoreBtn;
    @InjectView(R.id.stand_alone)
    ImageView standAlone;
    @InjectView(R.id.tv_stand_alone)
    TextView tvStandAlone;
    @InjectView(R.id.stand_alone_recyclerview)
    RecyclerView standAloneRecyclerview;
    @InjectView(R.id.stand_more_btn)
    Button standMoreBtn;
    @InjectView(R.id.online_game)
    ImageView onlineGame;
    @InjectView(R.id.tv_online_game)
    TextView tvOnlineGame;
    @InjectView(R.id.online_game_recyclerview)
    RecyclerView onlineGameRecyclerview;
    @InjectView(R.id.online_more_btn)
    Button onlineMoreBtn;
    @InjectView(R.id.e_sports)
    ImageView eSports;
    @InjectView(R.id.tv_e_sports)
    TextView tvESports;
    @InjectView(R.id.e_sports_recyclerview)
    RecyclerView eSportsRecyclerview;
    @InjectView(R.id.e_more_btn)
    Button eMoreBtn;
    @InjectView(R.id.Otaku_culture)
    ImageView OtakuCulture;
    @InjectView(R.id.tv_Otaku_culture)
    TextView tvOtakuCulture;
    @InjectView(R.id.Otaku_culture_recyclerview)
    RecyclerView OtakuCultureRecyclerview;
    @InjectView(R.id.Otaku_more_btn)
    Button OtakuMoreBtn;
    private String url = "http://live.bilibili.com/AppNewIndex/common?_device=android&appkey=1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&scale=hdpi&ts=1490013188000&sign=92541a11ed62841120e786e637b9db3b";
    private ArrayList<String> imageurls;
    private CommonalityAdapter drawingadapter;
    private GridLayoutManager manager;
    private CommonalityAdapter livingadapter;
    private GridLayoutManager manager1;
    private CommonalityAdapter songadapter;
    private CommonalityAdapter phoneadapter;
    private CommonalityAdapter standadapter;
    private CommonalityAdapter onlineadapter;
    private CommonalityAdapter eadapter;
    private CommonalityAdapter Otakuadapter;
    private GridLayoutManager manager2;
    private GridLayoutManager manager3;
    private GridLayoutManager manager4;
    private GridLayoutManager manager5;
    private GridLayoutManager manager6;
    private GridLayoutManager manager7;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_personal_center, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initTitle() {

    }


    @Override
    public void initData() {
        getDataNet();
    }

    private void getDataNet() {
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

    private void processData(String response) {
        BilibiliBean bilibiliBean = new Gson().fromJson(response, BilibiliBean.class);
        //设置顶部轮播图片
        List<BilibiliBean.DataBean.BannerBean> banner = bilibiliBean.getData().getBanner();
        imageurls = new ArrayList<>();
        for (int i = 0; i < banner.size(); i++) {
            String img = banner.get(i).getImg();
            imageurls.add(img);
        }
        bilibiliBanner.setImages(imageurls)
                .setImageLoader(new GlidImageLoader())
                .isAutoPlay(true)
                .setIndicatorGravity(Gravity.RIGHT)
                .start();

        List<BilibiliBean.DataBean.PartitionsBean> partitions = bilibiliBean.getData().getPartitions();

        BilibiliBean.DataBean.PartitionsBean partitionsBean = partitions.get(0);
        BilibiliBean.DataBean.PartitionsBean partitionsBean1 = partitions.get(1);
        BilibiliBean.DataBean.PartitionsBean partitionsBean2 = partitions.get(2);
        BilibiliBean.DataBean.PartitionsBean partitionsBean3 = partitions.get(3);
        BilibiliBean.DataBean.PartitionsBean partitionsBean4 = partitions.get(4);
        BilibiliBean.DataBean.PartitionsBean partitionsBean5 = partitions.get(5);
        BilibiliBean.DataBean.PartitionsBean partitionsBean6 = partitions.get(6);
        BilibiliBean.DataBean.PartitionsBean partitionsBean7 = partitions.get(7);
        BilibiliBean.DataBean.PartitionsBean.PartitionBean partition = partitionsBean.getPartition();
        BilibiliBean.DataBean.PartitionsBean.PartitionBean partition1 = partitionsBean1.getPartition();
        BilibiliBean.DataBean.PartitionsBean.PartitionBean partition2 = partitionsBean2.getPartition();
        BilibiliBean.DataBean.PartitionsBean.PartitionBean partition3 = partitionsBean3.getPartition();
        BilibiliBean.DataBean.PartitionsBean.PartitionBean partition4 = partitionsBean4.getPartition();
        BilibiliBean.DataBean.PartitionsBean.PartitionBean partition5 = partitionsBean5.getPartition();
        BilibiliBean.DataBean.PartitionsBean.PartitionBean partition6 = partitionsBean6.getPartition();
        BilibiliBean.DataBean.PartitionsBean.PartitionBean partition7 = partitionsBean7.getPartition();

        Glide.with(context)
                .load(partition.getSub_icon().getSrc())
                .into(drawingPrefecture);
        Glide.with(context)
                .load(partition1.getSub_icon().getSrc())
                .into(livingRecreation);

        Glide.with(context)
                .load(partition2.getSub_icon().getSrc())
                .into(songingDance);

        Glide.with(context)
                .load(partition3.getSub_icon().getSrc())
                .into(phoneGame);

        Glide.with(context)
                .load(partition4.getSub_icon().getSrc())
                .into(standAlone);

        Glide.with(context)
                .load(partition5.getSub_icon().getSrc())
                .into(onlineGame);

        Glide.with(context)
                .load(partition6.getSub_icon().getSrc())
                .into(eSports);

        Glide.with(context)
                .load(partition7.getSub_icon().getSrc())
                .into(OtakuCulture);
        List<BilibiliBean.DataBean.PartitionsBean.LivesBean> lives = partitionsBean.getLives();
        List<BilibiliBean.DataBean.PartitionsBean.LivesBean> lives1 = partitionsBean1.getLives();
        List<BilibiliBean.DataBean.PartitionsBean.LivesBean> lives2 = partitionsBean2.getLives();
        List<BilibiliBean.DataBean.PartitionsBean.LivesBean> lives3 = partitionsBean3.getLives();
        List<BilibiliBean.DataBean.PartitionsBean.LivesBean> lives4 = partitionsBean4.getLives();
        List<BilibiliBean.DataBean.PartitionsBean.LivesBean> lives5 = partitionsBean5.getLives();
        List<BilibiliBean.DataBean.PartitionsBean.LivesBean> lives6 = partitionsBean6.getLives();
        List<BilibiliBean.DataBean.PartitionsBean.LivesBean> lives7 = partitionsBean7.getLives();
        drawingadapter = new CommonalityAdapter(context, lives);
        drawingRecyclerview.setAdapter(drawingadapter);

        livingadapter = new CommonalityAdapter(context, lives1);
        livingRecyclerview.setAdapter(livingadapter);

        songadapter = new CommonalityAdapter(context, lives2);
        songingDanceRecyclerview.setAdapter(songadapter);

        phoneadapter = new CommonalityAdapter(context, lives3);
        phoneGameRecyclerview.setAdapter(phoneadapter);

        standadapter = new CommonalityAdapter(context, lives4);
        standAloneRecyclerview.setAdapter(standadapter);

        onlineadapter = new CommonalityAdapter(context, lives5);
        onlineGameRecyclerview.setAdapter(onlineadapter);

        eadapter = new CommonalityAdapter(context, lives6);
        eSportsRecyclerview.setAdapter(eadapter);

        Otakuadapter = new CommonalityAdapter(context, lives7);
        OtakuCultureRecyclerview.setAdapter(Otakuadapter);


        manager = new GridLayoutManager(context, 2);
        drawingRecyclerview.setLayoutManager(manager);

        manager1 = new GridLayoutManager(context, 2);
        livingRecyclerview.setLayoutManager(manager1);

        manager2 = new GridLayoutManager(context, 2);
        songingDanceRecyclerview.setLayoutManager(manager2);

        manager3 = new GridLayoutManager(context, 2);
        phoneGameRecyclerview.setLayoutManager(manager3);

        manager4 = new GridLayoutManager(context, 2);
        standAloneRecyclerview.setLayoutManager(manager4);

        manager5 = new GridLayoutManager(context, 2);
        onlineGameRecyclerview.setLayoutManager(manager5);

        manager6 = new GridLayoutManager(context, 2);
        eSportsRecyclerview.setLayoutManager(manager6);

        manager7 = new GridLayoutManager(context, 2);
        OtakuCultureRecyclerview.setLayoutManager(manager7);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.navigation_drawer, R.id.default_avatar, R.id.login_home, R.id.game_center, R.id.menu_download, R.id.download_search, R.id.tv_drawing, R.id.drawing_more_btn, R.id.tv_living, R.id.living_more_btn, R.id.tv_songing, R.id.songing_more_btn, R.id.tv_phone_game, R.id.phone_more_btn, R.id.tv_stand_alone, R.id.stand_more_btn, R.id.tv_online_game, R.id.online_more_btn, R.id.tv_e_sports, R.id.e_more_btn, R.id.tv_Otaku_culture, R.id.Otaku_more_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.navigation_drawer:
                break;
            case R.id.default_avatar:
                break;
            case R.id.login_home:
                break;
            case R.id.game_center:
                break;
            case R.id.menu_download:
                break;
            case R.id.download_search:
                break;
            case R.id.tv_drawing:
                break;
            case R.id.drawing_more_btn:
                break;
            case R.id.tv_living:
                break;
            case R.id.living_more_btn:
                break;
            case R.id.tv_songing:
                break;
            case R.id.songing_more_btn:
                break;
            case R.id.tv_phone_game:
                break;
            case R.id.phone_more_btn:
                break;
            case R.id.tv_stand_alone:
                break;
            case R.id.stand_more_btn:
                break;
            case R.id.tv_online_game:
                break;
            case R.id.online_more_btn:
                break;
            case R.id.tv_e_sports:
                break;
            case R.id.e_more_btn:
                break;
            case R.id.tv_Otaku_culture:
                break;
            case R.id.Otaku_more_btn:
                break;
        }
    }
}
