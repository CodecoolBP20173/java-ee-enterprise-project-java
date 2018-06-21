package com.codecool.library.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Place extends BaseModel {
    private String name;

//    @OneToMany(mappedBy = "place")
//    private List<BookInstance> bookInstances = new ArrayList<>();

    public Place() {
    }

    public Place(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    //    public List<BookInstance> getBookInstances() {
//        return Collections.unmodifiableList(bookInstances);
//    }
}

