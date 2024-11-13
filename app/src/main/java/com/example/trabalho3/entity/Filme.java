package com.example.trabalho3.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Filme {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String Nome;

    public int getId() { return Id; }

    public void setId(int id) { Id = id; }

    public String getNome() { return Nome; }

    public void setNome(String nome) { Nome = nome; }
}
