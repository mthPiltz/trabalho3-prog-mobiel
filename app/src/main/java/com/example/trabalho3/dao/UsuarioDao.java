package com.example.trabalho3.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabalho3.entity.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Insert
    void insert(Usuario usuario);

    @Update
    void update(Usuario usuario);

    @Delete
    void delete(Usuario usuario);

    @Query("SELECT * FROM usuario")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuario WHERE id = :id")
    Usuario getById(int id);

    @Query("SELECT * FROM usuario WHERE email =:email and senha =:senha")
    Usuario getByEmail(String email, String senha);
}
