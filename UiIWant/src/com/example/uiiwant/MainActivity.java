package com.example.uiiwant;

import com.astuetz.PagerSlidingTabStrip;

import android.R.color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private BackPressCloseHandler backPressCloseHandler;
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter mAdapter;

	private ActionBar actionBar;
	int actionBarColor = 0xff4b2a0b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		backPressCloseHandler = new BackPressCloseHandler(this);

		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		tabs.setShouldExpand(true);

		pager = (ViewPager) findViewById(R.id.pager);

		pager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				Toast.makeText(getApplication(), "onPageSelected " + position, Toast.LENGTH_SHORT).show();
				// openOptionsMenu();
				String title = null;

				switch (position) {
				case 0:
					title = "칭구";

					break;
				case 1:
					title = "채팅";

					break;
				case 2:
					title = "채널";

					break;
				case 3:
					title = "더보기";

					break;

				default:
					break;
				}
				actionBar.setTitle(Html.fromHtml(title));

			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int position) {

			}
		});

		mAdapter = new MyPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(mAdapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
				getResources().getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);

		actionBar = getSupportActionBar();
		actionBar.setTitle("칭구");
		actionBar.setBackgroundDrawable(new ColorDrawable(actionBarColor));
		// actionBar.setDisplayShowTitleEnabled(true);

		// getSupportActionBar().setIcon(android.R.drawable.ic_dialog_alert);
		// getSupportActionBar().setHomeButtonEnabled(true);
		// getSupportActionBar().setDisplayShowCustomEnabled(true);
		// actionBar.setTitle(Html.fromHtml("<font
		// color='#ff0000'>ActionBarTitle </font>"));

	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();
		backPressCloseHandler.onBackPressed();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.userPlus) {
			Toast.makeText(getApplicationContext(), "userPlus", Toast.LENGTH_SHORT).show();
		} else if (item.getItemId() == R.id.etcMenu) {
			Toast.makeText(getApplicationContext(), "etcMenu", Toast.LENGTH_SHORT).show();
		} else if (item.getItemId() == R.id.userCheck) {
			Toast.makeText(getApplicationContext(), "userCheck", Toast.LENGTH_SHORT).show();
		} else if (item.getItemId() == R.id.heart) {
			Toast.makeText(getApplicationContext(), "heart", Toast.LENGTH_SHORT).show();
		} else if (item.getItemId() == R.id.setting) {
			Toast.makeText(getApplicationContext(), "setting", Toast.LENGTH_SHORT).show();
		} 
		return super.onOptionsItemSelected(item);
	}

}
