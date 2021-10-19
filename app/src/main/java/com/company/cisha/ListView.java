package com.company.cisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListView extends AppCompatActivity {

    private RecyclerView recyclerView;


    private DatabaseReference database;
    private ItemViewHolder itemViewHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        recyclerView=findViewById(R.id.recyclerView_User);

        initRecyclerView();
    }
    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    protected void onStart() {
        super.onStart();

        database= FirebaseDatabase.getInstance().getReference().child("Flight");
        FirebaseRecyclerOptions<Flight> options=new FirebaseRecyclerOptions.Builder<Flight>()
                .setQuery(database,Flight.class).build();
        FirebaseRecyclerAdapter<Flight, ItemViewHolder> adapter = new FirebaseRecyclerAdapter<Flight, ItemViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull Flight model) {
                holder.name.setText("Название: "+model.getName());
                holder.departure.setText("Отправление "+model.getDeparture());
                holder.landing.setText("Приземление "+model.getLanding());
                holder.from.setText("Из "+model.getFrom());
                holder.to.setText("В "+model.getTo());
                holder.imageView.setImageURI(Uri.parse(model.getImage()));//тут мы конвертируем стринговую переменную в URI,это для картинки,так как мы не можем хранить в бд ничего кроме стрингов и интов,то мы конвертировали URI в стринг при добавление в бд,в активити  Add_Sweat
            }

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element,parent,false);
                itemViewHolder=new ItemViewHolder(view);
                return itemViewHolder;
            }
        };
        recyclerView.setAdapter(adapter);//Устанавливаем адаптер
        adapter.startListening();

    }
}