package mobileappscompany.celebrity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.concurrent.CancellationException;

/**
 * Created by fallaye on 12/4/17.
 */

public class Celebrity implements Parcelable{

    String id, name, age, gender, favorite;

    public Celebrity(String id, String name, String age, String gender, String favorite) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.favorite = favorite;
    }

    protected Celebrity(Parcel in) {
        id = in.readString();
        name = in.readString();
        age = in.readString();
        gender = in.readString();
        favorite = in.readString();
    }

    public static final Creator<Celebrity> CREATOR = new Creator<Celebrity>() {
        @Override
        public Celebrity createFromParcel(Parcel in) {
            return new Celebrity(in);
        }

        @Override
        public Celebrity[] newArray(int size) {
            return new Celebrity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
    public String getFavorite() { return favorite; }

    @Override
    public String toString() {
        return "Celebrity{" + '\'' +
                "id= " + id +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", favorite='" + favorite + '\'' +
                '}';
    }
}
