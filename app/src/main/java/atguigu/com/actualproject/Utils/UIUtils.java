package atguigu.com.actualproject.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by sun on 2017/7/5.
 */

public class UIUtils {
    public static Context getContext(){
        return MyApplication.getContext();
    }

    /*
    * 保证runnable在主线程中运行
    * */
    public static void UIThread(Runnable runnable){
        if (MyApplication.getPid() == android.os.Process.myTid()){
            runnable.run();
        }else{
            MyApplication.getHandler().post(runnable);
        }
    }

    /*
    * 显示吐司
    * */
    public static void showToast(final String message){
        UIThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
