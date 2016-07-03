package com.romano21a.test.nfctest;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.util.Log;


public class NfcScanningHandler extends Activity {

    final String TAG = "NfcScanHandler";

    private NfcAdapter nfcAdapter;


    public NfcScanningHandler(NfcAdapter nfcAdapter){
        this.nfcAdapter = nfcAdapter;
    }

    public boolean checkNfcCompatability() {
        if (nfcAdapter == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkNfcState() {
        return nfcAdapter.isEnabled();
    }

    /*public void handleIntent(Intent intent) {
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
        } else {
            Log.d(TAG, "Intent: " + intent.toString());
        }
    } */

}
