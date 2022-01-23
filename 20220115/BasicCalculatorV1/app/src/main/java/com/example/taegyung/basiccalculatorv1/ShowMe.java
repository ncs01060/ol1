package com.example.taegyung.basiccalculatorv1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by TaeGyung on 2021-12-26.
 */

public class ShowMe extends AppCompatActivity {

    Button b1;

    protected void onCreate(Bundle saveedInstanceState) {

        super.onCreate(saveedInstanceState);
        setContentView(R.layout.sub1);

        b1 = (Button)findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // 현재 실행중인 Activity를 종료한다.
                finish();
            }
        });
    }
}
