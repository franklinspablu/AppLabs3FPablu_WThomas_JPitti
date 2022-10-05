package com.example.applabs3fpablu_wthomas_jpitti;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SumaActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaramos las variables de los controles que vamos a utilizar
    EditText txtNum1;
    EditText txtNum2;
    TextView lblResultado;
    Spinner spinner;
    Button btnCalcular;
    CheckBox cb;
    double resultado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.InicializarControles();

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operaciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        btnCalcular.setOnClickListener((View.OnClickListener) this);
    }

    private void InicializarControles(){
        txtNum1 = (EditText) findViewById(R.id.txtNum1);
        txtNum2 = (EditText) findViewById(R.id.txtNum2);
        lblResultado = (TextView)findViewById(R.id.lblRes);
        btnCalcular = (Button) findViewById(R.id.btnCalculo);
        cb = (CheckBox) findViewById(R.id.cbDialog);
    }


    @Override
    public void onClick(View v) {
        try{
            double n1 = Integer.parseInt(txtNum1.getText().toString());
            int n2 = Integer.parseInt(txtNum2.getText().toString());
            String operacion = spinner.getSelectedItem().toString();

            if(operacion.equals("suma")) resultado = n1 + n2;
            if(operacion.equals("resta")) resultado = n1 - n2;
            if(operacion.equals("multiplicacion")) resultado = n1 * n2;
            if(operacion.equals("division")) resultado = n1 / n2;

            if (v.getId()==R.id.btnCalculo){
                validacion();
            }


            lblResultado.setText(Double.toString(resultado));
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Error en el calculo de los datos" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    private void validacion(){
        String res = "Resultado: ";

        if (cb.isChecked()==true){

            mostrarDialogo();

        }

        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();

    }

    public void mostrarDialogo(){

        AlertDialog.Builder builder = new AlertDialog.Builder(SumaActivity.this);
        builder.setTitle("Resultado");
        builder.setMessage(Double.toString(resultado))
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Todos los datos",Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Cancelar",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                }).show();


    }
}
