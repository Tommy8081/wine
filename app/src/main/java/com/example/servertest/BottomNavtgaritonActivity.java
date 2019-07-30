package com.example.servertest;

import android.os.Bundle;

import com.example.servertest.fragment.AFragment;
import com.example.servertest.fragment.BFragment;
import com.example.servertest.fragment.CFragment;
import com.example.servertest.fragment.DFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
 

public class BottomNavtgaritonActivity extends AppCompatActivity {
    private AFragment aFragment;
    private BFragment bFragment;
    private CFragment cFragment;
    private DFragment dFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener () {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId ()) {
                case R.id.home:
                    getSupportFragmentManager ().beginTransaction ().replace ( R.id.fl_container,aFragment ).commitAllowingStateLoss ();
                    return true;
                case R.id.order:
                    getSupportFragmentManager ().beginTransaction ().replace ( R.id.fl_container,bFragment ).commitAllowingStateLoss ();
                    return true;
                case R.id.cart:
                    getSupportFragmentManager ().beginTransaction ().replace ( R.id.fl_container,cFragment ).commitAllowingStateLoss ();
                    return true;
                case R.id.pcenter:
                    getSupportFragmentManager ().beginTransaction ().replace ( R.id.fl_container,dFragment ).commitAllowingStateLoss ();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_bottom_navtgariton );

        aFragment = new AFragment ();
        bFragment = new BFragment ();
        cFragment = new CFragment ();
        dFragment = new DFragment ();
        getSupportFragmentManager ().beginTransaction ().add (R.id.fl_container,aFragment).commitAllowingStateLoss ();
        BottomNavigationView navView = findViewById ( R.id.nav_view );
        navView.setOnNavigationItemSelectedListener ( mOnNavigationItemSelectedListener );
    }

}
