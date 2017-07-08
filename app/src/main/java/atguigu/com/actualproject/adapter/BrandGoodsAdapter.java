package atguigu.com.actualproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.bean.BrandGoodsBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/7.
 */

public class BrandGoodsAdapter extends RecyclerView.Adapter<BrandGoodsAdapter.BrandGoodsViewHolder> {
    private final Context context;
    private final List<BrandGoodsBean.DataBean.ItemsBean> datas;
    private final TextView tv;
    private final LayoutInflater brand;

    public BrandGoodsAdapter(Context context, List<BrandGoodsBean.DataBean.ItemsBean> items, TextView brandGoodsTextview) {
        this.context = context;
        this.datas = items;
        this.tv=brandGoodsTextview;
        this.brand= LayoutInflater.from(context);
    }

    @Override
    public BrandGoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = brand.inflate(R.layout.goods_item, parent, false);
        //View view = View.inflate(context, R.layout.goods_item, parent);
        return new BrandGoodsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BrandGoodsViewHolder holder, int position) {
        BrandGoodsBean.DataBean.ItemsBean itemsBean = datas.get(position);
        Glide.with(context)
                .load(itemsBean.getGoods_image())
                .into(holder.goodsImage);
        holder.goodsName.setText(itemsBean.getGoods_name());
        holder.brandName.setText(itemsBean.getBrand_info().getBrand_name());
        holder.likeCount.setText(itemsBean.getLike_count());
        holder.goodsPrice.setText(itemsBean.getPrice());
        tv.setText(itemsBean.getBrand_info().getBrand_desc());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class BrandGoodsViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.goods_image)
        ImageView goodsImage;
        @InjectView(R.id.goods_name)
        TextView goodsName;
        @InjectView(R.id.brand_name)
        TextView brandName;
        @InjectView(R.id.like_count)
        TextView likeCount;
        @InjectView(R.id.goods_price)
        TextView goodsPrice;
        public BrandGoodsViewHolder(View view) {
            super(view);
            ButterKnife.inject(this,view);
        }
    }
}
