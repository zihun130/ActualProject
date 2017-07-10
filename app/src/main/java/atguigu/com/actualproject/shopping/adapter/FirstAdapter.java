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
import atguigu.com.actualproject.activity.HtmlActivity;
import atguigu.com.actualproject.shopping.pagers.bean.FirstBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/7/6.
 */

public class FirstAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;
    private static final int TYPE_THREE = 2;
    private static final int TYPE_FOUR = 3;
    private final Context context;
    private final List<FirstBean.DataBean.ItemsBean.ListBean> datas;
    @InjectView(R.id.two_image)
    ImageView twoImage;
    @InjectView(R.id.two_images)
    ImageView twoImages;


    public FirstAdapter(Context context, List<FirstBean.DataBean.ItemsBean.ListBean> list) {
        this.context = context;
        this.datas = list;
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        FirstBean.DataBean.ItemsBean.ListBean listBean = datas.get(position);

        String type = listBean.getHome_type();
        if ("1".equals(type)) {
            itemViewType = TYPE_ONE;
        } else if ("2".equals(type)) {
            itemViewType = TYPE_TWO;
        } else if ("3".equals(type)) {
            itemViewType = TYPE_THREE;
        } else if ("4".equals(type)) {
            itemViewType = TYPE_FOUR;
        }

        return itemViewType;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View converView;
        switch (viewType) {
            case TYPE_ONE:
                converView = View.inflate(context,R.layout.one_viewholder,null);
                viewHolder = new OneHolder(converView);
                break;
            case TYPE_TWO:
                converView = View.inflate(context,R.layout.two_viewholder,null);
                viewHolder = new TwoHolder(converView);
                break;
            case TYPE_THREE:
                converView = View.inflate(context,R.layout.three_viewholder,null);
                viewHolder = new ThreeHolder(converView);
                break;
            case TYPE_FOUR:
                converView = View.inflate(context,R.layout.four_viewholder,null);
                viewHolder = new FourHolder(converView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ONE) {
            OneHolder oneHolder = (OneHolder) holder;
            oneHolder.setData(datas.get(position));
        } else if (getItemViewType(position) == TYPE_TWO) {
            TwoHolder twoHolder = (TwoHolder) holder;
            twoHolder.setData(datas.get(position));
        } else if (getItemViewType(position) == TYPE_THREE) {
            ThreeHolder threeHolder = (ThreeHolder) holder;
            threeHolder.setData(datas.get(position));
        } else if (getItemViewType(position) == TYPE_FOUR) {
            FourHolder fourHolder = (FourHolder) holder;
            fourHolder.setData(datas.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class OneHolder extends RecyclerView.ViewHolder {

        private final View converView;
        @InjectView(R.id.first_image)
        ImageView firstImage;


        public OneHolder(View converView) {
            super(converView);
            this.converView = converView;
            ButterKnife.inject(this, converView);
        }

        public void setData(final FirstBean.DataBean.ItemsBean.ListBean listBean) {
            Glide.with(context)
                    .load(listBean.getOne().getPic_url())
                    .into(firstImage);
            converView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HtmlActivity.class);
                    intent.putExtra("HTML", listBean.getOne().getTopic_url());
                    intent.putExtra("topic_name", listBean.getOne().getTopic_name());
                    context.startActivity(intent);
                }
            });
        }
    }

    class TwoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.two_image)
        ImageView twoImage;
        @InjectView(R.id.two_images)
        ImageView twoImages;
        private FirstBean.DataBean.ItemsBean.ListBean listBean;


        public TwoHolder(View converView) {
            super(converView);
            ButterKnife.inject(this, converView);
        }

        public void setData(final FirstBean.DataBean.ItemsBean.ListBean listBean) {
            this.listBean = listBean;
            Glide.with(context)
                    .load(listBean.getOne().getPic_url())
                    .into(twoImage);
            Glide.with(context)
                    .load(listBean.getTwo().getPic_url())
                    .into(twoImages);
            twoImage.setOnClickListener(this);
            twoImages.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,HtmlActivity.class);
            switch (v.getId()) {
                case R.id.two_image :
                    intent.putExtra("HTML",listBean.getOne().getTopic_url());
                    intent.putExtra("topic_name",listBean.getOne().getTopic_name());
                    break;
                case R.id.two_images :
                    intent.putExtra("HTML",listBean.getTwo().getTopic_url());
                    intent.putExtra("topic_name",listBean.getTwo().getTopic_name());
                    break;
            }
            context.startActivity(intent);
        }
    }

    class ThreeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.three_one_image)
        ImageView threeOneImage;
        @InjectView(R.id.three_two_image)
        ImageView threeTwoImage;
        @InjectView(R.id.three_three_image)
        ImageView threeThreeImage;
        private FirstBean.DataBean.ItemsBean.ListBean listBean;

        public ThreeHolder(View converView) {
            super(converView);
            ButterKnife.inject(this, converView);
        }

        public void setData(final FirstBean.DataBean.ItemsBean.ListBean listBean) {
            this.listBean=listBean;
            Glide.with(context)
                    .load(listBean.getOne().getPic_url())
                    .into(threeOneImage);
            Glide.with(context)
                    .load(listBean.getTwo().getPic_url())
                    .into(threeTwoImage);
            Glide.with(context)
                    .load(listBean.getThree().getPic_url())
                    .into(threeThreeImage);
            threeOneImage.setOnClickListener(this);
            threeTwoImage.setOnClickListener(this);
            threeThreeImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,HtmlActivity.class);
            switch (v.getId()) {
                case R.id.three_one_image :
                    intent.putExtra("HTML",listBean.getOne().getTopic_url());
                    intent.putExtra("topic_name",listBean.getOne().getTopic_name());
                    break;
                case R.id.three_two_image :
                    intent.putExtra("HTML",listBean.getTwo().getTopic_url());
                    intent.putExtra("topic_name",listBean.getTwo().getTopic_name());
                    break;
                case R.id.three_three_image :
                    intent.putExtra("HTML",listBean.getThree().getTopic_url());
                    intent.putExtra("topic_name",listBean.getThree().getTopic_name());
                    break;

            }
            context.startActivity(intent);
        }
    }

    class FourHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.four_one_image)
        ImageView fourOneImage;
        @InjectView(R.id.four_two_image)
        ImageView fourTwoImage;
        @InjectView(R.id.four_three_image)
        ImageView fourThreeImage;
        @InjectView(R.id.four_four_image)
        ImageView fourFourImage;
        private FirstBean.DataBean.ItemsBean.ListBean listBean;


        public FourHolder(View converView) {
            super(converView);
            ButterKnife.inject(this, converView);
        }

        public void setData(final FirstBean.DataBean.ItemsBean.ListBean listBean) {
            this.listBean=listBean;
            Glide.with(context)
                    .load(listBean.getOne().getPic_url())
                    .into(fourOneImage);
            Glide.with(context)
                    .load(listBean.getTwo().getPic_url())
                    .into(fourTwoImage);
            Glide.with(context)
                    .load(listBean.getThree().getPic_url())
                    .into(fourThreeImage);
            Glide.with(context)
                    .load(listBean.getFour().getPic_url())
                    .into(fourFourImage);
            fourOneImage.setOnClickListener(this);
            fourTwoImage.setOnClickListener(this);
            fourThreeImage.setOnClickListener(this);
            fourFourImage.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,HtmlActivity.class);
            switch (v.getId()) {
                case R.id.four_one_image :
                    intent.putExtra("HTML",listBean.getOne().getTopic_url());
                    intent.putExtra("topic_name",listBean.getOne().getTopic_name());
                    break;
                case R.id.four_two_image :
                    intent.putExtra("HTML",listBean.getTwo().getTopic_url());
                    intent.putExtra("topic_name",listBean.getTwo().getTopic_name());
                    break;
                case R.id.four_three_image :
                    intent.putExtra("HTML",listBean.getThree().getTopic_url());
                    intent.putExtra("topic_name",listBean.getThree().getTopic_name());
                    break;
                case R.id.four_four_image :
                    intent.putExtra("HTML",listBean.getFour().getTopic_url());
                    intent.putExtra("topic_name",listBean.getFour().getTopic_name());
                    break;

            }
            context.startActivity(intent);
        }

    }

}
