package com.example.trabalho3.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = Filme.class,
                        parentColumns = "Id",
                        childColumns = "IdFilme",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Usuario.class,
                        parentColumns = "Id",
                        childColumns = "IdUsuario",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Avaliacao {
    @PrimaryKey(autoGenerate = true)
    private int Id;

    private int IdFilme;
    private int IdUsuario;
    private String Comentario;

    public int getId() { return Id; }

    public void setId(int id) { Id = id; }

    public int getIdFilme() { return IdFilme; }

    public void setIdFilme(int idFilme) { IdFilme = idFilme; }

    public int getIdUsuario() { return IdUsuario; }

    public void setIdUsuario(int idUsuario) { IdUsuario = idUsuario; }

    public String getComentario() { return Comentario; }

    public void setComentario(String comentario) { Comentario = comentario; }
}
