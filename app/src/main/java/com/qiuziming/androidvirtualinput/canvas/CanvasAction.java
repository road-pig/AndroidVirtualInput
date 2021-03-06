package com.qiuziming.androidvirtualinput.canvas;

import com.qiuziming.androidvirtualinput.network.InputAction;

//for controlling the pen
public class CanvasAction extends InputAction {
    public static final int HOVER = 0;
    public static final int DOWN = 1;

    private final double posX;
    private final double posY;
    private final double pressure;

    private final int type;

    public CanvasAction(double posX, double posY, int type, double pressure) {
        this.posX = posX;
        this.posY = posY;
        this.pressure = pressure;
        this.type = type;
    }

    @Override
    public String getActionString() {
        //I don't know why I did this
        String typeString;
        switch (type){
            case 0:
                typeString = "HOVER";
                break;
            case 1:
                typeString = "DOWN";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        
        return typeString + ":" + posX + "," + posY + "," + pressure;
    }
}
