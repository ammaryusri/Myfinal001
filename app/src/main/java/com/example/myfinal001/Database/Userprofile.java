package com.example.myfinal001.Database;

import android.provider.BaseColumns;
public final class Userprofile {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Userprofile() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "Userinfo";
        public static final String COLUMN_1 = "userName";
        public static final String COLUMN_2 = "dateofbirth";
        public static final String COLUMN_3 = "password";
        public static final String COLUMN_4 = "Gender";

    }
}
