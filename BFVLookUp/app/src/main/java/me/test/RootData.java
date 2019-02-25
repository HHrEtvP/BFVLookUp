package me.test;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.*;

public class RootData implements Serializable {
    String status;
    String avatarUrl;
    String platformUserHandle;
    int platformId;
    MainObject data;

    /*
    public static final Parcelable.Creator<RootData> CREATOR = new Parcelable.Creator<RootData>(){
        @Override
        public RootData createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public RootData[] newArray(int i) {
            return new RootData[0];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.status);
        parcel.writeString(this.avatarUrl);
        parcel.writeValue(this.data);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

    public RootData(Parcel in){
        this.status=in.readString();
        this.avatarUrl=in.readString();
        this.data=(MainObject)in.readOb*/
    }

class MainObject implements Serializable {
    Aspect_Stats stats;
    List<Aspect_Classes> classes;
    List<Aspect_Weapons> weapons;
    List<Aspect_Vehicles> vehicles;
}

class Aspect_Stats implements Serializable{
    double id;
    double platformId;
    String lastUpdated;
    MetaData scorePerMinute;
    MetaData kdRatio;
    MetaData kills;
    MetaData deaths;
    MetaData damage;
    MetaData assists;
    MetaData killsAggregated;
    MetaData assistsAsKills;
    MetaData shotsHit;
    MetaData shotsTaken;
    MetaData shotsAccuracy;
    MetaData killStreak;
    MetaData dogtagsTaken ;
    MetaData avengerKills;
    MetaData saviorKills;
    MetaData headshots ;
    MetaData suppressionAssists;
    MetaData longestHeadshot ;
    MetaData killsPerMinute;
    MetaData damagePerMinute ;
    MetaData heals;
    MetaData revives;
    MetaData revivesRecieved;
    MetaData resupplies;
    MetaData aceSquad ;
    MetaData squadWipes;
    MetaData ordersCompleted;
    MetaData wlPercentage;
    MetaData wins;
    MetaData losses;
    MetaData draws ;
    MetaData rounds;
    MetaData roundsPlayed ;
    MetaData rank;
    MetaData rankScore ;
    MetaData timePlayed;
    MetaData scoreRound ;
    MetaData scoreGeneral;
    MetaData scoreCombat;
    MetaData scoreDefensive ;
    MetaData scoreObjective;
    MetaData scoreBonus;
    MetaData scoreSquad;
    MetaData scoreAward;
    MetaData scoreAssault;
    MetaData scoreSupport;
    MetaData scoreMedic ;
    MetaData scoreRecon ;
    MetaData scoreAir;
    MetaData scoreLand ;
    MetaData scoreTanks;
    MetaData scoreTransports;
}

class MetaData implements  Serializable{
    String displayName;
    double value;
}

class Aspect_Classes implements  Serializable{
    double id;
    //String class; 好蠢，键名称跟关键字重名了，这样只能通过顺序比对了
    double platformId;
    MetaData rank;
    MetaData kills;
    MetaData deaths;
    MetaData killsPerMinute;
    MetaData kdRatio;
    MetaData timePlayed;
    MetaData shotsFired;
    MetaData shotsHit;
    MetaData shotsAccuracy;
    MetaData score;
    MetaData scorePerMinute;
}

class Aspect_Weapons implements  Serializable{
    double id;
    String code;
    MetaData kills;
    MetaData killsPerMinute;
    MetaData timePlayed;
    MetaData shotsFired;
    MetaData shotsHit;
    MetaData shotsAccuracy;
    MetaData headshots;
}

class Aspect_Vehicles implements  Serializable{
    double id;
    String code;
    MetaData kills;
    MetaData killsPerMinute;
    MetaData timePlayed;
    MetaData destroyed;
}
