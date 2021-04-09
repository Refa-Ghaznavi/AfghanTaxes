package com.example.afghantaxes;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {
    public static final double[] AMOUNTS;
    public static final double AMOUNT_5000 = 5000.0;
    private static final DecimalFormat Decimalformatter;
    public static final double[] PERCENTS;
    EditText editText_originalAmount;
    boolean isManualChange = false;
    ImageView ivQuestion;
    TextView tv_calculatedTax;
    TextView tv_finalAmount;

    static {
        Decimalformatter = new DecimalFormat("#,###");
        PERCENTS = new double[]{0.2, 0.1, 0.02};
        AMOUNTS = new double[]{100000.0, 12500.0, 5000.0};
    }

    public static double calculateTax(double d) {
        double[] arrd;
        int n;
        if (d <= 5000.0) {
            return 0.0;
        }
        for (n = 0; n < (arrd = AMOUNTS).length && !(d > arrd[n]); ++n) {
        }
        double[] arrd2 = AMOUNTS;
        return (d - arrd2[n]) * PERCENTS[n] + MainActivity.calculateTax(arrd2[n]);
    }

    private void showBreakDownDialog() {
        double d = 0.0;
        block3 : {
            String string2 = this.editText_originalAmount.getText().toString();
            if (string2.isEmpty()) {
                return;
            }
            try {
                d = Double.valueOf((String)string2);
                if (d != 0.0) break block3;
                return;
            }
            catch (NumberFormatException numberFormatException) {}
        }
        Intent intent = new Intent(MainActivity.this, TaxBreakActivity.class);
        intent.putExtra("amount", (int)d);
        this.startActivity(intent);
    }

    public void afterTextChanged(Editable editable) {
        double d;
        String string2 = editable.toString();
        if (string2.isEmpty()) {
            this.tv_calculatedTax.setText((CharSequence)"");
            this.tv_finalAmount.setText((CharSequence)"");
            this.ivQuestion.setVisibility(View.INVISIBLE);
            return;
        }
        try {
            d = Double.valueOf((String)string2);
        }
        catch (NumberFormatException numberFormatException) {
            this.tv_calculatedTax.setText((CharSequence)"");
            this.tv_finalAmount.setText((CharSequence)"");
            this.ivQuestion.setVisibility(View.VISIBLE);
            return;
        }
        int n = (int)MainActivity.calculateTax(d);
        int n2 = (int)d - n;
        TextView textView = this.tv_calculatedTax;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((Object)Decimalformatter.format((long)n));
        stringBuilder.append(" AFN");
        textView.setText((CharSequence)stringBuilder.toString());
        TextView textView2 = this.tv_finalAmount;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append((Object)Decimalformatter.format((long)n2));
        stringBuilder2.append(" AFN");
        textView2.setText((CharSequence)stringBuilder2.toString());
        if (n == 0) {
            this.ivQuestion.setVisibility(View.INVISIBLE);
            return;
        }
        this.ivQuestion.setVisibility(View.VISIBLE);
    }

    public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
    }

    public void onBackPressed() {
        new AlertDialog.Builder((Context)this).setMessage((CharSequence)"Are you sure you want to exit?").setCancelable(false).setPositiveButton((CharSequence)"Yes", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                MainActivity.super.onBackPressed();
            }
        }).setNegativeButton((CharSequence)"No", null).show();
    }

    public void onClick(View view) {
        if (view.getId() != 0) {
            return;
        }
        this.showBreakDownDialog();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.getWindow().setSoftInputMode(3);
        this.setContentView(R.layout.activity_main);
        this.editText_originalAmount = (EditText)this.findViewById(R.id.editText_originalAmount);
        this.tv_calculatedTax = (TextView)this.findViewById(R.id.tv_calculatedTax);
        this.tv_finalAmount = (TextView)this.findViewById(R.id.tv_finalAmount);
        this.ivQuestion = (ImageView)this.findViewById(R.id.ivQuestion);
        this.editText_originalAmount.addTextChangedListener((TextWatcher)this);
        this.editText_originalAmount.setText((CharSequence)String.valueOf((int)20000));
        this.ivQuestion.setOnClickListener((View.OnClickListener)this);
    }

    public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {

    }

}

