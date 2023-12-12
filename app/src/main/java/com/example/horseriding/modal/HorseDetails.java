package com.example.horseriding.modal;
public class HorseDetails {
    private String horseName;
    private int horseImage;
    private String horseHeight;
    private String horseWeight;
    private String horseDescription;

    public HorseDetails(String horseName, int horseImage, String horseHeight, String horseWeight, String horseDescription) {
        this.horseName = horseName;
        this.horseImage = horseImage;
        this.horseHeight = horseHeight;
        this.horseWeight = horseWeight;
        this.horseDescription = horseDescription;
    }
    public String getHorseName() {
        return horseName;
    }
    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }
    public int getHorseImage() {
        return horseImage;
    }
    public void setHorseImage(int horseImage) {
        this.horseImage = horseImage;
    }
    public String getHorseHeight() {
        return horseHeight;
    }
    public void setHorseHeight(String horseHeight) {
        this.horseHeight = horseHeight;
    }
    public String getHorseWeight() {
        return horseWeight;
    }
    public void setHorseWeight(String horseWeight) {
        this.horseWeight = horseWeight;
    }
    public String getHorseDescription() {
        return horseDescription;
    }
    public void setHorseDescription(String horseDescription) {
        this.horseDescription = horseDescription;
    }
}
