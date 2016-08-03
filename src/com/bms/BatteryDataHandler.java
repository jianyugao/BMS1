package com.bms;

import com.bms.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by xuxu on 7/31/16.
 */
public class BatteryDataHandler implements DataHandler {
    @Override
    public void handleData(RawData rd) {
        System.out.println("get raw battery data!");
        if (rd instanceof RawBatteryData) {
            mysqlInsertData((RawBatteryData) rd);
        }
    }


    public void mysqlInsertData(RawBatteryData rbd) {
        Connection conn = JDBCUtils.getConn();
        String sql = "insert into batteryData (battery_id,ch_cur,dis_cur,temperature,battery_status,charger_status,timestp) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 0X01);
            ps.setDouble(2, (rbd.getChCur() / ADC_FULL_SCALE_VALUE / CHARGE_OPAMP_GAIN / CHARGE_SENSE_RESISTANCE));
            ps.setDouble(3, (rbd.getDisCur() / ADC_FULL_SCALE_VALUE / DISCHARGE_SENSE_RESISTANCE));
            ps.setDouble(4, rbd.getTemperature() / TEMPERATURE_FACTOR);
            ps.setInt(5, rbd.getBatteryStatus());
            ps.setInt(6, rbd.getChargerStatus());
            ps.setTimestamp(7, rbd.getTimestamp());


            ps.execute();
            System.out.println("insert a line!!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(ps);
        }

    }
}

