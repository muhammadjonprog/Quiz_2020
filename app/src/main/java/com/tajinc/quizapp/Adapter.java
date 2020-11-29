package com.tajinc.quizapp;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

 class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<String> Name;
    private List<Integer> logo;
    private LayoutInflater inflater;

 public Adapter (Context context,List<String> Name,List<Integer> logo) {
        this.Name = Name;
        this.logo = logo;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid_layout,parent,false);
        return new ViewHolder(view);

         }
         @Override
         public void onBindViewHolder(@NonNull final ViewHolder holder , final int position) {
        holder.title.setText(Name.get(position));
        holder.gridIcon.setImageResource(logo.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (position) {
                    case 0:
                        Intent coding = new Intent(view.getContext(), ProgrammerActivity.class);
                        view.getContext().startActivity(coding);
                        break;

                    case 1:
                        Intent sport = new Intent(view.getContext(), SportActivity.class);
                        view.getContext().startActivity(sport);
                        break;
                    case 2:
                       Intent geography = new Intent(view.getContext(), GeographyActivity.class);
                       view.getContext().startActivity(geography);
                        break;
                    case 3:
                        Intent movie = new Intent(view.getContext(), MovieActivity.class);
                        view.getContext().startActivity(movie);
                        break;
                    case 4:
                        Intent astronomy= new Intent(view.getContext(), AstronomyActivity.class);
                        view.getContext().startActivity(astronomy);
                        break;
                    case 5:
                        Intent history = new Intent(view.getContext(), HistoryActivity.class);
                        view.getContext().startActivity(history);
                    }
            }
        });
         }
    @Override
        public int getItemCount() {
        return Name.size();
        }
   static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView gridIcon;
        CardView cardView;
       ViewHolder(@NonNull final View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.textname);
            gridIcon = itemView.findViewById(R.id.logo);
            cardView = itemView.findViewById(R.id.mycard);
       }
    }
}
