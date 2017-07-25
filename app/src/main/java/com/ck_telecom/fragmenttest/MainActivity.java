package com.ck_telecom.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ck_telecom.fragmenttest.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements ListItemFragment.OnListFragmentInteractionListener,DetailFragment.OnDetailFragmentDestoryListener {
    public static final String TAG = "MainActivity";

    private  ListItemFragment  listItemFragment;
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listItemFragment = ListItemFragment.newInstance(1);
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.id_fl_fragment_list, listItemFragment).addToBackStack(null).commit();

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(this, "item:" + item.id, Toast.LENGTH_SHORT).show();
        Bundle data = new Bundle();
        data.putString("id", item.id);
        DetailFragment detailFragment = DetailFragment.newInstance(data);
        getSupportFragmentManager().beginTransaction().hide(listItemFragment).
                add(R.id.id_fl_fragment_list, detailFragment).commit();

//        detailFragment.setTargetFragment(listItemFragment,1);
//        startActivityFromFragment(detailFragment, i, 1);
//        detailFragment.startActivityForResult();
    }

    @Override
    public void onDetailFragmentDestory() {
       // getSupportFragmentManager().beginTransaction().show(listItemFragment).commit();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();屏蔽掉系统的返回事件
        if (fm!=null && fm.getBackStackEntryCount()>0){
            fm.popBackStack();
        }else {
            finish();
        }

        if (listItemFragment.isHidden()){
            getSupportFragmentManager().beginTransaction().show(listItemFragment).commit();
        }else if (listItemFragment!=null&&listItemFragment.isVisible()){
            super.onBackPressed();
        }
    }

}
