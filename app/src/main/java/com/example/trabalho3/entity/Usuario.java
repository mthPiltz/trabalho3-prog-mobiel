package com.example.trabalho3.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String Nome;

    private String Senha;
    private String Email;

    public int getId() { return Id; }

    public void setId(int id) { this.Id = id; }

    public String getNome() { return Nome; }

    public void setNome(String nome) { this.Nome = nome; }

    public String getSenha() { return Senha; }

    public void setSenha(String senha) { this.Senha = senha; }

    public String getEmail() { return Email; }

    public void setEmail(String email) { this.Email = email; }
}
