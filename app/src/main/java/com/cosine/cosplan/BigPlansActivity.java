package com.cosine.cosplan;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BigPlansActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_plans);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.addButton){
            newMemo();
        } else {

        }
    }

    public void newMemo(){
        /*
        AlertDialog.Builder dialog = new AlertDialog.Builder(BigPlansActivity.this);
        dialog.setTitle("New Dialog");
        dialog.setMessage("Message");
        dialog.setCancelable(true);

        AlertDialog ad = dialog.create();
        ad.show();
        */
        Intent intent = new Intent(BigPlansActivity.this, BigPlanMemoActivity.class);
        intent.putExtra("action", Constants.ADDMEMO);
        startActivity(intent);
    }

}
