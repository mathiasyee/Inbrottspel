package se.mathias.spel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    @org.junit.jupiter.api.Test
    void takeHit() {
    }

    @org.junit.jupiter.api.Test
    void isConscious() {
    }
    @Test
    public void whenTakehitShouldTakeDamage (){
        Resident resident = new Resident("Resident", 10, 4);
        resident.takeHit(4);
        int expected = 6;
        assertEquals(expected,resident.getHealth());
    }
    @Test
    public void ifIsConscious (){
        Resident resident = new Resident("resident", 10, 4);
        Burglar burglar = new Burglar("burglar", 10, 3);
        resident.punch(burglar);

        boolean expected = true;
        assertEquals(expected, resident.isConscious());
        assertEquals(expected, burglar.isConscious());
    }
    @Test
    public void ifNotConscious(){
        Resident resident = new Resident("resident", 10, 10);
        Burglar burglar = new Burglar("burglar", 10, 3);
        resident.punch(burglar);
        boolean expected = false;
        assertEquals(expected, burglar.isConscious());
    }
}