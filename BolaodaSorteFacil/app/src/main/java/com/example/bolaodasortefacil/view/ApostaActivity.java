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
    private Spinner premio;
    private EditText valor;
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
        premio = (Spinner)   findViewById(R.id.sp_premio);
        valor = (EditText)  findViewById(R.id.valor);
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

        String [] premios = {"1º ao 5º"};
        ArrayAdapter<String>  adapter_premios = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, premios);
        premio.setAdapter(adapter_premios);

        SolicitacaoConcursos sc = new SolicitacaoConcursos();
        sc.execute();

        while(resultado== null){}

        if (resultado.equals("401")){
            //botar um erro aqui

        } else if(resultado.equals("ok")){

            int tam, aux=0;
            tam = concursos.size();
            ArrayList<String> al = new ArrayList<String>();
            Concursos c;

            while(aux< tam){

                c = this.concursos.get(aux);
                al.add(c.getId() + " " + c.getData_final() + " - " + c.getHora_final());
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

        if(checkDezenaVazia() && checkDuplicidadeDezenas() && checkValor()){
           String a=  valor.getText().toString();
           a =  a.replace("R$", "");
           a = a.replace(",", ".");

            jogador.apostar(Integer.toString(this.getIdConcurso(sp_concursos.getSelectedItem().toString())),Vendedor.getVendedor(), Float.parseFloat(a),premio.getSelectedItem().toString(), data, Integer.parseInt(d1.getText().toString()),
                    Integer.parseInt(d2.getText().toString()), Integer.parseInt(d3.getText().toString()), Integer.parseInt(d4.getText().toString()),
                    Integer.parseInt(d5.getText().toString()), Integer.parseInt(d6.getText().toString()), Integer.parseInt(d7.getText().toString()),
                    Integer.parseInt(d8.getText().toString()), Integer.parseInt(d9.getText().toString()), Integer.parseInt(d10.getText().toString()));
            return true;
        }

        return false;
    }

    private boolean checkValor(){

        if(valor.getText().toString().isEmpty()){
            valor.setError("Informe o valor da aposta");
            return false;

        } else{
            valor.setError(null);
        }

        return true;
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

    public boolean checkDuplicidadeDezenas(){

        String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;

        s1 = d1.getText().toString();
        s2 = d2.getText().toString();
        s3 = d3.getText().toString();
        s4 = d4.getText().toString();
        s5 = d5.getText().toString();
        s6 = d6.getText().toString();
        s7 = d7.getText().toString();
        s8 = d8.getText().toString();
        s9 = d9.getText().toString();
        s10 = d10.getText().toString();

        if(s1.equals(s2) | s1.equals(s3) | s1.equals(s4) | s1.equals(s5)  | s1.equals(s6)  | s1.equals(s7) | s1.equals(s8) | s1.equals(s9) | s1.equals(s10)
                | s2.equals(s3)| s2.equals(s4)| s2.equals(s5)| s2.equals(s6)| s2.equals(s7)| s2.equals(s8)| s2.equals(s9)| s2.equals(s10)
                | s3.equals(s4)| s3.equals(s5)| s3.equals(s6)|| s3.equals(s7)| s3.equals(s8)| s3.equals(s9)| s3.equals(s10)
                | s4.equals(s5)| s4.equals(s6)| s4.equals(s7)| s4.equals(s8)| s4.equals(s9)| s4.equals(s10)
                | s5.equals(s6)| s5.equals(s7)| s5.equals(s8)| s5.equals(s9)| s5.equals(s10)
                | s6.equals(s7)| s6.equals(s8)| s6.equals(s9)| s5.equals(s10)
                | s7.equals(s8)| s7.equals(s9)| s7.equals(s10)
                | s8.equals(s9)| s8.equals(s10)
                | s9.equals(s10)){

            this.numero_duplicado.setText("Dezenas duplicadas");
            this.numero_duplicado.setVisibility(View.VISIBLE);
            return false;
        } else {
            this.numero_duplicado.setVisibility(View.INVISIBLE);

        }

        return true;
    }

    private int getIdConcurso(String concurso){

        int tam, aux=1;
        tam = concursos.size();
        ArrayList<String> al = new ArrayList<String>();
        Concursos c;

        while(aux< tam){

            c = this.concursos.get(aux);

            if(concurso.equals(c.getId() + " " + c.getData_final() + " - " + c.getHora_final())){
                return Integer.parseInt(c.getId());
            }

            aux = aux +1;
        }

        return 0;
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
        valor.setText("");
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
                servidor.escreverParaServidor("concursosAtivos");
                concursos = (ArrayList<Concursos>) servidor.getLer().readObject();
                servidor.fechaConexao();
                resultado = "ok";

            } catch (IOException | ClassNotFoundException e) {
                resultado = "401";
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
                servidor.escreverParaServidor("Apostar");
                servidor.escreverParaServidor(jogador);
                resultado = (String)servidor.lerDoServidor();
                servidor.fechaConexao();

            } catch (IOException | ClassNotFoundException e) {
                resultado = "401";
            }


            return null;
        }


    }

}
