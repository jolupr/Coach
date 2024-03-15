package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

import java.util.Date;

public final class Controle {

    private static Controle instance = null;
    private static Profil profil;

    private static String nomFic = "saveprofil";

    private AccesLocal accesLocal;

    private Controle(Context contexte) {
        super();
        //recupSerialize(contexte);

        accesLocal = AccesLocal.getInstance(contexte);
        profil = accesLocal.recupDernier();
    }

    public static final Controle getInstance(Context contexte){
        if(Controle.instance == null){
            Controle.instance = new Controle(contexte);
        }
        return Controle.instance;
    }

    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte){
        profil = new Profil (new Date(), poids,taille, age, sexe);
        //Serializer.serialize(nomFic, profil, contexte);

        accesLocal.ajout(profil);
    }

    private static void recupSerialize(Context contexte){
        profil = (Profil)Serializer.deSerialize(nomFic, contexte);
    }

    public float getImg(){
        if(profil != null) {
            return profil.getImg();
        }else{
            return 0;
        }
    }

    public String getMessage(){
        if(profil != null){
            return profil.getMessage();
        }else{
            return "";
        }
    }

    public Integer getTaille(){
        if(profil != null) {
            return profil.getTaille();
        }else{
            return null;
        }
    }

    public Integer getPoids(){
        if(profil != null) {
            return profil.getPoids();
        }else{
            return null;
        }
    }

    public Integer getAge(){
        if(profil != null) {
            return profil.getAge();
        }else{
            return null;
        }
    }

    public Integer getSexe(){
        if(profil != null) {
            return profil.getSexe();
        }else{
            return null;
        }
    }

}
