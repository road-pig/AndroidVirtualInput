package com.example.androidvirtualinput.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidvirtualinput.R;
import com.example.androidvirtualinput.network.NetworkManager;

public class MainActivity extends AppCompatActivity {

    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new Thread(() -> {
//            try {
//                networkManager = new NetworkManager(PORT, IP);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
    //this feels a bit workaroundish
    public NetworkManager getNetworkManager(){
        return networkManager;
    }

    @Override
    public void onPause() {
        networkManager.closeConnection();
        super.onPause();
    }
    //this is bad but I don't think I can parcelize the network manager
    public void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }
}