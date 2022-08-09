package com.example.customview.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.customview.R;

import java.util.List;

public class CustomViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CustomViewAdapter(List<String> data) {

        super(R.layout.adapter_item, data);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {

        baseViewHolder.setText(R.id.title, s);

    }
}
