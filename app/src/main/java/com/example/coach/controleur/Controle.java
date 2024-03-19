package com.example.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;
import com.example.coach.vue.CalculActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public final class Controle {

    private static Controle instance;
    //private static Profil profil;
    private static String nomFic = "saveprofil";
    private static AccesDistant accesDistant;
    private ArrayList<Profil> lesProfils = new ArrayList<Profil>();

    private Controle() {
        super();
        //recupSerialize(contexte);

        //accesLocal = AccesLocal.getInstance(contexte);
        //profil = accesLocal.recupDernier();
    }


    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance() {
        if(instance == null){
            instance = new Controle();
            accesDistant = AccesDistant.getInstance();
            accesDistant.envoi("tous", new JSONObject());
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
        Profil profil = new Profil(new Date(), poids, taille, age, sexe);
        lesProfils.add(profil);
        accesDistant.envoi("enreg", profil.convertToJSONObject());
//        accesLocal.ajout(profil);
//        Serializer.serialize(nomFic, profil, context);
    }

    public void delProfil(Profil profil){
        accesDistant.envoi("suppr", profil.convertToJSONObject());
        Log.d("profil", "************** profil json = "+profil.convertToJSONObject());
        lesProfils.remove(profil);
    }

    /**public void setProfil(Profil profil){
        Controle.profil = profil;
        ((CalculActivity)contexte).recupProfil();
    }

    private static void recupSerialize(Context contexte){
        profil = (Profil)Serializer.deSerialize(nomFic, contexte);
    }*/

    public float getImg(){
        if(lesProfils.size() > 0) {
            return lesProfils.get(lesProfils.size()-1).getImg();
        }else{
            return 0;
        }
    }

    public String getMessage(){
        if(lesProfils.size() > 0) {
            return lesProfils.get(lesProfils.size()-1).getMessage();
        }else{
            return "";
        }
    }


    public ArrayList<Profil> getLesProfils() {
        return lesProfils;
    }

    public void setLesProfils(ArrayList<Profil> lesProfils) {
        this.lesProfils = lesProfils;
    }
}
