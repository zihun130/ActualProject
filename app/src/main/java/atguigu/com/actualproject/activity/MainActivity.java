package atguigu.com.actualproject.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
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
import atguigu.com.actualproject.base.BaseFragment;
import atguigu.com.actualproject.expert.ExpertFragment;
import atguigu.com.actualproject.magazine.MagazineFragment;
import atguigu.com.actualproject.personal_center.PersonalCenterFragment;
import atguigu.com.actualproject.recommend.RecommendFragment;
import atguigu.com.actualproject.shopping.ShoppingFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

import static atguigu.com.actualproject.R.id.rg_main;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @InjectView(R.id.fl_fragment)
    FrameLayout flFragment;
    @InjectView(rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment tempfragment;
    private SensorManager sensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initData();
        rgMain.setOnCheckedChangeListener(this);
        rgMain.check(R.id.rb_shopping);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        listener = new JCVideoPlayer.JCAutoFullscreenListener();
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


    /**
     * 解决安卓6.0以上版本不能读取外部存储权限的问题
     * @param activity
     * @return
     */
    public static boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

            return false;
        }

        return true;
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

    //初始化节操播放
    @Override
    protected void onResume() {
        super.onResume();
        Sensor defaultSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener,defaultSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(listener);
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if(JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();

    }
}
