package atguigu.com.actualproject.expert.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
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

    public RecommendAdapter(Context context, List<RecommendBean.DataBean.ItemsBean.GoodsBean> goods) {
        this.context = context;
        this.datas = goods;
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
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

   class SingleViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.single_Image)
        ImageView singleImage;
        public SingleViewHolder(View inflate) {
            super(inflate);
            ButterKnife.inject(this,inflate);
        }
    }
}
