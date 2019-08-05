package com.ydh.lib_base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class LazyFragment extends Fragment {

    private View rootView;
    // view 是否创建
    private boolean isViewCreated = false;
    //
    private boolean currentVisibleState = false;
    private boolean isFirstVisible = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.e("zxy", "onCreateView");

        if (rootView == null) {
            rootView = inflater.inflate(getViewId(), container, false);
        }

        initView(rootView);
        isViewCreated = true;

        if (getUserVisibleHint() && !isHidden()) {
            dispatchUserVisibleHint(true);
        }

        return rootView;
    }

    /**
     * 对页面数据进行更新数据操作
     *
     * @param rootView view 对象
     * @return
     */
    abstract void initView(View rootView);


    abstract int getViewId();


    /**
     * @param isVisibleToUser 判断对用户是否可见，与生命周期无关
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("zxy", "setUserVisibleHint   " + isVisibleToUser);
        if (isViewCreated) {
            if (isVisibleToUser && !currentVisibleState) {
                dispatchUserVisibleHint(true);
            } else if (currentVisibleState && !isVisibleToUser) {
                dispatchUserVisibleHint(false);
            }
        }
    }

    /**
     * @param visible 是否界面上显示
     */
    private void dispatchUserVisibleHint(boolean visible) {
        if (currentVisibleState == visible) {
            return;
        }
        currentVisibleState = visible;
        if (visible) {
            if (isFirstVisible) {
                isFirstVisible = false;
                onFragmentFirstVisible();
            }
            onFragmentResume();
        } else {
            onFragmentPause();
        }
    }

    protected void onFragmentFirstVisible() {

    }

    ;

    /**
     * 加载数据
     */
    protected void onFragmentResume() {
        Log.e("zxy", "开始加载数据");
    }

    /**
     * 停止耗时操作
     */
    protected void onFragmentPause() {
        Log.e("zxy", "停止耗时操作");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("zxy", "onResume");
        if (!isHidden() && !currentVisibleState && getUserVisibleHint()) {
            dispatchUserVisibleHint(true);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("zxy", "onHiddenChanged " + hidden);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("zxy", "onResume");
        if (currentVisibleState && getUserVisibleHint()) {
            dispatchUserVisibleHint(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstVisible = true;
        currentVisibleState = false;
        isViewCreated = false;
    }
}
