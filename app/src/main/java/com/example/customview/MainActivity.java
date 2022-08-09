package com.example.customview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.customview.adapter.CustomViewAdapter;
import com.example.customview.widget.SuperDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private RecyclerView recyclerView;

    private List<String> data = new ArrayList<>();

    private enum CustomViewTitle {
        气泡漂浮动画,
        波浪动画贝塞尔曲线实现,
        波浪动画正余弦函数实现,
        水波雷达扩散效果,
        RecyclerView实现另类的Tag标签,
        按钮自定义动画,
        自定义支付密码输入框,
        自定义进度条,
        使用的带动画的view,
        粘性小球,
        banner,
        吸顶效果,
        揭露动画,
        支付宝首页效果,
        RecyclerView的item动画,
        路径path动画,
        仿新浪投票控件,
        直播侧滑清屏效果
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (CustomViewTitle c : CustomViewTitle.values()) {
            data.add(c.name());
        }

        CustomViewAdapter adapter = new CustomViewAdapter(data);

        adapter.setOnItemClickListener(this);

        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new SuperDividerItemDecoration.Builder(this)
                .setDividerPaddingLeft(15)
                .setDividerPaddingRight(15)
                .setDividerWidth(3)
                //.setDividerColor(R.color.divider_color)
                .build());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(BaseQuickAdapter<?,?> adapter, View view, int position) {

        String s = (String) adapter.getData().get(position);

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        data.clear();
        data = null;
    }
}