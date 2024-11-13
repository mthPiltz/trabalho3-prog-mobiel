package com.example.trabalho3.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.trabalho3.dao.AvaliacaoDao;
import com.example.trabalho3.dao.FilmeDao;
import com.example.trabalho3.dao.UsuarioDao;
import com.example.trabalho3.entity.Avaliacao;
import com.example.trabalho3.entity.Filme;
import com.example.trabalho3.entity.Usuario;

@Database(entities = {Avaliacao.class, Filme.class, Usuario.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract AvaliacaoDao avaliacaoDao();
    public abstract FilmeDao filmeDao();
    public abstract UsuarioDao usuarioDao();

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                       AppDatabase.class, "trabalho3")
                       .allowMainThreadQueries()
                       .build();

        }
        return INSTANCE;
    }
}
