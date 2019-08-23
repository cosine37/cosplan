package com.cosine.cosplan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cosine.cosplan.db.BigPlanDBHelper;
import com.cosine.cosplan.record.BigPlan;

public class BigPlanViewActivity extends AppCompatActivity {

    TextView titleText;
    TextView typeText;
    TextView contentText;

    Button editTypeButton;
    Button editContentButton;
    Button backButton;
    Button deleteButton;

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

        editTypeButton = findViewById(R.id.EditTypeButton);
        editContentButton = findViewById(R.id.EditContentButton);
        backButton = findViewById(R.id.BackButon);
        deleteButton = findViewById(R.id.DeleteButton);

        final String TITLE = bigPlan.getTitle();
        final String TYPE = bigPlan.getType();
        final String CONTENT = bigPlan.getContent();
        final EditText ET = new EditText(this);
        final EditText CT = new EditText(this);
        CT.setHeight(400);

        editTypeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(BigPlanViewActivity.this);
                dialog.setTitle("Type");
                ET.setText(TYPE);
                dialog.setView(ET);
                dialog.setCancelable(true);
                dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BigPlanDBHelper dbHelper = new BigPlanDBHelper(BigPlanViewActivity.this);
                        dbHelper.updateData(TITLE, BigPlanDBHelper.TYPE, ET.getText().toString());
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

        editContentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(BigPlanViewActivity.this);
                dialog.setTitle("Type");
                CT.setText(CONTENT);
                dialog.setView(CT);
                dialog.setCancelable(true);
                dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BigPlanDBHelper dbHelper = new BigPlanDBHelper(BigPlanViewActivity.this);
                        dbHelper.updateData(TITLE, BigPlanDBHelper.CONTENT, CT.getText().toString());
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

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(BigPlanViewActivity.this, BigPlansActivity.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(BigPlanViewActivity.this);
                dialog.setTitle("Delete");
                dialog.setMessage("Are you sure you want to delete "+ TITLE + "?");
                dialog.setCancelable(true);
                dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BigPlanDBHelper dbHelper = new BigPlanDBHelper(BigPlanViewActivity.this);
                        dbHelper.deletePlan(TITLE);
                        dialog.dismiss();
                        Toast.makeText(BigPlanViewActivity.this, "Deleted " + TITLE, Toast.LENGTH_LONG).show();
                        finish();
                        Intent intent = new Intent(BigPlanViewActivity.this, BigPlansActivity.class);
                        startActivity(intent);
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

    }
}
