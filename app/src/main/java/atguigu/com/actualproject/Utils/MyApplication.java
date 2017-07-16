package atguigu.com.actualproject.Utils;

import android.content.Context;
import android.os.Handler;

import com.mob.MobApplication;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by sun on 2017/7/5.
 */

public class MyApplication extends MobApplication {


    private static Context context;
    private static Handler handler;
    private static int pid;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        pid = android.os.Process.myPid();
        context = this;

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

    }

    public static Context getContext() {
        return context;
    }
    public static Handler getHandler() {
        return handler;
    }

    public static int getPid() {
        return pid;
    }


}
