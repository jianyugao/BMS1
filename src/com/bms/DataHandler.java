package com.bms;

/**
 * Created by xuxu on 7/31/16.
 */
public interface DataHandler {
    Double CHARGE_SENSE_RESISTANCE = 0.2;
    Double DISCHARGE_SENSE_RESISTANCE = 0.2;
    Double CHARGE_OPAMP_GAIN = 10.0;
    int ADC_FULL_SCALE_VALUE = 4095;
    double TEMPERATURE_FACTOR = 1000.0;

    void handleData(RawData rd);
}
