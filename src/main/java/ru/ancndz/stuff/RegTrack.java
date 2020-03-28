package ru.ancndz.stuff;

import java.util.Date;
import java.util.HashSet;

public class RegTrack {
    Tracked object;
    Date dateInteractionStart;
    Date dateInteractionEnd;
    double movedMeters;

    HashSet<Tracked> interactedList = new HashSet<>();

    public RegTrack(Tracked object, double movedMeters) {
        this.object = object;
        this.movedMeters = movedMeters;
    }

    public void addInteracted(Tracked object) {
        this.interactedList.add(object);
    }

    public void setDateInteractionStart(Date date) {
        this.dateInteractionStart = date;
    }

    public void setDateInteractionEnd(Date date) {
        this.dateInteractionEnd = date;
    }

    public HashSet<Tracked> getInteractedList() {
        return this.interactedList;
    }

    public Tracked getObject() {
        return this.object;
    }

    public String getInfo() {
        return "Передвижение: \nID: " + this.object.getGeoID() +
                "\nРасстояние: " + this.movedMeters +
                "\nDateStart: " + this.dateInteractionStart +
                "\nDateEnd: " + this.dateInteractionEnd;
    }
}
