package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {

    private TextView textView1, textView2, textView3, textView4;
    private Button sumButton, productButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_secondary);

        // Inițializare câmpuri text și butoane
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        sumButton = findViewById(R.id.sumButton);
        productButton = findViewById(R.id.productButton);

        // Preluarea valorilor trimise din activitatea principală
        int value1 = Integer.parseInt(getIntent().getStringExtra("text1"));
        int value2 = Integer.parseInt(getIntent().getStringExtra("text2"));
        int value3 = Integer.parseInt(getIntent().getStringExtra("text3"));
        int value4 = Integer.parseInt(getIntent().getStringExtra("text4"));

        // Setarea valorilor în TextView-uri
        textView1.setText(String.valueOf(value1));
        textView2.setText(String.valueOf(value2));
        textView3.setText(String.valueOf(value3));
        textView4.setText(String.valueOf(value4));

        // Setare acțiune pentru butonul "Sum"
        sumButton.setOnClickListener(view -> {
            int sum = value1 + value2 + value3 + value4;
            Intent resultIntent = new Intent();
            resultIntent.putExtra("result", "Sum: " + sum);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Setare acțiune pentru butonul "Product"
        productButton.setOnClickListener(view -> {
            int product = value1 * value2 * value3 * value4;
            Intent resultIntent = new Intent();
            resultIntent.putExtra("result", "Product: " + product);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
