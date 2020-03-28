package ru.ancndz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ancndz.living.Animal;
import ru.ancndz.living.Staff;
import ru.ancndz.stuff.Journal;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {

    @BeforeEach
    void setUp() {
        List<Animal> zooAnimals = new ArrayList<>();
        List<Journal> zooJournals = new ArrayList<>();

        zooAnimals.add(new Animal("bird", "jonathan livingston", "2 years"));
        zooJournals.add(new Journal(zooAnimals.get(0)));
        zooAnimals.add(new Animal("fish", "Clown Eddie", "1.2 years"));
        zooJournals.add(new Journal(zooAnimals.get(1)));
        zooAnimals.add(new Animal("bear", "Snowball", "5 years"));
        zooJournals.add(new Journal(zooAnimals.get(2)));

        List<Staff> zooStaff = new ArrayList<>();

        zooStaff.add(new Staff("Person 1", 10, "Birds"));
        zooStaff.add(new Staff("Person 2", 10, "Birds"));
        zooStaff.add(new Staff("Person 3", 10, "Birds"));
        zooStaff.add(new Staff("Person 4", 10, "Birds"));

        Zoo zoo = new Zoo("Z-O(3)", zooAnimals, zooStaff, zooJournals);
    }

    @Test
    void getGeoUpdate() {
    }

    @Test
    void getTrackList() {
    }
}