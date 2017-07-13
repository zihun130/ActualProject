package atguigu.com.actualproject.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import atguigu.com.actualproject.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class HtmlActivity extends AppCompatActivity implements View.OnClickListener {

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
    @InjectView(R.id.html_web)
    WebView htmlWeb;
    @InjectView(R.id.activity_html)
    LinearLayout activityHtml;
    @InjectView(R.id.title_forward)
    ImageView titleForward;
    @InjectView(R.id.title_favor_select)
    ImageView titleFavorSelect;
    @InjectView(R.id.title_bar)
    RelativeLayout titleBar;
    private String topic_name;
    private WebSettings settings;
    private View popupView;
    private PopupWindow popupWindow;
    private TextView textview1;
    private TextView textview2;
    private TextView textview3;
    private TextView textview4;
    private TextView textview5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        ButterKnife.inject(this);

        String html = getIntent().getStringExtra("HTML");
        topic_name = getIntent().getStringExtra("topic_name");



        //设置分享的popuwindow页面
        popupView = getLayoutInflater().inflate(R.layout.popuwindow_item, null);

        popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        textview1 = (TextView) popupView.findViewById(R.id.textview1);
        textview2 = (TextView) popupView.findViewById(R.id.textview2);
        textview3 = (TextView) popupView.findViewById(R.id.textview3);
        textview4 = (TextView) popupView.findViewById(R.id.textview4);
        textview5 = (TextView) popupView.findViewById(R.id.textview5);

        textview1.setOnClickListener(this);
        textview2.setOnClickListener(this);
        textview3.setOnClickListener(this);
        textview4.setOnClickListener(this);
        textview5.setOnClickListener(this);

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





        inittitle();

        settings = htmlWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);

        htmlWeb.loadUrl(html);

    }

    private void inittitle() {
        titleBack.setVisibility(View.VISIBLE);
        titleFavorSelect.setVisibility(View.VISIBLE);
        titleForward.setVisibility(View.VISIBLE);
        titleText.setText(topic_name);

    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @OnClick({R.id.title_forward, R.id.title_favor_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_forward:
                popupWindow.showAtLocation(activityHtml, Gravity.CENTER, 0, 0);
                break;
            case R.id.title_favor_select:
                break;
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("我是中国人");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://www.atguigu.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是一个兵，来自老百姓！！");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496999731520&di=940086649468f3966ca0f4b317fd18ff&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F215%2F46%2FJYYMR4I452LO_1000x500.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496999731520&di=940086649468f3966ca0f4b317fd18ff&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F215%2F46%2FJYYMR4I452LO_1000x500.jpg");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("尚硅谷");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496999731520&di=940086649468f3966ca0f4b317fd18ff&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F215%2F46%2FJYYMR4I452LO_1000x500.jpg");

// 启动分享GUI
        oks.show(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textview1 :
                showShare();
                break;
            case R.id.textview2 :
                showShare();
                break;
            case R.id.textview3 :
                showShare();
                break;
            case R.id.textview4 :
                showShare();
                break;
            case R.id.textview5 :
                popupWindow.dismiss();
                break;
        }
    }
}
