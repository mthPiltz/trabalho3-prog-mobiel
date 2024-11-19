package com.example.trabalho3.view.filmes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.trabalho3.R;
import com.example.trabalho3.database.AppDatabase;
import com.example.trabalho3.databinding.FragmentFormAdicionarFilmeBinding;
import com.example.trabalho3.entity.Filme;

public class FormAdicionarFilme extends Fragment {
    FragmentFormAdicionarFilmeBinding binding;
    private AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormAdicionarFilmeBinding.inflate(inflater, container, false);
        db= AppDatabase.getDatabase(requireContext());

        binding.btnNovoFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = binding.txtNome.getText().toString().trim();
                Filme filme = new Filme();
                filme.setNome(nome);

                db.filmeDao().insert(filme);
                Toast.makeText(getActivity(), "Filme inserido", Toast.LENGTH_SHORT).show();

                removerFragmento();
            }
        });

        return binding.getRoot();
    }

    private void removerFragmento(){
        getParentFragmentManager().setFragmentResult("atualizarSpinner", new Bundle());

        Button btnAdicionarActivity = getActivity().findViewById(R.id.btnNovoFilmeA);
        if (btnAdicionarActivity != null) {
            btnAdicionarActivity.setVisibility(View.VISIBLE);
        }

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_filme, new Fragment());
        transaction.commit();
    }
}