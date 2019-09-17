package com.example.bolaodasortefacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Date;

import com.example.bolaodasortefacil.R;
import com.example.bolaodasortefacil.model.Concursos;
import com.example.bolaodasortefacil.model.Servidor;
import java.util.ArrayList;


import java.io.IOException;
import com.example.bolaodasortefacil.model.Jogador;
import com.example.bolaodasortefacil.model.Vendedor;


public class ApostaActivity extends AppCompatActivity  {

    private TextView numero_duplicado;
    private Spinner sp_concursos;
    private EditText d1;
    private EditText d2;
    private EditText d3;
    private EditText d4;
    private EditText d5;
    private EditText d6;
    private EditText d7;
    private EditText d8;
    private EditText d9;
    private EditText d10;

    private Jogador jogador;
    private String resultado;
    private ArrayList<Concursos> concursos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aposta);

        sp_concursos = (Spinner) findViewById(R.id.sp_concursos);
        numero_duplicado = (TextView) findViewById(R.id.tv_eaposta);
        d1 = (EditText) findViewById(R.id.d1);
        d2 = (EditText) findViewById(R.id.d2);
        d3 = (EditText) findViewById(R.id.d3);
        d4 = (EditText) findViewById(R.id.d4);
        d5 = (EditText) findViewById(R.id.d5);
        d6 = (EditText) findViewById(R.id.d6);
        d7 = (EditText) findViewById(R.id.d7);
        d8 = (EditText) findViewById(R.id.d8);
        d9 = (EditText) findViewById(R.id.d9);
        d10 = (EditText) findViewById(R.id.d10);
        jogador = new Jogador();

        resultado = null;

        SolicitacaoConcursos sc = new SolicitacaoConcursos();
        sc.execute();

        while(resultado== null){}

        if (resultado.equals("401")){
            this.numero_duplicado.setText("Falha ao se comunicar com o servidor. Verifique sua internet ou tente mais tarde");
            this.numero_duplicado.setVisibility(View.VISIBLE);

        } else if(resultado.equals("402")) {

            this.numero_duplicado.setText("Erro: 402");
            this.numero_duplicado.setVisibility(View.VISIBLE);

        }else if(resultado.equals("1")){

            int tam, aux=0;
            tam = concursos.size();
            ArrayList<String> al = new ArrayList<String>();
            Concursos c;

            while(aux< tam){

                c = this.concursos.get(aux);
                al.add( c.getData_final() + " - " + c.getHora_final());
                aux = aux +1;
            }

            ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, al);
            sp_concursos.setAdapter(adapter);

        }


    }

    public void finalizar(View view){

        if(this.criaApostar()){
            ApostaS serv = new ApostaS();
            resultado = null;
            serv.execute();

            while(resultado==null){}


        Intent imprimir = new Intent(this, Impressao.class);
        imprimir.putExtra("jogador", jogador);
        startActivity(imprimir);}

    }

    public void fazerMaisApostas(View view){
        if (this.criaApostar()){
            this.limparTela();
        }

    }

    public boolean criaApostar(){

        Date data = new Date();

        if(checkDezenaVazia()){

            jogador.apostar(1,this.getIdConcurso(sp_concursos.getSelectedItem().toString()),Vendedor.getVendedor(), Float.parseFloat("10.00"),"1º ao 5º", data, Integer.parseInt(d1.getText().toString()),
                    Integer.parseInt(d2.getText().toString()), Integer.parseInt(d3.getText().toString()), Integer.parseInt(d4.getText().toString()),
                    Integer.parseInt(d5.getText().toString()), Integer.parseInt(d6.getText().toString()), Integer.parseInt(d7.getText().toString()),
                    Integer.parseInt(d8.getText().toString()), Integer.parseInt(d9.getText().toString()), Integer.parseInt(d10.getText().toString()));
            return true;
        }

        return false;
    }

    private boolean checkDezenaVazia(){

        if(d1.getText().toString().isEmpty() | d2.getText().toString().isEmpty() | d3.getText().toString().isEmpty()
            | d4.getText().toString().isEmpty() | d5.getText().toString().isEmpty() |d6.getText().toString().isEmpty()
            | d7.getText().toString().isEmpty()| d8.getText().toString().isEmpty() | d9.getText().toString().isEmpty()
            | d10.getText().toString().isEmpty()){
            this.numero_duplicado.setText("Dezenas não preenchidas");
            this.numero_duplicado.setVisibility(View.VISIBLE);
            return false;
        }

        this.numero_duplicado.setVisibility(View.INVISIBLE);


        return true;
    }


    private String getIdConcurso(String concurso){

        int tam, aux=0;
        tam = concursos.size();
        ArrayList<String> al = new ArrayList<String>();
        Concursos c;

        while(aux< tam){

            c = this.concursos.get(aux);

            if(concurso.equals( c.getData_final() + " - " + c.getHora_final())){
                return c.getData_final();
            }


            aux = aux +1;
        }

        return null;
    }


    public void limpar(View view) {
        this.limparTela();


    }

    public void limparTela(){
        d1.setText("");
        d2.setText("");
        d3.setText("");
        d4.setText("");
        d5.setText("");
        d6.setText("");
        d7.setText("");
        d8.setText("");
        d9.setText("");
        d10.setText("");
    }

    @Override
    public void onBackPressed() {
        ArrayList apostas = jogador.getAposta();
        apostas.clear();

        Intent aposta = new Intent(this, LoginJogador.class);
        startActivity(aposta);
    }



    private class SolicitacaoConcursos extends AsyncTask<String, Void, Void> {

        private Servidor servidor;

        public SolicitacaoConcursos (){}


        @Override
        protected Void doInBackground(String... voids) {
            servidor = new Servidor();
            try {
                servidor.abrirConexao();
                servidor.escreverParaServidor("304");
                concursos = (ArrayList<Concursos>) servidor.getLer().readObject();
                servidor.fechaConexao();
                resultado = "1";

            } catch (IOException e)  {
                resultado = "401";

            } catch(ClassNotFoundException e){
                resultado = "402";

            }

            return null;
        }


    }


    private class ApostaS extends AsyncTask<String, Void, Void> {

        private Servidor servidor;

        public ApostaS (){}


        @Override
        protected Void doInBackground(String... voids) {
            servidor = new Servidor();

            try {
                servidor.abrirConexao();
                servidor.escreverParaServidor("203");
                servidor.escreverParaServidor(jogador.getAposta());
                resultado = (String)servidor.lerDoServidor();
                servidor.fechaConexao();

            } catch (IOException e)  {
                resultado = "401";

            } catch(ClassNotFoundException e){
                resultado = "402";

            }


            return null;
        }


    }

}
