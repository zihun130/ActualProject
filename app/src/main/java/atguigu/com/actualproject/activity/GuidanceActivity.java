package atguigu.com.actualproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.login.LoginActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class GuidanceActivity extends AppCompatActivity {


    @InjectView(R.id.iv_welcome)
    ImageView ivWelcome;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.imagebtn)
    ImageButton imagebtn;
    @InjectView(R.id.rl_welcome)
    RelativeLayout rlWelcome;
    private int[] ids = {R.drawable.feature1, R.drawable.feature2, R.drawable.feature3, R.drawable.feature4, R.drawable.feature5};
    private ArrayList<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance);
        ButterKnife.inject(this);

        initdata();

        viewpager.setAdapter(new MyAdapter());

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == imageViews.size() - 1) {
                    //最后一个页面就显示
                    imagebtn.setVisibility(View.VISIBLE);
                } else {
                    //其他页面隐藏
                    imagebtn.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initdata() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {

            ImageView imageView = new ImageView(this);
            Glide.with(this)
                    .load(ids[i])
                    .placeholder(R.drawable.feature1)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
            imageViews.add(imageView);
        }
    }


    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews == null ? 0 : imageViews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            //添加到容器中
            container.addView(imageView);
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @OnClick(R.id.imagebtn)
    public void onViewClicked() {
        if(isLogin()) {
            startActivity(new Intent(GuidanceActivity.this,MainActivity.class));
        }else {
            startActivity(new Intent(GuidanceActivity.this,LoginActivity.class));
        }
       finish();
    }

    private boolean isLogin() {
        SharedPreferences sp = getSharedPreferences("loginBean", MODE_PRIVATE);
        String name = sp.getString("name","admin");
        if(name.equals("admin")) {
            return false;
        }else {
            return true;
        }

    }
}
