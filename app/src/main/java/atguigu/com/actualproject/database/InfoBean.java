package atguigu.com.actualproject.database;

/**
 * Created by sun on 2017/7/14.
 */

public class InfoBean {

    private String GoodsName;
    private String GoodsDesc;
    private String ImageUrl;
    private String Price;
    private int    Count;
    private String GoodsInfo;
    private boolean isChecked;


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public String getGoodsDesc() {
        return GoodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        GoodsDesc = goodsDesc;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public String getGoodsInfo() {
        return GoodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        GoodsInfo = goodsInfo;
    }



}
