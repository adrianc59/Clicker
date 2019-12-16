package com.example.clicker;

import java.util.Comparator;

public class scoreComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        userScore u1 = (userScore)o1;
        userScore u2 = (userScore)o2;

        if(u1.getScore() == u2.getScore())
            return 0;
        else if(u1.getScore() < u2.getScore())
            return 1;
        else
            return -1;
    }
}
