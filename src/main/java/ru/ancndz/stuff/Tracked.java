package ru.ancndz.stuff;

import java.util.HashSet;

public interface Tracked {
    double getGeoID();

    double getShift(double OX, double OY);
    double[] getCords();

    void addInteracted(Tracked person);
    void delInteracted(Tracked person);
    HashSet<Tracked> getInteractionSet();

    boolean isInZoo();
}
