package com.example.bolaodasortefacil;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bolaodasortefacil.model.Concurso;
import com.example.bolaodasortefacil.model.Concursos;
import com.example.bolaodasortefacil.model.Servidor;
import com.example.bolaodasortefacil.view.Impressao;
import com.example.bolaodasortefacil.view.LoginJogador;
import com.example.bolaodasortefacil.view.Vencedores;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.List;


import java.io.IOException;
import java.util.ArrayList;

public class Gerenciamento extends AppCompatActivity {

    private Spinner meses;
    private Spinner ano;
    private ListView lista_concursos;
    private ArrayList<Concursos> concurso;
    private String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciamento);

        meses = (Spinner) findViewById(R.id.mes);
        ano = (Spinner) findViewById(R.id.ano);
        lista_concursos = (ListView) findViewById(R.id.listaConcursos);


        String [] m = {"janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro",
        "outubro", "novembro", "dezembro"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
        meses.setAdapter(adapter);

        getConcursos();

        lista_concursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showVencedores(i);
            }
        });

    }


    private void getConcursos(){


        Serv s = new Serv();
        s.execute();

        while(concurso == null){}

        int tam, aux=0;
        tam = concurso.size();
        List<String> al = new ArrayList<String>();
        ArrayList<Integer> ano = new ArrayList<Integer>();
        Concursos c;

        while(aux< tam){

            c = this.concurso.get(aux);

            al.add(c.getId() + " " + c.getData_final() + "   " + c.getHora_final());

            if(!ano.contains(c.getAno())){
                ano.add(c.getAno());

            }
            aux = aux +1;
        }

        ArrayAdapter<String>  adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, al);
        lista_concursos.setAdapter(adapterC);

        ArrayAdapter<Integer>  adapterA = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, ano);
        this.ano.setAdapter(adapterA);


    }

    public void showVencedores(int i){

        Intent vencedores = new Intent(this, Vencedores.class);
        vencedores.putExtra("id",  concurso.get(i).getId());
        startActivity(vencedores);
    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }



    private class Serv extends AsyncTask<Void, Void, Void> {

        private Servidor servidor;

        public Serv (){}


        @Override
        protected Void doInBackground(Void... voids) {
            servidor = new Servidor();

            try {
                servidor.abrirConexao();
                servidor.escreverParaServidor("vencedores");
                concurso = (ArrayList<Concursos>)servidor.lerDoServidor();

            } catch (IOException | ClassNotFoundException e) {
                resultado = "401";
            }


            return null;
        }


    }


}
