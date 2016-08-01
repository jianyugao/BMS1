package com.bms.utils;

/**
 * Created by xuxu on 7/25/16.
 */

import com.bms.SerialData;
import jssc.*;

import java.io.IOException;
import java.util.ArrayList;


public class SerialUtils {

    private static SerialPort instance = null;


    private static String getPortName() {
        String[] portNames = SerialPortList.getPortNames();
        if (portNames.length == 0) {
            System.out.println("There are no serial-ports.");
            System.out.println("Press Enter to exit...");
            try {
                System.in.read();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return portNames[0];
        }
    }

    public static SerialPort getInstance() {
        if (null == instance) {
            instance = new SerialPort(SerialUtils.getPortName());
            try {
                instance.openPort();
                instance.setParams(9600, 8, 1, 0);
                //Preparing a mask. In a mask, we need to specify the types of events that we want to track.
                //Well, for example, we need to know what came some data, thus in the mask must have the
                //following value: MASK_RXCHAR. If we, for example, still need to know about changes in states
                //of lines CTS and DSR, the mask has to look like this: SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR
                int mask = SerialPort.MASK_RXCHAR;
                //Set the prepared mask
                instance.setEventsMask(mask);
                //Add an interface through which we will receive information about events
                instance.addEventListener(new SerialPortReader());
                System.out.println("port open!");
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
        return instance;
    }


    private static class SerialPortReader implements SerialPortEventListener {
        private static SerialData sdata = new SerialData();


        public static void getSerialData(SerialPortEvent event) {
            try {
//                if (sdata == null) {
//                    sdata = new SerialData(instance.readIntArray(event.getEventValue()));
//                } else {
//                    sdata.setContent(instance.readIntArray(event.getEventValue()));
//                }
                int [] xxx=instance.readIntArray(event.getEventValue());
                ArrayList<Integer> ss = new ArrayList<Integer>(xxx);

                sdata.setContent(instance.readIntArray(event.getEventValue()));
                sdata.setRawData();
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR()) {//If data is available
                getSerialData(event);
            } else if (event.isCTS()) {//If CTS line has changed state
                if (event.getEventValue() == 1) {//If line is ON
                    System.out.println("CTS - ON");
                } else {
                    System.out.println("CTS - OFF");
                }
            } else if (event.isDSR()) {///If DSR line has changed state
                if (event.getEventValue() == 1) {//If line is ON
                    System.out.println("DSR - ON");
                } else {
                    System.out.println("DSR - OFF");
                }
            }
        }
    }
}



