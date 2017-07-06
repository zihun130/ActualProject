package atguigu.com.actualproject.shopping.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import atguigu.com.actualproject.base.BaseFragment;

/**
 * Created by sun on 2017/7/6.
 */

public class ShoppingAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> pagers;
    private final List<String> namelist;

    public ShoppingAdapter(FragmentManager fm, List<BaseFragment> pagers, List<String> namelist) {
        super(fm);
        this.pagers=pagers;
        this.namelist=namelist;
    }

    @Override
    public Fragment getItem(int position) {
        return pagers.get(position);
    }

    @Override
    public int getCount() {
        return namelist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return namelist.get(position);
    }
}
