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
import atguigu.com.actualproject.shopping.pagers.bean.TopicBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/7.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {
    private final Context context;
    private final List<TopicBean.DataBean.ItemsBean> datas;

    public TopicAdapter(Context context, List<TopicBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.datas = items;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.topic_item, null);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int position) {
        TopicBean.DataBean.ItemsBean itemsBean = datas.get(position);
        Glide.with(context)
                .load(itemsBean.getCover_img_new())
                .into(holder.topicImage);
        holder.topicText.setText(itemsBean.getTopic_name());
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


    class TopicViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.topic_image)
        ImageView topicImage;
        @InjectView(R.id.topic_text)
        TextView topicText;
        public TopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
