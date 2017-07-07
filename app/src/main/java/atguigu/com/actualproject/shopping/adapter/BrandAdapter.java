package atguigu.com.actualproject.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.shopping.pagers.bean.BrandBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/7.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandHolder> {
    private final Context context;
    private final List<BrandBean.DataBean.ItemsBean> datas;

    public BrandAdapter(Context context, List<BrandBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.datas = items;
    }

    @Override
    public BrandHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.brand_item, null);
        return new BrandHolder(view);
    }

    @Override
    public void onBindViewHolder(BrandHolder holder, int position) {
        BrandBean.DataBean.ItemsBean itemsBean = datas.get(position);
        Glide.with(context)
                .load(itemsBean.getBrand_logo())
                .into(holder.brandImage);
       holder.brandText.setText(itemsBean.getBrand_name());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class BrandHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.brand_image)
        ImageView brandImage;
        @InjectView(R.id.brand_text)
        TextView brandText;
        @InjectView(R.id.brand_into)
        ImageView brandInto;

        public BrandHolder(View converView) {
            super(converView);
            ButterKnife.inject(this,converView);
        }

    }
}
