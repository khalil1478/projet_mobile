package com.example.mobile_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_project.entities.Event;

import java.util.List;



public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ProductViewHolder> {


    List<Event> events;
    Context context;

    public PostAdapter( List<Event> events,Context context)
    {
        this.events= events;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_postacc,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Event p = events.get(position);
        holder.name_post.setText(p.getName());
        holder.place_post.setText(p.getPlace());
        holder.type_post.setText(p.getType_event());
        holder.nbr_places.setText(p.getNb_places());
        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(context,"item clicked",Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        Button details;
        TextView name_post,place_post,type_post,nbr_places;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);


            name_post = itemView.findViewById(R.id.name_post);
            place_post = itemView.findViewById(R.id.place_post);
            type_post = itemView.findViewById(R.id.type_post);
            nbr_places = itemView.findViewById(R.id.nbr_places);
            details = itemView.findViewById(R.id.details);


        }
    }
}
