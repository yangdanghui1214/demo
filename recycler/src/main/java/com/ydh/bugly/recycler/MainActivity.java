package com.ydh.bugly.recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.ydh.bugly.recycler.view.AutoPollAdapter;
import com.ydh.bugly.recycler.view.AutoPollRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AutoPollRecyclerView mRecyclerView;
    AutoPollAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (AutoPollRecyclerView) findViewById(R.id.auto_poll);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 13; ) {
            list.add(" Item: " + ++i);
        }
        adapter = new AutoPollAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecyclerView.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                adapter.addItem("zx");
                break;
            case R.id.rm:
                adapter.rmItem(3);
                break;
            case R.id.update:
                adapter.updateItem(3, "zx");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
