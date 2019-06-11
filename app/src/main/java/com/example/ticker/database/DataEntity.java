package com.example.ticker.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class DataEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    /*CHANNEL_ID	integer	Channel ID
    BID	float	Price of last highest bid
    BID_SIZE	float	Size of the last highest bid
    ASK	float	Price of last lowest ask
    ASK_SIZE	float	Size of the last lowest ask
    DAILY_CHANGE	float	Amount that the last price has changed since yesterday
    DAILY_CHANGE_PERC	float	Amount that the price has changed expressed in percentage terms
    LAST_PRICE	float	Price of the last trade.
    VOLUME	float	Daily volume
    HIGH	float	Daily high
    LOW	float	Daily low*/

}
