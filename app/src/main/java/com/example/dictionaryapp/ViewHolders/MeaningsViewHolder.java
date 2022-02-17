package com.example.dictionaryapp.ViewHolders;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dictionaryapp.R;

public class MeaningsViewHolder extends RecyclerView.ViewHolder{
    public TextView textView_partsOfSpeech;
    public RecyclerView recycle_definitons;

    public MeaningsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_partsOfSpeech = itemView.findViewById(R.id.partOfSpeech);
        recycle_definitons = itemView.findViewById(R.id.recycler_definitions);
    }
}
