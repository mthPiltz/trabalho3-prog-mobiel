package com.example.trabalho3.view.filmes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabalho3.R;
import com.example.trabalho3.database.AppDatabase;
import com.example.trabalho3.databinding.FragmentFilmeBinding;
import com.example.trabalho3.entity.Filme;

public class FilmeFragment extends Fragment {
    FragmentFilmeBinding binding;
    AppDatabase db;
    private static final String ARG_FILME_ID = "filme_id";
    private int filmeId;

    public static FilmeFragment newInstance(int filmeId) {
        FilmeFragment fragment = new FilmeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_FILME_ID, filmeId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmeBinding.inflate(inflater, container, false);
        db= AppDatabase.getDatabase(requireContext());

        Filme filme = db.filmeDao().getById(filmeId);

        binding.lblNomeFilme.setText(filme.getNome());

        binding.btnEditarFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        binding.btnExcluirFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filme filme = db.filmeDao().getById(filmeId);
                db.filmeDao().delete(filme);
                getParentFragmentManager().setFragmentResult("atualizarSpinner", new Bundle());

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_filme, new Fragment());
                transaction.commit();
            }
        });

        binding.btnEditarFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filme filme = db.filmeDao().getById(filmeId);
                binding.lblNomeFilme.setVisibility(View.GONE);
                binding.txtNomeFilme.setText(filme.getNome());
                binding.txtNomeFilme.setVisibility(View.VISIBLE);
                binding.btnEditarFilme.setVisibility(View.GONE);
                binding.btnSalvarFilme.setVisibility(View.VISIBLE);
            }
        });

        binding.btnSalvarFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filme filme = db.filmeDao().getById(filmeId);
                filme.setNome(binding.txtNomeFilme.getText().toString());

                db.filmeDao().update(filme);
                binding.txtNomeFilme.setVisibility(View.GONE);
                binding.lblNomeFilme.setText(filme.getNome());
                binding.lblNomeFilme.setVisibility(View.VISIBLE);
                binding.btnSalvarFilme.setVisibility(View.GONE);
                binding.btnEditarFilme.setVisibility(View.VISIBLE);
                getParentFragmentManager().setFragmentResult("atualizarSpinner", new Bundle());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filmeId = getArguments().getInt(ARG_FILME_ID);
        }
    }
}