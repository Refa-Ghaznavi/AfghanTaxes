package com.example.afghantaxes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class TaxBreakActivity extends AppCompatActivity {
    public static final double[] AMOUNTS = new double[]{100000.0, 12500.0, 5000.0};
    public static final double AMOUNT_5000 = 5000.0;
    private static final DecimalFormat Decimalformatter = new DecimalFormat("#,###,###");
    public static final double[] PERCENTS = new double[]{0.2, 0.1, 0.02};
    LinearLayout[] linearLayouts;
    TextView[] textViews;
    TextView tvTotal;

    public double calculateTax(double d) {
        int n;
        double[] arrd;
        if (d <= 5000.0) {
            return 0.0;
        }
        for (n = 0; n < (arrd = AMOUNTS).length && d <= arrd[n]; ++n) {
        }
        double d2 = (d - AMOUNTS[n]) * PERCENTS[n];
        this.linearLayouts[n].setVisibility(View.INVISIBLE);
        this.textViews[n].setText((CharSequence)Decimalformatter.format(d2));
        return d2 + this.calculateTax(AMOUNTS[n]);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_tax_break);
        this.linearLayouts = new LinearLayout[4];
        this.textViews = new TextView[4];
        this.linearLayouts[3] = (LinearLayout)this.findViewById(R.id.linearLayout3);
        this.textViews[3] = (TextView)this.findViewById(R.id.textView3);
        this.linearLayouts[2] = (LinearLayout)this.findViewById(R.id.linearLayout2);
        this.textViews[2] = (TextView)this.findViewById(R.id.textView2);
        this.linearLayouts[1] = (LinearLayout)this.findViewById(R.id.linearLayout1);
        this.textViews[1] = (TextView)this.findViewById(R.id.textView1);
        this.linearLayouts[0] = (LinearLayout)this.findViewById(R.id.linearLayout0);
        this.textViews[0] = (TextView)this.findViewById(R.id.textView0);
        this.tvTotal = (TextView)this.findViewById(R.id.tvTotal);
        Intent intent = this.getIntent();
        if (intent != null) {
            TextView textView = this.tvTotal;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Decimalformatter.format(this.calculateTax(intent.getIntExtra("amount", 0))));
            stringBuilder.append(" AFN");
            textView.setText((CharSequence)stringBuilder.toString());
        }
    }
}

