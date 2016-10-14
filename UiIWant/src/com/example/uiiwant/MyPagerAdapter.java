package com.example.uiiwant;

import com.astuetz.PagerSlidingTabStrip.IconTabProvider;
import com.example.uiiwant.frag1.FrOne;
import com.example.uiiwant.frag2.FrTwo;
import com.example.uiiwant.frag3.FrThree;
import com.example.uiiwant.frag4.FrFour;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter implements IconTabProvider {
	

	private final int[] SWITCH_ICONS = { R.drawable.tab_selector1, R.drawable.tab_selector2, R.drawable.tab_selector3,
			R.drawable.tab_selector4 };

	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getCount() {
		return SWITCH_ICONS.length;
	}

	@Override
	public int getPageIconResId(int position) {
		return SWITCH_ICONS[position];
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		// Open HomeFragment.java
		case 0:
			FrOne fragmentOne = new FrOne();
			return fragmentOne;
		case 1:
			FrTwo fragmentTwo = new FrTwo();
			return fragmentTwo;
		case 2:
			FrThree fragmentThree = new FrThree();
			return fragmentThree;
		case 3:
			FrFour fragmentFour = new FrFour();
			return fragmentFour;
		}
		return null;

	}
}
