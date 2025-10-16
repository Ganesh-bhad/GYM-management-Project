package com.mountreachsolution.gymmanagementsystem.MyPackage.Pojo;

public class MyPackageModel {

    String id,package_name,fees,benefit;

    public MyPackageModel(String id, String package_name, String fees, String benefit) {
        this.id = id;
        this.package_name = package_name;
        this.fees = fees;
        this.benefit = benefit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }
}
