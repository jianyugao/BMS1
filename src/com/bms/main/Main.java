package com.bms.main;

import com.bms.utils.SerialUtils;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * Created by xuxu on 7/29/16.
 */
public class Main {
    static SerialPort serialPort;

    public static void main(String[] args) {
        serialPort = SerialUtils.getInstance();
    }
}
