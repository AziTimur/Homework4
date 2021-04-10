package com.geektech;

import java.util.Random;

public class Main {

    public static int[] heroesHealth = {270, 280, 250, 300, 600, 200, 230, 300};
    public static String[] heroesNames = {"Lu Kang ", "Jax ",
            "Scorpion ", "Medic ", "Golem ", "Lucy ", "Berserk ", "Thor"};
    public static int[] heroesStrike = {20, 15, 25, 0, 10, 15, 30, 22};

    public static String bossName = "Shao Kahn ";
    public static int bossHealth = 2000;
    public static int bossStrike = 100;
    public static String superStrike = "";
    public static int roundNumber = 0;


    public static void main(String[] args) {
        // write your code here
        printStatistics();
        System.out.println("------The game started-------");

        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        System.out.println("-----Round " + roundNumber + "-----");
        superStrike = getSuperStrikeHero();

        bossHits();
        heroesHits();
        methodThor();
        berserk();
        golemsHits();
        LucyHits();

        printStatistics();
        healingHeroes();

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!! " +
                    "Mortal Kombat finished");
            return true;
        }

        boolean allHeroesDead = true;

        for (int heroHealth : heroesHealth) {
            if (heroHealth > 0) {
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead) {
            System.out.println(bossName +
                    " Won!!! Mortal Combat finished");
        }
        return allHeroesDead;
    }

    public static void heroesHits() {
        Random random = new Random();
        int coeff = random.nextInt(9) + 2;
        for (int i = 0; i < heroesStrike.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (superStrike == heroesNames[i]) {
                    bossHealth = bossHealth - heroesStrike[i] * coeff;
                    System.out.println("Super strike damage " +
                            superStrike + " " + (heroesStrike[i] * coeff));
                } else {
                    bossHealth = bossHealth - heroesStrike[i];
                }
            }
            if (bossHealth < 0) {
                bossHealth = 0;
            }
        }
    }

    public static void methodThor() {
        Random random = new Random();
        int randomThor = random.nextInt(2);
        if (randomThor == 1 && heroesHealth[7] > 0) {
            bossStrike=0;
          // isStunds=true;
            System.out.println(heroesNames[7]+" stunds boss");
        } else {
            bossStrike=100;
            System.out.println(heroesNames[7] +" не оглушил удар");
        }
    }

    public static boolean isStunds = false;

    public static void bossHits() {
 /*       Random random = new Random();
        int randomThor = random.nextInt(2);*/
        for (int i = 0; i < heroesHealth.length; i++) {
                /*if (randomThor == 1&&heroesHealth[7]>=0) {
                    isStunds=true;
                    System.out.println("thor stunds boss");
                    break;
                }*/
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossStrike;
            }
            if (heroesHealth[i] < 0) {
                heroesHealth[i] = 0;
            }
        }
    }


    public static void healingHeroes() {
        Random randomMed = new Random();
        int help = randomMed.nextInt(100) + 50;
        if (heroesHealth[3] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] < 100 && heroesHealth[i] != heroesHealth[3]) {
                    heroesHealth[i] += help;
                    System.out.println("Медик вылечил  " + heroesNames[i] + " на" + help);
                    break;
                }
            }
        }

    }

    public static void golemsHits() {
        if (heroesHealth[4] > 100) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] != heroesHealth[4]) {
                    heroesHealth[i] += bossStrike / 5;
                    heroesHealth[4] -= bossStrike / 5;

                }
            }
        }
    }

    public static void LucyHits() {
        Random random = new Random();
       /* int randomLucy = random.nextInt(bossStrike);
        heroesHealth[5] += randomLucy;
        if (heroesHealth[5] > 0) {
            System.out.println(heroesNames[5] + " уклонялся от удара, " + bossName + " ударил" + (bossStrike - randomLucy));
        } else {
            System.out.println(heroesNames[5] + "не уклонялся");
        }*/
        int randomLucy = random.nextInt(2);
        if (randomLucy == 1 && !isStunds) {
            heroesHealth[5] += bossStrike;
            System.out.println(heroesNames[5] + " уклонился от удара");
        }

    }

    public static void berserk() {
       /* if (isStunds = true) {
            bossStrike = 100;*/
            Random random = new Random();
            int randomLucy = random.nextInt(bossStrike);

//            heroesStrike[6] += bossStrike / 2;
            if (heroesHealth[6] > 0) {
                heroesHealth[6] += bossStrike - randomLucy;
                bossHealth -= heroesStrike[6] + (bossStrike - randomLucy);
                System.out.println(heroesNames[6] + "strike" + randomLucy);
          /*  System.out.println(heroesNames[6] + " уклонялся от удара, " + bossName + " ударил" + (bossStrike - randomLucy) + " "
                    + heroesNames[6] + " strike " + heroesStrike[6]);*/
            } else {
                System.out.println(heroesNames[6] + " не заблокировал удар");

            }
        }

    public static String getSuperStrikeHero() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesNames.length);
        return heroesNames[randomIndex];
    }

    public static void printStatistics() {
        System.out.println(bossName + "= health " + bossHealth +
                " strike [" + bossStrike + "]");
        for (int i = 0; i < heroesNames.length; i++) {
            System.out.println(heroesNames[i] + "= health                    " +
                    heroesHealth[i] + " strike [" +
                    heroesStrike[i] + "]");
        }
    }
}
