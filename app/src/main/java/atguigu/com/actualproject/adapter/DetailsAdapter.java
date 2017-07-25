package atguigu.com.actualproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.database.InfoBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/16.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.CartViewHolder> {
    private final Context context;
    private final List<InfoBean> datas;
    private final TextView allPriceText;

    public DetailsAdapter(Context context, List<InfoBean> allGoodsContent, TextView allPriceText) {
        this.context = context;
        this.datas = allGoodsContent;
        this.allPriceText = allPriceText;
        showTotalPrice();
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.details_item, null);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, int position) {
        final InfoBean infoBean = datas.get(position);

        Glide.with(context)
                .load(infoBean.getImageUrl())
                .into(holder.goodsinfoImage);
        holder.goodsinfoName.setText(infoBean.getGoodsName());
        holder.goodsinfoContent.setText(infoBean.getGoodsInfo());
        holder.priceText.setText(infoBean.getPrice());
        holder.countText.setText(infoBean.getCount() + "");
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.goodsinfo_image)
        ImageView goodsinfoImage;
        @InjectView(R.id.goodsinfo_name)
        TextView goodsinfoName;
        @InjectView(R.id.goodsinfo_content)
        TextView goodsinfoContent;
        @InjectView(R.id.price_text)
        TextView priceText;
        @InjectView(R.id.goodsinfo_price)
        LinearLayout goodsinfoPrice;
        @InjectView(R.id.count_text)
        TextView countText;
        @InjectView(R.id.goodsinfo_count)
        LinearLayout goodsinfoCount;

        public CartViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {

                        onItemClickListener.onItemClick(v, getLayoutPosition());
                    }
                }
            });
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
