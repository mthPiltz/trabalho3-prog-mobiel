package com.example.trabalho3.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.trabalho3.dao.AvaliacaoDao;
import com.example.trabalho3.dao.FilmeDao;
import com.example.trabalho3.dao.UsuarioDao;
import com.example.trabalho3.entity.Avaliacao;
import com.example.trabalho3.entity.Filme;
import com.example.trabalho3.entity.Usuario;

@Database(entities = {Avaliacao.class, Filme.class, Usuario.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract AvaliacaoDao avaliacaoDao();
    public abstract FilmeDao filmeDao();
    public abstract UsuarioDao usuarioDao();
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Adiciona a nova coluna Nota com valor default
            database.execSQL("ALTER TABLE Avaliacao ADD COLUMN Nota INTEGER");
        }
    };

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                       AppDatabase.class, "trabalho3")
                       .addMigrations(MIGRATION_1_2)
                       .allowMainThreadQueries()
                       .build();

        }
        return INSTANCE;
    }
}
