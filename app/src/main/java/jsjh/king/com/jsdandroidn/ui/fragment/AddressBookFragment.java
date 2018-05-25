package jsjh.king.com.jsdandroidn.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.adapter.AddressBookAdapter;
import jsjh.king.com.jsdandroidn.base.BaseFragment;
import jsjh.king.com.jsdandroidn.databinding.FragmentAddressBookBinding;
import jsjh.king.com.jsdandroidn.model.bean.AddressBookBean;
import jsjh.king.com.jsdandroidn.utils.CommonUtil;
import jsjh.king.com.jsdandroidn.viewmodel.AddressBookViewModel;
import jsjh.king.com.jsdandroidn.weight.CustomItemDecoration;
import jsjh.king.com.jsdandroidn.weight.SideBar;

/**
 * Created by ShaoGeng on 2018/5/21.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class AddressBookFragment extends BaseFragment{

    private View view;
    private FragmentAddressBookBinding mBinding;
    private AddressBookViewModel mViewModel;
    private AddressBookAdapter mAdapter;
    private CustomItemDecoration decoration;
    List<AddressBookBean> nameList = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    public static Fragment getInstance(String tagId) {
        AddressBookFragment fragment = new AddressBookFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tagId", tagId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View initLayout() {
        view = View.inflate(getActivity(), R.layout.fragment_address_book, null);
        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        mBinding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void initViewModel() {

    }

    private void initDatas(){
        String[] names = {"孙尚香", "安其拉", "白起", "不知火舞", "@小马快跑", "_德玛西亚之力_", "妲己", "狄仁杰", "典韦", "韩信",
                "老夫子", "刘邦", "刘禅", "鲁班七号", "墨子", "孙膑", "孙尚香", "孙悟空", "项羽", "亚瑟",
                "周瑜", "庄周", "蔡文姬", "甄姬", "廉颇", "程咬金", "后羿", "扁鹊", "钟无艳", "小乔", "王昭君", "虞姬",
                "李元芳", "张飞", "刘备", "牛魔", "张良", "兰陵王", "露娜", "貂蝉", "达摩", "曹操", "芈月", "荆轲", "高渐离",
                "钟馗", "花木兰", "关羽", "李白", "宫本武藏", "吕布", "嬴政", "娜可露露", "武则天", "赵云", "姜子牙",};
        for (String name : names) {
            AddressBookBean bean = new AddressBookBean();
            bean.setName(name);
            nameList.add(bean);
        }
        //对数据源进行排序
        CommonUtil.sortData(nameList);
        //返回一个包含所有Tag字母在内的字符串并赋值给tagsStr
        String tagsStr = CommonUtil.getTags(nameList);
        mBinding.sideBarActivityAddressBook.setIndexStr(tagsStr);
        decoration.setDatas(nameList, tagsStr);
        mAdapter.addAll(nameList);
    }

    @Override
    public void initView() {
        initRecyclerView();
    }

    @Override
    public void initListener() {
        mBinding.sideBarActivityAddressBook.setIndexChangeListener(new SideBar.indexChangeListener() {
            @Override
            public void indexChanged(String tag) {
                if (TextUtils.isEmpty(tag) || nameList.size() <= 0) return;
                for (int i = 0; i < nameList.size(); i++) {
                    if (tag.equals(nameList.get(i).getIndexTag())) {
                        layoutManager.scrollToPositionWithOffset(i, 0);
//                        layoutManager.scrollToPosition(i);
                        return;
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new AddressBookAdapter(view.getContext());
        //侧边导航栏
//        side_bar = (SideBar) findViewById(R.id.side_bar);
        layoutManager = new LinearLayoutManager(view.getContext());
        mBinding.recycleViewAddressBook.setLayoutManager(layoutManager);
        mBinding.recycleViewAddressBook.addItemDecoration(decoration = new CustomItemDecoration(view.getContext()));
//        mBinding.recycleViewAddressBook.setItemAnimator(new SlideInOutLeftItemAnimator(mBinding.recycleViewAddressBook));
        initDatas();
        mBinding.recycleViewAddressBook.setAdapter(mAdapter);
    }
}
