package com.example.trabalho3.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabalho3.entity.Filme;

import java.util.List;

@Dao
public interface FilmeDao {
    @Insert
    void insert(Filme filme);

    @Update
    void update(Filme filme);

    @Delete
    void delete(Filme filme);

    @Query("SELECT * FROM filme")
    List<Filme> getAll();

    @Query("SELECT * FROM filme WHERE id = :id")
    Filme getById(int id);
}
