package atguigu.com.actualproject.personal_center;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import atguigu.com.actualproject.IJKPlayer.IRenderView;
import atguigu.com.actualproject.IJKPlayer.IjkVideoView;
import atguigu.com.actualproject.R;
import atguigu.com.actualproject.personal_center.bean.BilibiliBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import master.flame.danmaku.ui.widget.DanmakuView;

public class IJKVideoActivity extends AppCompatActivity {

    @InjectView(R.id.IJKVideo_view)
    IjkVideoView IJKVideoView;
    @InjectView(R.id.ijk_image)
    ImageView ijkImage;
    @InjectView(R.id.activity_ijkvideo)
    RelativeLayout activityIjkvideo;
    @InjectView(R.id.av_text)
    TextView avText;
    @InjectView(R.id.iv_more)
    ImageView ivMore;
    @InjectView(R.id.IJK_danmaku)
    DanmakuView IJKDanmaku;
    private BilibiliBean.DataBean.PartitionsBean.LivesBean ijkbean;
    private String playurl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijkvideo);
        ButterKnife.inject(this);

        initVideoView();
        initdData();

    }

    private void initVideoView() {
        ijkbean = (BilibiliBean.DataBean.PartitionsBean.LivesBean) getIntent().getSerializableExtra("IJKURL");

        playurl = ijkbean.getPlayurl();
        Log.e("TAG", playurl);

        IJKVideoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        IJKVideoView.setVideoURI(Uri.parse(playurl));
        IJKVideoView.start();

    }

    private void initdData() {
        ijkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        avText.setText("av" + ijkbean.getRoom_id());

    }


}
