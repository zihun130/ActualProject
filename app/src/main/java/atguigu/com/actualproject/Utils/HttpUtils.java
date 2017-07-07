package atguigu.com.actualproject.Utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by sun on 2017/7/6.
 */

public class HttpUtils {

    private HttpUtils(){}
    private static HttpUtils httpUtils=new HttpUtils();
    private static HttpUtils getInstance(){
        return httpUtils;
    }

    private OnHttpUtilsListener onHttpUtilsListener;

    public void get(String url,OnHttpUtilsListener onHttpUtilsListener){
        this.onHttpUtilsListener=onHttpUtilsListener;
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new MyStringCallback());


    }
    private class MyStringCallback extends StringCallback {
        @Override
        public void onError(Call call, Exception e, int id) {
             if(onHttpUtilsListener!=null){
                 onHttpUtilsListener.onFailure(e.getMessage());
             }
        }

        @Override
        public void onResponse(String response, int id) {
            if(onHttpUtilsListener!=null){
                onHttpUtilsListener.onSuccess((String) response);
            }
        }

    }

    public interface OnHttpUtilsListener{
        void onSuccess(String json);
        void onFailure(String message);
    }



}
