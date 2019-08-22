package com.cosine.cosplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cosine.cosplan.db.BigPlanDBHelper;
import com.cosine.cosplan.record.BigPlan;

public class BigPlanMemoActivity extends AppCompatActivity {

    EditText titleEdit;
    EditText typeEdit;
    EditText contentEdit;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_plan_memo);

        titleEdit = findViewById(R.id.TitleEdit);
        typeEdit = findViewById(R.id.TypeEdit);
        contentEdit = findViewById(R.id.ContentEdit);
        saveButton = findViewById(R.id.SaveButton);
        cancelButton = findViewById(R.id.CancelButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMemo();
                Intent intent = new Intent(BigPlanMemoActivity.this, BigPlansActivity.class);
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    void saveMemo(){
        BigPlan bigPlan = new BigPlan();
        bigPlan.setTitle(titleEdit.getText().toString());
        bigPlan.setType(typeEdit.getText().toString());
        bigPlan.setContent(contentEdit.getText().toString());
        BigPlanDBHelper dbHelper = new BigPlanDBHelper(this);
        dbHelper.insertData(bigPlan);
        System.out.println(dbHelper.getData());
    }
}
