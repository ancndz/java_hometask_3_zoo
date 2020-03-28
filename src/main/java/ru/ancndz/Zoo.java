package ru.ancndz;


import ru.ancndz.living.Animal;
import ru.ancndz.living.Staff;
import ru.ancndz.stuff.Journal;
import ru.ancndz.stuff.RegTrack;
import ru.ancndz.stuff.Tracked;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Zoo {
    private List<RegTrack> trackList = new ArrayList<>();

    private String title;
    private List<Animal> animals = new ArrayList<>();
    private List<Staff> staff = new ArrayList<>();
    private List<Journal> journals = new ArrayList<>();

    public Zoo(String title) {
        this.title = title;
    }

    public Zoo(String title, List<Animal> animals, List<Staff> staff, List<Journal> journals) {
        this(title);
        this.animals = animals;
        this.staff = staff;
        this.journals = journals;
    }

    //считаем что ккординаты приходят разные, если человек просто стоит - ничего не приходит
    public void getGeoUpdate(Tracked trackedEntity, double x, double y) {
        //пройденное расстояние
        double moveMeters = trackedEntity.getShift(x, y);
        //создали запись
        RegTrack track = new RegTrack(trackedEntity, moveMeters);
        //ставим метку, в зоопарке энтити или нет
        track.setInZoo(trackedEntity.isInZoo());
        //получаем новые координаты
        double[] trackedCords = trackedEntity.getCords();
        //получаем список всех отслеживаемых что бы пройтись по ним
        HashSet<Tracked> allTracked = new HashSet<>(this.animals);
        allTracked.addAll(this.staff);

        for (Tracked each : allTracked) {
            //сам с собой не считается)
            if (!trackedEntity.equals(each)) {
                //берем кординаты каждого
                double[] eachCords = each.getCords();
                //проверяем взаимодействие
                if (Math.abs(trackedCords[0] - eachCords[0]) <= 3 || Math.abs(trackedCords[1] - eachCords[1]) <= 3) {
                    //добавили в запись взаимодействие с каждым
                    track.addInteracted(each);
                    //время начала взаимодействия
                    track.setDateInteractionStart(new Date());
                    //добавили каждому, что он в контакте сейчас
                    each.addInteracted(trackedEntity);
                    trackedEntity.addInteracted(each);
                    //если в контакте и отдалился (закончился контакт)
                } else if (trackedEntity.getInteractionSet().contains(each)) {
                    //добавляем дату конца взаимодействия
                    track.setDateInteractionEnd(new Date());
                    //удаляем из листов взаимодействия
                    trackedEntity.delInteracted(each);
                    each.delInteracted(trackedEntity);
                }
            }
        }
        //добавляем запись в лист со всеми записями
        this.trackList.add(track);
    }

    public void addStaff(Staff person) {
        this.staff.add(person);
    }

    //удалить сотрдуника можем только если у него нет таких животных, у которого этот сотрудник - последний.
    //метод у сотрудника getAloneAnimal возвращает животное, если такое существует и null если таких животных нет
    public void fireStaff(Staff person) {
        Animal caredAnimal = person.getAloneAnimal();
        if (caredAnimal == null) {
            this.staff.remove(person);
        } else {
            System.out.printf("Нельзя удалить работника! он ухаживает за %s!", caredAnimal.getName());
        }
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
        this.journals.add(new Journal(animal));
    }

    //при удалении животного сначала чистится это животное у каждого работника из списка заботы
    public void delAnimal(Animal animal) {
        animal.delAllCareStaff();
        this.animals.remove(animal);
    }

    public List<RegTrack> getTrackList() {
        return trackList;
    }

    public String getTitle() {
        return title;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public Staff getStaffPerson(String name) {
        for (Staff each : this.staff) {
            if (each.getName().equals(name)) {
                return each;
            }
        }
        return null;
    }

    public Animal getAnimalByName(String name) {
        for (Animal each : this.animals) {
            if (each.getName().equals(name)) {
                return each;
            }
        }
        return null;
    }

    public List<Journal> getJournals() {
        return journals;
    }
}
