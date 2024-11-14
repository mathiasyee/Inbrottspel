package se.mathias.spel;

import java.util.Scanner;

public abstract class Entity {
    private String role;
    private int health;
    private int damage;


    public Entity(String role, int health, int damage) {
        this.role = role;
        this.health = health;
        this.damage = damage;
    }

    public String getRole() {
        return role;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void punch(Entity toPunch) {
    toPunch.takeHit(damage);
    }

    public void addDamage(int damage) {
        this.damage += damage;

    }
    public void takeHit(int damage){
        health -= damage;
        System.out.println(role +" tog " + damage + " skada. Återstående hälsa: " + health);
    }
    public boolean isConscious(){
    if (health > 0) {return true;
    }
    return false;
    }

}


