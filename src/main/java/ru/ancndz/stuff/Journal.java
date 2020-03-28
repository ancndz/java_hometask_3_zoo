package ru.ancndz.stuff;

import ru.ancndz.living.Animal;
import ru.ancndz.living.Staff;

import java.util.ArrayList;
import java.util.List;


public class Journal {
    private Animal animal;
    private List<String> pages = new ArrayList<>();
    private Staff lastModifiedBy = null;

    public Journal(Animal animal) {
        this.animal = animal;
        animal.setJournal(this);
    }

    public void addPage(String page, Staff staff) {
        this.pages.add(page);
        this.lastModifiedBy = staff;
    }

    public boolean delPage(int pos) {
        try {
            this.pages.remove(pos);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public Animal getAnimal() {
        return animal;
    }

    public List<String> getPages() {
        return pages;
    }

    public Staff getLastModifiedBy() {
        return lastModifiedBy;
    }
}
