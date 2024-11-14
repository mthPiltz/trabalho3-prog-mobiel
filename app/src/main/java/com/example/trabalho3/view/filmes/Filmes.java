package com.example.trabalho3.view.filmes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.trabalho3.R;
import com.example.trabalho3.Util;
import com.example.trabalho3.database.AppDatabase;
import com.example.trabalho3.databinding.ActivityFilmesBinding;
import com.example.trabalho3.entity.Filme;
import com.example.trabalho3.entity.Usuario;

import java.util.List;

public class Filmes extends AppCompatActivity {
    ActivityFilmesBinding binding;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFilmesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getDatabase(getApplicationContext());

        carregaFilmes();

        binding.btnNovoFilmeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnNovoFilmeA.setVisibility(View.GONE);

                Fragment novoFilme = new FormAdicionarFilme();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_filme, novoFilme);
                transaction.commit();
            }
        });

        getSupportFragmentManager().setFragmentResultListener("atualizarSpinner", this, (requestKey, result) -> {
            carregaFilmes();
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        carregaFilmes();
    }

    private void carregaFilmes(){
        List<Filme> filmes = db.filmeDao().getAll();
        ArrayAdapter<Filme> adapter = new ArrayAdapter<Filme>(this,
                android.R.layout.simple_list_item_1, filmes);
        binding.listFilmes.setAdapter(adapter);
    }
}