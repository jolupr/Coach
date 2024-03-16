package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;
import com.example.coach.vue.MainActivity;

import org.json.JSONObject;

import java.util.Date;

public final class Controle {

    private static Controle instance = null;
    private static Profil profil;
    private static Context contexte;

    private static String nomFic = "saveprofil";

    //private AccesLocal accesLocal;

    private static AccesDistant accesDistant;

    private Controle(Context contexte) {
        if(contexte != null) {
            Controle.contexte = contexte;
        }
        //recupSerialize(contexte);

        //accesLocal = AccesLocal.getInstance(contexte);
        //profil = accesLocal.recupDernier();
    }


    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance(Context contexte) {
        if(instance == null){
            instance = new Controle(contexte);
            accesDistant = AccesDistant.getInstance();
            accesDistant.envoi("dernier", new JSONObject());
        }
        return instance;
    }

    /**
     * Création du profil par rapport aux informations saisies
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        profil = new Profil(new Date(), poids, taille, age, sexe);
        accesDistant.envoi("enreg", profil.convertToJSONObject());
//        accesLocal.ajout(profil);
//        Serializer.serialize(nomFic, profil, context);
    }

    public void setProfil(Profil profil){
        Controle.profil = profil;
        ((MainActivity)contexte).recupProfil();
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
