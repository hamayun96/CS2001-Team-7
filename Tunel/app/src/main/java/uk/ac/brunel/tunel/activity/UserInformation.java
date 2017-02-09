/*
 * Created by Mohamed Bushra on 08/02/17 17:02
 * Copyright (c) 2017. All rights reserved.
 *
 * Last Modified 08/02/17 15:15.
 */

package uk.ac.brunel.tunel.activity;

/**
 * Created by Mohamed on 07/02/2017.
 */

public class UserInformation {

    public String userFullName;
    public String userStudentID;
    public String userCourse;
    public String userCourseYear;

    public UserInformation(){

    }

    public UserInformation(String userFullName, String userStudentID, String userCourse,
                           String userCourseYear) {
        this.userFullName = userFullName;
        this.userStudentID = userStudentID;
        this.userCourse = userCourse;
        this.userCourseYear = userCourseYear;
    }

    // we have to have public getters to save object to Firebase
    public String getUserFullName() {
        return userFullName;
    }

    public String getUserStudentID() {
        return userStudentID;
    }

    public String getUserCourse() {
        return userCourse;
    }

    public String getUserCourseYear() {
        return userCourseYear;
    }
}
