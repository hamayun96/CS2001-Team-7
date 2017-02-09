/*
 * Created by Mohamed Bushra on 09/02/17 20:36
 * Copyright (c) 2017. All rights reserved.
 *
 * Last Modified 09/02/17 18:40.
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
    public String usergtaPal;

    public UserInformation(){

    }

    public UserInformation(String userFullName, String userStudentID, String userCourse,
                           String userCourseYear, String usergtaPal) {
        this.userFullName = userFullName;
        this.userStudentID = userStudentID;
        this.userCourse = userCourse;
        this.userCourseYear = userCourseYear;
        this.usergtaPal = usergtaPal;
    }
}
