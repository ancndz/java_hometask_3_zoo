package ru.ancndz.living;

import ru.ancndz.stuff.Tracked;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Staff implements Tracked {
    private final double geoID;

    private double cordOX = 0;
    private double cordOY = 0;
    private HashSet<Tracked> inInteraction = new HashSet<>();

    private boolean inZoo = true;

    private String name;
    private int expMonths = 0;
    private String animalCareClass; //класс животного для ухода (птицы / животные / рыбы и т.д.)
    private List<Animal> careAnimals = new ArrayList<>();

    //без стажа
    public Staff(String name, String animalCareClass) {
        this.name = name;
        this.animalCareClass = animalCareClass;
        this.geoID = Math.random();
    }

    public Staff(String name, int expMonths, String animalCareClass) {
        this(name, animalCareClass);
        this.expMonths = expMonths;
    }

    public Animal getAloneAnimal() {
        for (Animal each: careAnimals) {
            each.getCareStaff().remove(this);
            if (each.getCareStaff().isEmpty()) {
                each.getCareStaff().add(this);
                //System.out.println("Этот сотрудник последний!");
                return each;
            } else {
                each.getCareStaff().add(this);
                //return false;
            }
        }
        return null;
    }

    public void addCareAnimal(Animal animal) {
        this.careAnimals.add(animal);
    }

    public boolean delCareAnimal(Animal animal) {
        try {
            this.careAnimals.remove(animal);
            return true;
        } catch (Exception e) {
            System.out.println("Удаление не завершено. Оно существует?");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public int getExpMonths() {
        return expMonths;
    }

    public String getAnimalCareClass() {
        return animalCareClass;
    }

    public List<Animal> getCareAnimals() {
        return careAnimals;
    }

    public boolean isInZoo() {
        return inZoo;
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
        this.inZoo = (this.cordOX >= 0 && this.cordOX <= 100 && this.cordOY >= -100 && this.cordOY <= 100);
        return Math.sqrt(Math.pow(OX, 2) + Math.pow(OY, 2));
    }

    //возвращает координаты
    @Override
    public double[] getCords() {
        return new double[]{this.cordOX, this.cordOY};
    }

    @Override
    public void addInteracted(Tracked person) {
        this.inInteraction.add(person);
    }

    @Override
    public void delInteracted(Tracked person) {
        try {
            this.inInteraction.remove(person);
        } catch (Exception ignored) {
        }
    }

    @Override
    public HashSet<Tracked> getInteractionSet() {
        return this.inInteraction;
    }
}
