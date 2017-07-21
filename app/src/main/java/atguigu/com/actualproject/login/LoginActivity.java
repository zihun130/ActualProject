package atguigu.com.actualproject.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.Constant;
import atguigu.com.actualproject.Utils.DensityUtil;
import atguigu.com.actualproject.Utils.UIUtils;
import atguigu.com.actualproject.activity.MainActivity;
import atguigu.com.actualproject.bean.LoginBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import okhttp3.Call;

import static android.R.attr.action;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, PlatformActionListener {

    @InjectView(R.id.back)
    ImageView back;
    @InjectView(R.id.ed_phone)
    EditText edPhone;
    @InjectView(R.id.ed_number)
    EditText edNumber;
    @InjectView(R.id.anth_code)
    Button anthCode;
    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.tv_more)
    TextView tvMore;
    @InjectView(R.id.activity_login)
    LinearLayout activityLogin;
    @InjectView(R.id.register_btn)
    Button registerBtn;
    private View inflate;
    private PopupWindow popupWindow;
    private ImageButton app_tencent_qq;
    private ImageButton app_tencent_weixin;
    private ImageButton app_weibo;
    private ImageButton app_wxfriend;
    private LinearLayout third_login;
    private Handler handler;

    private static final int MSG_SMSSDK_CALLBACK = 1;
    private static final int MSG_AUTH_CANCEL = 2;
    private static final int MSG_AUTH_ERROR= 3;
    private static final int MSG_AUTH_COMPLETE = 4;
    private OnLoginListener signupListener;


    /** 设置授权回调，用于判断是否进入注册 */
    public void setOnLoginListener(OnLoginListener l) {
        this.signupListener = l;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);


        handler = new Handler();

        inflate = getLayoutInflater().inflate(R.layout.third_login_item, null);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        app_tencent_qq = (ImageButton) inflate.findViewById(R.id.app_tencent_qq);
        app_tencent_weixin = (ImageButton) inflate.findViewById(R.id.app_tencent_weixin);
        app_weibo = (ImageButton) inflate.findViewById(R.id.app_weibo);
        app_wxfriend = (ImageButton) inflate.findViewById(R.id.app_wxfriend);
        third_login = (LinearLayout) inflate.findViewById(R.id.third_login);

        app_tencent_qq.setOnClickListener(this);
        app_tencent_weixin.setOnClickListener(this);
        app_weibo.setOnClickListener(this);
        app_wxfriend.setOnClickListener(this);


        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });

    }

    @OnClick({R.id.back, R.id.login, R.id.tv_more,R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.login:
                String phone = edPhone.getText().toString().trim();
                String number = edNumber.getText().toString().trim();
                if(TextUtils.isEmpty(phone)) {
                    UIUtils.showToast("账号不能为空");
                    return;
                }
                if(TextUtils.isEmpty(number)) {
                    UIUtils.showToast("密码不能为空");
                    return;
                }

                OkHttpUtils.get()
                        .url(Constant.LOGIN)
                        .addParams("phone",phone)
                        .addParams("password",number)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                UIUtils.showToast(e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                processData(response);
                            }
                        });


                break;
            case R.id.tv_more:
                popupWindow.showAtLocation(third_login, Gravity.CENTER, 0, DensityUtil.dip2px(this,50));
                break;
            case R.id.register_btn:
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void processData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            boolean success = jsonObject.getBoolean("success");
            if(success) {
                JSONObject data =jsonObject.getJSONObject("data");
                String name = data.getString("name");
                String imageurl = data.getString("imageurl");
                String iscredit = data.getString("iscredit");
                String phone = data.getString("phone");
                LoginBean loginBean = new LoginBean();
                loginBean.setName(name);
                loginBean.setImageurl(imageurl);
                loginBean.setIscredit(iscredit);
                loginBean.setPhone(phone);

                saveUser(loginBean);

                startActivity(new Intent(this, MainActivity.class));
                finish();
            }else {
                UIUtils.showToast("账号或密码不对");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveUser(LoginBean bean) {
        SharedPreferences sp=getSharedPreferences("loginBean",MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        edit.putString("name",bean.getName());
        edit.putString("imageurl",bean.getImageurl());
        edit.putString("iscredit",bean.getIscredit());
        edit.putString("phone",bean.getPhone());
        edit.commit();
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()) {
              case R.id.app_tencent_qq :
                  break;
              case R.id.app_tencent_weixin :
                  //新浪微博
                  Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
                  authorize(sina);
                  break;
              case R.id.app_weibo :
                  //QQ空间
                  Platform qzone = ShareSDK.getPlatform(QZone.NAME);
                  authorize(qzone);
                  break;
              case R.id.app_wxfriend :
                  break;
          }
    }


    //执行授权,获取用户信息
    //文档：http://wiki.mob.com/Android_%E8%8E%B7%E5%8F%96%E7%94%A8%E6%88%B7%E8%B5%84%E6%96%99
    private void authorize(Platform plat) {
        if (plat == null) {
            return;
        }


        plat.setPlatformActionListener(this);
        //关闭SSO授权
        plat.SSOSetting(true);
        plat.showUser(null);
    }

    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        if (action == Platform.ACTION_USER_INFOR) {
            Message msg = new Message();
            msg.what = MSG_AUTH_COMPLETE;
            msg.obj = new Object[] {platform.getName(), res};
            handler.sendMessage(msg);
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable t) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_ERROR);
        }
        t.printStackTrace();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_CANCEL);
        }
    }


    @SuppressWarnings("unchecked")
    public boolean handleMessage(Message msg) {
        switch(msg.what) {
            case MSG_AUTH_CANCEL: {
                //取消授权
                Toast.makeText(this, R.string.auth_cancel, Toast.LENGTH_SHORT).show();
            } break;
            case MSG_AUTH_ERROR: {
                //授权失败
                Toast.makeText(this, R.string.auth_error, Toast.LENGTH_SHORT).show();
            } break;
            case MSG_AUTH_COMPLETE: {
                //授权成功
                Toast.makeText(this, R.string.auth_complete, Toast.LENGTH_SHORT).show();
                Object[] objs = (Object[]) msg.obj;
                String platform = (String) objs[0];
                HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
                if (signupListener != null && signupListener.onSignin(platform, res)) {
                    SignupPage signupPage = new SignupPage();
                    signupPage.setOnLoginListener(signupListener);
                    signupPage.setPlatform(platform);
                    signupPage.show(this, null);
                }
            } break;
        }
        return false;
    }
}
