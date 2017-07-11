package atguigu.com.actualproject.expert.adapter;

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
import atguigu.com.actualproject.expert.ExpertInfoActivity;
import atguigu.com.actualproject.expert.bean.ExpertBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/10.
 */

public class ExpertAdapter extends RecyclerView.Adapter <ExpertAdapter.ExpertViewHolder>{
    private final Context context;
    private final List<ExpertBean.DataBean.ItemsBean> datas;
    private final LayoutInflater inflater;


    public ExpertAdapter(Context context, List<ExpertBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.datas = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ExpertViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.expert_item, parent, false);
        return new ExpertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpertViewHolder holder, int position) {
        ExpertBean.DataBean.ItemsBean itemsBean = datas.get(position);
        Glide.with(context)
                .load(itemsBean.getUser_images().getOrig())
                .into(holder.expertImage);
        holder.expertName.setText(itemsBean.getUsername());
        holder.expertDuty.setText(itemsBean.getDuty());
        holder.setListener(itemsBean.getUid(),itemsBean.getUsername(),itemsBean.getUser_images().getOrig(),itemsBean.getDuty());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ExpertViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        @InjectView(R.id.expert_image)
        ImageView expertImage;
        @InjectView(R.id.expert_name)
        TextView expertName;
        @InjectView(R.id.expert_duty)
        TextView expertDuty;
        public ExpertViewHolder(View view) {
            super(view);
            this.view=view;
            ButterKnife.inject(this,view);
        }

        public void setListener(final String uid, final String username, final String mid, final String duty) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,ExpertInfoActivity.class);
                    intent.putExtra("UID",uid);
                    intent.putExtra("NAME",username);
                    intent.putExtra("MID",mid);
                    intent.putExtra("DUTY",duty);
                    context.startActivity(intent);
                }
            });
        }
    }
}
