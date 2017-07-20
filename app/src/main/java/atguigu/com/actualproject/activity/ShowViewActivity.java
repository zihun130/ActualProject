package atguigu.com.actualproject.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.DensityUtil;
import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ShowViewActivity extends AppCompatActivity {

    @InjectView(R.id.activity_show_view)
    PhotoView activityShowView;
    private ImageOptions builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_view);
        ButterKnife.inject(this);

        String imageAndgif = getIntent().getStringExtra("imageAndgif");

        final PhotoViewAttacher attacher = new PhotoViewAttacher(activityShowView);

        builder = new ImageOptions.Builder()
//                .setSize(DensityUtil.dip2px(80), DensityUtil.dip2px(80))
                //设置圆角
                .setRadius(DensityUtil.dip2px(this,5))
                .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.video_default)
                .setFailureDrawableId(R.drawable.video_default)
                .build();

        x.image().bind(activityShowView,imageAndgif,builder, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable drawable) {
                 attacher.update();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });



    }
}
