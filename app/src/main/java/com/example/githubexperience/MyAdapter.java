package com.example.githubexperience;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Users> usuarios;
    private List<Repositorios> repos;

    //private View.OnClickListener listener;

    public void setData (List<Repositorios> repositorios ){
        this.repos = repositorios;
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MyAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false));
    }





    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repositorios repositorios = repos.get(position);
        //Users users = usuarios.get(1);
        String language = repositorios.getLanguage();
        String name = repositorios.getName();

        holder.name.setText(name);
        holder.language.setText(language);
//        holder.followings.setText();
    }



    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView language;
        public View layout;

        /////

        TextView followers;
        TextView followings;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            name = itemView.findViewById(R.id.name);
            language = itemView.findViewById(R.id.language);
            followers = itemView.findViewById(R.id.followers);
            followings = itemView.findViewById(R.id.followings);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}