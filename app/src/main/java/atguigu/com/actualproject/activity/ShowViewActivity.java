package atguigu.com.actualproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import atguigu.com.actualproject.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ShowViewActivity extends AppCompatActivity {

    @InjectView(R.id.activity_show_view)
    PhotoView activityShowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_view);
        ButterKnife.inject(this);

        String imageAndgif = getIntent().getStringExtra("imageAndgif");

        PhotoViewAttacher attacher = new PhotoViewAttacher(activityShowView);


    }
}
