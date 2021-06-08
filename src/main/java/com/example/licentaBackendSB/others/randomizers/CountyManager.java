package com.example.licentaBackendSB.others.randomizers;

import java.util.HashMap;
import java.util.Map;

public class CountyManager {
    public static String getCountyFromTwoDigitCode(String countyCode)
    {
        Map <Integer, String> counties = getCounties();
        String result = null;

        int countyIndex = Integer.parseInt(countyCode);

        result = counties.get(countyIndex);
        return result;
    }

    public static Map<Integer, String> getCounties()
    {
        Map<Integer, String> counties = new HashMap<>();

        counties.putIfAbsent(1, "Alba");
        counties.putIfAbsent(2, "Arad");
        counties.putIfAbsent(3, "Argeș");
        counties.putIfAbsent(4, "Bacău");
        counties.putIfAbsent(5, "Bihor");
        counties.putIfAbsent(6, "Bistrița-Năsăud");
        counties.putIfAbsent(7, "Botoșani");
        counties.putIfAbsent(8, "Brașov");
        counties.putIfAbsent(9, "Brăila");
        counties.putIfAbsent(10, "Buzău");
        counties.putIfAbsent(11, "Caraș-Severin");
        counties.putIfAbsent(12, "Cluj");
        counties.putIfAbsent(13, "Constanța");
        counties.putIfAbsent(14, "Covasna");
        counties.putIfAbsent(15, "Dâmbovița");
        counties.putIfAbsent(16, "Dolj");
        counties.putIfAbsent(17, "Galați");
        counties.putIfAbsent(18, "Gorj");
        counties.putIfAbsent(19, "Harghita");
        counties.putIfAbsent(20, "Hunedoara");
        counties.putIfAbsent(21, "Ialomița");
        counties.putIfAbsent(22, "Iași");
        counties.putIfAbsent(23, "Ilfov");
        counties.putIfAbsent(24, "Maramureș");
        counties.putIfAbsent(25, "Mehedinți");
        counties.putIfAbsent(26, "Mureș");
        counties.putIfAbsent(27, "Neamț");
        counties.putIfAbsent(28, "Olt");
        counties.putIfAbsent(29, "Prahova");
        counties.putIfAbsent(30, "Satu Mare");
        counties.putIfAbsent(31, "Sălaj");
        counties.putIfAbsent(32, "Sibiu");
        counties.putIfAbsent(33, "Suceava");
        counties.putIfAbsent(34, "Teleorman");
        counties.putIfAbsent(35, "Timiș");
        counties.putIfAbsent(36, "Tulcea");
        counties.putIfAbsent(37, "Vaslui");
        counties.putIfAbsent(38, "Vâlcea");
        counties.putIfAbsent(39, "Vrancea");
        counties.putIfAbsent(40, "București");
        counties.putIfAbsent(41, "București - Sector 1");
        counties.putIfAbsent(42, "București - Sector 2");
        counties.putIfAbsent(43, "București - Sector 3");
        counties.putIfAbsent(44, "București - Sector 4");
        counties.putIfAbsent(45, "București - Sector 5");
        counties.putIfAbsent(46, "București - Sector 6");
        //nu exista 47,48,49,50
        counties.putIfAbsent(51, "Călărași ");
        counties.putIfAbsent(52, "Giurgiu");

        return counties;
    }
}
