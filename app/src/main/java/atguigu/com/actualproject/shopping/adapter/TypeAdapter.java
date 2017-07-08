package atguigu.com.actualproject.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.Constant;
import atguigu.com.actualproject.activity.GoodsInfoActivity;
import atguigu.com.actualproject.shopping.pagers.bean.TypeBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/7.
 */

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {
    private final Context context;
    private final List<TypeBean.DataBean.ItemsBean> datas;
    String[] strings={Constant.TYPEONE,Constant.TYPETWO,Constant.TYPETHREE,Constant.TYPEFOUR,Constant.TYPEFIVE,Constant.TYPESIX,
                       Constant.TYPESEVEN,Constant.TYPEEIGHT,Constant.TYPENINE,Constant.TYPETEN,Constant.TYPEELEVEN,Constant.TYPETWELVE,
                       Constant.TYPETHIRTEEN,Constant.TYPEFOURTEEN,Constant.TYPEFIFTEEN,Constant.TYPESIXTEEN,Constant.TYPESEVENTEEN,Constant.TYPEEIGHTEEN,Constant.TYPENINETEEN};


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
        holder.setListener(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        @InjectView(R.id.type_image)
        ImageView typeImage;
        public TypeViewHolder(View view) {
            super(view);
            this.view=view;
            ButterKnife.inject(this,view);


        }
        public void setListener(final int position){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,GoodsInfoActivity.class);

                    intent.putExtra("gift_image",strings[position]);

                    context.startActivity(intent);
                }
            });
        }
    }


}
