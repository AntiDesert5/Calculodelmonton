package com.example.calculodelmonton;
//AXEl Valenzuela Juarez

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Tablas extends AppCompatActivity {
    double totalmonton;
    int contDat = 1;
    int contTab = 1;
    double varV = 0;
    double varE = 0;
    double Num_Variable_Cols = 0;
    boolean check = true;
    EditText edi;
    ArrayList<Double> ArreVariables = new ArrayList<Double>();
    ArrayList<Double> ArreEstaticos = new ArrayList<Double>();
    LinearLayout line;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.edit1)
    EditText edit1;
    @BindView(R.id.buttonnext)
    ImageButton buttonnext;
    @BindView(R.id.buttonadd)
    ImageButton buttonadd;

    String[] datos = {"Integer", "Varchar", "Date", "Numeric"};
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.mostinfo)
    TextView mostinfo;
    @BindView(R.id.mostdat)
    TextView mostdat;
    @BindView(R.id.Resultado)
    TextView Resultado;
    @BindView(R.id.noFilas)
    EditText noFilas;
    @BindView(R.id.paracrear)
    LinearLayout paracrear;
    @BindView(R.id.ResTMonton)
    TextView ResTMonton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablas);
        ButterKnife.bind(this);
        spinner();
    }

    public void spinner() {

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos));


    }


    @OnClick({R.id.buttonnext, R.id.buttonadd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonnext:
                if (edit1.getText().toString().isEmpty() || noFilas.getText().toString().isEmpty()) {
                    Toast.makeText(Tablas.this, "Rellene todos los campos", Toast.LENGTH_LONG).show();
                } else {
                    spinner.setEnabled(false);
                    edit1.setEnabled(false);
                    String dat = (spinner.getSelectedItem().toString().trim());
                    switch (dat) {
                        case "Integer":
                            edit1.setText("n/a");
                            System.out.println("funciona case 1");
                            ArreEstaticos.add(4.0);
                            varE += 4.0;
                            break;
                        case "Varchar":
                            Num_Variable_Cols++;
                            System.out.println("funciona case 2");
                            String au = edit1.getText().toString().trim();
                            double doble = Double.parseDouble(au);
                            ArreVariables.add(1 + doble);
                            varV += 1 + doble;
                            break;
                        case "Date":
                            edit1.setText("n/a");
                            System.out.println("funciona case 3");
                            ArreEstaticos.add(3.0);
                            varE += 3.0;
                            break;
                        case "Numeric":
                            Num_Variable_Cols++;
                            System.out.println("funciona case 4");
                            String au1 = edit1.getText().toString().trim();
                            double doble1 = Double.parseDouble(au1);
                            ArreVariables.add(2 + doble1);
                            varV += 2 + doble1;
                            break;
                    }


                    //aqui
                    String Snfilas = noFilas.getText().toString().trim();
                    double nfilas = Double.parseDouble(Snfilas);
                    int Num_Cols = contDat;
                    double Fixed_Data_Size = varE;
                    //Num_Variable_Cols esta declarado solo mandar a llamar
                    double Max_Var_Size = varV;
                    int nullmap = (int) 2 + ((Num_Cols + 7) / 8);
                    double Variable_Data_Size = 2 + (Num_Variable_Cols * 2) + Max_Var_Size;//
                    double Row_size = Fixed_Data_Size + Variable_Data_Size + nullmap + 4;
                    double Rows_per_page = (8096) / (Row_size + 2);
                    double redondea = Math.round(Rows_per_page);
                    double redondeado = nfilas / redondea;
                    long Num_pages = (int) Math.rint(redondeado);
                    double monton = (8192) * Num_pages;
                    Resultado.setText("Valor longitud fija: " + varE + "  Valor longitud variables: " + varV + "  Montón tabla actual: " + monton);
                    totalmonton += monton;
                    ResTMonton.setText("El total del montón al momento es: " + totalmonton);
                    Snfilas = null;
                    nfilas = 0;
                    Num_Cols = 0;
                    Fixed_Data_Size = 0;
                    Num_Variable_Cols = 0;
                    Max_Var_Size = 0;

//

                    contTab++;
                    mostinfo.setText("Tabla Numero: " + contTab);


                    paracrear.removeAllViews();
                    noFilas.setText("");


                    contDat = 0;
                    //edit1.setText(spinner.getSelectedItem().toString());

                    //for (int i = 0; i <= ArreVariables.size(); i++){
                    //}
                /*Iterator<Double> nombreIterator = ArreVariables.iterator();
                while (nombreIterator.hasNext()) {
                    double elemento = nombreIterator.next();
                    System.out.print("algo: " + elemento + " / ");
                    Resultado.setText(elemento+",");
                }*/

                    //*******
                    contDat++;

                    mostdat.setText("Numero de datos: " + contDat);
                    line = new LinearLayout(Tablas.this);
                    spinner = new Spinner(Tablas.this);
                    edit1 = new EditText(Tablas.this);
                    edit1.setHint("Tamaño");
                    spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos));
                    spinner.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                    spinner.setContentDescription("spinerbueno" + contDat);
                    edit1.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                    edit1.setInputType(InputType.TYPE_CLASS_NUMBER);
                    line.addView(spinner);
                    line.addView(edit1);
                    paracrear.addView(line);


                }
                break;
            case R.id.buttonadd:
                if (edit1.getText().toString().isEmpty()) {
                    edit1.setText("1");
                }
                spinner.setEnabled(false);
                edit1.setEnabled(false);

                String dat = (spinner.getSelectedItem().toString().trim());
                switch (dat) {
                    case "Integer":
                        edit1.setText("n/a");
                        System.out.println("funciona case 1");
                        ArreEstaticos.add(4.0);
                        varE += 4.0;
                        break;
                    case "Varchar":
                        Num_Variable_Cols++;
                        String au2 = edit1.getText().toString().trim();
                        int int1 = Integer.parseInt(au2);
                        if (int1 == 0) {
                            edit1.setText("1");
                        }
                        System.out.println("funciona case 2");
                        String au = edit1.getText().toString().trim();
                        double doble = Double.parseDouble(au);
                        ArreVariables.add(1 + doble);
                        varV += 1 + doble;
                        break;
                    case "Date":
                        edit1.setText("n/a");
                        System.out.println("funciona case 3");
                        ArreEstaticos.add(3.0);
                        varE += 3.0;
                        break;
                    case "Numeric":
                        Num_Variable_Cols++;
                        String au3 = edit1.getText().toString().trim();
                        int int3 = Integer.parseInt(au3);
                        if (int3 == 0) {
                            edit1.setText("1");
                        }
                        System.out.println("funciona case 4");
                        String au1 = edit1.getText().toString().trim();
                        double doble1 = Double.parseDouble(au1);
                        ArreVariables.add(2 + doble1);
                        varV += 2 + doble1;
                        break;
                }

//                if (edit1.getText().toString().isEmpty() || noFilas.getText().toString().isEmpty()) {
//                    Toast.makeText(Tablas.this, "Rellene todos los campos", Toast.LENGTH_LONG).show();
//                } else {
                contDat++;

                mostdat.setText("Numero de datos: " + contDat);
                line = new LinearLayout(Tablas.this);
                spinner = new Spinner(Tablas.this);
                edit1 = new EditText(Tablas.this);
                edit1.setHint("Tamaño");
                spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos));
                spinner.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                spinner.setContentDescription("spinerbueno" + contDat);
                edit1.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                edit1.setInputType(InputType.TYPE_CLASS_NUMBER);
                line.addView(spinner);
                line.addView(edit1);
                paracrear.addView(line);
                // }

                break;
        }
    }

}
