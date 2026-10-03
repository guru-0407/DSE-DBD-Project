package com.mediconnect.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "doctors")
public class Doctor {
    @Id private String id;
    private String name;
    private String department;
    private String specialization;
    private double rating;
    private int experience;
    private boolean available;

    public Doctor() {}
    public String getId(){ return id; }
    public void setId(String v){ id=v; }
    public String getName(){ return name; }
    public void setName(String v){ name=v; }
    public String getDepartment(){ return department; }
    public void setDepartment(String v){ department=v; }
    public String getSpecialization(){ return specialization; }
    public void setSpecialization(String v){ specialization=v; }
    public double getRating(){ return rating; }
    public void setRating(double v){ rating=v; }
    public int getExperience(){ return experience; }
    public void setExperience(int v){ experience=v; }
    public boolean isAvailable(){ return available; }
    public void setAvailable(boolean v){ available=v; }
}
