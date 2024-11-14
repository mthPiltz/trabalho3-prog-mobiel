package com.example.trabalho3.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.trabalho3.R;
import com.example.trabalho3.Util;
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
                String email = binding.email.getText().toString().trim();;
                String nome = binding.nome.getText().toString().trim();;
                String senha = binding.senha.getText().toString().trim();;

                if(nome.isEmpty() || senha.isEmpty()){
                    Toast.makeText(getActivity(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getActivity(), "Por favor, insira um email válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                String senhaCriptografada = Util.hashSenha(senha);

                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setNome(nome);
                usuario.setSenha(senhaCriptografada);


                new Thread(() -> {
                    db.usuarioDao().insert(usuario);

                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getActivity(), "Usuário inserido", Toast.LENGTH_SHORT).show();

                        Fragment login = new Logar();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.novo_usuario, login);
                        transaction.commit();
                    });
                }).start();
            }
        });

        return binding.getRoot();
    }
}