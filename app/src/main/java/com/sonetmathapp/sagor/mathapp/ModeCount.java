package com.sonetmathapp.sagor.mathapp;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ModeCount {


    public ModeCount() {
    }

    public ModeCount(String element, String count) {
        this.element = element;
        this.count = count;
    }

    String element;
    String count;

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }



}
