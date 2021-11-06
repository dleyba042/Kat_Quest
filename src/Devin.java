public class Devin extends Creature{

    private int intelligence;

    public Devin(int health, int attack, int defense, String name, int intelligence) {
        super(health, attack, defense,name);
        this.intelligence = intelligence;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void damageIntelligence(int intelligence) {
        this.intelligence -= intelligence;
    }
}
