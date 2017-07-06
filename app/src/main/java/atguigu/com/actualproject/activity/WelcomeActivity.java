package atguigu.com.actualproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;

import atguigu.com.actualproject.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class WelcomeActivity extends AppCompatActivity {

    //handler发送消息成功的状态码
    private static final int MESSAGE_SUCCESS = 0;
    @InjectView(R.id.imageview)
    ImageView imageview;
    //handler发送消息所携带的参数（持续时间）
    private int duration = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_SUCCESS:
                    startActivity(new Intent(WelcomeActivity.this, GuidanceActivity.class));
                    overridePendingTransition(R.anim.right_in,R.anim.left_out);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);

        Glide.with(this)
                .load(R.drawable.loading_start)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<Integer, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        GifDrawable drawable= (GifDrawable) resource;
                        GifDecoder decoder = drawable.getDecoder();
                        for(int i = 0; i <drawable.getFrameCount() ; i++) {
                            duration+= decoder.getDelay(i);
                        }
                        handler.sendEmptyMessageDelayed(MESSAGE_SUCCESS,duration);
                        return false;
                    }
                }).into(new GlideDrawableImageViewTarget(imageview,1));
    }

}
