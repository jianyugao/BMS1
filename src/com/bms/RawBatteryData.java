package com.bms;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 2016/7/31.
 */
public class RawBatteryData extends RawData {

    private int batteryId;
    private int chCur;
    private int disCur;
    private double temperature;
    private int batteryStatus;
    private int chargerStatus;
    private Timestamp timestamp;

    private BatteryDataHandler bdh = new BatteryDataHandler();

    public RawBatteryData() {
    }

    public void setChCur(int chCur) {
        this.chCur = chCur;
    }

    public void setDisCur(int disCur) {
        this.disCur = disCur;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setBatteryStatus(int batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public void setChargerStatus(int chargerStatus) {
        this.chargerStatus = chargerStatus;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getBatteryId() {
        return batteryId;
    }

    public int getChCur() {
        return chCur;
    }

    public int getDisCur() {
        return disCur;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getBatteryStatus() {
        return batteryStatus;
    }

    public int getChargerStatus() {
        return chargerStatus;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void print(){
        System.out.println("chur = " + this.getChCur());
        System.out.println("dis_cur = " + this.getDisCur());
        System.out.println("temp = " + this.getTemperature());
    }


    @Override
    public void notifyDataHandler(DataHandler dh, RawData rd) {
        dh.handleData(rd);
    }

    @Override
    public void setData(List<Integer> list) {
        System.out.println("Set RBD1!");

        this.setChCur((list.get(2).intValue() << 8) + list.get(3).intValue());

        this.setDisCur((list.get(4).intValue() << 8) + list.get(5).intValue());

        this.setTemperature((list.get(6).intValue() << 8) + list.get(7).intValue());

        this.setBatteryStatus(list.get(8).intValue());

        this.setChargerStatus(list.get(9).intValue());
        //System.out.println("Set RBD2!");
        int year = (list.get(10).intValue() << 8) + list.get(11).intValue();
        int month = list.get(12).intValue();
        int day = list.get(13).intValue();
        int hour = list.get(14).intValue();
        int min = list.get(15).intValue();
        int sec = list.get(16).intValue();

        LocalDateTime time = LocalDateTime.of(year, Month.of(month), day, hour, min, sec);
        Timestamp timestamp = Timestamp.valueOf(time);
        //LocalDateTime ldt2 = timestamp.toLocalDateTime();

        this.timestamp = timestamp;

        this.print();
        notifyDataHandler(bdh, this);
    }

}