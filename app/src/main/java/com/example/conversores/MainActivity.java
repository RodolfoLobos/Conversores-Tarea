package com.example.conversores;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    TabHost tbh;
    TextView tempVal;
    Spinner spn;
    Button btn;
    conversores miObj = new conversores();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tbh = findViewById(R.id.tbhConversor);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("LONGITUD").setContent(R.id.Longitud).setIndicator("LONGITUD", null));
        tbh.addTab(tbh.newTabSpec("Area").setContent(R.id.Area).setIndicator("Area", null));

        btn = findViewById(R.id.btnConvertirLongitud);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeLongitud);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnALongitud);
                int a = spn.getSelectedItemPosition();


                tempVal = findViewById(R.id.txtCantidadLongitud);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double resp = miObj.convertir(0, de, a, cantidad);
                Toast.makeText(getApplicationContext(), "Respuesta: "+ resp, Toast.LENGTH_LONG).show();
            }
        });

        btn = findViewById(R.id.btnConvertirArea);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeArea);
                int de = spn.getSelectedItemPosition();
                spn = findViewById(R.id.spnAArea);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(R.id.txtCantidadArea);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double resp = miObj.convertir(1, de, a, cantidad);
                Toast.makeText(getApplicationContext(), "Respuesta: "+ resp, Toast.LENGTH_LONG).show();
            }
        });

    }
}

class conversores {
    double[][] valores = {
            {1,},
            {10.7639, 0.698796, 0.836127, 1, 628.8, 6474.9702758, 10000}, //Área
    };

    public double convertir(int opcion, int de, int a, double cantidad) {
        return valores[opcion][a] / valores[opcion][de] * cantidad;
}

    public static double calcularValorPagar ( int metrosConsumidos){
        double valorPagar = 0;

        if (metrosConsumidos >= 1 && metrosConsumidos <= 18) {
            valorPagar = 6;
        } else if (metrosConsumidos >= 19 && metrosConsumidos <= 28) {
            int exceso = metrosConsumidos - 18;
            valorPagar = 6 + (exceso * 0.45);
        } else if (metrosConsumidos >= 29) {
            int exceso28 = metrosConsumidos - 28;
            int exceso18 = 28 - 18;
            valorPagar = 6 + (exceso28 * 0.65) + (exceso18 * 0.45);
        }

        return valorPagar();
    }

    private static double valorPagar() {
        return 0;
    }


    public static void main (String[]args){
        int metrosConsumidos = 38;
        double valorAPagar = calcularValorPagar(metrosConsumidos);
        System.out.println("Valor a pagar: $" + valorAPagar);
        }
        }