package com.example.bolaodasortefacil.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bolaodasortefacil.R;
import com.example.bolaodasortefacil.model.Aposta;
import com.example.bolaodasortefacil.model.Servidor;
import com.example.bolaodasortefacil.model.Vendedor;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Vencedores extends AppCompatActivity {

    private ArrayList<Aposta> aposta;
    private String id;
    private String resultado;
    private ListView win;
    private TextView tv_win;
    private TextView tv_dezena;
    private ArrayList<Integer> dezena;
    private TextView texto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vencedores);
        id = (String)getIntent().getExtras().getSerializable("id");
       // win = (ListView) findViewById(R.id.vencedores);
        tv_win = (TextView) findViewById(R.id.tv_win);

        texto = (TextView) findViewById(R.id.texto);
        tv_dezena = (TextView) findViewById(R.id.dezena_concurso);


        tv_win.setText("Concurso: " + id);

        Serv s = new Serv();
        aposta = null;
        resultado = null;
        s.execute();

        while(aposta == null || resultado == null || dezena == null){}



        if(aposta != null){

            tv_dezena.setText(dezena.get(0) + " " + dezena.get(1) + " "+ dezena.get(2)+" "+ dezena.get(3)+ " "+ dezena.get(4)+" "+ dezena.get(5)+
                    " "+ dezena.get(6)+" "+ dezena.get(7)+ " " + dezena.get(8)+ " "+ dezena.get(9));

            int aux =0, tam = aposta.size();
            List<String> all = new ArrayList<String>() ;
            resultado = "";

            while(aux < tam){
                Aposta a = aposta.get(aux);

                if(a.getVendedor().equals(Vendedor.getVendedor())) {
                    resultado = resultado + ("\n\n Telefone: " + a.getTelefoneJogador() + "\n Pontos: " + a.getPontos() +
                            "\n Dezenas jogadas: "+a.getDezenas(0) + " " + a.getDezenas(1) + " " + a.getDezenas(2) + " " + a.getDezenas(3) + " " + a.getDezenas(4) + " " + a.getDezenas(5) +
                            " " + a.getDezenas(6) + " " + a.getDezenas(7) + " " + a.getDezenas(8) + " " + a.getDezenas(9) + "\n\n" );
                } else {
                    resultado = resultado + ("\n\n Pontos: " + a.getPontos() +
                                "\n Dezenas jogadas: "+a.getDezenas(0) + " " + a.getDezenas(1) + " " + a.getDezenas(2) + " " + a.getDezenas(3) + " " + a.getDezenas(4) + " " + a.getDezenas(5) +
                                " " + a.getDezenas(6) + " " + a.getDezenas(7) + " " + a.getDezenas(8) + " " + a.getDezenas(9) + "\n\n" );

                }

                aux = aux +1;
            }

            texto.setText(resultado);

        }


    }



    private class Serv extends AsyncTask<Void, Void, Void> {

        private Servidor servidor;

        public Serv (){}


        @Override
        protected Void doInBackground(Void... voids) {
            servidor = new Servidor();

            try {
                servidor.abrirConexao();
                servidor.escreverParaServidor("win");
                servidor.escreverParaServidor(id);
                aposta = (ArrayList<Aposta>)servidor.lerDoServidor();
                dezena = (ArrayList<Integer>) servidor.lerDoServidor();
                resultado = "ok";

            } catch (IOException | ClassNotFoundException e) {
                resultado = "401";
            }


            return null;
        }


    }
}
