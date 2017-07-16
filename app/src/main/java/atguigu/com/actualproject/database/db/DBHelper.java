package atguigu.com.actualproject.database.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import atguigu.com.actualproject.database.table.GoodsInfoTable;

/**
 * Created by sun on 2017/7/14.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context,"goodsinfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(GoodsInfoTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
