package atguigu.com.actualproject.recommend.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.actualproject.R;
import atguigu.com.actualproject.activity.CommentActivity;
import atguigu.com.actualproject.activity.ShowViewActivity;
import atguigu.com.actualproject.recommend.bean.RecomBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by sun on 2017/7/18.
 */

public class RecomAdapter extends RecyclerView.Adapter {

    private static final int RECOMMEND_TEXT = 0;
    private static final int RECOMMEND_VIDEO = 1;
    private static final int RECOMMEND_IMAGE = 2;
    private static final int RECOMMEND_GIF = 3;
    private static final int RECOMMEND_HTML = 4;
    private final Context context;
    private final List<RecomBean.ListBean> datas;


    public RecomAdapter(Context context, List<RecomBean.ListBean> list) {
        this.context = context;
        this.datas = list;
    }

    @Override
    public int getItemViewType(int position) {
        int itemviewtype = -1;
        RecomBean.ListBean listBean = datas.get(position);
        String type = listBean.getType();
        Log.e("TAG", type);
        if ("text".equals(type)) {
            itemviewtype = RECOMMEND_TEXT;
        } else if ("video".equals(type)) {
            itemviewtype = RECOMMEND_VIDEO;
        } else if ("image".equals(type)) {
            itemviewtype = RECOMMEND_IMAGE;
        } else if ("gif".equals(type)) {
            itemviewtype = RECOMMEND_GIF;
        } else if ("html".equals(type)) {
            itemviewtype = RECOMMEND_HTML;
        }

        return itemviewtype;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View converView;

        switch (viewType) {
            case RECOMMEND_TEXT:
                converView = View.inflate(context, R.layout.item_text, null);
                viewHolder = new TextViewHolder(converView);

                break;
            case RECOMMEND_VIDEO:
                converView = View.inflate(context, R.layout.item_video, null);
                viewHolder = new VideoViewHolder(converView);

                break;
            case RECOMMEND_IMAGE:
                converView = View.inflate(context, R.layout.item_image, null);
                viewHolder = new ImageViewHolder(converView);

                break;
            case RECOMMEND_GIF:
                converView = View.inflate(context, R.layout.item_gif, null);
                viewHolder = new GifViewHolder(converView);

                break;
            case RECOMMEND_HTML:
                converView = View.inflate(context, R.layout.item_html, null);
                viewHolder = new HtmlViewHolder(converView);

                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == RECOMMEND_TEXT) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            textViewHolder.setData(datas.get(position));

        } else if (getItemViewType(position) == RECOMMEND_VIDEO) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            videoViewHolder.setData(datas.get(position));

        } else if (getItemViewType(position) == RECOMMEND_IMAGE) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            imageViewHolder.setData(datas.get(position));

        } else if (getItemViewType(position) == RECOMMEND_GIF) {
            GifViewHolder gifViewHolder = (GifViewHolder) holder;
            gifViewHolder.setData(datas.get(position));

        } else if (getItemViewType(position) == RECOMMEND_HTML) {
            HtmlViewHolder htmlViewHolder = (HtmlViewHolder) holder;
            htmlViewHolder.setData(datas.get(position));

        }

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    //创建viewholder的父类
    class BaseViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_headpic)
        CircleImageView ivHeadpic;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_time_refresh)
        TextView tvTimeRefresh;
        @InjectView(R.id.ll_video_user_info)
        LinearLayout llVideoUserInfo;
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

        public BaseViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);


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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecomBean.ListBean listBean = datas.get(getLayoutPosition());
                    if (listBean != null) {
                        Intent intent = new Intent(context, ShowViewActivity.class);
                        if (listBean.getType().equals("image")) {
                            String imageurl = listBean.getImage().getBig().get(0);
                            intent.putExtra("imageAndgif", imageurl);
                            context.startActivity(intent);
                        } else if (listBean.getType().equals("gif")) {
                            String gifurl = listBean.getGif().getImages().get(0);
                            intent.putExtra("imageAndgif", gifurl);
                            context.startActivity(intent);
                        }
                    }
                }
            });

        }

        public void setData(RecomBean.ListBean listBean) {
            if (listBean.getU() != null && listBean.getU().getHeader() != null && listBean.getU().getHeader().get(0) != null) {
                Glide.with(context)
                        .load(listBean.getU().getHeader().get(0))
                        .into(ivHeadpic);
            }
            if (listBean.getU() != null && listBean.getU().getName() != null) {
                tvName.setText(listBean.getU().getName());
            }

            tvTimeRefresh.setText(listBean.getPasstime());
            tvShenheDingNumber.setText(listBean.getUp());
            tvShenheCaiNumber.setText(listBean.getDown() + "");
            tvPostsNumber.setText(listBean.getForward() + "");
            tvShenheRecommendNumber.setText(listBean.getComment());
        }
    }


    class TextViewHolder extends BaseViewHolder {
        @InjectView(R.id.tv_context)
        TextView tvContext;
        @InjectView(R.id.text_ll)
        LinearLayout textLl;

        public TextViewHolder(View converView) {
            super(converView);
        }

        @Override
        public void setData(RecomBean.ListBean listBean) {
            super.setData(listBean);
            tvContext.setText(listBean.getText() + "_" + listBean.getType());

            textLl.setOnClickListener(new View.OnClickListener() {
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

            List<RecomBean.ListBean.TopCommentsBean> topComments = listBean.getTop_comments();
            if (topComments != null && topComments.size() > 0) {

                for (int i = 0; i < topComments.size(); i++) {
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    TextView textView = new TextView(context);
                    textView.setTextColor(Color.BLUE);
                    textView.setText(topComments.get(i).getU().getName() + " : ");
                    TextView textView1 = new TextView(context);
                    textView1.setTextColor(Color.BLACK);
                    textView1.setText(topComments.get(i).getContent());
                    linearLayout.addView(textView);
                    linearLayout.addView(textView1);
                    textLl.addView(linearLayout);
                }
            }
        }
    }

    class VideoViewHolder extends BaseViewHolder {
        @InjectView(R.id.tv_video_context)
        TextView tvVideoContext;
        @InjectView(R.id.jcv_videoplayer)
        JCVideoPlayerStandard jcvVideoplayer;
        @InjectView(R.id.rl_holder)
        RelativeLayout rlHolder;
        @InjectView(R.id.video_ll)
        LinearLayout videoLl;

        public VideoViewHolder(View converView) {
            super(converView);

        }

        @Override
        public void setData(RecomBean.ListBean listBean) {
            super.setData(listBean);
            tvVideoContext.setText(listBean.getText() + "_" + listBean.getType());

            boolean setUp = jcvVideoplayer.setUp(listBean.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
            if (setUp) {
                Glide.with(context)
                        .load(listBean.getVideo().getThumbnail().get(0))
                        .into(jcvVideoplayer.thumbImageView);
            }


            videoLl.setOnClickListener(new View.OnClickListener() {
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


            List<RecomBean.ListBean.TopCommentsBean> topComments = listBean.getTop_comments();
            if (topComments != null && topComments.size() > 0) {

                for (int i = 0; i < topComments.size(); i++) {
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    TextView textView = new TextView(context);
                    textView.setTextColor(Color.BLUE);
                    textView.setText(topComments.get(i).getU().getName() + " : ");
                    TextView textView1 = new TextView(context);
                    textView1.setTextColor(Color.BLACK);
                    textView1.setText(topComments.get(i).getContent());
                    linearLayout.addView(textView);
                    linearLayout.addView(textView1);
                    videoLl.addView(linearLayout);
                }
            }

        }
    }

    class ImageViewHolder extends BaseViewHolder {
        @InjectView(R.id.tv_image_context)
        TextView tvImageContext;
        @InjectView(R.id.iv_image_icon)
        ImageView ivImageIcon;
        @InjectView(R.id.image_ll)
        LinearLayout imageLl;

        public ImageViewHolder(View converView) {
            super(converView);
        }

        @Override
        public void setData(RecomBean.ListBean listBean) {
            super.setData(listBean);
            tvImageContext.setText(listBean.getText() + "_" + listBean.getType());
            if (listBean.getImage() != null && listBean.getImage().getBig() != null && listBean.getImage().getSmall() != null) {
                Glide.with(context)
                        .load(listBean.getImage().getDownload_url().get(0))
                        .into(ivImageIcon);
            }


            imageLl.setOnClickListener(new View.OnClickListener() {
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


            List<RecomBean.ListBean.TopCommentsBean> topComments = listBean.getTop_comments();
            if (topComments != null && topComments.size() > 0) {

                for (int i = 0; i < topComments.size(); i++) {
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    TextView textView = new TextView(context);
                    textView.setTextColor(Color.BLUE);
                    textView.setText(topComments.get(i).getU().getName() + " : ");
                    TextView textView1 = new TextView(context);
                    textView1.setTextColor(Color.BLACK);
                    textView1.setText(topComments.get(i).getContent());
                    linearLayout.addView(textView);
                    linearLayout.addView(textView1);
                    imageLl.addView(linearLayout);
                }
            }
        }
    }

    class GifViewHolder extends BaseViewHolder {
        @InjectView(R.id.tv_gif_context)
        TextView tvGifContext;
        @InjectView(R.id.iv_image_gif)
        ImageView ivImageGif;
        @InjectView(R.id.gif_ll)
        LinearLayout gifLl;

        public GifViewHolder(View converView) {
            super(converView);
        }

        @Override
        public void setData(RecomBean.ListBean listBean) {
            super.setData(listBean);
            tvGifContext.setText(listBean.getText() + "_" + listBean.getType());
            Glide.with(context)
                    .load(listBean.getGif().getImages().get(0))
                    .into(ivImageGif);



            gifLl.setOnClickListener(new View.OnClickListener() {
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

            List<RecomBean.ListBean.TopCommentsBean> topComments = listBean.getTop_comments();
            if (topComments != null && topComments.size() > 0) {

                for (int i = 0; i < topComments.size(); i++) {
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    TextView textView = new TextView(context);
                    textView.setTextColor(Color.BLUE);
                    textView.setText(topComments.get(i).getU().getName() + " : ");
                    TextView textView1 = new TextView(context);
                    textView1.setTextColor(Color.BLACK);
                    textView1.setText(topComments.get(i).getContent());
                    linearLayout.addView(textView);
                    linearLayout.addView(textView1);
                    gifLl.addView(linearLayout);
                }
            }
        }
    }

    class HtmlViewHolder extends BaseViewHolder {
        @InjectView(R.id.tv_html_context)
        TextView tvHtmlContext;
        @InjectView(R.id.iv_html_image_icon)
        ImageView ivHtmlImageIcon;
        @InjectView(R.id.btn_install)
        Button btnInstall;
        @InjectView(R.id.html_ll)
        LinearLayout htmlLl;

        public HtmlViewHolder(View converView) {
            super(converView);
        }
    }
}
