package atguigu.com.actualproject.recommend.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import atguigu.com.actualproject.base.BaseFragment;

/**
 * Created by sun on 2017/7/18.
 */

public class RecommendAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> pagers;
    private final List<String> titleList;

    public RecommendAdapter(FragmentManager fm, List<BaseFragment> pagers, List<String> titleList) {
        super(fm);
        this.pagers=pagers;
        this.titleList=titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return pagers.get(position);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
