package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class searchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText query = findViewById(R.id.query);
        final EditText category = findViewById(R.id.category);
        final Button button = findViewById(R.id.btnSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = query.getText().toString().trim();
                String type = category.getText().toString().trim();
                if(search.isEmpty() && type.isEmpty()){
                    String searchError = getString(R.string.no_search_data);
                    Toast.makeText(getApplicationContext(), searchError, Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(searchActivity.this, SearchResultActivity.class);
                    intent.putExtra("category", type);
                    intent.putExtra("query", search);
                    startActivity(intent);
                }
            }
        });
    }
}