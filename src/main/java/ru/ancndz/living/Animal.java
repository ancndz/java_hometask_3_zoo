package ru.ancndz.living;


import ru.ancndz.stuff.Journal;
import ru.ancndz.stuff.Tracked;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Animal implements Tracked {
    private final double geoID;

    private double cordOX = 0;
    private double cordOY = 0;
    private HashSet<Tracked> inIteraction = new HashSet<>();

    private String animalType; //рыба / птица и т.д.
    private String name;
    private String age;
    private boolean isSick = false; //болеет или нет
    private String character; //характер
    private String extra; // дополнительно о животном
    private List<Staff> careStaff = new ArrayList<>();
    private Journal journal = null;

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Journal getJournal() {
        return journal;
    }

    public Animal(String animalType, String name, String age, String character, String extra) {
        this(animalType, name, age);
        this.character = character;
        this.extra = extra;
    }

    public Animal(String animalType, String name, String age) {
        this.geoID = Math.random();
        this.animalType = animalType;
        this.name = name;
        this.age = age;
    }

    public void addCareStaff(Staff person) {
        this.careStaff.add(person);
    }

    public List<Staff> getCareStaff() {
        return careStaff;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }


    //в данном методе ведется удаление выбранного человека у животного. Если этот человек единственный (после удаления список пустой)
    //удаление отменяется.
    public boolean delCareStaff(Staff person) {
        this.careStaff.remove(person);
        if (this.careStaff.isEmpty()) {
            this.careStaff.add(person);
            System.out.println("Этот человек единственный ухаживает за данным животным! Операция прервана.");
            return false;
        } else return true;
    }

    //метод для удаления данного животного у каждого работника, который закреплен за ним
    public void delAllCareStaff() {
        for (Staff staff: this.careStaff) {
            if (!staff.delCareAnimal(this)) {
                //срабатывает, если у человека нет в списке заботы этого животного
                System.out.printf("\nОшибка при удалении сотрудника %s у животного %s", staff.getName(), this.getName());
            }
        }
    }

    public void animalSick() {
        this.isSick = true;
        System.out.println("Animal is sick.");
    }

    public void animalNotSick() {
        this.isSick = false;
        System.out.println("Animal is not sick!");
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public boolean isSick() {
        return isSick;
    }

    public String getCharacter() {
        return character;
    }

    public String getExtra() {
        return extra;
    }

    @Override
    public double getGeoID() {
        return this.geoID;
    }

    //возвращает пройденное расстояние, обновляет кооринаты у парсонажа
    @Override
    public double getShift(double OX, double OY) {
        this.cordOX += OX;
        this.cordOY += OY;
        return Math.sqrt(Math.pow(OX, 2) + Math.pow(OY, 2));
    }

    //возвращает координаты
    @Override
    public double[] getCords() {
        return new double[]{this.cordOX, this.cordOY};
    }

    @Override
    public void addInteracted(Tracked person) {
        this.inIteraction.add(person);
    }

    @Override
    public void delInteracted(Tracked person) {
        try {
            this.inIteraction.remove(person);
        } catch (Exception ignored) {
        }
    }

    @Override
    public HashSet<Tracked> getInteractionSet() {
        return this.inIteraction;
    }
}
