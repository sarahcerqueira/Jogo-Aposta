package com.example.bolaodasortefacil.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bolaodasortefacil.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Gerencimento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerencimento);
    }


    public  void cadastroConcurcoActivity(View view){
        Intent cadconcurso = new Intent(this, CadastroConcurso.class);
        startActivity(cadconcurso);
    }

    public  void cadastroVendedorActivity(View view){
        Intent cadvendedor = new Intent(this, CadastroVendedor.class);
        startActivity(cadvendedor);
    }

    public  void listaVendedorActivity(View view){
        Intent listvendedor = new Intent(this, ListarVendedores.class);
        startActivity(listvendedor);
    }

    public  void listaConcursoActivity(View view){
        Intent listconcurso = new Intent(this, ListarConcurso.class);
        startActivity(listconcurso);
    }



}
