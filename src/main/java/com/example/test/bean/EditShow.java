package com.example.test.bean;

import java.sql.Date;

public class EditShow {
    String UserName;
    Date DateTime;

    public EditShow(String userName, Date dateTime) {
        UserName = userName;
        DateTime = dateTime;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }
}
