package com.salazarisaiahnoel.sap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class sAPModuleAdapter extends RecyclerView.Adapter<sAPModuleAdapter.sAPModuleHolder> {

    Context context;
    List<String> data;
    OnItemClickListener listener;

    public sAPModuleAdapter(Context context, List<String> data, OnItemClickListener listener){
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public sAPModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new sAPModuleHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull sAPModuleHolder holder, int position) {
        holder.b1.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class sAPModuleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button b1;
        OnItemClickListener listener;

        public sAPModuleHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            b1 = itemView.findViewById(R.id.bitem);

            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
