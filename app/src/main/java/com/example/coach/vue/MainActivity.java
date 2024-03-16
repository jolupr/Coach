package com.example.coach.vue;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    private EditText txtPoids, txtTaille, txtAge;
    private RadioButton rdHomme;

    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;

    private Controle controle;

    private void init(){
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        btnCalc = (Button) findViewById(R.id.btnCalc);

        controle = Controle.getInstance(this);

        ecouteCalcul();
        //recupProfil();
    }

    private void ecouteCalcul(){
        btnCalc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Integer poids = 0, taille = 0, age = 0;
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e){}
                Integer sexe = 0;
                if(rdHomme.isChecked()){
                    sexe = 1;
                }
                if(taille == 0 || poids == 0 || age == 0){
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                }else{
                    affichResult(poids, taille, age, sexe);
                }

            }
        });
    }

    private void affichResult(Integer poids, Integer taille, Integer age, Integer sexe){
        controle.creerProfil(poids, taille, age, sexe);
        String message = controle.getMessage();
        float img = controle.getImg();
        switch(message){
            case "normal":
                imgSmiley.setImageResource(R.drawable.normal);
                lblIMG.setTextColor(Color.GREEN);
                break;
            case "trop faible":
                imgSmiley.setImageResource(R.drawable.maigre);
                lblIMG.setTextColor(Color.RED);
                break;
            case "trop élevé":
                imgSmiley.setImageResource(R.drawable.graisse);
                lblIMG.setTextColor(Color.RED);
                break;
        }
        lblIMG.setText(String.format("%.01f", img)+" : IMG "+message);
    }

    public void recupProfil(){
        if(controle.getTaille()!= null){
            txtPoids.setText(controle.getPoids().toString());
            txtTaille.setText(controle.getTaille().toString());
            txtAge.setText(controle.getAge().toString());
            if(controle.getSexe() == 0){
                rdFemme.setChecked(true);
            }else{
                rdHomme.setChecked(true);
            }
            btnCalc.performClick();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

    }
}