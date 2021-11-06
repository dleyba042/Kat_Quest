import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);
    public static int choice;
    public static Creature randomCreature;
    public static int randomNum;
    public static int turnNumber = 1;

    public static void main(String[] args) {

         Devin devin = new Devin(80,20,20,"Devin", 30);
         Kiki kimhari = new Kiki(80,20,14,44,46);


        System.out.println("You awaken in a daze and are shocked to see that you \n" +
        "have taken on the appearance of a cat. Your belly aches \n" + "with hunger");
        System.out.println("YOUR intelligence is " + kimhari.getIntelligence());
        choice = kimhari.getChoice();

        while(kimhari.getFoodLevel() > 0 && (choice != 0)) {

            randomNum = (int)(Math.random() * 100) ;

            if(turnNumber < 15) {

                randomCreature = generateWeakCreature();

            }else{
                randomCreature = generateStrongCreature();
            }

            System.out.println("Turn Number: " + turnNumber);
            gameDecision(choice,kimhari,randomCreature,devin);
            choice = kimhari.getChoice();
            turnNumber++;

        }

        System.out.println("GAME OVER");

    }

    public static void gameDecision(int choice, Kiki player, Creature monster, Devin devin){

        if(player.getFoodLevel() <= 0){
            return;
        }


        switch(choice){

            case 1: player.checkStats();
                break;
            case 2:
                    Battle.executeBattle(monster,player);
                break;
            case 3: Scrounge.scrounge(((int)(Math.random() * 4) + 1),player);
                break;
            case 4: player.beg();
                break;
            case 5: Annoy.annoy(player,devin);
                break;
            case 6: player.selectItem(monster, true, "");
                break;
            case 99:
                break;
            default:
                System.out.println("Invalid input");
        }


    }

    public static Creature generateWeakCreature(){

        String[] names = {"Bird", "Mouse", "Squirrel"};

        int health = (int)(Math.random() * 15) + 5;
        int attack = (int)(Math.random() * 10) + 5;
        int defense = (int)(Math.random() * 10) + 5;
        String name = names[(int)(Math.random() * 3)];

        return new Creature(health,attack,defense,name);
    }

    public static Creature generateStrongCreature(){

        String[] names = {"Snake", "Tiger"};

        int health = (int)(Math.random() * 30) + 15;
        int attack = (int)(Math.random() * 25) + 15;
        int defense = (int)(Math.random() * 25) + 15;
        String name = names[(int)(Math.random() * 2)];

        return new Creature(health,attack,defense,name);
    }

}
