package ru.ancndz;

import ru.ancndz.living.Animal;
import ru.ancndz.living.Staff;
import ru.ancndz.stuff.Journal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

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

        //добавили первому работнику в список заботы нашего медведя
        zoo.getStaff().get(0).addCareAnimal(zooAnimals.get(2));
        zoo.getAnimals().get(2).addCareStaff(zoo.getStaff().get(0));
        //животное заболело, сотрудник, который за ним закреплен вносит правки в журнал животного
        zoo.getAnimals().get(2).animalSick();
        zoo.getAnimals().get(2).getJournal().addPage("First page in sick history", zoo.getAnimals().get(2).getCareStaff().get(0));
        //пытаемся уволить сотрудника
        zoo.fireStaff(zoo.getStaff().get(0));

    }

}
