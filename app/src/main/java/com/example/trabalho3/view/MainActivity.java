package com.example.trabalho3.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.trabalho3.R;
import com.example.trabalho3.database.AppDatabase;
import com.example.trabalho3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getDatabase(getApplicationContext());

        Fragment login = new Logar();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();;
        transaction.replace(R.id.novo_usuario, login);
        transaction.commit();

        binding.btnNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarCadastro();
            }
        });

    }

    private void mostrarCadastro(){
        Fragment cadastrarUser = new CadastrarUsuario();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();;
        transaction.replace(R.id.novo_usuario, cadastrarUser);
        transaction.commit();
    }
}