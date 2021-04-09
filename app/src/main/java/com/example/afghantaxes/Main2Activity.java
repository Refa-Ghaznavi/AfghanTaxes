package com.example.afghantaxes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Main2Activity extends AppCompatActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_main2);
        this.setSupportActionBar((Toolbar)this.findViewById(R.id.afTaxes));
        ((FloatingActionButton)this.findViewById(R.id.floating)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Snackbar.make((View)view, (CharSequence)"Replace with your own action", (int)0).setAction((CharSequence)"Action", null).show();
            }
        });
    }

}