
import java.util.Scanner;
import java.util.ArrayList;

public class Kiki {

    private ArrayList<Item> inventory = new ArrayList<>();
    private int foodLevel;
    private int cutePoints;
    private int intelligence;
    private int strength;
    private int defense;
    private Scanner answer = new Scanner(System.in);

    public Kiki(int foodLevel, int cutePoints, int intelligence, int strength, int defense) {
        this.foodLevel = foodLevel;
        this.cutePoints = cutePoints;
        this.intelligence = intelligence;
        this.strength = strength;
        this.defense = defense;
        inventory.add(new Item("Cat Treat", "Restores 5 food level"));
        inventory.add(new Item("Cat Nip", "Raises Intelligence by 5"));
    }



    public void beg() {

        int winningNumber = (int) ((Math.random() * this.cutePoints) + (Math.random() * 5));
        int begWinner = (int) ((Math.random() * this.cutePoints) + (Math.random() * this.intelligence / 4));

        if (begWinner > winningNumber) {
            System.out.println("Devin gives in to your begging and gives you a cat treat");
            inventory.add(new Item("Cat Treat", "Restores 5 food level"));

        } else {

            System.out.println("Your begging has no effect on devin.....");
            System.out.println("Try raising your intelligence or cute point stats..");
        }


    }

    public int getFoodLevel() {
        return this.foodLevel;
    }

    public void upFoodLevel(int foodLevel) {
        this.foodLevel += foodLevel;
    }

    public void damageFoodLevel(int foodLevel) {
        this.foodLevel -= foodLevel;
    }

    public void getAttacked(int hit) {
        this.foodLevel -= hit;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void upCutePoints(int cutePoints) {
        this.cutePoints += cutePoints;
    }

    public void damageIntelligence(int intelligence) {
        this.intelligence -= intelligence;
    }

    public void upIntelligence(int intelligence) {
        this.intelligence += intelligence;
    }

    public void upStrength(int strength) {
        this.strength += strength;
    }

    public void upDefense(int defense) {
        this.defense += defense;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public void addToInventory(Item item) {
        this.inventory.add(item);
    }

    public int getStrength() {
        return this.strength;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getCutePoints() {
        return cutePoints;
    }

    public void checkStats() {
        System.out.println();
        System.out.println("Your stats are currently as follows:");
        System.out.println("Cute Points................. " + this.cutePoints);
        System.out.println("Intelligence................ " + this.intelligence);
        System.out.println("Strength.................... " + this.strength);
        System.out.println("Defense..................... " + this.defense);
        System.out.println("Food Level.................. " + this.foodLevel);
        System.out.println();
    }

    public void displayChoices() {
        System.out.println();
        System.out.println("........(1.)Check Stats.........");
        System.out.println("...........(2.)Hunt.............");
        System.out.println("......(3.)Scrounge around.......");
        System.out.println("............(4.)Beg.............");
        System.out.println("...........(5.)Annoy............");
        System.out.println(".........(6.)Use Item...........");
        System.out.println();
    }

    public void levelUp() {
        System.out.println();
        System.out.println("Congratulations you have leveled up which of your stats will you increase?");
        System.out.println("1.cute points(+2)");
        System.out.println("2.intelligence(+2)");
        System.out.println("3.strength(+2)");
        System.out.println("4.defense(+2)");
        System.out.println("5.food level(+10)");
        System.out.println();

        int choice = answer.nextInt();

        switch (choice) {

            case 1:
                upCutePoints(2);
                break;
            case 2:
                upIntelligence(2);
                break;
            case 3:
                upStrength(2);
                break;
            case 4:
                upDefense(2);
                break;
            case 5:
                upFoodLevel(10);
                break;
            default:
                System.out.println("Invalid input");
        }

        System.out.println("After leveling up..............");
        this.checkStats();
    }

    public int getChoice() {
        if (this.getFoodLevel() <= 0) {
            return 99;
        }
        System.out.println("What will you do next?");
        this.displayChoices();
        int choice = answer.nextInt();
        answer.nextLine();
        return choice;
    }


    public void selectItem(Creature creature, boolean fromMain, String spell) {

        boolean defBoost = spell.equals("defBoost");
        boolean usedGun = false;

        if (this.inventory.size() == 0) {

            System.out.println("You currently hold no items");
        } else {

            String input;

            System.out.println("Inventory Includes:");

            for (Item item : this.inventory) {
                System.out.println(item.getName() + ": " + item.getProperties());
            }

            System.out.println("type an item name above");
            System.out.println("or type none to exit.");

            input = answer.nextLine();

            switch (input.toLowerCase()) {

                case "cat treat":
                    System.out.println("Restoring food level");
                    this.upFoodLevel(5);
                    this.inventory.remove(getItem("cat treat"));
                    break;
                case "half eaten burger patty":
                    System.out.println("Restoring food level");
                    this.upFoodLevel(10);
                    this.inventory.remove(getItem("half eaten burger patty"));
                    break;
                case "cat nip":
                    System.out.println("Restoring intelligence");
                    this.upIntelligence(5);
                    this.inventory.remove(getItem("cat nip"));
                    break;
                case "large animal carcass":
                    System.out.println("Restoring food level");
                    this.upFoodLevel(20);
                    this.inventory.remove(getItem("large animal carcass"));
                    break;
                case "small animal carcass":
                    System.out.println("Restoring food level");
                    this.upFoodLevel(10);
                    this.inventory.remove(getItem("small animal carcass"));
                    break;
                case "gun":
                    if (!fromMain && getItem("bullet") != null) {
                        creature.killCreature();
                        System.out.println("You aim down the sites and put one between the " + creature.getName() + "'s eyes");
                        this.inventory.remove(getItem("bullet"));
                        usedGun = true;
                    } else {
                        System.out.println("You are out of bullets");
                    }
                    break;
                case "none":
                    break;

                default:
                    System.out.println("Invalid input");
            }

            if (!fromMain && !usedGun) {
                //Creature attacks
                int hit = creature.getAttack();
                hit = hit - (int) (Math.random() * (this.defense / 4) + ((defBoost) ? 10 : 0));
                if (hit <= 0) {
                    System.out.println(creature.getName() + "'s attack MISSES!");
                } else {
                    this.getAttacked(hit);
                    System.out.println(creature.getName() + " hits for " + hit + " damage");
                }
            }
        }


    }

    public Item getItem(String name) {

        Item item = null;

        for (Item i : this.inventory) {
            if (i.getName().toLowerCase().equals(name)) {
                item = i;

            }
        }

        return item;
    }
}
