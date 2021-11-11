package com.example.mobile_project.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String place;

    private  String tarif;

    private  String nb_places;

    private  String date;
    private  String type_event;
    private  String rating;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getNb_places() {
        return nb_places;
    }

    public void setNb_places(String nb_places) {
        this.nb_places = nb_places;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public Event(  String name, String place, String tarif, String nb_places, String date, String type_event, String rating) {

        this.name = name;
        this.place = place;
        this.tarif = tarif;
        this.nb_places = nb_places;
        this.date = date;
        this.type_event = type_event;
        this.rating = rating;
    }

    public Event(  String name, String place, String tarif, String nb_places, String type_event, String rating) {

        this.name = name;
        this.place = place;
        this.tarif = tarif;
        this.nb_places = nb_places;

        this.type_event = type_event;
        this.rating = rating;
    }

    public  Event(){

    }

    public  Event( String name, String place){
this.name = name;
this.place = place;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", tarif='" + tarif + '\'' +
                ", nb_places='" + nb_places + '\'' +
                ", date='" + date + '\'' +
                ", type_event='" + type_event + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
