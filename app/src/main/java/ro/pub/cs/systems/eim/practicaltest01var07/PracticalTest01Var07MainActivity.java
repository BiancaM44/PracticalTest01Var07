package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {

    private EditText editText1, editText2, editText3, editText4;
    private Button setButton;
    private static final int REQUEST_CODE = 1;
    private static final String TAG = "PracticalTest01Var07";

    // Variabile pentru suma și produsul
    private int sum = 0;
    private int product = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);

        // Inițializarea câmpurilor text și butonului
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        setButton = findViewById(R.id.setButton);

        // Setarea acțiunii pentru butonul "Set"
        setButton.setOnClickListener(view -> {
            // Verificăm dacă toate câmpurile conțin numere valide
            if (isNumeric(editText1.getText().toString()) &&
                    isNumeric(editText2.getText().toString()) &&
                    isNumeric(editText3.getText().toString()) &&
                    isNumeric(editText4.getText().toString())) {

                // Obținem valorile numerice
                int value1 = Integer.parseInt(editText1.getText().toString());
                int value2 = Integer.parseInt(editText2.getText().toString());
                int value3 = Integer.parseInt(editText3.getText().toString());
                int value4 = Integer.parseInt(editText4.getText().toString());

                // Calculăm suma și produsul
                sum = value1 + value2 + value3 + value4;
                product = value1 * value2 * value3 * value4;

                // Afișăm suma și produsul în Toast
                Toast.makeText(this, "Sum: " + sum + ", Product: " + product, Toast.LENGTH_LONG).show();

                // Crearea unui intent pentru activitatea secundară
                Intent intent = new Intent(this, PracticalTest01Var07SecondaryActivity.class);
                intent.putExtra("text1", String.valueOf(value1));
                intent.putExtra("text2", String.valueOf(value2));
                intent.putExtra("text3", String.valueOf(value3));
                intent.putExtra("text4", String.valueOf(value4));

                // Pornirea activității secundare
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                Toast.makeText(this, "Introdu doar numere în toate câmpurile!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Salvarea stării activității
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("sum", sum);
        outState.putInt("product", product);
        Log.d(TAG, "Starea a fost salvată: Sum = " + sum + ", Product = " + product);
    }

    // Restaurarea stării activității
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            sum = savedInstanceState.getInt("sum");
            product = savedInstanceState.getInt("product");
            Toast.makeText(this, "Restored: Sum = " + sum + ", Product = " + product, Toast.LENGTH_LONG).show();
            Log.d(TAG, "Starea a fost restaurată: Sum = " + sum + ", Product = " + product);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String result = data.getStringExtra("result");
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                Log.d(TAG, "Rezultatul primit: " + result);
            }
        } else {
            Log.d(TAG, "Activitatea secundară nu a returnat un rezultat valid.");
        }
    }

    // Metodă auxiliară pentru a verifica dacă un text este numeric
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
