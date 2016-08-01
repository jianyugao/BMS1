package com.bms.main;

import com.bms.utils.SerialUtils;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import jssc.SerialPort;
import jssc.SerialPortException;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Created by xuxu on 7/29/16.
 */
public class Main {
    static SerialPort serialPort;

    public static void main(String[] args) {
//        //serialPort = SerialUtils.getInstance();
//        //68 0F 0F FF 0F FF 5D 09 02 00 07 D0 01 01 04 2C 29 88
//        int [] array = {0x68,0x0F,0x0F,0xFF,0x0F,0xFF,0x5D,0x09,0x02,0x00,0x07,0xD0,0x01,0x01,0x04,0x2C,0x29,0x88};
//        int year = (array[10] << 8) + array[11];
//        int month = array[12];
//        int day = array[13];
//        int hour = array[14];
//        int min = array[15];
//        int sec = array[16];
//
//
//
//        LocalDateTime time = LocalDateTime.of(year, Month.of(month), day, hour, min, sec);
//        java.sql.Timestamp t = java.sql.Timestamp.valueOf(time);
//        LocalDateTime ldt2 = t.toLocalDateTime();
//        System.out.println(t);
//        System.out.println(ldt2);
        serialPort = SerialUtils.getInstance();
    }
}
