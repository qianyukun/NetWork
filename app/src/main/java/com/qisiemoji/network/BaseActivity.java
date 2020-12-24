package com.qisiemoji.network;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 功能描述
 *
 * @author ywx
 * @date 2020-12-24
 */
public class BaseActivity extends AppCompatActivity {

    private List<Disposable> mDisposables;

    public void addDisposable(Disposable disposable) {
        if (mDisposables == null) {
            mDisposables = new ArrayList<>();
        }
        mDisposables.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposables != null) {
            for (Disposable disposable : mDisposables) {
                if (disposable != null) {
                    disposable.dispose();
                }
            }
            mDisposables.clear();
            mDisposables = null;
        }
    }

}
