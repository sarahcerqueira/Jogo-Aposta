package com.example.bolaodasortefacil.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bolaodasortefacil.R;
import com.example.bolaodasortefacil.model.Aposta;
import com.example.bolaodasortefacil.model.Servidor;
import com.example.bolaodasortefacil.model.Vendedor;

import android.os.AsyncTask;
import android.os.Bundle;
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
    private  ArrayList<Integer []> dezena;
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
            int aux =0, tam = dezena.size();
            resultado= "\nValor arrecadado: R$"+ 10.00 * aposta.size()+"0";
            Integer[] d;

            while(aux<tam){
                d = dezena.get(aux);
                resultado = resultado+ "\n ";


                for(int j=0; j<5; j++){

                    if(d[j]< 10){
                        resultado =resultado + "0"+ d[j]+" ";

                    } else {
                        resultado = resultado + d[j]+" ";

                    }
                }

            aux = aux + 1;

            }

            tv_dezena.setText(resultado);

             aux =0;
             tam = aposta.size();
            List<String> all = new ArrayList<String>() ;
            resultado = "";

            while(aux < tam){
                Aposta a = aposta.get(aux);

                if(a.getVendedor().equals(Vendedor.getVendedor())) {

                    resultado = resultado + ( "\n\n Nome: "+a.getJogador()+ "\n Telefone: " + a.getTelefoneJogador() + "\n Pontos: " + a.getPontos() +
                            "\n Dezenas jogadas: ");


                } else {
                    resultado = resultado + ("\n\n Pontos: " + a.getPontos() +
                                "\n Dezenas jogadas: ");

                }

                for(int j=0; j<10; j++){

                    int dez = a.getDezenas(j);

                    if(dez < 10){
                        resultado =resultado + "0"+ dez+" ";

                    } else {
                        resultado = resultado + dez+" ";

                    }
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
                servidor.escreverParaServidor("301");
                servidor.escreverParaServidor(id);
                aposta = (ArrayList<Aposta>)servidor.lerDoServidor();
                dezena = ( ArrayList<Integer []>) servidor.lerDoServidor();
                resultado = "ok";

            } catch (IOException e)  {
                resultado = "401";

            } catch(ClassNotFoundException e){
                resultado = "402";

            }


            return null;
        }


    }
}
