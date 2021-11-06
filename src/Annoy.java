public class Annoy {

    public static void annoy(Kiki player,Devin devin) {

        int devinBeginIntelligence = devin.getIntelligence();
        int kikiBeginIntelligence = player.getIntelligence();

        System.out.println("You attempt to annoy Devin by stepping on the PS4");

        boolean ignored = true;

        if (devin.getIntelligence() < player.getIntelligence()) {
            System.out.println("Uh oh Devin is pissed and starts to attack!");
            Battle.executeBattle(devin,player);
        } else {

            while ((devin.getIntelligence() > 0) && (player.getIntelligence() > 0)) {

                //kiki annoys
                int intelHit = (int) (Math.random() * player.getIntelligence() + (player.getCutePoints()/ 4));
                devin.damageIntelligence(intelHit);
                System.out.println("You annoy devin and do " + intelHit + " damage to his intelligence");

                if (devin.getIntelligence() < 0 || player.getIntelligence() < 0) {
                    break;
                }


                //devin tries to be nice
                intelHit = (int) (Math.random() * devin.getIntelligence() + (devin.getHealth() / 6));
                player.damageIntelligence(intelHit);
                System.out.println("Devin starts ignoring and does " + intelHit + " damage to your intelligence");
            }

            if ((devin.getIntelligence() < 0) && (player.getIntelligence() > 0)) {
                ignored = false;
                devin.setIntelligence(devinBeginIntelligence / 2 + (devinBeginIntelligence / 4));
            }

            if (!ignored) {

                System.out.println("You have succeeded in annoying Devin enough to earn attention");
                System.out.println("Devin's intelligence is now " + devin.getIntelligence());
                player.setIntelligence(kikiBeginIntelligence);
                player.levelUp();

            } else {

                if (player.getIntelligence()< 0) {
                    player.setIntelligence(0);
                }
                player.upIntelligence(kikiBeginIntelligence / 2 + (kikiBeginIntelligence / 4));
                System.out.println("Devin is now ignoring you completely");
                System.out.println("Your intelligence is now " + player.getIntelligence());
            }
        }


    }
}
