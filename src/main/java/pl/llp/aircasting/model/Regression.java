package pl.llp.aircasting.model;

import android.util.Log;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.DatatypeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by marcin on 17/07/14.
 */
public class Regression {

    @Expose @SerializedName("sensor_name") private String sensorName;
    @Expose @SerializedName("sensor_package_name") private String sensorPackageName;
    @Expose @SerializedName("measurement_type") private String measurementType;
    @Expose @SerializedName("measurement_short_type") private String shortType;
    @Expose @SerializedName("unit_symbol") private String measurementSymbol;
    @Expose @SerializedName("unit_name") private String measurementUnit;
    @Expose @SerializedName("coefficients") private double[] coefficients;
    @Expose @SerializedName("threshold_very_low") private int thresholdVeryLow;
    @Expose @SerializedName("threshold_low") private int thresholdLow;
    @Expose @SerializedName("threshold_medium") private int thresholdMedium;
    @Expose @SerializedName("threshold_high") private int thresholdHigh;
    @Expose @SerializedName("threshold_very_high") private int thresholdVeryHigh;
    @Expose @SerializedName("reference_sensor_name") private String referenceSensorName;
    @Expose @SerializedName("reference_sensor_package_name") private String referenceSensorPackageName;
    @Expose @SerializedName("is_owner") private boolean isOwner;
    @Expose @SerializedName("id") private int backendId;
    private boolean isEnabled;
    @Expose @SerializedName("created_at") private String createdAt;

    public Date getDate() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            return format.parse(getCreatedAt());
        } catch (ParseException e) {
            return new Date();
        }
    }

    public String formattedDate() {
        return new SimpleDateFormat("MM/dd/yyyy, HH:mm").format(getDate());
    }

    public String getReferenceSensorName() {
        return referenceSensorName;
    }

    public String getReferenceSensorPackageName() {
        return referenceSensorPackageName;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getBackendId() {
        return backendId;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getSensorPackageName() {
        return sensorPackageName;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public String getShortType() {
        return shortType;
    }

    public String getMeasurementSymbol() {
        return measurementSymbol;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public int getThresholdVeryLow() {
        return thresholdVeryLow;
    }

    public int getThresholdLow() {
        return thresholdLow;
    }

    public int getThresholdMedium() {
        return thresholdMedium;
    }

    public int getThresholdHigh() {
        return thresholdHigh;
    }

    public int getThresholdVeryHigh() {
        return thresholdVeryHigh;
    }

    public Regression(String sensorName, String sensorPackageName, String measurementType, String shortType,
                      String measurementSymbol, String measurementUnit, double[] coefficients, int thresholdVeryLow,
                      int thresholdLow, int thresholdMedium, int thresholdHigh, int thresholdVeryHigh,
                      String referenceSensorName, String referenceSensorPackageName, boolean isOwner,
                      int backendId, boolean isEnabled, String createdAt) {
        this.sensorName = sensorName;
        this.sensorPackageName = sensorPackageName;
        this.measurementType = measurementType;
        this.shortType = shortType;
        this.measurementSymbol = measurementSymbol;
        this.measurementUnit = measurementUnit;
        this.coefficients = coefficients;
        this.thresholdVeryLow = thresholdVeryLow;
        this.thresholdLow = thresholdLow;
        this.thresholdMedium = thresholdMedium;
        this.thresholdHigh = thresholdHigh;
        this.thresholdVeryHigh = thresholdVeryHigh;
        this.referenceSensorName = referenceSensorName;
        this.referenceSensorPackageName = referenceSensorPackageName;
        this.isOwner = isOwner;
        this.backendId = backendId;
        this.isEnabled = isEnabled;
        this.createdAt = createdAt;
    }

    public double apply(double value) {
        if (!isEnabled()) return value;
        int len = coefficients.length;
        double val = coefficients[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            val *= value;
            val += coefficients[i];
        }
        return val;
    }


}
