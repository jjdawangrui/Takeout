package com.itheima.takeout.model.dao.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable(tableName = "t_user")
public class UserBean {
    @DatabaseField(id = true)
    private int _id;
//    @ForeignCollectionField(eager = true)
//    private ForeignCollection<AddressBean> addressList;
    @DatabaseField()
    private String name;
    @DatabaseField()
    private float balance;
    @DatabaseField()
    private int discount;
    @DatabaseField()
    private int integral;
    @DatabaseField()
    private String phone;
    @DatabaseField
    private boolean login;



    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public UserBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
    //    public ForeignCollection<AddressBean> getAddressList() {
//        return addressList;
//    }
//
//    public void setAddressList(ForeignCollection<AddressBean> addressList) {
//        this.addressList = addressList;
//    }
}
