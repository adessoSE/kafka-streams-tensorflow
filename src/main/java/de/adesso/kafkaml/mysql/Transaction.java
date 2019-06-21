package de.adesso.kafkaml.mysql;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

@DatabaseTable(tableName = "payment_data")
public class Transaction {

    static int numTransactions = 0;

    @DatabaseField(id = true)
    private int transactionId;
    @DatabaseField
    private int step;
    @DatabaseField
    private String action;
    @DatabaseField
    private double amount;

    @DatabaseField
    private String nameOrig;
    @DatabaseField
    private String place;
    @DatabaseField
    private String date;
    @DatabaseField
    private String datetime;
    @DatabaseField
    private String verwendungszweck;
    @DatabaseField
    private double oldBalanceOrig;
    @DatabaseField
    private double newBalanceOrig;
    @DatabaseField
    private String nameDest;
    @DatabaseField
    private double oldBalanceDest;
    @DatabaseField
    private double newBalanceDest;
    @DatabaseField
    private boolean isFraud;
    @DatabaseField
    private boolean isFlaggedFraud;
    @DatabaseField
    private boolean isUnauthorizedOverdraft;
    @DatabaseField
    private Date timestamp;

    public Transaction() {
    }

    public Transaction(String transaction){
        String[] values = transaction.split(",");

        this.transactionId = this.numTransactions;
        this.numTransactions++;

        this.step = Integer.parseInt(values[0]);
        this.action = values[1];
        this.amount = Double.parseDouble(values[2]);

        this.place = values[4];
        this.date = values[5];
        this.datetime = values[6];
        this.verwendungszweck = values[7];

        this.nameOrig = values[3];
        this.oldBalanceOrig = Double.parseDouble(values[8]);
        this.newBalanceOrig = Double.parseDouble(values[9]);

        this.nameDest = values[10];
        this.oldBalanceDest = Double.parseDouble(values[11]);
        this.newBalanceDest = Double.parseDouble(values[12]);

        this.isFraud = Boolean.parseBoolean(values[13]);
        this.isFlaggedFraud = Boolean.parseBoolean(values[14]);
        this.isUnauthorizedOverdraft = Boolean.parseBoolean(values[15]);

        this.timestamp = new Date();
    }
}

