package atguigu.com.actualproject.database.table;

/**
 * Created by sun on 2017/7/14.
 */

public class GoodsInfoTable {

    public static final String TABLE_NAME="goodsinfo";
    public static final String NAME="Name";
    public static final String IMAGEURL="ImageUrl";
    public static final String DESC="Desc";
    public static final String PRICE="Price";
    public static final String COUNT="Count";
    public static final String GOODSINFO="Info";
    //创建表语句
    public static final String CREATE_TABLE="create table "+TABLE_NAME+"("+NAME+" text,"+IMAGEURL+" text,"+DESC+" text,"+PRICE+" double,"+COUNT+" integer,"+GOODSINFO+" text)";

}
