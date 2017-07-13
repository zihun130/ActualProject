package atguigu.com.actualproject.magazine;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.magazine.fragment.AuthorFragment;
import atguigu.com.actualproject.magazine.fragment.ClassifyFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MagazineInfoActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    @InjectView(R.id.magazine_btn1)
    Button magazineBtn1;
    @InjectView(R.id.magazine_btn2)
    Button magazineBtn2;
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
    @InjectView(R.id.activity_magazine_info)
    LinearLayout activityMagazineInfo;
    @InjectView(R.id.fl_magazine)
    FrameLayout flMagazine;
    @InjectView(R.id.magazine_scrollview)
    ScrollView magazineScrollview;
    @InjectView(R.id.rg_magazine)
    RadioGroup rgMagazine;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazine_info);
        ButterKnife.inject(this);
        inittitle();
        rgMagazine.setOnCheckedChangeListener(this);
        rgMagazine.check(R.id.magazine_btn2);

    }

    private void inittitle() {
        titleText.setText("杂 志");
        //代码中给TextView设置图片
        Drawable drawable = titleText.getResources().getDrawable(R.drawable.up);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        titleText.setCompoundDrawables(null, null, drawable, null);

        titleText.setCompoundDrawablePadding(5);

        titleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, R.anim.up_out);
            }
        });

        fragments = new ArrayList<>();
        fragments.add(new ClassifyFragment());
        fragments.add(new AuthorFragment());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.magazine_btn1:
                position = 0;
                break;
            case R.id.magazine_btn2:
                position = 1;
                break;
        }

        BaseFragment currentFragment = fragments.get(position);
        addFragment(currentFragment);
    }

    private void addFragment(BaseFragment currentFragment) {
        if (tempFragment != currentFragment) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!currentFragment.isAdded()) {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.fl_magazine, currentFragment);

            } else {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            }

            ft.commit();
            tempFragment = currentFragment;

        }
    }
}
