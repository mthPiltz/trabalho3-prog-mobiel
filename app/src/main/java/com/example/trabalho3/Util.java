package com.example.trabalho3;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.trabalho3.entity.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public  class Util {
    public static String hashSenha(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(senha.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Usuario getUsuario(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1); // Retorna -1 se não existir
        String userName = sharedPreferences.getString("userName", null);
        String userEmail = sharedPreferences.getString("userEmail", null);

        Usuario user = new Usuario();
        user.setNome(userName);
        user.setEmail(userEmail);

        return user;
    }
}
