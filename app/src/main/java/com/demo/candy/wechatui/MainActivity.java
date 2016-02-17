package com.demo.candy.wechatui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.demo.candy.wechatui.ui.AboutFragment;
import com.demo.candy.wechatui.ui.ChatsFragment;
import com.demo.candy.wechatui.ui.ContactsFragment;
import com.demo.candy.wechatui.ui.DiscoverFragment;
import com.demo.candy.wechatui.view.GradientIconView;
import com.demo.candy.wechatui.view.GradientTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener{

    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<>();
    private PagerAdapter mAdapter;

    private GradientIconView mIconChats, mIconContacts, mIconDiscover, mIconAbout;
    private GradientTextView mTextChats, mTextContacts, mTextDiscover, mTextAbout;

    private LinearLayout mContainerChats, mContainerContacts, mContainerDiscover, mContainerAbout;

    private List<GradientIconView> mIconIndicator = new ArrayList<>();
    private List<GradientTextView> mTextIndicator = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mViewPager = (ViewPager)findViewById(R.id.viewPager);

        mIconChats = (GradientIconView)findViewById(R.id.chat_icon);
        mIconIndicator.add(mIconChats);
        mIconChats.setIconAlpha(1f);

        mIconContacts = (GradientIconView)findViewById(R.id.contacts_icon);
        mIconIndicator.add(mIconContacts);

        mIconDiscover = (GradientIconView)findViewById(R.id.discover_icon);
        mIconIndicator.add(mIconDiscover);

        mIconAbout = (GradientIconView)findViewById(R.id.about_icon);
        mIconIndicator.add(mIconAbout);


        mTextChats = (GradientTextView)findViewById(R.id.chat_text);
        mTextIndicator.add(mTextChats);
        mTextChats.setTextViewAlpha(1f);

        mTextContacts = (GradientTextView)findViewById(R.id.contacts_text);
        mTextIndicator.add(mTextContacts);

        mTextDiscover = (GradientTextView)findViewById(R.id.discover_text);
        mTextIndicator.add(mTextDiscover);

        mTextAbout = (GradientTextView)findViewById(R.id.about_text);
        mTextIndicator.add(mTextAbout);


        mContainerChats = (LinearLayout)findViewById(R.id.chat_container);
        mContainerChats.setOnClickListener(this);

        mContainerContacts = (LinearLayout)findViewById(R.id.contacts_container);
        mContainerContacts.setOnClickListener(this);

        mContainerDiscover = (LinearLayout)findViewById(R.id.discover_container);
        mContainerDiscover.setOnClickListener(this);

        mContainerAbout = (LinearLayout)findViewById(R.id.about_container);
        mContainerAbout.setOnClickListener(this);

        initFragments();
    }



    private void initFragments() {
        mTabs.add(ChatsFragment.newInstance("",""));
        mTabs.add(ContactsFragment.newInstance("",""));
        mTabs.add(DiscoverFragment.newInstance("", ""));
        mTabs.add(AboutFragment.newInstance("", ""));

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return mTabs.get(position);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);
    }

    private void resetTabs() {
        resetIconTabs();
        resetTextTabs();
    }

    private void resetIconTabs() {
        for(int i=0; i<mIconIndicator.size(); i++) {
            mIconIndicator.get(i).setIconAlpha(0);
        }
    }

    private void resetTextTabs() {
        for(int i=0; i<mTextIndicator.size(); i++) {
            mTextIndicator.get(i).setTextViewAlpha(0);
        }
    }

    @Override
    public void onClick(View v) {
        resetTabs();

        switch(v.getId()) {
            case R.id.chat_container:
                mViewPager.setCurrentItem(0, false);
                mIconIndicator.get(0).setIconAlpha(1f);
                mTextIndicator.get(0).setTextViewAlpha(1f);
                break;
            case R.id.contacts_container:
                mViewPager.setCurrentItem(1, false);
                mIconIndicator.get(1).setIconAlpha(1f);
                mTextIndicator.get(1).setTextViewAlpha(1f);
                break;
            case R.id.discover_container:
                mViewPager.setCurrentItem(2, false);
                mIconIndicator.get(2).setIconAlpha(1f);
                mTextIndicator.get(2).setTextViewAlpha(1f);
                break;
            case R.id.about_container:
                mViewPager.setCurrentItem(3, false);
                mIconIndicator.get(3).setIconAlpha(1f);
                mTextIndicator.get(3).setTextViewAlpha(1f);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(positionOffset>0) {
            GradientIconView iconLeft = mIconIndicator.get(position);
            GradientIconView iconRight = mIconIndicator.get(position+1);

            GradientTextView textLeft = mTextIndicator.get(position);
            GradientTextView textRight = mTextIndicator.get(position+1);

            iconLeft.setIconAlpha(1-positionOffset);
            iconRight.setIconAlpha(positionOffset);
            textLeft.setTextViewAlpha(1-positionOffset);
            textRight.setTextViewAlpha(positionOffset);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {

    }
}
