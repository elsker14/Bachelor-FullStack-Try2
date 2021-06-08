package com.example.licentaBackendSB.others.randomizers;

import java.util.*;
import java.util.stream.Stream;

public class DoBandCNPandGenderRandomizer {

    public static String getDoB()
    {
        Random rand = new Random();
        String result = null;

        //ziua
        int firstDayOfMonth = 1;
        int lastDayOfMonth = 31;
        int randomDay = rand.nextInt(lastDayOfMonth - firstDayOfMonth) + firstDayOfMonth;

        //luna
        Map<Integer, String> months = DoBandCNPandGenderRandomizer.getMonths();
        int firstMonth = 1;
        int lastMonth = 12;
        int randomMonth = rand.nextInt(lastMonth - firstMonth) + firstMonth;

        //anul
        int firstYear = 1997;
        int lastYear = 2002;
        int randomYear = rand.nextInt(lastYear - firstYear) + firstYear;

        //concatenare
        result = (randomDay < 10 ? "0" + randomDay : randomDay)
                + "." + months.get(randomMonth)
                + "." + randomYear;

        return result;
    }

    public static String getGender()
    {
        List<String> genders = new ArrayList<>();
        genders.add("Masculin");
        genders.add("Feminin");

        String result = null;
        Random rand = new Random();
        int startIndex = 1;
        int endIndex = 100;

        int randomGenderIndex = rand.nextInt(endIndex - startIndex) + startIndex;
        result = genders.get(randomGenderIndex % 2);

        return result;
    }

    public static String getCNP(String tmp, String gender)
    {
        Map<Integer, String> months = getMonths();
        Random rand = new Random();
        String result = null;

        //Split zi de nastere dupa caracterul punct "."
        String day = tmp.split("\\.")[0];
        String month = tmp.split("\\.")[1];
        String year = tmp.split("\\.")[2];

        //Preluarea indexului lunii stiind valoarea din map si extragerea ultimelor 2 cifre din an
        Integer keyofMonth = getFirstKeyByValue(months, month);
        String last2DigitsFromYear = year.substring(2,4);

        //In functie de sex si de an
        int genderIndicator;
        if(gender.equals("Masculin") && Integer.parseInt(year) < 2000)
            genderIndicator = 1;
        else if(gender.equals("Masculin") && Integer.parseInt(year) >= 2000)
            genderIndicator = 5;
        else if(gender.equals("Feminin") && Integer.parseInt(year) < 2000)
            genderIndicator = 2;
        else
            genderIndicator = 6;

        //Generare random a codului de judet
        int startCountyCode = 1;
        int endCountyCode = 52;
        int randomCountyCode = 48;  //oricare din 47, 48, 49, 50
        while(!DoBandCNPandGenderRandomizer.checkCountyCode(randomCountyCode))
        {
            randomCountyCode = rand.nextInt(endCountyCode - startCountyCode) + startCountyCode;
        }

        //Generare random a ultimelor 4 cifre din cnp
        int startLetter = 0;
        int endLetter = 9;
        int randomFirstLetter = rand.nextInt(endLetter - startLetter) + startLetter;
        int randomSecondLetter = rand.nextInt(endLetter - startLetter) + startLetter;
        int randomThirdLetter = rand.nextInt(endLetter - startLetter) + startLetter;
        int randomFourthLetter = rand.nextInt(endLetter - startLetter) + startLetter;

        //Concatenarea rezultatului
        result = genderIndicator + ""
                +  last2DigitsFromYear + ""
                + (keyofMonth < 10 ? "0" + keyofMonth : keyofMonth) + ""
                + day + ""
                + (randomCountyCode < 10 ? "0" + randomCountyCode : randomCountyCode) + ""
                + randomFirstLetter
                + randomSecondLetter
                + randomThirdLetter
                + randomFourthLetter;

        return result;
    }

    public static Boolean checkCountyCode(int tmp)
    {
        return tmp != 47 && tmp != 48 && tmp != 49 && tmp != 50;
    }

    public static String splitDoBbyDot(String tmp)
    {
        Map<Integer, String> months = getMonths();
        String result;

        String day = tmp.split("\\.")[0];
        String month = tmp.split("\\.")[1];
        String year = tmp.split("\\.")[2];
        Integer keyofMonth = getFirstKeyByValue(months, month);

        result = day + (keyofMonth < 10 ? "0" + keyofMonth : keyofMonth) + year;

        return result;
    }

    public static Map<Integer, String> getMonths()
    {
        Map<Integer, String> months = new HashMap<>();

        months.putIfAbsent(1, "Ianuarie");
        months.putIfAbsent(2, "Februarie");
        months.putIfAbsent(3, "Martie");
        months.putIfAbsent(4, "Aprilie");
        months.putIfAbsent(5, "Mai");
        months.putIfAbsent(6, "Iunie");
        months.putIfAbsent(7, "Iulie");
        months.putIfAbsent(8, "August");
        months.putIfAbsent(9, "Septembrie");
        months.putIfAbsent(10, "Octombrie");
        months.putIfAbsent(11, "Noiembrie");
        months.putIfAbsent(12, "Decembrie");

        return months;
    }

    public static <T, E> T getFirstKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    //In situatia asta exista mai multe key cu aceeasi valoare
//    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
//        Set<T> keys = new HashSet<T>();
//        for (Map.Entry<T, E> entry : map.entrySet()) {
//            if (Objects.equals(value, entry.getValue())) {
//                keys.add(entry.getKey());
//            }
//        }
//        return keys;
//    }
}
