package atguigu.com.actualproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.recommend.RecommendFragment;
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.expert.ExpertFragment;
import atguigu.com.actualproject.magazine.MagazineFragment;
import atguigu.com.actualproject.personal_center.PersonalCenterFragment;
import atguigu.com.actualproject.shopping.ShoppingFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

import static atguigu.com.actualproject.R.id.rg_main;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @InjectView(R.id.fl_fragment)
    FrameLayout flFragment;
    @InjectView(rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment tempfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initData();
        rgMain.setOnCheckedChangeListener(this);
        rgMain.check(R.id.rb_shopping);
    }

    private void initData() {
        fragments = new ArrayList<>();

        fragments.add(new ShoppingFragment());
        fragments.add(new MagazineFragment());
        fragments.add(new ExpertFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new PersonalCenterFragment());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_shopping:
                position = 0;
                break;
            case R.id.rb_magazine:
                position = 1;

                break;
            case R.id.rb_expert:
                position = 2;

                break;
            case R.id.rb_all_goods:
                position = 3;
                break;
            case R.id.rb_personal_center:
                position = 4;
                break;
        }
        BaseFragment  currentfragment = fragments.get(position);
        addFragment(currentfragment);
    }

    private void addFragment(BaseFragment currentfragment) {
        if(tempfragment!=currentfragment){

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if(!currentfragment.isAdded()){
                if(tempfragment!=null){
                    ft.hide(tempfragment);
                }
                ft.add(R.id.fl_fragment,currentfragment);
            }else {
                if(tempfragment!=null){
                    ft.hide(tempfragment);
                }
                ft.show(currentfragment);
            }

            ft.commit();
            tempfragment=currentfragment;

        }
    }

    private boolean isExit=false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(position!=0){
                rgMain.check(R.id.rb_shopping);
                return true;
            }else if(!isExit){
                Toast.makeText(MainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                isExit=true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit=false;
                    }
                }, 2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
