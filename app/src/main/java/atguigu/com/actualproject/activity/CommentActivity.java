package atguigu.com.actualproject.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.adapter.HotCommentAdapter;
import atguigu.com.actualproject.adapter.NormalCommentAdapter;
import atguigu.com.actualproject.bean.CommentBean;
import atguigu.com.actualproject.recommend.bean.RecomBean;
import atguigu.com.actualproject.zxing.encoding.EncodingHandler;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;

public class CommentActivity extends AppCompatActivity {


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
    @InjectView(R.id.title_forward)
    ImageView titleForward;
    @InjectView(R.id.edit_deleter)
    TextView editDeleter;
    @InjectView(R.id.title_favor_select)
    CheckBox titleFavorSelect;
    @InjectView(R.id.title_bar)
    RelativeLayout titleBar;
    @InjectView(R.id.iv_headpic)
    CircleImageView ivHeadpic;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_time_refresh)
    TextView tvTimeRefresh;
    @InjectView(R.id.ll_video_user_info)
    LinearLayout llVideoUserInfo;
    @InjectView(R.id.tv_context)
    TextView tvContext;
    @InjectView(R.id.tv_video_context)
    TextView tvVideoContext;
    @InjectView(R.id.jcv_videoplayer)
    JCVideoPlayerStandard jcvVideoplayer;
    @InjectView(R.id.rl_holder)
    RelativeLayout rlHolder;
    @InjectView(R.id.tv_image_context)
    TextView tvImageContext;
    @InjectView(R.id.iv_image_icon)
    ImageView ivImageIcon;
    @InjectView(R.id.tv_gif_context)
    TextView tvGifContext;
    @InjectView(R.id.iv_image_gif)
    ImageView ivImageGif;
    @InjectView(R.id.tv_html_context)
    TextView tvHtmlContext;
    @InjectView(R.id.iv_html_image_icon)
    ImageView ivHtmlImageIcon;
    @InjectView(R.id.btn_install)
    Button btnInstall;
    @InjectView(R.id.tv_ding)
    ImageView tvDing;
    @InjectView(R.id.tv_shenhe_ding_number)
    TextView tvShenheDingNumber;
    @InjectView(R.id.ll_ding)
    LinearLayout llDing;
    @InjectView(R.id.iv_cai)
    ImageView ivCai;
    @InjectView(R.id.tv_shenhe_cai_number)
    TextView tvShenheCaiNumber;
    @InjectView(R.id.ll_cai)
    LinearLayout llCai;
    @InjectView(R.id.tv_posts_number)
    TextView tvPostsNumber;
    @InjectView(R.id.ll_share)
    LinearLayout llShare;
    @InjectView(R.id.iv_recommend)
    ImageView ivRecommend;
    @InjectView(R.id.tv_shenhe_recommend_number)
    TextView tvShenheRecommendNumber;
    @InjectView(R.id.ll_download)
    LinearLayout llDownload;
    @InjectView(R.id.hot_comment)
    RecyclerView hotComment;
    @InjectView(R.id.normal_comment)
    RecyclerView normalComment;
    @InjectView(R.id.tv_sousuo)
    EditText tvSousuo;
    @InjectView(R.id.iv_record)
    ImageView ivRecord;
    @InjectView(R.id.activity_comment)
    LinearLayout activityComment;
    @InjectView(R.id.text_middle)
    LinearLayout textMiddle;
    @InjectView(R.id.video_middle)
    LinearLayout videoMiddle;
    @InjectView(R.id.image_middle)
    LinearLayout imageMiddle;
    @InjectView(R.id.gif_middle)
    LinearLayout gifMiddle;
    @InjectView(R.id.html_middle)
    LinearLayout htmlMiddle;
    private String purl = "http://c.api.budejie.com/topic/comment_list/";
    private String surl = "/0/budejie-android-6.6.3/0-20.json";
    private String uid;
    private HotCommentAdapter hotadapter;
    private NormalCommentAdapter normaladapter;
    private RecomBean.ListBean recommends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.inject(this);

        recommends = (RecomBean.ListBean) getIntent().getSerializableExtra("RECOMMEND");

        uid = recommends.getId();
        inittitle();
        initData();
    }

    private void inittitle() {
        titleBack.setVisibility(View.VISIBLE);
        titleForward.setVisibility(View.VISIBLE);
        titleText.setText("评论");
    }

    private void initData() {
        String type = recommends.getType();

        if (recommends.getU() != null && recommends.getU().getHeader() != null && recommends.getU().getHeader().get(0) != null) {
            Glide.with(this)
                    .load(recommends.getU().getHeader().get(0))
                    .into(ivHeadpic);
        }
        if (recommends.getU() != null && recommends.getU().getName() != null) {
            tvName.setText(recommends.getU().getName());
        }

        tvTimeRefresh.setText(recommends.getPasstime());
        tvShenheDingNumber.setText(recommends.getUp());
        tvShenheCaiNumber.setText(recommends.getDown() + "");
        tvPostsNumber.setText(recommends.getForward() + "");
        tvShenheRecommendNumber.setText(recommends.getComment());

        if ("text".equals(type)) {
            textMiddle.setVisibility(View.VISIBLE);
            videoMiddle.setVisibility(View.GONE);
            imageMiddle.setVisibility(View.GONE);
            gifMiddle.setVisibility(View.GONE);
            htmlMiddle.setVisibility(View.GONE);
            tvContext.setText(recommends.getText());
        } else if ("video".equals(type)) {
            textMiddle.setVisibility(View.GONE);
            videoMiddle.setVisibility(View.VISIBLE);
            imageMiddle.setVisibility(View.GONE);
            gifMiddle.setVisibility(View.GONE);
            htmlMiddle.setVisibility(View.GONE);
            tvVideoContext.setText(recommends.getText());

            boolean setUp = jcvVideoplayer.setUp(recommends.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
            if (setUp) {
                Glide.with(this)
                        .load(recommends.getVideo().getThumbnail().get(0))
                        .into(jcvVideoplayer.thumbImageView);
            }

        } else if ("image".equals(type)) {

            textMiddle.setVisibility(View.GONE);
            videoMiddle.setVisibility(View.GONE);
            imageMiddle.setVisibility(View.VISIBLE);
            gifMiddle.setVisibility(View.GONE);
            htmlMiddle.setVisibility(View.GONE);

            tvImageContext.setText(recommends.getText());
            if (recommends.getImage() != null && recommends.getImage().getBig() != null && recommends.getImage().getSmall() != null) {
                Glide.with(this)
                        .load(recommends.getImage().getDownload_url().get(0))
                        .into(ivImageIcon);
            }

        } else if ("gif".equals(type)) {

            textMiddle.setVisibility(View.GONE);
            videoMiddle.setVisibility(View.GONE);
            imageMiddle.setVisibility(View.GONE);
            gifMiddle.setVisibility(View.VISIBLE);
            htmlMiddle.setVisibility(View.GONE);

            tvGifContext.setText(recommends.getText());
            Glide.with(this)
                    .load(recommends.getGif().getImages().get(0))
                    .into(ivImageGif);

        } else if ("html".equals(type)) {
            textMiddle.setVisibility(View.GONE);
            videoMiddle.setVisibility(View.GONE);
            imageMiddle.setVisibility(View.GONE);
            gifMiddle.setVisibility(View.GONE);
            htmlMiddle.setVisibility(View.VISIBLE);
        }


        getDataNet();

    }

    private void getDataNet() {
        OkHttpUtils.get()
                .url(purl + uid + surl)
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
        CommentBean commentBean = new Gson().fromJson(json, CommentBean.class);
        List<CommentBean.HotBean.ListBean> hotlist = commentBean.getHot().getList();

        hotadapter = new HotCommentAdapter(this, hotlist);
        hotComment.setAdapter(hotadapter);
        hotComment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<CommentBean.NormalBean.ListBeanX> normallist = commentBean.getNormal().getList();

        normaladapter = new NormalCommentAdapter(this, normallist);
        normalComment.setAdapter(normaladapter);

        normalComment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @OnClick({R.id.title_back, R.id.ll_share,R.id.title_forward})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_share:
                break;
            case R.id.title_forward:
                //生成二维码
                try {
                    //String code =JSON.toJSONString(purl + uid + surl);
                    Bitmap qrCode = EncodingHandler.createQRCode(recommends.getShare_url(), 220);
                    ImageView imageView = new ImageView(this);
                    imageView.setImageBitmap(qrCode);

                    new AlertDialog.Builder(this)
                                .setTitle("生成的二维码")
                                .setView(imageView)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                    
                                    }
                                })
                                .setNegativeButton("取消", null)
                                .show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
