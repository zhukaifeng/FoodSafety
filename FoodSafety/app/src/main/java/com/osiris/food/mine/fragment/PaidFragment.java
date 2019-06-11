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

public class PaidFragment extends BaseFragment {

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
        dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));
        dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));
        dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));
        dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));
        dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));
        dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));
        dataList.add(TextUti.ToDBC("恭喜您，系统已为您生成学籍，学籍编号20190410678"));

        rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        rv_data.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();

    }

    @Override
    protected void initData() {

    }
}
