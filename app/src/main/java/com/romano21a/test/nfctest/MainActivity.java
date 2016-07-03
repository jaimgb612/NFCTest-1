package com.romano21a.test.nfctest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Main";

    private EditText scannedData;
    private EditText sendData;
    private EditText sendFakeData;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        final NfcScanningHandler nfcScanningHandler = new NfcScanningHandler(nfcAdapter);

        scannedData = (EditText) findViewById(R.id.fakeData);
        sendData = (EditText) findViewById(R.id.sendData);
        sendFakeData = (EditText) findViewById(R.id.fakeData);

        final Button sendFakeButton = (Button) findViewById(R.id.sendFake);
        Button sendButton = (Button) findViewById(R.id.send);


        if (!nfcScanningHandler.checkNfcCompatability()) {
            Toast.makeText(this, "NFC not supported", Toast.LENGTH_LONG).show();
            finish();
            return;
        } else if (!nfcScanningHandler.checkNfcState()) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final Dialog[] nfcDisabled = new Dialog[]{null};
            nfcDisabled[0] = builder.setMessage("Pease activate NFC").setPositiveButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            }).setNegativeButton("Close App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    return;
                }
            }).setCancelable(false).create();
            nfcDisabled[0].show();
        }


        //nfcScanningHandler.handleIntent(getIntent());



    }

    public void onResume() {
        super.onResume();

        NdefMessage msgs[];

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                    Log.d(TAG, msgs[i].toString());
                }
            }
        }

    }
}
