package atguigu.com.actualproject.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.shopping.pagers.bean.TypeBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/7.
 */

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {
    private final Context context;
    private final List<TypeBean.DataBean.ItemsBean> datas;


    public TypeAdapter(Context context, List<TypeBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.datas = items;
    }

    @Override
    public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.type_item, null);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TypeViewHolder holder, int position) {
        TypeBean.DataBean.ItemsBean itemsBean = datas.get(position);
        Glide.with(context)
                .load(itemsBean.getNew_cover_img())
                .into(holder.typeImage);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.type_image)
        ImageView typeImage;
        public TypeViewHolder(View view) {
            super(view);
            ButterKnife.inject(this,view);
        }
    }
}
