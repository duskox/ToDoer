package com.pokrenise.dvorkapic.todoer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by dvorkapic on 13/11/14.
 */
public class MainActivity extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this
     * becomes too memory intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    private static final String[] TITLES = {"Deadlines", "To Do", "Done"};

    void initTasksArrays() {
        Singleton.deadline = new ArrayList<Task>();
        Singleton.todo = new ArrayList<Task>();
        Singleton.done = new ArrayList<Task>();

        Time t = new Time();
        t.set(0, 10, 12, 15, 5, 2014);
        Singleton.deadline.add(new Task("Prvi deadline", "Opis prvog", R.drawable.ic_launcher, t));
        t.set(0, 30, 12, 15, 5, 2014);
        Singleton.deadline.add(new Task("Drugi deadline", "Opis drugog", R.drawable.ic_launcher, t));
        Singleton.todo.add(new Task("Prvi todo", "Opis treceg", R.drawable.ic_launcher, null));
        Singleton.done.add(new Task("Prvi done", "Opis treceg", R.drawable.ic_launcher, null));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        initTasksArrays();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.addtask_menuitem) {
            Intent i = new Intent(this, AddTaskFragActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentList = new ArrayList();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

            Fragment f;

            for(int i=0;i<3;i++) {
                f = new ListTaskFragment();
                Bundle a = new Bundle();

                a.putInt("LIST-TYPE", i);
                f.setArguments(a);
                fragmentList.add(f);
            }
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();

            return TITLES[position];
        }
    }
}
