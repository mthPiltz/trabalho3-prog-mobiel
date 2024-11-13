package com.example.trabalho3.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabalho3.entity.Avaliacao;

import java.util.List;
@Dao
public interface AvaliacaoDao {
    @Insert
    void insert(Avaliacao avaliacao);

    @Update
    void update(Avaliacao avaliacao);

    @Delete
    void delete(Avaliacao avaliacao);

    @Query("SELECT * FROM avaliacao")
    List<Avaliacao> getAll();

    @Query("SELECT * FROM avaliacao WHERE id = :id")
    Avaliacao getById(int id);

    @Query("SELECT * FROM avaliacao WHERE IdUsuario = :idUsuario")
    Avaliacao getByUser(int idUsuario);

    @Query("SELECT * FROM avaliacao WHERE IdFilme = :IdFilme")
    Avaliacao getByFilme(int IdFilme);
}
