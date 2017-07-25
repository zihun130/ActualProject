package atguigu.com.actualproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.DensityUtil;
import atguigu.com.actualproject.database.HelperManager;
import atguigu.com.actualproject.database.InfoBean;
import atguigu.com.actualproject.view.AddSubView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/16.
 */

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.CartViewHolder> {
    private final Context context;
    private final List<InfoBean> datas;
    private final TextView allPriceText;
    private final CheckBox checkboxAll;
    private final TextView editDeleter;
    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;
    private boolean isDelete;


    public CartRecyclerAdapter(Context context, List<InfoBean> allGoodsContent, TextView allPriceText, CheckBox checkboxAll, TextView editDeleter, boolean isDelete) {
        this.context = context;
        this.datas = allGoodsContent;
        this.allPriceText = allPriceText;
        this.checkboxAll = checkboxAll;
        this.editDeleter = editDeleter;
        this.isDelete = isDelete;
        editDeleter.setTag(ACTION_EDIT);
        checkAll();
        showTotalPrice();

    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.cart_recycler_item, null);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, int position) {
        final InfoBean infoBean = datas.get(position);

        holder.checkboxNone.setChecked(infoBean.isChecked());

        /*int tag = (int) editDeleter.getTag();
        if (tag == ACTION_EDIT) {
            showDelete(holder.goodsinfoName, holder.countText, holder.shoppingCountContent, holder.recyclerviewBtn, holder.goodsinfoContent);

        }
        if (tag == ACTION_COMPLETE) {
            hideDelete(holder.goodsinfoName, holder.countText, holder.shoppingCountContent, holder.recyclerviewBtn, holder.goodsinfoContent);
        }*/

        Glide.with(context)
                .load(infoBean.getImageUrl())
                .into(holder.goodsinfoImage);
        holder.goodsinfoName.setText(infoBean.getGoodsName());
        holder.goodsinfoContent.setText(infoBean.getGoodsInfo());
        holder.priceText.setText(infoBean.getPrice());
        holder.countText.setText(infoBean.getCount() + "");

        holder.setListener(position);
    }

    public void hideDelete(TextView goodsinfoName, TextView countText, AddSubView shoppingCountContent, Button recyclerviewBtn, TextView goodsinfoContent) {

        editDeleter.setText("编辑");
        editDeleter.setTag(ACTION_EDIT);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) goodsinfoName.getLayoutParams();

        params.setMargins(DensityUtil.dip2px(context, 15), 0, 0, 0);

        goodsinfoName.setLayoutParams(params);
        goodsinfoContent.setVisibility(View.VISIBLE);
        countText.setVisibility(View.VISIBLE);
        shoppingCountContent.setVisibility(View.GONE);
        recyclerviewBtn.setVisibility(View.GONE);
        checkAll();
        showTotalPrice();
    }

    public void showDelete(final TextView goodsinfoName, final TextView countText, final AddSubView shoppingCountContent, final Button recyclerviewBtn, final TextView goodsinfoContent) {
        editDeleter.setText("完成");
        editDeleter.setTag(ACTION_COMPLETE);
        goodsinfoContent.setVisibility(View.GONE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) goodsinfoName.getLayoutParams();

        params.setMargins(DensityUtil.dip2px(context, 15), DensityUtil.dip2px(context, 30), 0, 0);


        goodsinfoName.setLayoutParams(params);
        countText.setVisibility(View.GONE);
        shoppingCountContent.setVisibility(View.VISIBLE);
        recyclerviewBtn.setVisibility(View.VISIBLE);
        showTotalPrice();
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


    class CartViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.checkbox_none)
        CheckBox checkboxNone;
        @InjectView(R.id.goodsinfo_image)
        ImageView goodsinfoImage;
        @InjectView(R.id.goodsinfo_name)
        TextView goodsinfoName;
        @InjectView(R.id.goodsinfo_content)
        TextView goodsinfoContent;
        @InjectView(R.id.shopping_count_content)
        AddSubView shoppingCountContent;
        @InjectView(R.id.price_text)
        TextView priceText;
        @InjectView(R.id.goodsinfo_price)
        LinearLayout goodsinfoPrice;
        @InjectView(R.id.count_text)
        TextView countText;
        @InjectView(R.id.goodsinfo_count)
        LinearLayout goodsinfoCount;
        @InjectView(R.id.recyclerview_btn)
        Button recyclerviewBtn;

        public CartViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int tag = (int) editDeleter.getTag();
                    if (tag == ACTION_EDIT) {
                        showDelete(goodsinfoName, countText, shoppingCountContent, recyclerviewBtn, goodsinfoContent);

                    }
                    if (tag == ACTION_COMPLETE) {
                        hideDelete(goodsinfoName, countText, shoppingCountContent, recyclerviewBtn, goodsinfoContent);
                    }
                    return true;
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {

                        onItemClickListener.onItemClick(v, getLayoutPosition());
                    }
                }
            });


        }

        public void setListener(final int position) {
            recyclerviewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InfoBean bean = datas.get(position);
                    datas.remove(position);
                    HelperManager.getInstance().getGoodsInfoDAO().DeleteGoods(bean.getImageUrl());
                    notifyDataSetChanged();
                }
            });
        }
    }


    public void checkAll_none(boolean isChecked) {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                InfoBean infoBean = datas.get(i);
                infoBean.setChecked(isChecked);
                notifyItemChanged(i);
            }
        }
    }

    public void checkAll() {
        if (datas != null && datas.size() > 0) {

            for (int i = 0; i < datas.size(); i++) {
                InfoBean infoBean = datas.get(i);
                if (!infoBean.isChecked()) {
                    checkboxAll.setChecked(false);
                    return;
                }
            }
            checkboxAll.setChecked(true);
        } else {
            checkboxAll.setChecked(false);
        }
    }

    public void showTotalPrice() {
        allPriceText.setText(getTotalPrice() + "");
    }

    public double getTotalPrice() {
        double total = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                InfoBean infoBean = datas.get(i);
                if (infoBean.isChecked()) {
                    total = total + datas.get(i).getCount() * Double.parseDouble(datas.get(i).getPrice());

                }
            }
        }
        return total;
    }


    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
