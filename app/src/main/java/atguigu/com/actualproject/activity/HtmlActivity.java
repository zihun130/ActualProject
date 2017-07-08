package atguigu.com.actualproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import atguigu.com.actualproject.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HtmlActivity extends AppCompatActivity {

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
    private String topic_name;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        ButterKnife.inject(this);

        String html = getIntent().getStringExtra("HTML");
        topic_name = getIntent().getStringExtra("topic_name");
        inittitle();

        settings = htmlWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);

        htmlWeb.loadUrl(html);

    }

    private void inittitle() {
        titleBack.setVisibility(View.VISIBLE);
        titleText.setText(topic_name);

    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }
}
