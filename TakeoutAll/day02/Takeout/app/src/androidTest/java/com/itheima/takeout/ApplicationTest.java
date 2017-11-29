package com.itheima.takeout;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.itheima.takeout.model.dao.DBHelper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    public void testTableCreate()
    {
        DBHelper.getInstance(getContext()).getWritableDatabase();
    }
}

