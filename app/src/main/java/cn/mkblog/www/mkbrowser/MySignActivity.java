package cn.mkblog.www.mkbrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MySignActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MySignAdapter mySignAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sign);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mySignAdapter = new MySignAdapter(this, new MySignAdapter.OnclickItemListener() {
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
