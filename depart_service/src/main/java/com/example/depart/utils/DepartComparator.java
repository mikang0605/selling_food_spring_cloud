package com.example.depart.utils;

import com.example.depart.pojo.Depart;

import java.util.Comparator;

public class DepartComparator implements Comparator<Depart> {

    @Override
    public int compare(Depart o1, Depart o2) {
        int num1 = Integer.parseInt(o1.getCode());
        int num2 = Integer.parseInt(o2.getCode());
        return num1 - num2;
    }
}
