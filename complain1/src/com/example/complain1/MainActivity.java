package com.example.complain1;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.*;

public class MainActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    private TabHost tabHost;
    private RadioButton tab1,tab2,tab3,tab4,tab5;
    private int bottomTab_height = 0;
    private ImageView bottomImg;
    private long mBackPressed;
    private static int TIME_INTERVAL = 2000;
    private final String TAB5 = "tab5",TAB1 = "tab1",TAB2 = "tab2",TAB3 = "tab3",TAB4 = "tab4";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.main_tab);
        initTab();
        Log.d("initTab","initTab");
    }

    private void initTab(){
        tabHost = getTabHost();
        RadioGroup main_bottom = (RadioGroup)this.findViewById(R.id.main_bottom);
        bottomImg = (ImageView)this.findViewById(R.id.main_bottomImg);
//        setBottomTab_height();
        main_bottom.setOnCheckedChangeListener(new Main_bottomOnCheckedChangeListener());
        tab1 = (RadioButton)this.findViewById(R.id.main_tab1);
        tab2 = (RadioButton)this.findViewById(R.id.main_tab2);
        tab3 = (RadioButton)this.findViewById(R.id.main_tab3);
        tab4 = (RadioButton)this.findViewById(R.id.main_tab4);
        tab5 = (RadioButton)this.findViewById(R.id.main_tab5);
        Log.d("initTab","setTabBtn");
        setTabBtn();
    }
    //添加5个tab选项卡，定义他们的tab名，指示名，目标屏对应的类
    private void setTabBtn(){
        tabHost.addTab(tabHost.newTabSpec(TAB1).setIndicator(TAB1).setContent(new Intent(MainActivity.this,Tab1Activity.class)));
        Log.d("addTab","setTabBtn");
        tabHost.addTab(tabHost.newTabSpec(TAB2).setIndicator(TAB2).setContent(new Intent(MainActivity.this,Tab2Activity.class)));
        tabHost.addTab(tabHost.newTabSpec(TAB3).setIndicator(TAB3).setContent(new Intent(MainActivity.this,Tab3Activity.class)));
        tabHost.addTab(tabHost.newTabSpec(TAB4).setIndicator(TAB4).setContent(new Intent(MainActivity.this,Tab4Activity.class)));
        tabHost.addTab(tabHost.newTabSpec(TAB5).setIndicator(TAB5).setContent(new Intent(MainActivity.this,Tab5Activity.class)));

    }

    class Main_bottomOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            switch (checkedId){
                case R.id.main_tab1:
                    tab1.setTextColor(getResources().getColor(R.color.main_tab_focused));
                    tab2.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab3.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab4.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab5.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tabHost.setCurrentTabByTag(TAB1);
                    break;
                case R.id.main_tab2:
                    tab1.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab2.setTextColor(getResources().getColor(R.color.main_tab_focused));
                    tab3.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab4.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab5.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tabHost.setCurrentTabByTag(TAB2);
                    break;
                case R.id.main_tab3:
                    tab1.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab2.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab3.setTextColor(getResources().getColor(R.color.main_tab_focused));
                    tab4.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab5.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tabHost.setCurrentTab(2);
                    break;
                case R.id.main_tab4:
                    tab1.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab2.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab3.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab4.setTextColor(getResources().getColor(R.color.main_tab_focused));
                    tab5.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tabHost.setCurrentTab(3);
                    break;
                case R.id.main_tab5:
                    tab1.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab2.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab3.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab4.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    tab5.setTextColor(getResources().getColor(R.color.main_tab_focused));
                    tabHost.setCurrentTab(4);
                    break;
            }
        }
    }

//    //获取底部图片高度
//    public void setBottomTab_height(){
//        ViewTreeObserver vto = bottomImg.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                bottomTab_height = bottomImg.getHeight();
//                bottomImg.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });
//        SharedPreferences preferences = getSharedPreferences("preferences",Activity.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putInt("bottomTab_height",bottomTab_height);
//        editor.commit();
//    }
//    @Override
//    public void onBackPressed() {
//        Toast.makeText(getBaseContext(),"请在按一次退出",Toast.LENGTH_SHORT).show();
//        if(mBackPressed + TIME_INTERVAL > System.currentTimeMillis()){
//            super.onBackPressed();
//            return;
//        }
//        else {
//            Toast.makeText(getBaseContext(),"请在按一次退出",Toast.LENGTH_SHORT).show();
//        }
//        mBackPressed = System.currentTimeMillis();
//    }

//设置双击退出功能
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            if(mBackPressed + TIME_INTERVAL > System.currentTimeMillis()){
                super.onBackPressed();
                finish();
            }
            else {
                Toast.makeText(getBaseContext(),"请在按一次退出",Toast.LENGTH_SHORT).show();
            }
            mBackPressed = System.currentTimeMillis();
        }
        else {
            return super.dispatchKeyEvent(event);
        }
        return true;
    }


}
