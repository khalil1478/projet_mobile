package com.example.mobile_project.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mobile_project.entities.Event;
import com.example.mobile_project.entities.User;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void addEvent(Event event);

    @Delete
    void deleteEvent(Event event);

    @Query("SELECT * FROM Event")
    List<User> getEventList();

    @Query("SELECT * FROM Event")
    List<Event> getEvents();
}
