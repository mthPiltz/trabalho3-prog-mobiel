package com.example.trabalho3.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.trabalho3.R;
import com.example.trabalho3.Util;
import com.example.trabalho3.database.AppDatabase;
import com.example.trabalho3.databinding.FragmentCadastrarUsuarioBinding;
import com.example.trabalho3.databinding.FragmentLogarBinding;
import com.example.trabalho3.entity.Usuario;


public class Logar extends Fragment {
    private FragmentLogarBinding binding;
    private AppDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLogarBinding.inflate(inflater, container, false);
        db=AppDatabase.getDatabase(requireContext());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.txtEmail.getText().toString().trim();
                String senha = Util.hashSenha(binding.txtSenha.getText().toString().trim());

                Usuario usuario = db.usuarioDao().getByEmail(email, senha);

                if(usuario != null){
                    Toast.makeText(getActivity(), "Ta logado", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }
}