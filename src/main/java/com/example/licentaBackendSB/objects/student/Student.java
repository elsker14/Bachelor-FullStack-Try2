package com.example.licentaBackendSB.objects.student;

import com.example.licentaBackendSB.objects.sort.sortingAlgorithms.*;
import com.example.licentaBackendSB.objects.student.randomizers.nameRandomizer;
import com.example.licentaBackendSB.objects.student.randomizers.ygsRandomizer;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity //for hibernate framework
@Table  //for database
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    //needed for Statistics && Main sources

    //Fields -----------------------------------------------------------------------------------------------------------
    private Long id;
    private String nume;
    private String prenume;
    private String grupa;
    private String serie;
    private Integer an;
    private Double medie;

    //Constructor ------------------------------------------------------------------------------------------------------

    public Student(Long id, String nume, String prenume, String grupa, String serie, Integer an, Double medie) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
        this.serie = serie;
        this.an = an;
        this.medie = medie;
    }

    public Student(String nume, String prenume, String grupa, String serie, Integer an, Double medie) {
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
        this.serie = serie;
        this.an = an;
        this.medie = medie;
    }

    public Student() {}

    //GETTERs && SETTERs -----------------------------------------------------------------------------------------------
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getAn() {
        return an;
    }

    public void setAn(Integer an) {
        this.an = an;
    }

    public Double getMedie() {
        return medie;
    }

    public void setMedie(Double medie) {
        this.medie = medie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //toString ---------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return  ("id = " + id +
                "nume = '" + nume +
                ", prenume = '" + prenume +
                ", an = " + an +
                ", grupa = '" + grupa +
                ", serie = '" + serie +
                ", medie = " + medie +
                "\r\n");
    }

    //Methods for Statistics -------------------------------------------------------------------------------------------

    public static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

    public static Map <String, Integer> fillInSortAlgorithmStatistics()
    {
        Map<String, Integer> performanceStatiticsOfSortAlgorithms = new HashMap<>();

        performanceStatiticsOfSortAlgorithms.put("Collection.sort", 0);
        performanceStatiticsOfSortAlgorithms.put("List.sort", 0);
        performanceStatiticsOfSortAlgorithms.put("QuickSort", 0);
        performanceStatiticsOfSortAlgorithms.put("HeapSort", 0);
        performanceStatiticsOfSortAlgorithms.put("MergeSort", 0);
        performanceStatiticsOfSortAlgorithms.put("SelectionSort", 0);
        performanceStatiticsOfSortAlgorithms.put("InsertionSort", 0);
        performanceStatiticsOfSortAlgorithms.put("BubbleSort", 0);

        return performanceStatiticsOfSortAlgorithms;
    }

    //Methods for Students ---------------------------------------------------------------------------------------------

    public static List <Student> hardcodeStudents()
    {
        List <Student> hardcodedListOfStudents = new ArrayList<>();
        Random rand = new Random();

        //manual harcode to test search query => check StudentRepository
        hardcodedListOfStudents.add(new Student(1L, "Iancu", "Jianu", "445", "E", 4, 10D));

        for(long i = 1; i < 10; i++)
        {
            String group = ygsRandomizer.getRandomGroup();
            String series = ygsRandomizer.getRandomSeries();
            int year = Character.getNumericValue(group.charAt(1));

            hardcodedListOfStudents.add(new Student(
                    (i + 1),
                    nameRandomizer.getAlphaNumericString(5),                //nume
                    nameRandomizer.getAlphaNumericString(5),                //prenume
                    group,                                                     //grupa, old way: RandomAlphaNumericString.getAlphaNumericString(3)
                    series,                                                    //serie, old way : RandomAlphaNumericString.getAlphaNumericString(1)
                    year,                                                      //an, old way: ((int) (Math.random() * (5 - 1)) + 1)
                    (1D + (10D - 1D) * rand.nextDouble())                      //medie
            ));
        }

        return hardcodedListOfStudents;
    }

    public static void sortStudents(List <Student> tmp)
    {
        tmp.sort((o1, o2) -> o2.getMedie().compareTo(o1.getMedie()));
    }

    public static void printStudents(List <Student> tmp)
    {
        System.out.println(Arrays.toString(tmp.toArray()));
    }

    //Sorting Algorithms returning execution time ----------------------------------------------------------------------

    public static long collectionSort(List <Student> tmp)
    {
        long timeStarted, timeEnded;

        timeStarted = System.nanoTime();
        Collections.sort(tmp, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getMedie().compareTo(o1.getMedie());
            }
        });
        timeEnded = System.nanoTime();
        //System.out.println("Collection.sort: sorting time => " + (timeEnded - timeStarted) + " ns");
        return (timeEnded - timeStarted);
    }

    public static long listSort(List <Student> tmp)
    {
        long timeStarted, timeEnded;

        timeStarted = System.nanoTime();
        tmp.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getMedie().compareTo(o1.getMedie());
            }
        });
        timeEnded = System.nanoTime();
        //System.out.println("List.sort: sorting time => " + (timeEnded - timeStarted) + " ns");
        return (timeEnded - timeStarted);
    }

    public static long quickSort(List <Student> tmp)
    {
        long timeStarted, timeEnded;

        timeStarted = System.nanoTime();
        QuickSort.quickSort(tmp, 0, tmp.size() - 1);
        timeEnded = System.nanoTime();
        //System.out.println("QuickSort: sorting time => " + (timeEnded - timeStarted) + " ns");
        return (timeEnded - timeStarted);
    }

    public static long heapSort(List <Student> tmp)
    {
        long timeStarted, timeEnded;

        timeStarted = System.nanoTime();
        HeapSort.heapSort(tmp);
        timeEnded = System.nanoTime();
        //System.out.println("HeapSort: sorting time => " + (timeEnded - timeStarted) + " ns");
        return (timeEnded - timeStarted);
    }

    public static long mergeSort(List <Student> tmp)
    {
        long timeStarted, timeEnded;

        timeStarted = System.nanoTime();
        MergeSort.mergeSort(tmp, 0, tmp.size() - 1);
        timeEnded = System.nanoTime();
        //System.out.println("MergeSort: sorting time => " + (timeEnded - timeStarted) + " ns");
        return (timeEnded - timeStarted);
    }

    public static long selectionSort(List <Student> tmp)
    {
        long timeStarted, timeEnded;

        timeStarted = System.nanoTime();
        SelectionSort.selectionSort(tmp);
        timeEnded = System.nanoTime();
        //System.out.println("SelectionSort: sorting time => " + (timeEnded - timeStarted) + " ns");
        return (timeEnded - timeStarted);
    }

    public static long insertionSort(List <Student> tmp)
    {
        long timeStarted, timeEnded;

        timeStarted = System.nanoTime();
        InsertionSort.insertionSort(tmp);
        timeEnded = System.nanoTime();
        //System.out.println("InsertionSort: sorting time => " + (timeEnded - timeStarted) + " ns");
        return (timeEnded - timeStarted);
    }

    public static long bubbleSort(List <Student> tmp)
    {
        long timeStarted, timeEnded;

        timeStarted = System.nanoTime();
        BubbleSort.bubbleSort(tmp);
        timeEnded = System.nanoTime();
        //System.out.println("BubbleSort: sorting time => " + (timeEnded - timeStarted) + " ns");
        return (timeEnded - timeStarted);
    }

}

