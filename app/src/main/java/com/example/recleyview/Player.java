package com.example.recleyview;

import com.google.gson.annotations.SerializedName;

public class Player {
    @SerializedName("idPlayer") //nama  dari API
    private String idPlyaer;
    @SerializedName("strNationality")
    private String negara;
    @SerializedName("strPlayer")
    private String name;
    @SerializedName("dateBorn")
    private String birthDate;
    @SerializedName("strBirthLocation")
    private String birthPlace;
    @SerializedName("strDescriptionEN")
    private String description;
    @SerializedName("strThumb")
    private String imagePath;


    public Player(String idPlyaer, String negara, String name, String birthDate, String birthPlace, String description, String imagePath) {
        this.idPlyaer = idPlyaer;
        this.negara = negara;
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getIdPlyaer() {
        return idPlyaer;
    }

    public String getNegara() {
        return negara;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}
