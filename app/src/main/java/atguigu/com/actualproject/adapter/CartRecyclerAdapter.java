package atguigu.com.actualproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.database.InfoBean;
import atguigu.com.actualproject.view.AddSubView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.content.Intent.ACTION_EDIT;

/**
 * Created by sun on 2017/7/16.
 */

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.CartViewHolder> {
    private final Context context;
    private final List<InfoBean> datas;
    private final TextView allPriceText;
    private final CheckBox checkboxAll;
    private final TextView editDeleter;


    public CartRecyclerAdapter(Context context, List<InfoBean> allGoodsContent, TextView allPriceText, CheckBox checkboxAll, TextView editDeleter) {
        this.context = context;
        this.datas = allGoodsContent;
        this.allPriceText=allPriceText;
        this.checkboxAll=checkboxAll;
        this.editDeleter=editDeleter;

        showTotalPrice();
    }

    public void showTotalPrice() {
        allPriceText.setText(getTotalPrice()+"");
    }

    private double getTotalPrice() {
        double total = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i).isChecked()) {
                    total = total + datas.get(i).getCount() * datas.get(i).getPrice();

                }
            }
        }
        return total;
    }




    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.cart_recycler_item, null);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        final InfoBean infoBean = datas.get(position);

        holder.checkboxAll.setChecked(infoBean.isChecked());

        Glide.with(context)
                .load(infoBean.getImageUrl())
                .into(holder.goodsinfoImage);
        holder.goodsinfoName.setText(infoBean.getGoodsDesc());
        holder.goodsinfoContent.setText(infoBean.getGoodsInfo());
        holder.priceText.setText((int) infoBean.getPrice());
        holder.countText.setText(infoBean.getCount());
        holder.checkboxAll.setChecked(true);


    }

    public void hideDelete() {
        editDeleter.setText("编辑");
        editDeleter.setTag(ACTION_EDIT);

        showTotalPrice();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.checkbox_all)
        CheckBox checkboxAll;
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
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
