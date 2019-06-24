package com.osiris.food.train;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.model.VideoDetailBean;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.train.fragment.CommentFragment;
import com.osiris.food.train.fragment.VideoDetailFragment;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.utils.T;
import com.osiris.food.view.PagerSlidingTabStrip;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

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
    private String[] title;

    private int mId;
    private String mPic;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_train_content_look;
    }

    @Override
    public void init() {
        mId = getIntent().getIntExtra("v_id", 0);
        mPic = getIntent().getStringExtra("pic");
        Log.e("xzw", mId + "");
        getVideoDetail();

        title = new String[]{getResources().getString(R.string.detail), "评论"};

        mViewPager.setAdapter(new myPagerAdapter(this.getSupportFragmentManager()));
        tab_strip.setViewPager(mViewPager);
        tab_strip.setTextSize((int) getResources().getDimension(R.dimen.sp16));

        JCVideoPlayer.setJcUserAction(new MyUserActionStandard());
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

    private void getVideoDetail() {
        //String url = ApiRequestTag.API_HOST + "/api/v1/videos/" + mId;
        String url = ApiRequestTag.API_HOST + "/api/v1/videos/" + 2;
        Log.e("xzw", url);
        NetRequest.requestNoParam(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
            @Override
            public void requestSuccess(int tag, String successResult) {
                Log.e("xzw", successResult);
                VideoDetailBean videoDetailBean = JsonUtils.deserialize(successResult, VideoDetailBean.class);
                if (successResult.contains("id")) {
                    tvTitle.setText(videoDetailBean.getData().getName());
                    videoplayer.setUp(videoDetailBean.getData().getPath()
                            , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoDetailBean.getData().getName());
                    Picasso.with(mActivity)
                            .load(mPic)
                            .into(videoplayer.thumbImageView);
                    EventBus.getDefault().post(videoDetailBean);
                } else {
                    T.showShort(mActivity, "信息加载错误，请稍后重试");
                    finish();
                }
            }

            @Override
            public void requestFailure(int tag, int code, String msg) {
                Log.e("xzw", msg);
            }
        });
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
