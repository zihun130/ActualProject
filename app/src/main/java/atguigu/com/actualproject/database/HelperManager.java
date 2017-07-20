package atguigu.com.actualproject.database;

import android.content.Context;

import atguigu.com.actualproject.database.dao.GoodsInfoDAO;

/**
 * Created by sun on 2017/7/16.
 */

public class HelperManager {
    private Context context;

    private GoodsInfoDAO goodsInfoDAO;

    private HelperManager(){}
    public static HelperManager instance=new HelperManager();
    public static HelperManager getInstance(){
        return instance;
    }

    public void init(Context context){
        this.context = context;
        goodsInfoDAO = new GoodsInfoDAO(context);
    }

    public GoodsInfoDAO getGoodsInfoDAO() {
        return goodsInfoDAO;
    }
}
