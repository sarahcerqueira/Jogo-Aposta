package com.example.bolaodasortefacil.view;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import com.example.bolaodasortefacil.model.*;
import java.text.SimpleDateFormat;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;


import com.example.bolaodasortefacil.model.Jogador;

import com.example.bolaodasortefacil.R;

import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;


public class Impressao extends AppCompatActivity implements Runnable {

    private TextView textoImpressao;
    private TextView statuImpressora;
    private Button conectar;
    private Button imprimir;
    private Jogador jogador;


    //Do bluetooth
    protected static final String TAG = "TAG";
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    BluetoothAdapter mBluetoothAdapter;
    private UUID applicationUUID = UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ProgressDialog mBluetoothConnectProgressDialog;
    private BluetoothSocket mBluetoothSocket;
    private BluetoothDevice mBluetoothDevice;
    private String dados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impressao);

        this.textoImpressao = (TextView) findViewById(R.id.tvImpressao);
        this.statuImpressora = (TextView) findViewById(R.id.tvStatusImpressora);
        this.conectar = (Button) findViewById(R.id.bt_conectarB);
        this.imprimir = (Button) findViewById(R.id.bt_imprimir);
        jogador = (Jogador)getIntent().getExtras().getSerializable("jogador");
        this.statuImpressora.setText("Disconectado");
        getDados();

        this.textoImpressao.setText(dados);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                if (conectar.getText().equals("Conectar")) {
                    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                    if (mBluetoothAdapter == null) {
                        Toast.makeText(Impressao.this, "Message1", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!mBluetoothAdapter.isEnabled()) {
                            Intent enableBtIntent = new Intent(
                                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivityForResult(enableBtIntent,
                                    REQUEST_ENABLE_BT);
                        } else {
                            ListPairedDevices();

                            Intent connectIntent = new Intent(Impressao.this,
                                    DeviceListActivity.class);
                            startActivityForResult(connectIntent,
                                    REQUEST_CONNECT_DEVICE);

                        }
                    }

                } else if (conectar.getText().equals("Disconectado")) {
                    if (mBluetoothAdapter != null)
                        mBluetoothAdapter.disable();
                    statuImpressora.setText("");
                    statuImpressora.setText("Disconectado");
                    statuImpressora.setTextColor(Color.rgb(199, 59, 59));
                    imprimir.setEnabled(false);
                    conectar.setEnabled(true);
                    conectar.setText("Conectar");
                }
            }
        });

        //mPrint.setTypeface(custom);
        imprimir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View mView) {

                impressao();


            }
        });

    }

    private void impressao(){
        Thread t = new Thread() {
            public void run() {
                try {
                    OutputStream os = mBluetoothSocket.getOutputStream();
                    os.write(dados.getBytes());

                } catch (Exception e) {
                    Log.e("PrintActivity", "Exe ", e);
                }
            }
        };
        t.start();
    }

    public void connectDisconnect(View view)
    {
        if(conectar.getText().toString() == "Connect")
        {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                Toast.makeText(Impressao.this, "Message1", Toast.LENGTH_SHORT).show();
            } else {
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(
                            BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent,
                            REQUEST_ENABLE_BT);
                } else {
                    ListPairedDevices();
                    Intent connectIntent = new Intent(Impressao.this,
                            DeviceListActivity.class);
                    startActivityForResult(connectIntent,
                            REQUEST_CONNECT_DEVICE);
                }
            }


        }else{
            if (mBluetoothAdapter != null)
            {
                mBluetoothAdapter.disable();
            }
            else{
                statuImpressora.setText("");
                statuImpressora.setText("Disconectado");
                statuImpressora.setTextColor(Color.rgb(199, 59, 59));
                imprimir.setEnabled(false);
                /*mDisc.setEnabled(false);
                mDisc.setBackgroundColor(Color.rgb(161, 161, 161));*/
                //mPrint.setBackgroundColor(Color.rgb(161, 161, 161));
                conectar.setBackgroundColor(Color.rgb(0,0,0));
                conectar.setEnabled(true);
                conectar.setText("Disconectado");
            }
        }

    }



    public void onActivityResult(int mRequestCode, int mResultCode,
                                 Intent mDataIntent) {
        super.onActivityResult(mRequestCode, mResultCode, mDataIntent);

        switch (mRequestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (mResultCode == Activity.RESULT_OK) {
                    Bundle mExtra = mDataIntent.getExtras();
                    String mDeviceAddress = mExtra.getString("DeviceAddress");
                    Log.v(TAG, "Coming incoming address " + mDeviceAddress);
                    mBluetoothDevice = mBluetoothAdapter
                            .getRemoteDevice(mDeviceAddress);
                    mBluetoothConnectProgressDialog = ProgressDialog.show(this,
                            "Connecting...", mBluetoothDevice.getName() + " : "
                                    + mBluetoothDevice.getAddress(), true, false);
                    Thread mBlutoothConnectThread = new Thread(this);
                    mBlutoothConnectThread.start();
                    // pairToDevice(mBluetoothDevice); This method is replaced by
                    // progress dialog with thread
                }
                break;

            case REQUEST_ENABLE_BT:
                if (mResultCode == Activity.RESULT_OK) {
                    ListPairedDevices();
                    Intent connectIntent = new Intent(Impressao.this,
                            DeviceListActivity.class);
                    startActivityForResult(connectIntent, REQUEST_CONNECT_DEVICE);
                } else {
                    Toast.makeText(Impressao.this, "Message", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void ListPairedDevices() {
        Set<BluetoothDevice> mPairedDevices = mBluetoothAdapter
                .getBondedDevices();
        if (mPairedDevices.size() > 0) {
            for (BluetoothDevice mDevice : mPairedDevices) {
                Log.v(TAG, "PairedDevices: " + mDevice.getName() + "  "
                        + mDevice.getAddress());
            }
        }
    }

    public void run() {
        try {
            mBluetoothSocket = mBluetoothDevice
                    .createRfcommSocketToServiceRecord(applicationUUID);
            mBluetoothAdapter.cancelDiscovery();
            mBluetoothSocket.connect();
            mHandler.sendEmptyMessage(0);
        } catch (IOException eConnectException) {
            Log.d(TAG, "CouldNotConnectToSocket", eConnectException);
            closeSocket(mBluetoothSocket);
            return;
        }
    }

    private void closeSocket(BluetoothSocket nOpenSocket) {
        try {
            nOpenSocket.close();
            Log.d(TAG, "SocketClosed");
        } catch (IOException ex) {
            Log.d(TAG, "CouldNotCloseSocket");
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        try {
            if (mBluetoothSocket != null)
                mBluetoothSocket.close();
        } catch (Exception e) {
            Log.e("Tag", "Exe ", e);
        }
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mBluetoothConnectProgressDialog.dismiss();

            // Snackbar snackbar = Snackbar.make(layout, "Bluetooth Printer is Connected!", Snackbar.LENGTH_LONG);
            // snackbar.show();
            statuImpressora.setText("");
            statuImpressora.setText("Conectado");
            statuImpressora.setTextColor(Color.rgb(97, 170, 74));
            imprimir.setEnabled(true);
            conectar.setText("Disconectar");

        }
    };

    public static byte intToByteArray(int value) {
        byte[] b = ByteBuffer.allocate(4).putInt(value).array();

        for (int k = 0; k < b.length; k++) {
            System.out.println("Selva  [" + k + "] = " + "0x"
                    + UnicodeFormatter.byteToHex(b[k]));
        }

        return b[3];
    }

    public byte[] sel(int val) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putInt(val);
        buffer.flip();
        return buffer.array();
    }

    private void getDados() {

        dados = "\n      BOLAO DA SORTE FACIL \n\n";

        ArrayList<Aposta> aposta = jogador.getAposta();
        int aux = 0, tam = aposta.size();
        float total = 0;
        Aposta a = null;

        while (aux < tam) {

            a = aposta.get(aux);

            if(aux == 0){
                Date data = a.getdata();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy  HH:mm:ss");
                dados = dados + " "+ format.format(data);
            }

            dados = dados + "\n Concurso: " + a.getConcurso() + "\n";

            dados = dados + " Dezena:\n" +" " +a.getDezenas(0) + " " + a.getDezenas(1) + " " + a.getDezenas(2) + " " + a.getDezenas(3) + " " + a.getDezenas(4) + " " + a.getDezenas(5) +
                    " " + a.getDezenas(6) + " " + a.getDezenas(7) + " " + a.getDezenas(8) + " " + a.getDezenas(9) + "\n\n";

            dados = dados + " Premio: " + a.getPremio() + "\n";

            dados = dados + " Valor: R$" + Float.toString(a.getValor()) + "0\n";

            total = total + a.getValor();

            aux = aux + 1;

            if (aux == tam) {
                dados = dados + "\n Total: R$" + Float.toString(total) + "0\n\n";

                dados = dados + " Vendedor: " + a.getVendedor() + "\n";

                dados = dados + " Id do usuario: " + a.getTelefoneJogador() + "\n\n\n";
            }

        }

    }

    @Override
    public void onBackPressed() {
        Intent aposta = new Intent(this, LoginJogador.class);
        startActivity(aposta);

    }

    }