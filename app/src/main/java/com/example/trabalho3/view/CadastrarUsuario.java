package com.example.trabalho3.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.trabalho3.R;
import com.example.trabalho3.database.AppDatabase;
import com.example.trabalho3.databinding.FragmentCadastrarUsuarioBinding;
import com.example.trabalho3.entity.Usuario;


public class CadastrarUsuario extends Fragment {
    private FragmentCadastrarUsuarioBinding binding;
    private AppDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCadastrarUsuarioBinding.inflate(inflater, container, false);
        db=AppDatabase.getDatabase(requireContext());

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.email.getText().toString();
                String nome = binding.nome.getText().toString();
                String senha = binding.nome.getText().toString();

                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setNome(nome);
                usuario.setSenha(senha);

                db.usuarioDao().insert(usuario);
                Toast.makeText(getActivity(), "Usuario inserido", Toast.LENGTH_SHORT).show();

            }
        });

        return binding.getRoot();
    }
}