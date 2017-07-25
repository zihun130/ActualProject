package atguigu.com.actualproject.personal_center.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.personal_center.IJKVideoActivity;
import atguigu.com.actualproject.personal_center.bean.BilibiliBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/23.
 */

public class CommonalityAdapter extends RecyclerView.Adapter<CommonalityAdapter.CommonalityViewHolder> {
    private final Context context;
    private final List<BilibiliBean.DataBean.PartitionsBean.LivesBean> datas;
    private final LayoutInflater inflater;

    public CommonalityAdapter(Context context, List<BilibiliBean.DataBean.PartitionsBean.LivesBean> lives) {
        this.context = context;
        this.datas = lives;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public CommonalityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.commonality_item, parent, false);
        return new CommonalityViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(CommonalityViewHolder holder, int position) {
        BilibiliBean.DataBean.PartitionsBean.LivesBean livesBean = datas.get(position);
        Glide.with(context)
                .load(livesBean.getCover().getSrc())
                .into(holder.bilibiliImage);
        holder.videoTitle.setText(livesBean.getTitle());
        holder.userName.setText(livesBean.getOwner().getName());
        holder.number.setText(livesBean.getOnline()+"");

        holder.setListener(livesBean);
    }


    @Override
    public int getItemCount() {
        return 4;
    }

    class CommonalityViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        @InjectView(R.id.bilibili_image)
        ImageView bilibiliImage;
        @InjectView(R.id.video_title)
        TextView videoTitle;
        @InjectView(R.id.user_name)
        TextView userName;
        @InjectView(R.id.number)
        TextView number;

        public CommonalityViewHolder(View inflate) {
            super(inflate);
            this.view=inflate;
            ButterKnife.inject(this,inflate);
        }

        public void setListener(final BilibiliBean.DataBean.PartitionsBean.LivesBean livesBean) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,IJKVideoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("IJKURL",livesBean);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
