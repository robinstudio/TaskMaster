package com.android.dongnan.taskmaster;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.container,
                new MainFragment()).commit();

        View view = findViewById(R.id.container);
        view.setOnClickListener(v->{});

    }
}
