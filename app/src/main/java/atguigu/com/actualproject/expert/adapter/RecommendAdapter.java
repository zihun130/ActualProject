package atguigu.com.actualproject.expert.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.expert.RecommendAndLikeActivity;
import atguigu.com.actualproject.expert.bean.RecommendBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/10.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.SingleViewHolder> {
    private final Context context;
    private final LayoutInflater single;
    private final List<RecommendBean.DataBean.ItemsBean.GoodsBean> datas;
    private final RecommendBean.DataBean.ItemsBean dataBean;

    public RecommendAdapter(Context context, List<RecommendBean.DataBean.ItemsBean.GoodsBean> goods, RecommendBean.DataBean.ItemsBean items) {
        this.context = context;
        this.datas = goods;
        this.dataBean=items;
        this.single = LayoutInflater.from(context);
    }

    @Override
    public SingleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = single.inflate(R.layout.singleimage_item, parent, false);
        return new SingleViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SingleViewHolder holder, int position) {
        RecommendBean.DataBean.ItemsBean.GoodsBean goodsBean = datas.get(position);
        Glide.with(context)
                .load(goodsBean.getGoods_image())
                .into(holder.singleImage);

        holder.setListener(goodsBean.getGoods_image(),goodsBean.getGoods_name(),goodsBean.getPrice(),dataBean.getUser_name(),dataBean.getUser_image().getOrig(),dataBean.getLike_count(),dataBean.getUser_id(),dataBean.getUser_desc());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

   class SingleViewHolder extends RecyclerView.ViewHolder {
       private final View view;
       @InjectView(R.id.single_Image)
        ImageView singleImage;
        public SingleViewHolder(View inflate) {
            super(inflate);
            this.view=inflate;
            ButterKnife.inject(this,inflate);
        }

       public void setListener(final String goods_image, final String goods_name, final String price, final String user_name, final String mid, final String like_count, final String user_id, final String user_desc) {
           view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent=new Intent(context,RecommendAndLikeActivity.class);
                   intent.putExtra("IMAGE",goods_image);
                   intent.putExtra("GOODNAME",goods_name);
                   intent.putExtra("PRICE",price);
                   intent.putExtra("USERNAME",user_name);
                   intent.putExtra("MID",mid);
                   intent.putExtra("FAVOR",like_count);
                   intent.putExtra("ID",user_id);
                   intent.putExtra("DESC",user_desc);
                   context.startActivity(intent);
               }
           });
       }
   }
}
