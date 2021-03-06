package atguigu.com.actualproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.bean.CommentBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sun on 2017/7/19.
 */

public class HotCommentAdapter extends RecyclerView.Adapter<HotCommentAdapter.HotViewHolder> {
    private final Context context;
    private final List<CommentBean.HotBean.ListBean> datas;


    public HotCommentAdapter(Context context, List<CommentBean.HotBean.ListBean> hotlist) {
        this.context = context;
        this.datas = hotlist;
    }

    @Override
    public HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_hot_comment, null);
        return new HotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotViewHolder holder, int position) {
        CommentBean.HotBean.ListBean listBean = datas.get(position);
        Glide.with(context)
                .load(listBean.getUser().getProfile_image())
                .into(holder.ivPicture);
        String sex = listBean.getUser().getSex();
        if("m".equals(sex)) {
            holder.personalSexMan.setVisibility(View.VISIBLE);
            holder.personalSexWoman.setVisibility(View.GONE);
        }else if("f".equals(sex)) {
            holder.personalSexMan.setVisibility(View.GONE);
            holder.personalSexWoman.setVisibility(View.VISIBLE);
        }

        holder.tvName.setText(listBean.getUser().getUsername());
        holder.hotText.setText(listBean.getLike_count()+"");
        holder.hotTextContent.setText(listBean.getContent());

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_picture)
        CircleImageView ivPicture;
        @InjectView(R.id.personal_sex_man)
        ImageView personalSexMan;
        @InjectView(R.id.personal_sex_woman)
        ImageView personalSexWoman;
        @InjectView(R.id.tv_name)
        TextView tvName;
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
        @InjectView(R.id.hot_text)
        TextView hotText;
        @InjectView(R.id.hot_text_content)
        TextView hotTextContent;
        public HotViewHolder(View view) {
            super(view);
            ButterKnife.inject(this,view);
        }
    }
}
