package com.cosine.cosplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cosine.cosplan.db.BigPlanDBHelper;
import com.cosine.cosplan.record.BigPlan;

public class BigPlanViewActivity extends AppCompatActivity {

    TextView titleText;
    TextView typeText;
    TextView contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_plan_view);

        String title = getIntent().getStringExtra("title");
        BigPlanDBHelper dbHelper = new BigPlanDBHelper(this);
        BigPlan bigPlan = dbHelper.getPlan(title);
        titleText = findViewById(R.id.titleText);
        typeText = findViewById(R.id.typeText);
        contentText = findViewById(R.id.contentText);
        titleText.setText(bigPlan.getTitle());
        typeText.setText(bigPlan.getType());
        contentText.setText(bigPlan.getContent());
    }
}
