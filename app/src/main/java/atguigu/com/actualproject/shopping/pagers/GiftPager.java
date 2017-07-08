package atguigu.com.actualproject.shopping.pagers;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.Constant;
import atguigu.com.actualproject.activity.GoodsInfoActivity;
import atguigu.com.actualproject.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static atguigu.com.actualproject.R.id.gift_image;

/**
 * Created by sun on 2017/7/6.
 */

public class GiftPager extends BaseFragment {
    @InjectView(gift_image)
    ImageView giftImage;
    @InjectView(R.id.gift_festival)
    ImageView giftFestival;
    @InjectView(R.id.gift_love)
    ImageView giftLove;
    @InjectView(R.id.gift_birthday)
    ImageView giftBirthday;
    @InjectView(R.id.gift_friends)
    ImageView giftFriends;
    @InjectView(R.id.gift_childs)
    ImageView giftChilds;
    @InjectView(R.id.gift_mather)
    ImageView giftMather;
    @InjectView(R.id.gift_remind)
    ImageView giftRemind;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.pager_gift, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({gift_image, R.id.gift_festival, R.id.gift_love, R.id.gift_birthday, R.id.gift_friends, R.id.gift_childs, R.id.gift_mather, R.id.gift_remind})
    public void onViewClicked(View view) {
               Intent intent=new Intent(context,GoodsInfoActivity.class);
        switch (view.getId()) {
            case gift_image:
                intent.putExtra("gift_image", Constant.GIFT_IMAGE);
                break;
            case R.id.gift_festival:
                intent.putExtra("gift_image", Constant.GIFT_FESTIVAL);
                break;
            case R.id.gift_love:
                intent.putExtra("gift_image", Constant.GIFT_LOVE);
                break;
            case R.id.gift_birthday:
                intent.putExtra("gift_image", Constant.GIFT_BIRTHDAY);
                break;
            case R.id.gift_friends:
                intent.putExtra("gift_image", Constant.GIFT_FRIENDS);
                break;
            case R.id.gift_childs:
                intent.putExtra("gift_image", Constant.GIFT_CHILDS);
                break;
            case R.id.gift_mather:
                intent.putExtra("gift_image", Constant.GIFT_MATHER);
                break;
            case R.id.gift_remind:
                break;
        }
        startActivity(intent);
    }
}
