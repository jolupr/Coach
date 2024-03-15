package com.example.coach.controleur;

import com.example.coach.modele.Profil;

public final class Controle {

    private static Controle instance = null;
    private static Profil profil;

    private Controle() {
        super();
    }

    public static final Controle getInstance(){
        if(Controle.instance == null){
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe){
        profil = new Profil (poids,taille, age, sexe);
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

}
