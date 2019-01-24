package cn.mkblog.www.mkbrowser.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import cn.mkblog.www.mkbrowser.R;
import cn.mkblog.www.mkbrowser.adapter.MyBrowseAdapter;
import cn.mkblog.www.mkbrowser.adapter.MySignAdapter;

public class MyBrowseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyBrowseAdapter myBrowseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_browse);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myBrowseAdapter = new MyBrowseAdapter(this, new MyBrowseAdapter.OnclickItemListener() {
            @Override
            public void onClick(String url) {
                Intent intent = new Intent();
                intent.putExtra("url", url);
                setResult(200, intent);
                finish();
            }
        }));
    }
}
