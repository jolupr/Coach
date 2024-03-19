package com.example.coach.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    private Controle controle;
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

    private void init(){
        controle = Controle.getInstance();
        creerMenu();
    }

    private void creerMenu(){
        ecouteMenu((ImageButton)findViewById(R.id.btnMonIMG), CalculActivity.class);
        ecouteMenu((ImageButton)findViewById(R.id.btnMonHistorique), HistoActivity.class);
    }

    /**
     *
     * Met en place une écoute événementielle sur une image
     * @param btn l'image "bouton" concernée
     * @param classe la classe correspondant à l'activity à ouvrir sur le clic du bouton
    */
    private void ecouteMenu(ImageButton btn, Class classe) {
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, classe);
                startActivity(intent);
            }
        });
    }
}