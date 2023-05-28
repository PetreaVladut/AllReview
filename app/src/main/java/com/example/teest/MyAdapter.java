package com.example.teest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Scrollable_Object> mDataset;
    Context context;
    Intent i;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        private ImageView image;
        private TextView title;
        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView3);
            title = itemView.findViewById(R.id.info_text);
        }
    }

    public MyAdapter(ArrayList<Scrollable_Object> myDataset, Context context) {
        mDataset = myDataset;
        this.context= context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.card, null);
        //CardView c = v.findViewById(R.id.card_view);
        //LayoutInflater inflater=
        //View theInflatedView = LayoutInflater.from(MainActivity2.this).inflate(R.layout.scroll_object, null);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        Scrollable_Object model = mDataset.get(position);
        holder.title.setText(model.getName());
        Uri imageUri = Uri.parse(model.getImageResource());
        holder.image.setImageURI(imageUri);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform actions when the card is clicked
                // You can access the clicked item's data using 'model' variable

                // Example: Display a toast message with the course name
                MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
                //SQLiteDatabase database = dbHelper.getReadableDatabase();

                Review rev;
                //((MyDatabaseHelper) dbHelper).delete(database);
                rev= ((MyDatabaseHelper) dbHelper).getDataById(model.getId());
                i=new Intent(context, MainActivity7.class);
                i.putExtra("PARAMETER", rev);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}