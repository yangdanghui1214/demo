package com.ydh.custom.spiner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ydh.custom.R;

import java.util.ArrayList;
import java.util.List;

public class SpinerActivity extends AppCompatActivity implements View.OnClickListener, AbstractSpinerAdapter.IOnItemSelectListener {

    private TextView mTView;
    private ImageButton mBtnDropDown;
    private List<String> nameList = new ArrayList<String>();
    private AbstractSpinerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiner);
        setupViews();
    }

    private void setupViews() {

        mTView = (TextView) findViewById(R.id.tv_value);
//        mBtnDropDown = (ImageButton) findViewById(R.id.bt_dropdown);
//        mBtnDropDown.setOnClickListener(this);
        mTView.setOnClickListener(this);


        String[] names = getResources().getStringArray(R.array.hero_name);
        for (int i = 0; i < names.length; i++) {

            nameList.add(names[i]);
        }


        mAdapter = new CustemSpinerAdapter(this);
        mAdapter.refreshData(nameList, 0);

        mSpinerPopWindow = new SpinerPopWindow(this);
        mSpinerPopWindow.setAdatper(mAdapter);
        mSpinerPopWindow.setItemListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_value:
                showSpinWindow();
                break;
            default:
                break;
        }
    }


    private void setHero(int pos) {
        if (pos >= 0 && pos <= nameList.size()) {
            mTView.setText(nameList.get(pos));
        }
    }


    private SpinerPopWindow mSpinerPopWindow;

    private void showSpinWindow() {
        Log.e("", "showSpinWindow");
        mSpinerPopWindow.setWidth(mTView.getWidth());
        mSpinerPopWindow.showAsDropDown(mTView);
    }


    @Override
    public void onItemClick(int pos) {
        setHero(pos);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
