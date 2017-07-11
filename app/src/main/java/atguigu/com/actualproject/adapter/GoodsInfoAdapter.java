package atguigu.com.actualproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.activity.AllGoodsInfoActivity;
import atguigu.com.actualproject.shopping.pagers.bean.GoodsBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/7.
 */

public class GoodsInfoAdapter extends RecyclerView.Adapter<GoodsInfoAdapter.GoodsViewHolder> {
    private final Context context;
    private final List<GoodsBean.DataBean.ItemsBean> datas;
    private final LayoutInflater goods;


    public GoodsInfoAdapter(Context context, List<GoodsBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.datas = items;
        this.goods= LayoutInflater.from(context);

    }

    @Override
    public GoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = goods.inflate(R.layout.goods_item, parent, false);
        //View view = View.inflate(context, R.layout.goods_item, null);
        return new GoodsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(GoodsViewHolder holder, int position) {
        GoodsBean.DataBean.ItemsBean itemsBean = datas.get(position);
        Glide.with(context)
                .load(itemsBean.getGoods_image())
                .into(holder.goodsImage);
        holder.goodsName.setText(itemsBean.getGoods_name());
        holder.brandName.setText(itemsBean.getBrand_info().getBrand_name());
        holder.likeCount.setText(itemsBean.getLike_count());
        holder.goodsPrice.setText(itemsBean.getPrice());

        holder.setListener(itemsBean.getGoods_id());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class GoodsViewHolder extends RecyclerView.ViewHolder {
        private final View view;
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
        public GoodsViewHolder(View view) {
            super(view);
            this.view=view;
            ButterKnife.inject(this,view);
        }

        public void setListener(final String goods_id) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,AllGoodsInfoActivity.class);
                    intent.putExtra("GOODSINFO",goods_id);
                    context.startActivity(intent);
                }
            });
        }
    }
}
