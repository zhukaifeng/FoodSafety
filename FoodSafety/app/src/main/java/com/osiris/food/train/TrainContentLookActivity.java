package com.osiris.food.train;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.train.fragment.CommentFragment;
import com.osiris.food.train.fragment.VideoDetailFragment;
import com.osiris.food.view.PagerSlidingTabStrip;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCUserAction;
import fm.jiecao.jcvideoplayer_lib.JCUserActionStandard;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class TrainContentLookActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rlBack;
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.videoplayer)
	JCVideoPlayerStandard videoplayer;
	@BindView(R.id.tab_strip)
	PagerSlidingTabStrip tab_strip;
	@BindView(R.id.viewPager)
	ViewPager mViewPager;
	private  String[] title;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_train_content_look;
	}




	@Override
	public void init() {

		title = new String[]{getResources().getString(R.string.detail), getResources().getString(R.string
				.comments)};


		videoplayer.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
				, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "测试视频");
		Picasso.with(this)
				.load("http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360")
				.into(videoplayer.thumbImageView);

		JCVideoPlayer.setJcUserAction(new MyUserActionStandard());
		mViewPager.setAdapter(new myPagerAdapter(this.getSupportFragmentManager()));
		tab_strip.setViewPager(mViewPager);
		tab_strip.setTextSize((int) getResources().getDimension(R.dimen.sp16));


	}

	class MyUserActionStandard implements JCUserActionStandard {

		@Override
		public void onEvent(int type, String url, int screen, Object... objects) {
			switch (type) {
				case JCUserAction.ON_CLICK_START_ICON:
					Log.i("USER_EVENT", "ON_CLICK_START_ICON" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserAction.ON_CLICK_START_ERROR:
					Log.i("USER_EVENT", "ON_CLICK_START_ERROR" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserAction.ON_CLICK_START_AUTO_COMPLETE:
					Log.i("USER_EVENT", "ON_CLICK_START_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserAction.ON_CLICK_PAUSE:
					Log.i("USER_EVENT", "ON_CLICK_PAUSE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserAction.ON_CLICK_RESUME:
					Log.i("USER_EVENT", "ON_CLICK_RESUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserAction.ON_SEEK_POSITION:
					Log.i("USER_EVENT", "ON_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserAction.ON_AUTO_COMPLETE:
					Log.i("USER_EVENT", "ON_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserAction.ON_ENTER_FULLSCREEN:
					Log.i("USER_EVENT", "ON_ENTER_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserAction.ON_QUIT_FULLSCREEN:
					Log.i("USER_EVENT", "ON_QUIT_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserAction.ON_ENTER_TINYSCREEN:
					Log.i("USER_EVENT", "ON_ENTER_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserAction.ON_QUIT_TINYSCREEN:
					Log.i("USER_EVENT", "ON_QUIT_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserAction.ON_TOUCH_SCREEN_SEEK_VOLUME:
					Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_VOLUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserAction.ON_TOUCH_SCREEN_SEEK_POSITION:
					Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;

				case JCUserActionStandard.ON_CLICK_START_THUMB:
					Log.i("USER_EVENT", "ON_CLICK_START_THUMB" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				case JCUserActionStandard.ON_CLICK_BLANK:
					Log.i("USER_EVENT", "ON_CLICK_BLANK" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
					break;
				default:
					Log.i("USER_EVENT", "unknow");
					break;
			}
		}
	}



	private class myPagerAdapter extends FragmentPagerAdapter {


		VideoDetailFragment fragment1;
		CommentFragment fragment2;

		public myPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
				case 0:
					fragment1 = new VideoDetailFragment();
					return fragment1;

				case 1:
					fragment2 = new CommentFragment();
					return fragment2;

				default:
					return null;
			}
		}

		@Override
		public int getCount() {

			return title.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return title[position];
		}

	}


}
