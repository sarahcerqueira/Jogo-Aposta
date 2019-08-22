package com.example.bolaodasortefacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import com.example.bolaodasortefacil.R;

import java.util.UUID;

public class Impressao extends AppCompatActivity {

    private TextView textoImpressao;
    private TextView statuImpressora;
    private Button conectar;
    private Button imprimir;

    //Do bluetooth
    protected static final String TAG = "TAG";
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    BluetoothAdapter mBluetoothAdapter;
    private UUID applicationUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ProgressDialog mBluetoothConnectProgressDialog;
    private BluetoothSocket mBluetoothSocket;
    BluetoothDevice mBluetoothDevice;

    int printstat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impressao);

        this.textoImpressao = (TextView) findViewById(R.id.tvImpressao);
        this.statuImpressora = (TextView) findViewById(R.id.tvStatusImpressora);
        this.conectar = (Button) findViewById(R.id.bt_conectarB);
        this.imprimir = (Button) findViewById(R.id.bt_imprimir);
    }
}
