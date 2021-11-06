import java.util.Scanner;

public class Scrounge {


    public static void scrounge(int num, Kiki player) {

        int input;
        String strInput;
        Scanner answer = new Scanner(System.in);

        switch (num) {

            case 1:

                System.out.println("You were able to make it out of the apartment and head towards \n" +
                        "Burger King, one of your prime locations for scrounging around........... \n" +
                        "You begin to rummage through the trash..."
                        );
                if((int)(Math.random() * player.getIntelligence()) > (player.getIntelligence()/2)){
                    System.out.println("You uncover two half eaten burger patties and a round of" +
                            "Ammunition. Score!");
                    player.addToInventory(new Item("Half Eaten Burger Patty", "Restores 10 food level"));
                    player.addToInventory(new Item("Bullet", "Needed to fire GUN"));
                }else{
                    System.out.println("But you only find refuse that makes your stomach turn");
                }

                Item hasKeycard = player.getItem("yellow key card");

                if(hasKeycard == null){

                    System.out.println("A strange yellow light emanates from a key-slot in the backdoor of the Burger King \n");
                    System.out.println("Your curiosity is piqued, and you try to enter. \n" +
                            "Despite your attempts you find no way into the elusive Burger King Kitchen");
                }else{

                    System.out.println("You examine the key-card in your inventory and notice that it has \n" +
                            "the same yellow tinge as the key-slot before you. The key-card opens the door \n" +
                            "with ease and you enter Burger King with the thrill of the hunt.");

                    System.out.println("You realize that you don't have long and in order to avoid detection\n" +
                            " you make the decision to...");
                    System.out.println("(1.) Pillage Food Stores (2.) Investigate Back Office");

                    int choice = answer.nextInt();
                    answer.nextLine();


                    if(choice == 1){

                        System.out.println("You enter the refrigerator and see a mountain of food ripe for the taking.\n" +
                                "After eating, you fill your pockets with hamburger patties and head for\n " +
                                "the exit.");
                        player.addToInventory(new Item("Hamburger Patty", "Restores 20 food level"));
                        System.out.println("Your food level increase by 50!");
                        player.upFoodLevel(50);

                    }else if(choice == 2){

                        System.out.println("You enter the office and hop on the computer. You find that the \n" +
                                "Employee has an amazon account linked to this computer. You want to order  \n" +
                                "Some Cat Armor using the account but can only find the password protection \n" +
                                "riddle. It reads 'Whats can you catch but never throw'. After pondering you \n" +
                                "enter the word.... ");
                        String strChoice = answer.nextLine();

                        if(strChoice.equalsIgnoreCase("cold")){

                            System.out.println("Success! The account opens and you place your order for the \n" +
                                    "Cat Armor you have been wanting. This will aid you greatly in your quest \n" +
                                    "for world domination...");
                            player.addToInventory(new Item("Cat Armor", "Adds 20 points to defense stat"));

                        }else{

                            System.out.println("As the computer locks up due to a bad entry the manager enters his office");
                            Creature bkManager = new Creature(40,20,20,"BK Manager");
                            Battle.executeBattle(bkManager,player);

                        }

                    }else{

                        System.out.println("An employee suddenly appears from around the corner and gives chase");
                        Creature bkEmp = new Creature(30,15,15,"BK Employee");
                        Battle.executeBattle(bkEmp,player);
                        System.out.println("He dropped his spatula and you grab it as you run away.");
                        System.out.println("You see it as a perfect addition to your arsenal");
                        player.addToInventory(new Item("Spatula", "Increases attack strength by 15"));


                    }

                }

                break;

            case 2:
                System.out.println("You spot an opportunity to jump off the balcony and you take it");
                int damage = (int) (Math.random() * 10);
                player.damageFoodLevel(damage);
                if (player.getFoodLevel() < 0) {
                    break;
                }
                System.out.println("You take " + damage + " damage in the fall");
                System.out.println("You stagger your way through the wilds and come upon a crossroads");
                System.out.println("Which way do you turn? (1.) Left (2.) Right");
                input = answer.nextInt();
                answer.nextLine();
                if (input == 2) {

                    System.out.println("You hear beasts rustling angrily about");
                    System.out.println("A pit drops in your stomach.......");

                    Creature beast = Main.generateStrongCreature();
                    Battle.executeBattle(beast,player);

                    if (player.getFoodLevel() < 0) {
                        break;
                    }else {

                        System.out.println("After defeating the beast you notice a shiny object");
                        System.out.println("That appears partial covered by it's fallen body");
                        System.out.println("You retrieve the object");
                        System.out.println("It's a yellow Key Card...");
                        player.addToInventory(new Item("Yellow Key Card", "A yellow tinged key-card of unknown origin"));
                        System.out.println("You return home with your newfound plunder.");
                    }

                } else if (input == 1) {

                    Item hasGun = player.getItem("gun");

                    int randomNum = (int) (Math.random() * 10);

                    if (randomNum > 5) {
                        System.out.println("You see a bump in the leaves...");
                        System.out.println("This must contains some sort of artifact");
                        System.out.println("After some digging you uncover a GUN");
                        System.out.println("And one lone bullet");
                        System.out.println("You experience a new found surge of POWER");

                        if(hasGun != null){
                            System.out.println("You already have a gun, but you take the bullet" +
                                    " Lying beside and add it to your arsenal.");
                            player.addToInventory(new Item("Bullet", "Needed to fire gun"));
                        }else {
                            player.addToInventory(new Item("GUN", "One Bullet to END any battle"));
                            player.addToInventory(new Item("Bullet", "Needed to fire gun"));
                        }
                        System.out.println("You return home with your newfound plunder");

                    } else {
                        System.out.println("You approach an imposing tree and as you draw near...");
                        System.out.println("...you notice an inscription etched into the trees bark.");
                        System.out.println("The inscription reads: I am not alive but I grow; I don't " +
                                "have lungs but I need air; I don't have a mouth, but water kills me." +
                                " What am I? ");
                        System.out.println("Upon thinking about this riddle the first word that comes to your " +
                                "mind is:   ");
                        strInput = answer.nextLine();

                        if (strInput.equalsIgnoreCase("fire")) {
                            player.upIntelligence(5);
                            System.out.println("Your intelligence grows by 5 points");
                            System.out.println("You feel a rumbling beneath your feet and see" +
                                    " at the base of the tree an opening form and \n" +
                                    "it's just big enough for you to fit inside.\n" +
                                    " Do you enter now or return later? type a number: (1.) enter, (2.) return home");
                            input = answer.nextInt();
                            answer.nextLine();
                            if (input == 2) {
                                System.out.println("Frightened at what may lurk inside you opt to return home for now");
                            } else if (input == 1) {
                                System.out.println("You crouch down into the tree stump and ,after crawling through\n" +
                                        "the opening, find that it opens up to a large burrow of some sort.\n" +
                                        "No typical creature could have engineered what lies before you...\n");

                                //KEEP ADDING you square of against a unique boss creature who is a rogue racoon
                                //if you can manage to beat him you gain the racoon claw weapon which increases your
                                //strength by 15 points, you also find a map that when used generates a unique quest


                            }
                        } else {
                            System.out.println("Nothing happens. Perhaps you need to read more books.");
                        }
                    }


                } else {
                    System.out.println("You hear footsteps behind you and before you can react" +
                            " Devin snatches you up off of the ground. Your journey outside comes to" +
                            "and early close....");
                    player.damageIntelligence(5);
                    System.out.println("Your pride is hurt and you lose 5 intelligence points.");
                }
                break;

            //check the pockets of the couch
            //low success rate

            case 3:
                break;

            //Self Guided quest for lots of potential discovery and growth opportunities here

            case 4:
                break;

            default:
                System.out.println("Invalid input");
                break;

        }


    }
}
