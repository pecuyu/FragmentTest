package com.ck_telecom.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ck_telecom.fragmenttest.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements ListItemFragment.OnListFragmentInteractionListener,DetailFragment.OnDetailFragmentDestoryListener {
    public static final String TAG = "MainActivity";
    public  boolean isPhone = true;
    private  ListItemFragment  listItemFragment;
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkIsPhoneOrPad();
        loadProperView();
    }

    /**
     * 加载合适的视图
     */
    private void loadProperView() {
        listItemFragment = ListItemFragment.newInstance(1);
        fm = getSupportFragmentManager();
        if (isPhone) {
            fm.beginTransaction().replace(R.id.id_fl_fragment_list, listItemFragment).addToBackStack(null).commit();
        } else {
            fm.beginTransaction().replace(R.id.id_fl_fragment_list_land, listItemFragment).addToBackStack(null).commit();

        }
    }

    /**
     * 判断是手机还是平板
     */
    private void checkIsPhoneOrPad() {
        if (findViewById(R.id.id_fl_fragment_detail_land) == null) {
            isPhone = true;
        } else {
            isPhone = false;
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(this, "item:" + item.id, Toast.LENGTH_SHORT).show();
        Bundle data = new Bundle();
        data.putString("id", item.id);
        if (isPhone) {
            Intent i = new Intent(this, DetailActivity.class);
            i.putExtras(data);
            startActivity(i);
        } else {
            DetailFragment detailFragment = DetailFragment.newInstance(data);
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.id_fl_fragment_detail_land, detailFragment).commit();
        }
    }

    @Override
    public void onDetailFragmentDestory() {
       // getSupportFragmentManager().beginTransaction().show(listItemFragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
