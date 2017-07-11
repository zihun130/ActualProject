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
import atguigu.com.actualproject.expert.bean.FansBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/11.
 */

public class FansAdapter extends RecyclerView.Adapter<FansAdapter.FansViewHolder> {
    private final Context context;
    private final List<FansBean.DataBean.ItemsBean.UsersBean> datas;
    private final LayoutInflater single;

    public FansAdapter(Context context, List<FansBean.DataBean.ItemsBean.UsersBean> users) {
        this.context=context;
        this.datas=users;
        this.single = LayoutInflater.from(context);
    }

    @Override
    public FansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = single.inflate(R.layout.singlepersonal_item, parent, false);
        return new FansViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(FansViewHolder holder, int position) {
        FansBean.DataBean.ItemsBean.UsersBean usersBean = datas.get(position);
        Glide.with(context)
                .load(usersBean.getUser_image().getOrig())
                .into(holder.singlePerson);
        holder.singleTextname.setText(usersBean.getUser_name());
        holder.singleText.setText(usersBean.getUser_desc());

        holder.setListener(usersBean.getUser_id(),usersBean.getUser_name(),usersBean.getUser_image().getOrig(),usersBean.getUser_desc());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class FansViewHolder extends RecyclerView.ViewHolder {
        private final View views;
        @InjectView(R.id.single_person)
        ImageView singlePerson;
        @InjectView(R.id.single_textname)
        TextView singleTextname;
        @InjectView(R.id.single_text)
        TextView singleText;
        public FansViewHolder(View inflate) {
            super(inflate);
            this.views=inflate;
            ButterKnife.inject(this,inflate);
        }

        public void setListener(final String user_id, final String user_name, final String orig, final String user_desc) {
            views.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ExpertInfoActivity.class);
                    intent.putExtra("UID",user_id);
                    intent.putExtra("NAME",user_name);
                    intent.putExtra("MID",orig);
                    intent.putExtra("DUTY",user_desc);
                    context.startActivity(intent);
                }
            });
        }
    }
}
