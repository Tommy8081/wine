package com.example.servertest;

import android.os.Bundle;

import com.example.servertest.fragment.AFragment;
import com.example.servertest.fragment.BFragment;
import com.example.servertest.fragment.CFragment;
import com.example.servertest.fragment.DFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;


public class BottomNavtgaritonActivity extends AppCompatActivity {
    private AFragment aFragment;
    private BFragment bFragment;
    private CFragment cFragment;
    private DFragment dFragment;
    private static boolean isExit = false;
    /**
     * 创建四个底部导航栏的监听
     */
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

    /**
     * 通过BottomNavigation调用四个Fragment
     * @param savedInstanceState
     */
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

    /**
     * 按两次返回键退出系统
     */
    Handler mHandler = new Handler (  ){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage ( msg );
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown ( keyCode, event );
    }

    private void exit() {
        if(!isExit){
            isExit = true;
            Toast.makeText ( BottomNavtgaritonActivity.this,"再按一次退出程序！",Toast.LENGTH_LONG ).show ();
            //利用handler延迟发送更改状态消息
            mHandler.sendEmptyMessageDelayed ( 0,2000 );
        }else {
            finish ();
            System.exit ( 0 );
        }
    }

}
