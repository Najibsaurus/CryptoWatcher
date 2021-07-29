package com.najib.cryptowatcher;

import android.os.Parcel;
import android.os.Parcelable;

public class Crypto implements Parcelable {

    private String id;
    private String symbol;
    private String name;
    private String image;
    private String currentPrice;
    private String lowPrice;
    private String highPrice;
    private Double marketCapChangePercentage24h;

    public Crypto(){
    }


    public static final Creator<Crypto> CREATOR = new Creator<Crypto>() {
        @Override
        public Crypto createFromParcel(Parcel in) {
            return new Crypto(in);
        }

        @Override
        public Crypto[] newArray(int size) {
            return new Crypto[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }


    public Double getMarketCapChangePercentage24h() {
        return marketCapChangePercentage24h;
    }

    public void setMarketCapChangePercentage24h(Double marketCapChangePercentage24h) {
        this.marketCapChangePercentage24h = marketCapChangePercentage24h;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(symbol);
        parcel.writeString(name);
        parcel.writeString(image);
        parcel.writeString(currentPrice);
        parcel.writeString(lowPrice);
        parcel.writeString(highPrice);
        if (marketCapChangePercentage24h == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(marketCapChangePercentage24h);
        }
    }
    protected Crypto(Parcel in) {
        id = in.readString();
        symbol = in.readString();
        name = in.readString();
        image = in.readString();
        currentPrice = in.readString();
        lowPrice = in.readString();
        highPrice = in.readString();
        if (in.readByte() == 0) {
            marketCapChangePercentage24h = null;
        } else {
            marketCapChangePercentage24h = in.readDouble();
        }
    }
}
