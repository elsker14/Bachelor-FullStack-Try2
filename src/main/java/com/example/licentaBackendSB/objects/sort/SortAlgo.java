package com.example.licentaBackendSB.objects.sort;

public class SortAlgo {

    //needed for Statistics source
    private String sortingAlgoName;
    private long duration;

    public SortAlgo() { }

    public SortAlgo(String sortingAlgoName, long duration) {
        this.sortingAlgoName = sortingAlgoName;
        this.duration = duration;
    }

    public String getSortingAlgoName() {
        return sortingAlgoName;
    }

    public void setSortingAlgoName(String sortingAlgoName) {
        this.sortingAlgoName = sortingAlgoName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
