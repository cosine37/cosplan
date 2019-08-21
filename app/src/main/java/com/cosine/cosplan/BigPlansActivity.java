package com.cosine.cosplan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cosine.cosplan.db.BigPlanDBHelper;

import java.util.List;

public class BigPlansActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton addButton;
    LinearLayout svll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_plans);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        svll = findViewById(R.id.svll);
        showAllBigPlans();
    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.addButton){
            newMemo();
        } else {

        }
    }

    public void showAllBigPlans() {
        BigPlanDBHelper dbHelper = new BigPlanDBHelper(this);
        List<String> titles = dbHelper.getAllTitles();
        TextView textView;
        Button viewButton;
        Button deleteButton;
        LinearLayout sll;
        LinearLayout bll;
        for (int i = 0; i < titles.size(); i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 120);

            textView = new TextView(this);
            textView.setText(titles.get(i));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

            viewButton = new Button(this);
            viewButton.setText("View");
            viewButton.setContentDescription(titles.get(i));
            viewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(BigPlansActivity.this, BigPlanViewActivity.class);
                    intent.putExtra("title", view.getContentDescription());
                    startActivity(intent);
                }
            });

            deleteButton = new Button(this);
            deleteButton.setText("Delete");
            deleteButton.setContentDescription(titles.get(i));
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String TTL = (String) view.getContentDescription();
                    AlertDialog.Builder dialog = new AlertDialog.Builder(BigPlansActivity.this);
                    dialog.setTitle("Delete");
                    dialog.setMessage("Are you sure you want to delete " + view.getContentDescription());
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            BigPlanDBHelper dbHelper = new BigPlanDBHelper(BigPlansActivity.this);
                            dbHelper.deletePlan(TTL);
                            Toast.makeText(BigPlansActivity.this, "Deleted " + TTL, Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            finish();
                            startActivity(getIntent());
                        }
                    });
                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog ad = dialog.create();
                    ad.show();
                }
            });

            bll = new LinearLayout(this);
            bll.setOrientation(LinearLayout.HORIZONTAL);
            bll.setLayoutParams(lp);
            bll.setGravity(Gravity.RIGHT);
            bll.addView(viewButton);
            bll.addView(deleteButton);

            sll = new LinearLayout(this);
            sll.setOrientation(LinearLayout.HORIZONTAL);
            sll.setLayoutParams(lp);
            sll.addView(textView);
            sll.addView(bll);

            svll.addView(sll);
        }
    }

    public void newMemo(){
        Intent intent = new Intent(BigPlansActivity.this, BigPlanMemoActivity.class);
        intent.putExtra("action", Constants.ADDMEMO);
        startActivity(intent);
    }

}
