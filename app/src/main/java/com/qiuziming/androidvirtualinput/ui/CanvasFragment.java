package com.qiuziming.androidvirtualinput.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.qiuziming.androidvirtualinput.R;
import com.qiuziming.androidvirtualinput.macro.MacroButton;
import com.qiuziming.androidvirtualinput.network.NetworkManager;

import java.io.IOException;

//this thing need not exist
//it literally exists as a holder for canvas view
public class CanvasFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_canvas, container, false);
        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //set button preferences
        ((MacroButton)root.findViewById(R.id.macroButton)).setButtonId("Button1");
        ((MacroButton)root.findViewById(R.id.macroButton2)).setButtonId("Button2");
        ((MacroButton)root.findViewById(R.id.macroButton3)).setButtonId("Button3");
        ((MacroButton)root.findViewById(R.id.macroButton4)).setButtonId("Button4");

        root.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    requireView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
                }
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        //set to fullscreen
        requireView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        if(((MainActivity) getActivity()).getNetworkManager() == null){
            Navigation.findNavController(getView()).navigate(R.id.action_canvasFragment_to_connectFragment);
        }
    }


    @Override
    public void onPause() {
        NetworkManager networkManager = ((MainActivity) requireActivity()).getNetworkManager();
        if (networkManager != null) {
            try {
                networkManager.closeConnection();
                ((MainActivity) requireActivity()).setNetworkManager(null);
            } catch (IOException | NullPointerException exception) {
                Toast.makeText(getContext(), "Failed to close connection", Toast.LENGTH_LONG).show();
            }
        }
        super.onPause();
    }
}