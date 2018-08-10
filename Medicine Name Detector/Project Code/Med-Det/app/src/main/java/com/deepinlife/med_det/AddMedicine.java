package com.deepinlife.med_det;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMedicine extends AppCompatActivity {
  private EditText mname,description;
    private Button msuccess;
    private String MESSAGE_KEY1="nameofmedicine";
    private String MESSAGE_KEY2="descriptionaboutmedicine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

         mname=(EditText)findViewById(R.id.mname);
        description=(EditText)findViewById(R.id.description);
        msuccess=(Button)findViewById(R.id.msuccess);


        msuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mname.getText().toString().matches("") || description.getText().toString().matches(""))
                {
                    Toast.makeText(getApplicationContext(), "You did not enter text", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), ProcessText.class);
                    Bundle bun = new Bundle();
                    bun.putString(MESSAGE_KEY1, mname.getText().toString());
                    bun.putString(MESSAGE_KEY2, description.getText().toString());
                    intent.putExtras(bun);
                    mname.setText(null);
                    description.setText(null);
                    startActivity(intent);
                }

            }

        });

    }
}
