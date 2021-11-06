import java.util.Scanner;

public class Battle {

    public static void executeBattle(Creature creature,Kiki player) {

        Scanner answer = new Scanner(System.in);

        boolean wins = false;
        int battleChoice;
        String spellCast = "";


        int kikiBeginIntelligence = player.getIntelligence();


        boolean strongCreature = (creature.getHealth() > 25) || (creature.getAttack() > 20);




        System.out.println("You come upon a " + creature.getName() + " and prepare for attack");
        if (strongCreature) {
            System.out.println("This is a fierce looking Beast, be cautious....");
        }

        while ((creature.getHealth()) > 0 && (player.getFoodLevel() > 0)) {

            if (player.getFoodLevel() < 0) {
                break;
            }


            System.out.println("What do you do next?");
            System.out.println("Current food level: " + player.getFoodLevel());
            System.out.println("Current intelligence: " + player.getIntelligence());
            System.out.println("**************");
            System.out.println("1.Use item \n" +
                    "2. Attack  \n" +
                    "3. Defend \n" +
                    "4. Cast Spell  ");
            System.out.println("**************");
            System.out.println();

            battleChoice = answer.nextInt();
            answer.nextLine();

            switch (battleChoice) {

                case 1:
                    player.selectItem(creature, false, spellCast);
                    if (spellCast.equals("strBoost") || spellCast.equals("defBoost")) {
                        spellCast = "";
                        System.out.println("Spell is wearing off....");
                    }
                    break;
                case 2:
                    System.out.println("ATTACK!!!");
                    attackSequence(creature,player, spellCast);
                    if (spellCast.equals("strBoost") || spellCast.equals("defBoost")) {
                        spellCast = "";
                        System.out.println("Spell is wearing off....");
                    }
                    break;
                case 3:
                    System.out.println("Defend");
                    attackSequenceDefending(creature,player,spellCast);
                    if (spellCast.equals("defBoost")) {
                        spellCast = "";
                    }
                    System.out.println("Spell is wearing off....");
                    break;
                case 4:
                    System.out.println("cast spell");
                    if (!spellCast.equals("")) {
                        System.out.println("You already have an active spell cast");
                    } else {
                        spellCast = castSpell(player, answer);
                    }
                    break;
                default:
                    System.out.println("Invalid input");
            }

        }


        if ((creature.getHealth() <= 0) && (player.getFoodLevel() > 0)) {
            wins = true;
        }


        if (wins) {
            System.out.println("You have vanquished the " + creature.getName());
            if (strongCreature) {
                System.out.println(creature.getName() + " was a strong beast");
                System.out.println("It drops a healthy portion of meat");
                player.addToInventory(new Item("Large Animal Carcass", "Restores 20 food Level"));

            } else {
                System.out.println(creature.getName() + " was easy prey..");
                System.out.println("It drops a small portion of meat");
                player.addToInventory(new Item("Small Animal Carcass", "Restores 10 food Level"));
            }
            player.setIntelligence(kikiBeginIntelligence);
            player.levelUp();
        } else {
            System.out.println("You were lost in the scuffle");
        }

    }

    public static void attackSequence(Creature creature,Kiki player, String spell) {

        boolean strBoost = spell.equals("strBoost");
        boolean defBoost = spell.equals("defBoost");
        boolean bkEmplee = creature.getName().equalsIgnoreCase("bk employee");
        boolean bkManager = creature.getName().equalsIgnoreCase("bk manager");


        //kiki attacks
        int hit = (int) ((Math.random() * player.getStrength() + (player.getStrength() / 4)) + ((strBoost) ? 10 : 0));

        creature.setHealth(hit - (int) (Math.random() * (creature.getDefense() / 2)));
        System.out.println("you hit for " + hit + " damage");

        if (creature.getHealth() < 0 || player.getFoodLevel() < 0) {
            System.out.println("you land a critical Hit!!!");
        } else {


            //Creature attacks
            hit = creature.getAttack();
            hit = hit - (int) ((Math.random() * (player.getDefense() / 4)) - ((defBoost) ? 10 : 0));
            if (hit <= 0) {
                System.out.println(creature.getName() + "'s attack MISSES!");
            } else {
                player.getAttacked(hit);
                if(bkEmplee){
                    System.out.println("The employee grabs his spatula and hurls it towards your face!");
                }else if(bkManager){
                    System.out.println("The manager stabs at you with his mighty pen!");
                }
                System.out.println(creature.getName() + " hits for " + hit + " damage");
            }
        }

    }

    public static void attackSequenceDefending(Creature creature,Kiki player, String spell) {

        boolean defBoost = spell.equals("defBoost");
        boolean bkEmplee = creature.getName().equalsIgnoreCase("bk employee");
        boolean bkManager = creature.getName().equalsIgnoreCase("bk manager");

        int hit;

        //kiki attacks
        int defense = (int) ((Math.random() * player.getDefense() + (player.getIntelligence() / 2)) + ((defBoost) ? 10 : 0));
        System.out.println("You brace for impact!");


        //Creature attacks
        hit = creature.getAttack();
        hit = hit - (int) (Math.random() * defense) + (player.getDefense() / 2);
        if (hit <= 0) {
            System.out.println(creature.getName() + "'s attack MISSES!");
        } else {
            player.getAttacked(hit);
            if(bkEmplee){
                System.out.println("The employee flings a burger pattie at you!");
            }else if(bkManager){
                System.out.println("The manager swings his clipboard wildly!");
            }
            System.out.println(creature.getName() + " hits for " + hit + " damage");
        }
    }

    public static String castSpell(Kiki player, Scanner answer) {

        int input;

        if (player.getIntelligence() >= 5) {

            System.out.println("Spells Available: ");
            System.out.println("(1.) Boost Strength");
            System.out.println("(2.) Boost Defense");
            System.out.println("Type a number or 0 to exit");

            input = answer.nextInt();
            answer.nextLine();
            switch (input) {

                case 0:
                    break;

                case 1:
                    System.out.println("You start to channel all of your strength in preparation for the next attack!");
                    System.out.println("5 intelligence spent");
                    player.damageIntelligence(5);
                    return "strBoost";
                case 2:
                    System.out.println("You tighten up and raise your defensive strength!");
                    System.out.println("5 intelligence spent");
                    player.damageIntelligence(5);
                    return "defBoost";

                default:
                    System.out.println("Bad input soldier");
                    break;
            }

        } else {

            System.out.println("Not enough intelligence left to cast a spell.");
        }

        return "null";
    }

}
