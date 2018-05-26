package jsjh.king.com.jsdandroidn.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.adapter.MenuAdapter;
import jsjh.king.com.jsdandroidn.base.BaseActivity;
import jsjh.king.com.jsdandroidn.databinding.ActivityMainBinding;
import jsjh.king.com.jsdandroidn.model.bean.MenuBean;
import jsjh.king.com.jsdandroidn.ui.fragment.AboutFragment;
import jsjh.king.com.jsdandroidn.ui.fragment.AddressBookFragment;
import jsjh.king.com.jsdandroidn.ui.fragment.SettingFragment;
import jsjh.king.com.jsdandroidn.ui.fragment.WorkFragment;
import jsjh.king.com.jsdandroidn.ui.login.LoginActivity;
import jsjh.king.com.jsdandroidn.ui.mine.MineInformationActivity;
import jsjh.king.com.jsdandroidn.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ActivityMainBinding mBinding;
    private MainViewModel mViewModel;
    private MenuAdapter adapter;
    private static MainActivity _theInstance;
    private List<MenuBean> mMenuItemList = new ArrayList<MenuBean>();
    private Fragment mContent = new Fragment();

    @Override
    public void initLayoutView() {
        super.initLayoutView();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void initView() {
        super.initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        mViewModel = new MainViewModel(this);
        mViewModel.title.set(getString(R.string.title_main_work));
        mBinding.setVm(mViewModel);

//        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, mBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        View v = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        mBinding.menuListView.addHeaderView(v);
        mMenuItemList.add(new MenuBean(getString(R.string.title_menu_work), R.mipmap.icon_menu_work, R.mipmap.icon_menu_work_select, true));
        mMenuItemList.add(new MenuBean(getString(R.string.title_menu_address), R.mipmap.icon_menu_address, R.mipmap.icon_menu_address_select, false));
        mMenuItemList.add(new MenuBean(getString(R.string.title_menu_setting), R.mipmap.icon_menu_setting, R.mipmap.icon_menu_setting_select, false));
        mMenuItemList.add(new MenuBean(getString(R.string.title_menu_about), R.mipmap.icon_menu_about, R.mipmap.icon_menu_about_select, false));
        mMenuItemList.add(new MenuBean(getString(R.string.title_menu_exit), R.mipmap.icon_menu_exit, R.mipmap.icon_menu_exit_select, false));
        adapter = new MenuAdapter(this, mMenuItemList);
        mBinding.menuListView.setAdapter(adapter);
        mBinding.menuListView.setOnItemClickListener(this);
//        mBinding.navView.setNavigationItemSelectedListener(this);

        pushFragment(WorkFragment.getInstance(getString(R.string.title_menu_drive)));
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        if (position == 0) {
            startActivity(new Intent(MainActivity.this, MineInformationActivity.class));
            return;
        }
        for (int i = 0; i < mMenuItemList.size(); i++) {
            if (mMenuItemList.size() == position) {
                break;
            }
            if (i == position - 1) {
                if (mMenuItemList.get(i).getSelect()) return;
                mMenuItemList.get(i).setSelect(true);
            } else {
                mMenuItemList.get(i).setSelect(false);
            }
        }
        adapter.notifyDataSetChanged();
        switch (position) {
            case 1:
                pushFragment(WorkFragment.getInstance(getString(R.string.title_menu_drive)));
                mViewModel.title.set(getString(R.string.title_menu_drive));
                break;
            case 2:
                pushFragment(AddressBookFragment.getInstance(getString(R.string.title_menu_address)));
//                switchContent(mContent, AddressBookFragment.getInstance(getString(R.string.title_menu_address)));
                mViewModel.title.set(getString(R.string.title_menu_address));
                break;
            case 3:
                pushFragment(SettingFragment.getInstance(getString(R.string.title_menu_setting)));
//                switchContent(mContent, SettingFragment.getInstance(getString(R.string.title_menu_setting)));
                mViewModel.title.set(getString(R.string.title_menu_setting));
                break;
            case 4:
                pushFragment(AboutFragment.getInstance(getString(R.string.title_menu_about)));
//                switchContent(mContent, AboutFragment.getInstance(getString(R.string.title_menu_about)));
                mViewModel.title.set(getString(R.string.title_menu_about));
                break;
            case 5:
                showNormalDialog();
                break;
            default:
                break;
        }
    }

    public void pushFragment(Fragment frag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_fade_in, R.anim.abc_fade_out);
        // For the drawer navigation, we replace the fragment
        ft.replace(R.id.content_frame, frag).commit();
    }

    /**
     * fragment 切换
     *
     * @param from
     * @param to
     */
    public void switchContent(Fragment from, Fragment to) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().setCustomAnimations(
                android.R.anim.fade_in, R.anim.abc_fade_out);
        if (mContent != to) {
            mContent = to;
            if (!to.isAdded()) {                                                                                                     // 先判断是否被add过
                transaction.hide(from).add(R.id.content_frame, to).commit();                  // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit();                                                    // 隐藏当前的fragment，显示下一个
            }
        }
    }

    Fragment getVisibleFragment() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.getUserVisibleHint()) {
                return fragment;
            }
        }
        return null;
    }

    public static MainActivity getInstance() {
        return _theInstance;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (_theInstance == null) {
            _theInstance = this;
        }
    }

    private void showNormalDialog() {

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("退出")
                .setMessage("您确定要出吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent it = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(it);
                        finish();
                    }
                })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
    }

    //    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//        if (id == R.id.nav_work) {
//
//        } else if (id == R.id.nav_address) {
//
//        } else if (id == R.id.nav_setting) {
//
//        } else if (id == R.id.nav_about) {
//
//        } else if (id == R.id.nav_exit) {
//            showToast("1222");
//        }
//        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
}
