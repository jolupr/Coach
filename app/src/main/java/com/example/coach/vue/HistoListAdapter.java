package com.example.coach.vue;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;
import com.example.coach.outils.MesOutils;
import java.util.ArrayList;

public class HistoListAdapter extends RecyclerView.Adapter<HistoListAdapter.ViewHolder>{

    private Context contexte;
    private ArrayList<Profil> lesProfils;

    public HistoListAdapter(Context contexte, ArrayList<Profil> lesProfils) {
        this.lesProfils = lesProfils;
        this.contexte = contexte;
    }


    @NonNull
    @Override
    public HistoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context parentContext = parent.getContext();
        LayoutInflater layout = LayoutInflater.from(parentContext);
        View view = layout.inflate(R.layout.layout_liste_histo, parent, false);
        return new HistoListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HistoListAdapter.ViewHolder holder, int position) {
        holder.txtListDate.setText(MesOutils.convertDateToString(lesProfils.get(position).getDateMesure()));
        holder.txtListIMG.setText(MesOutils.format2Decimal(lesProfils.get(position).getImg()));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return lesProfils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView txtListDate, txtListIMG;
        public final ImageButton btnListSuppr;

        /**
         * Constructeur : crée un lien avec les objets graphiques de la ligne
         * et gère les événements sur ces objets graphiques
         * @param itemView
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtListDate = (TextView) itemView.findViewById(R.id.txtListDate);
            txtListIMG = (TextView) itemView.findViewById(R.id.txtListIMG);
            btnListSuppr = (ImageButton) itemView.findViewById(R.id.btnListSuppr);
            btnListSuppr.setOnClickListener((new View.OnClickListener() {
                public void onClick(View v) {
                    Controle controle = Controle.getInstance();
                    controle.delProfil(lesProfils.get(getAdapterPosition()));
                    notifyDataSetChanged();
                }
            }));

            txtListDate.setOnClickListener((new View.OnClickListener() {
                public void onClick(View v) {
                    ((HistoActivity)contexte).afficheProfil(lesProfils.get(getAdapterPosition()));
                }
            }));
            txtListIMG.setOnClickListener((new View.OnClickListener() {
                public void onClick(View v) {
                    ((HistoActivity)contexte).afficheProfil(lesProfils.get(getAdapterPosition()));
                }
            }));
        }
    }
}
