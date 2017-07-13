package atguigu.com.actualproject.magazine.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.Utils.Constant;
import atguigu.com.actualproject.magazine.AuthorInfoActivity;
import atguigu.com.actualproject.magazine.bean.AuthorBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sun on 2017/7/12.
 */

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {
    private final Context context;
    private final List<AuthorBean.DataBean.ItemsBean> datas;
    private final LayoutInflater view;
    private String[] strings={Constant.MAGAZINEONE,Constant.MAGAZINETWO,Constant.MAGAZINETHREE,Constant.MAGAZINEFOUR,Constant.MAGAZINEFIVE,Constant.MAGAZINESIX,Constant.MAGAZINESEVEN,Constant.MAGAZINEEIGHT,Constant.MAGAZINENINE,Constant.MAGAZINETEN,Constant.MAGAZINEELEVEN,Constant.MAGAZINETWELVE};


    public AuthorAdapter(Context context, List<AuthorBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.datas = items;
        this.view = LayoutInflater.from(context);
    }

    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = view.inflate(R.layout.author_item, parent, false);
        return new AuthorViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(AuthorViewHolder holder, int position) {
        AuthorBean.DataBean.ItemsBean itemsBean = datas.get(position);
        Glide.with(context)
                .load(itemsBean.getThumb())
                .into(holder.authorImage);
        holder.authorTextname.setText(itemsBean.getAuthor_name());
        holder.authorText.setText(itemsBean.getNote());

        holder.setListener(position,itemsBean.getAuthor_name());
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder {
        private final View author;
        @InjectView(R.id.author_image)
        CircleImageView authorImage;
        @InjectView(R.id.author_textname)
        TextView authorTextname;
        @InjectView(R.id.author_text)
        TextView authorText;
        public AuthorViewHolder(View inflate) {
            super(inflate);
            this.author=inflate;
            ButterKnife.inject(this,inflate);
        }

        public void setListener(final int position, final String author_name) {
            author.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,AuthorInfoActivity.class);
                    intent.putExtra("URL",strings[position]);
                    intent.putExtra("AUTHOR_NAME",author_name);
                    context.startActivity(intent);
                }
            });
        }
    }
}
