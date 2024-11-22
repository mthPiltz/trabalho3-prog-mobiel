package com.example.trabalho3.view.filmes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.trabalho3.R;
import com.example.trabalho3.Util;
import com.example.trabalho3.database.AppDatabase;
import com.example.trabalho3.databinding.FragmentFilmeBinding;
import com.example.trabalho3.entity.Avaliacao;
import com.example.trabalho3.entity.Filme;
import com.example.trabalho3.entity.Usuario;

import java.util.List;

public class FilmeFragment extends Fragment {
    FragmentFilmeBinding binding;
    AppDatabase db;
    private static final String ARG_FILME_ID = "filme_id";
    private int filmeId;
    Usuario user ;

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

        user = Util.getUsuario(getActivity());
        Filme filme = db.filmeDao().getById(filmeId);
        binding.lblNomeFilme.setText(filme.getNome());

        Avaliacao avaliacao = db.avaliacaoDao().getByFilmeUser(user.getId(), filmeId);


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
                binding.lblComentario.setVisibility(View.GONE);
                binding.txtComentario.setVisibility(View.VISIBLE);
            }
        });

        binding.btnSalvarFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comentario = binding.txtComentario.getText().toString();
                if(comentario.isEmpty()){
                    Toast.makeText(getActivity(), "Por favor, escreva um breve comentario sobre o filme", Toast.LENGTH_SHORT).show();
                    return;
                }

                Filme filme = db.filmeDao().getById(filmeId);
                filme.setNome(binding.txtNomeFilme.getText().toString());
                db.filmeDao().update(filme);

                Avaliacao avaliacao = db.avaliacaoDao().getByFilmeUser(user.getId(), filmeId);
                if(avaliacao == null)
                    avaliacao = new Avaliacao();

                avaliacao.setComentario(comentario);
                avaliacao.setIdFilme(filmeId);
                avaliacao.setIdUsuario(user.getId());

                int nota = binding.avaliacao.getSelectedItemPosition() + 1;
                avaliacao.setNota(nota);


                if(avaliacao.getId() != 0){
                    db.avaliacaoDao().update(avaliacao);
                }
                else{
                    db.avaliacaoDao().insert(avaliacao);
                }


                binding.txtNomeFilme.setVisibility(View.GONE);
                binding.lblNomeFilme.setText(filme.getNome());
                binding.lblNomeFilme.setVisibility(View.VISIBLE);
                binding.btnSalvarFilme.setVisibility(View.GONE);
                binding.btnEditarFilme.setVisibility(View.VISIBLE);
                binding.lblComentario.setVisibility(View.VISIBLE);
                binding.txtComentario.setVisibility(View.GONE);
                getParentFragmentManager().setFragmentResult("atualizarSpinner", new Bundle());
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.avaliacoes,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.avaliacao.setAdapter(adapter);

        if(avaliacao != null){
            binding.txtComentario.setText(avaliacao.getComentario());
            binding.lblComentario.setText(avaliacao.getComentario());

            Integer nota = avaliacao.getNota() - 1;
            if(nota != null)
                binding.avaliacao.setSelection(nota);
        }

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