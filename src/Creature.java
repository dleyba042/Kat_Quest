public class Creature {

    private int health;
    private int attack;
    private int defense;
    private String name;

    public Creature() {
    }

    public Creature(int health, int attack, int defense, String name) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return (int)(Math.random() * this.attack + (this.attack/6));
    }

    public int getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public void setHealth(int hit) {
        this.health -= hit;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void killCreature(){
        this.health = 0;
    }
}
