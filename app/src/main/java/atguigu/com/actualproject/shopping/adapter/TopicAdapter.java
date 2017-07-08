package atguigu.com.actualproject.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.activity.HtmlActivity;
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
        String topic_name = itemsBean.getTopic_name();
        Glide.with(context)
                .load(itemsBean.getCover_img_new())
                .into(holder.topicImage);
        holder.topicText.setText(topic_name);

        String access_url = itemsBean.getAccess_url();
        holder.setListener(access_url,topic_name);
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


    class TopicViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        @InjectView(R.id.topic_image)
        ImageView topicImage;
        @InjectView(R.id.topic_text)
        TextView topicText;
        public TopicViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
            ButterKnife.inject(this,itemView);
        }

        public void setListener(final String access_url, final String topic_name) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,HtmlActivity.class);
                    intent.putExtra("HTML",access_url);
                    intent.putExtra("topic_name",topic_name);
                    context.startActivity(intent);
                }
            });
        }
    }
}
