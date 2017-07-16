package atguigu.com.actualproject.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.actualproject.database.InfoBean;
import atguigu.com.actualproject.database.db.DBHelper;
import atguigu.com.actualproject.database.table.GoodsInfoTable;

/**
 * Created by sun on 2017/7/14.
 */

public class GoodsInfoDAO {

    private GoodsInfoDAO(){}
    public static GoodsInfoDAO instance=new GoodsInfoDAO();
    public static GoodsInfoDAO getInstance(){
        return instance;
    }

    private DBHelper dbHelper;
    public GoodsInfoDAO(Context context){
        this.dbHelper = new DBHelper(context);
    }


    //得到所有所选商品
  public List<InfoBean> getAllGoodsContent(){
      SQLiteDatabase database = dbHelper.getReadableDatabase();

      String sql = "select * from "+ GoodsInfoTable.TABLE_NAME;

      Cursor cursor = database.rawQuery(sql, null);
      ArrayList<InfoBean> infolist = new ArrayList<>();

      while (cursor.moveToNext()){
          InfoBean infoBean = new InfoBean();
          infoBean.setGoodsName(cursor.getString(cursor.getColumnIndex(GoodsInfoTable.NAME)));
          infoBean.setGoodsDesc(cursor.getString(cursor.getColumnIndex(GoodsInfoTable.DESC)));
          infoBean.setImageUrl(cursor.getString(cursor.getColumnIndex(GoodsInfoTable.IMAGEURL)));
          infoBean.setPrice(cursor.getDouble(cursor.getColumnIndex(GoodsInfoTable.PRICE)));
          infoBean.setCount(cursor.getInt(cursor.getColumnIndex(GoodsInfoTable.COUNT)));
          infoBean.setGoodsInfo(cursor.getString(cursor.getColumnIndex(GoodsInfoTable.GOODSINFO)));

          infolist.add(infoBean);
      }
      cursor.close();

      return infolist;
  }

    public void AddGoods(InfoBean infoBean){
        if(infoBean==null){
            throw new NullPointerException("不能为空");
        }

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(GoodsInfoTable.NAME,infoBean.getGoodsName());
        contentValues.put(GoodsInfoTable.DESC,infoBean.getGoodsDesc());
        contentValues.put(GoodsInfoTable.IMAGEURL,infoBean.getImageUrl());
        contentValues.put(GoodsInfoTable.PRICE,infoBean.getPrice());
        contentValues.put(GoodsInfoTable.COUNT,infoBean.getCount());
        contentValues.put(GoodsInfoTable.GOODSINFO,infoBean.getGoodsInfo());
        database.replace(GoodsInfoTable.TABLE_NAME,null,contentValues);
    }

    public void DeleteGoods(String imageUrl){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.delete(GoodsInfoTable.TABLE_NAME,GoodsInfoTable.IMAGEURL+"=?",new String[]{imageUrl});
    }

}
