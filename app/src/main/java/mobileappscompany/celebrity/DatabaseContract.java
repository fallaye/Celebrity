package mobileappscompany.celebrity;

import android.provider.BaseColumns;

/**
 * Created by fallaye on 12/4/17.
 */

public class DatabaseContract {

    private DatabaseContract(){}

    public static class CelebrityEntry implements BaseColumns{
        public static final String TABLE_NAME = "celebrity";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String GENDER = "gender";
    }

}
