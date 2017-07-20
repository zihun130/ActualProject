package atguigu.com.actualproject.recommend.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.activity.CommentActivity;
import atguigu.com.actualproject.recommend.bean.RecomBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/18.
 */

public class DuanZiAdapter extends RecyclerView.Adapter<DuanZiAdapter.DuanZiViewHolder> {
    private final Context context;
    private final List<RecomBean.ListBean> datas;


    public DuanZiAdapter(Context context, List<RecomBean.ListBean> list) {
        this.context = context;
        this.datas = list;
    }

    @Override
    public DuanZiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.duanzi_item, null);
        return new DuanZiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DuanZiViewHolder holder, int position) {
        RecomBean.ListBean listBean = datas.get(position);
        holder.tvContext.setText(listBean.getText());
        if (listBean.getU() != null && listBean.getU().getHeader() != null && listBean.getU().getHeader().get(0) != null) {
            Glide.with(context)
                    .load(listBean.getU().getHeader().get(0))
                    .into(holder.ivHeadpic);
        }
        if (listBean.getU() != null && listBean.getU().getName() != null) {
            holder.tvName.setText(listBean.getU().getName());
        }

        holder.tvTimeRefresh.setText(listBean.getPasstime());
        holder.tvShenheDingNumber.setText(listBean.getUp());
        holder.tvShenheCaiNumber.setText(listBean.getDown() + "");
        holder.tvPostsNumber.setText(listBean.getForward() + "");
        holder.tvShenheRecommendNumber.setText(listBean.getComment());

        List<RecomBean.ListBean.TopCommentsBean> top_comments = listBean.getTop_comments();
        if(top_comments!=null && top_comments.size()>0) {
            for (int i = 0; i < top_comments.size(); i++) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                TextView textView = new TextView(context);
                textView.setTextColor(Color.BLUE);
                textView.setText(top_comments.get(i).getU().getName() + " : ");
                TextView textView1 = new TextView(context);
                textView1.setTextColor(Color.BLACK);
                textView1.setText(top_comments.get(i).getContent());
                linearLayout.addView(textView);
                linearLayout.addView(textView1);
                holder.duanziLl.addView(linearLayout);
            }
        }
    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class DuanZiViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_headpic)
        ImageView ivHeadpic;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_time_refresh)
        TextView tvTimeRefresh;
        @InjectView(R.id.ll_video_user_info)
        LinearLayout llVideoUserInfo;
        @InjectView(R.id.tv_context)
        TextView tvContext;
        @InjectView(R.id.tv_ding)
        ImageView tvDing;
        @InjectView(R.id.tv_shenhe_ding_number)
        TextView tvShenheDingNumber;
        @InjectView(R.id.ll_ding)
        LinearLayout llDing;
        @InjectView(R.id.iv_cai)
        ImageView ivCai;
        @InjectView(R.id.tv_shenhe_cai_number)
        TextView tvShenheCaiNumber;
        @InjectView(R.id.ll_cai)
        LinearLayout llCai;
        @InjectView(R.id.tv_posts_number)
        TextView tvPostsNumber;
        @InjectView(R.id.ll_share)
        LinearLayout llShare;
        @InjectView(R.id.iv_recommend)
        ImageView ivRecommend;
        @InjectView(R.id.tv_shenhe_recommend_number)
        TextView tvShenheRecommendNumber;
        @InjectView(R.id.ll_download)
        LinearLayout llDownload;
        @InjectView(R.id.duanzi_ll)
        LinearLayout duanziLl;
        public DuanZiViewHolder(View view) {
            super(view);
            ButterKnife.inject(this,view);


            duanziLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecomBean.ListBean listBean = datas.get(getLayoutPosition());
                    Intent intent = new Intent();
                    intent.setClass(context, CommentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("RECOMMEND",listBean);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            llDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecomBean.ListBean listBean = datas.get(getLayoutPosition());
                    Intent intent = new Intent();
                    intent.setClass(context, CommentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("RECOMMEND",listBean);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
