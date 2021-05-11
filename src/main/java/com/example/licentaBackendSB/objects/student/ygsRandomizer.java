package com.example.licentaBackendSB.objects.student;

import java.util.ArrayList;
import java.util.List;

public class ygsRandomizer {

    //needed for Student object class
    public static String getRandomSeries()
    {
        //needs to be between ASCII code [65, 71]
        final int minASCIIcodeForSeries = 65;   //A
        final int maxASCIIcodeForSeries = 71;   //G
        int randomASCII = (int)Math.floor(Math.random()*(maxASCIIcodeForSeries-minASCIIcodeForSeries+1)+minASCIIcodeForSeries);

        return Character.toString((char) randomASCII);
    }

    public static String getRandomGroup()
    {
        List<String> groups = new ArrayList<>();
        int randomPos = (int)(Math.random() * 20);
        groups = getHarcodedGroups();

        return groups.get(randomPos);
    }

    private static List<String> getHarcodedGroups() {
        List<String> groups = new ArrayList<>();

        //Anu 1
        groups.add("411");
        groups.add("412");
        groups.add("413");
        groups.add("414");
        groups.add("415");

        //Anu 2
        groups.add("421");
        groups.add("422");
        groups.add("423");
        groups.add("424");
        groups.add("425");

        //Anu 3
        groups.add("431");
        groups.add("432");
        groups.add("433");
        groups.add("434");
        groups.add("435");

        //Anu 4
        groups.add("441");
        groups.add("442");
        groups.add("443");
        groups.add("444");
        groups.add("445");

        return groups;
    }
}
