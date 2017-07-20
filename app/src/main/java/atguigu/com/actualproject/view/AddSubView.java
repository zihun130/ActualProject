package atguigu.com.actualproject.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import atguigu.com.actualproject.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/7/13.
 */

public class AddSubView extends LinearLayout {

    private final Context context;
    @InjectView(R.id.iv_sub)
    ImageView ivSub;
    @InjectView(R.id.tv_value)
    TextView tvValue;
    @InjectView(R.id.iv_add)
    ImageView ivAdd;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tvValue.setText(value+"");
    }

    public int getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(int maxvalue) {
        this.maxvalue = maxvalue;
    }

    public int getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(int minvalue) {
        this.minvalue = minvalue;
    }

    private int value=0;
    private int minvalue=0;
    private int maxvalue=100;



    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        View inflate = View.inflate(context, R.layout.add_view, AddSubView.this);
        ButterKnife.inject(this,inflate);

        if(attrs!=null){
           TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.addSubView);
           int value=tintTypedArray.getInt(R.styleable.addSubView_value,0);

           if(value>0){
               setValue(value);
           }

           int minvalue=tintTypedArray.getInt(R.styleable.addSubView_minvalue,0);

           if(value>0){
               setMinvalue(minvalue);
           }

           int maxvalue=tintTypedArray.getInt(R.styleable.addSubView_maxvalue,0);
           if(value>0){
               setMaxvalue(maxvalue);
           }

           Drawable adddrawable = tintTypedArray.getDrawable(R.styleable.addSubView_numberAddBackground);
           if(adddrawable!=null){
               ivAdd.setImageDrawable(adddrawable);
           }

           Drawable subdrawable = tintTypedArray.getDrawable(R.styleable.addSubView_numberSubBackground);

           if(subdrawable!=null){
               ivSub.setImageDrawable(subdrawable);
           }

       }

    }

    @OnClick({R.id.iv_sub, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sub:
                subNumber();
                break;
            case R.id.iv_add:
                addNumber();
                break;
        }
    }

    private void subNumber() {
      if(value>minvalue){
          value--;
      }
        setValue(value);
        if(listener!=null){
            listener.numberChange(value);
        }

    }

    private void addNumber() {
        if(value<maxvalue){
            value++;
        }
        setValue(value);
        if(listener!=null){
            listener.numberChange(value);
        }
    }



    private OnNumberChangeListener listener;

    public interface OnNumberChangeListener{
        void numberChange(int value);
    }

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.listener = onNumberChangeListener;
    }

}
