package ru.ancndz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ancndz.living.Animal;
import ru.ancndz.living.Staff;
import ru.ancndz.stuff.Tracked;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {

    private Zoo zoo;

    @BeforeEach
    void setUp() {
        Animal johnBird = new Animal("bird", "jonathan livingston", "2 years");
        Animal eddieFish = new Animal("fish", "Clown Eddie", "1.2 years");
        Animal SnowBear = new Animal("bear", "Snowball", "5 years");

        this.zoo = new Zoo("Z-O(3)");
        //теперь журнал создается сам при вызове этой функции
        this.zoo.addAnimal(johnBird);
        this.zoo.addAnimal(eddieFish);
        this.zoo.addAnimal(SnowBear);

        Staff alexStaff = new Staff("Alex", 6, "Birds");
        Staff johnStaff = new Staff("John", "Animals");
        Staff gordonStaff = new Staff("Gordon", 24, "Birds");
        Staff bobStaff = new Staff("Bob", 15, "Fish");

        this.zoo.addStaff(alexStaff);
        this.zoo.addStaff(johnStaff);
        this.zoo.addStaff(gordonStaff);
        this.zoo.addStaff(bobStaff);

    }

    @Test
    void testGetStaffPerson() {
        assertEquals(this.zoo.getStaff().get(0), this.zoo.getStaffPerson("Alex"));
        assertEquals(this.zoo.getStaff().get(2), this.zoo.getStaffPerson("Gordon"));
        assertNotEquals(this.zoo.getStaff().get(0), this.zoo.getStaffPerson("Bob"));
    }

    @Test
    void testGetAnimalByName() {
        assertEquals(this.zoo.getAnimals().get(1), this.zoo.getAnimalByName("Clown Eddie"));
        assertEquals(this.zoo.getAnimals().get(2), this.zoo.getAnimalByName("Snowball"));
        assertNotEquals(this.zoo.getAnimals().get(0), this.zoo.getAnimalByName("Snowball"));
    }

    @Test
    void testOnWork() {
        //Gordon
        Tracked testEntity = this.zoo.getStaffPerson("Gordon");
        //прямая проверка
        this.zoo.getGeoUpdate(testEntity, +4, -1);
        assertTrue(this.zoo.getStaffPerson("Gordon").isInZoo());
        //обратная проверка
        this.zoo.getGeoUpdate(testEntity, -1000, -1000);
        assertFalse(this.zoo.getStaffPerson("Gordon").isInZoo());

        //проверка наличия записи что кто-то покинул карантин(зачеркнуто) зоопарк
        //false - покинул
        assertFalse(this.zoo.getTrackList().get(1).isInZoo());
        //обратная
        assertTrue(this.zoo.getTrackList().get(0).isInZoo());
    }

    @Test
    void testGetGeoUpdate() {
        //Gordon
        Tracked testEntity = this.zoo.getStaffPerson("Gordon");
        this.zoo.getGeoUpdate(testEntity, +4, -5);

        Tracked testAnimal = this.zoo.getAnimalByName("Snowball");
        this.zoo.getGeoUpdate(testAnimal, 1, -8);

        //проверка координат
        assertEquals(this.zoo.getStaffPerson("Gordon").getCords()[0], 4);

        //заполнение журнала
        assertEquals(this.zoo.getTrackList().get(0).getObject().getGeoID(), testEntity.getGeoID());

        //проверка на контакт
        //get(1)  - передвижение снежка, у этой записи берем лист с кем он контактировал
        assertTrue(this.zoo.getTrackList().get(1).getInteractedList().contains(testEntity));

        //обратная проверка
        this.zoo.getGeoUpdate(testAnimal, 10, -7);
        assertFalse(this.zoo.getTrackList().get(2).getInteractedList().contains(testEntity));
    }

}