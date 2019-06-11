package com.osiris.food.mine.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.mine.adapter.OrderAllAdapter;
import com.osiris.food.utils.TextUti;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AllFragment extends BaseFragment {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    private List<String> dataList = new ArrayList<>();
    private OrderAllAdapter dataAdapter = new OrderAllAdapter(dataList);

    @Override
    protected int setLayout() {
        return R.layout.fragment_order_all;
    }

    @Override
    protected void initView() {
        dataList.add(TextUti.ToDBC("2017~2018年度食品安全中级培训课程"));
        dataList.add(TextUti.ToDBC("2017~2018年度食品安全中级培训课程"));
        dataList.add(TextUti.ToDBC("2017~2018年度食品安全中级培训课程"));
        dataList.add(TextUti.ToDBC("2017~2018年度食品安全中级培训课程"));
        dataList.add(TextUti.ToDBC("2017~2018年度食品安全中级培训课程"));
        dataList.add(TextUti.ToDBC("2017~2018年度食品安全中级培训课程"));
        dataList.add(TextUti.ToDBC("2017~2018年度食品安全中级培训课程"));

        rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        rv_data.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();

    }

    @Override
    protected void initData() {

    }
}
