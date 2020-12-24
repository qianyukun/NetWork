package com.qisiemoji.network;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qisiemoji.network.api.Api;
import com.qisiemoji.network.bean.Sound;
import com.kika.network.RetrofitManager;
import com.kika.network.exception.ApiException;
import com.kika.network.observer.CustomObserver;
import com.kika.network.rx.TransformUtils;

import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fetchKika).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchKika();
            }
        });
        findViewById(R.id.fetchTenor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchTenor();
            }
        });


    }

    private void fetchKika() {
        RetrofitManager.getInstance().create(Api.class)
            .fetchKika()
            .compose(TransformUtils.<Sound>defaultSchedulers())
            .subscribe(new CustomObserver<Sound>() {
                @Override
                public void onError(ApiException e) {
                    Toast.makeText(getApplicationContext(), e.getDisplayMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void success(Sound sound) {

                }

                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }
            });
    }

    private void fetchTenor() {
        RetrofitManager.getInstance().create(Api.class)
            .fetchTenor()
            .compose(TransformUtils.<Sound>defaultSchedulers())
            .subscribe(new CustomObserver<Sound>() {
                @Override
                public void onError(ApiException e) {
                    Toast.makeText(getApplicationContext(), e.getDisplayMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void success(Sound sound) {

                }

                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }
            });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}