package atguigu.com.actualproject.magazine.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.activity.HtmlActivity;
import atguigu.com.actualproject.magazine.bean.MagazineBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/12.
 */

public class MagazineAdapter extends RecyclerView.Adapter<MagazineAdapter.MagazineViewHolder> {
    private final Context context;
    private final ArrayList<MagazineBean.DataBean.ItemsBean.SecondBean> datas;
    private final LayoutInflater inflate;

    public MagazineAdapter(Context context, ArrayList<MagazineBean.DataBean.ItemsBean.SecondBean> objects) {
        this.context = context;
        this.datas = objects;
        this.inflate = LayoutInflater.from(context);
    }

    @Override
    public MagazineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflate.inflate(R.layout.magazine_item, null);
        return new MagazineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MagazineViewHolder holder, int position) {
        MagazineBean.DataBean.ItemsBean.SecondBean secondBean = datas.get(position);
        Glide.with(context)
                .load(secondBean.getCover_img_new())
                .into(holder.topicImage);
        holder.topicText.setText(secondBean.getTopic_name());
        holder.dateText.setText(secondBean.getAddtime());
        holder.setListener(secondBean.getTopic_url(),secondBean.getTopic_name());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

     class MagazineViewHolder extends RecyclerView.ViewHolder {
        private final View info;
        @InjectView(R.id.date_text)
        TextView dateText;
        @InjectView(R.id.topic_image)
        ImageView topicImage;
        @InjectView(R.id.topic_text)
        TextView topicText;

        public MagazineViewHolder(View view) {
            super(view);
            this.info=view;
            ButterKnife.inject(this,view);
        }

        public void setListener(final String topic_url, final String topic_name) {
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, HtmlActivity.class);
                    intent.putExtra("HTML",topic_url);
                    intent.putExtra("topic_name",topic_name);
                    context.startActivity(intent);
                }
            });
        }
    }
}
