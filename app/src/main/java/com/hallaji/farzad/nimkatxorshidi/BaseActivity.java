package com.hallaji.farzad.nimkatxorshidi;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hallaji.farzad.nimkatxorshidi.util.LocaleHelper;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
    }

    protected abstract int getLayout();


}
