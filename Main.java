
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;



public class Main {

    //sleep function
    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        }

    // Clear Screen
    public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    }


    // Character Creation
    public static String[] charatorCreation() {
        Scanner imput = new Scanner(System.in);

        System.out.println("Enter your character's name: ");
        String name = imput.nextLine();
        System.out.println("Enter your character's gender (Male, Female): ");
        String gender = imput.nextLine();   
        System.out.println("Choose your class: 1. Warrior 2. Mage 3. Claric");
        String classChoice = imput.nextLine();
        // Return character attributes as an array
        System.out.println("Character " + name + "Gender " + gender + " created!");

        return new String[]{name, gender, classChoice};
        
        }

    public static String [] classInfo(int classChoice) {
        String [] skills = {};
        switch (classChoice) {
            case 1:
                skills = new String[]{"Power Strike", "Backstab", "10", "10", "70"}; // Warrior skills and stats
                break;
            case 2:
                skills = new String[]{"Fireball", "Ice Spike", "15", "5", "60"}; // Mage skills and stats
                break;
            case 3:
                skills = new String[]{"Heal", "Power Strike", "10", "15", "80"}; // Cleric skills and stats
                break;

        }
    
        return skills;
    }
    // Skill Effect
    
    public static void skillEffect(String skillName, int enemyHealth, int playStr, int enemyDef, int playermaxHealth, int playerHealth) {
        Random random = new Random();
        int skillDamage = 0;

        switch (skillName) {
            case "Fireball" : // Fireball
                skillDamage = random.nextInt(15) + playStr - enemyDef;
                enemyHealth -= skillDamage;
                System.out.println("You used Fireball and dealt " + skillDamage + " damage to the enemy.");
                break;
            case "Ice Spike": // Ice Spike    
                skillDamage = random.nextInt(10) + playStr - enemyDef;
                enemyHealth -= skillDamage;
                System.out.println("You used Ice Spike and dealt " + skillDamage + " damage to the enemy.");
                break;
            case "Backstab": // Backstab
                skillDamage = random.nextInt(20) + playStr - enemyDef;
                enemyHealth -= skillDamage;
                System.out.println("You used Backstab and dealt " + skillDamage + " damage to the enemy.");
                break;
            case "Power Strike": // Power Strike
                skillDamage = random.nextInt(15) + playStr - enemyDef;
                enemyHealth -= skillDamage;
                System.out.println("You used Power Strike and dealt " + skillDamage + " damage to the enemy.");
                break;
            case "Heal": // Heal
                if (playermaxHealth == playerHealth) {
                    System.out.println("You are already at full health.");
                    break;
                }
                else{
                    if (playermaxHealth - playerHealth < 10) {
                        int healAmount = playermaxHealth - playerHealth;
                        playerHealth += healAmount;
                        System.out.println("You used Heal and restored " + healAmount + " health.");
                        break;
                    }
                    else {
                        playerHealth += 10;
                        System.out.println("You used Heal and restored 10 health.");
                        break;
                    }
                }
                
            default:
                System.out.println("Skill not recognized.");
                break;
        }
    }

        // enemy info
    public static String[] enemyInfo(int enemyChoice) {
        String [] enemyStats = {};  
        switch (enemyChoice) {
            case 1:
                enemyStats = new String[]{"Goblin", "30", "8", "5"}; 
                break;
            case 2:
                enemyStats = new String[]{"wolf", "50", "17", "5"}; 
                break;
            case 3:
                enemyStats = new String[]{"Orc", "50", "12", "8"}; 
                break;
            case 4:
                enemyStats = new String[]{"Troll", "80", "15", "10"}; 
            case 5:
                enemyStats = new String[]{"Dragon", "150", "25", "15"}; 
                break;
            case 6:
                enemyStats = new String[]{"Dark Knight", "120", "20", "20"}; 
                break;
            case 7:
                enemyStats = new String[]{"Necromancer", "100", "18", "12"}; 
                break;
            case 8:
                enemyStats = new String[]{"Squirrel", "30", "5", "5"}; 
                break;
                }
        return enemyStats;

        }


    // Battle System
    public static void battle(int playerMaxHealth, int playerHealth, int playStr, int playDef, int enemyHealth, int enemyStr, int enemyDef, String enemyName, String[] skill) {
        Scanner imput = new Scanner(System.in);
        Random random = new Random();

        while (playerHealth > 0 && enemyHealth > 0) {
            sleep(7);
            clearScreen();
            System.out.println("\nYour Health: " + playerHealth + "/" + playerMaxHealth);
            System.out.println("\nChoose your action: 1. Attack 2. Defend .3 Skill\n\n");
            int action = imput.nextInt();

            if (action == 1) {
                int playerDamage = random.nextInt(10) + playStr - enemyDef; // this is to calculate player damage. with class stats added
                enemyHealth -= playerDamage;
                System.out.println("You dealt " + playerDamage + " damage to the enemy.");
            } 
            else if (action == 2) {
                System.out.println("You defended against the next attack.");
            } 
            else if (action == 3) {
                System.out.println("Choose a skill to use:");
                for (int i = 0; i < skill.length; i++) {
                    System.out.println((i + 1) + ". " + skill[i]);
                }
                int skillChoice = imput.nextInt() - 1;
                if (skillChoice >= 0 && skillChoice < skill.length) {
                    skillEffect(skill[skillChoice], enemyHealth, playStr, enemyDef, playerMaxHealth, playerHealth);
                } else {
                    System.out.println("Invalid skill choice. Turn skipped.");
                    continue;
                }
            }
            else {
                System.out.println("Invalid action. Try again.");
                continue;
            }

            if (enemyHealth > 0) {
                if (action == 1) {
                int enemyDamage = random.nextInt(10) + enemyStr - playDef;// this is to calculate enemy damage. 
                playerHealth -= enemyDamage;
                System.out.println("The enemy dealt " + enemyDamage + " damage to you.");
                }
                else if (action == 2) {
                    playerHealth -= 1; // reduced damage when defending
                    System.out.println("The enemy's attack was blocked! But you still took 1 damage.");
                }   
                else {
                    int enemyDamage = random.nextInt(10) + enemyStr - playDef;// this is to calculate enemy damage. 
                    playerHealth -= enemyDamage;
                    System.out.println("The enemy dealt " + enemyDamage + " damage to you.");
                }
            }
        }

        if (playerHealth <= 0) {
            System.out.println("You have been defeated!");
        } else {
            System.out.println("You have defeated the " + enemyName + "!");
        }
        
    }
    public static void storyLine(String[] player) {
        int a = 1;

        Scanner imput = new Scanner(System.in);

        //to retrieve class info
        String [] helpClass = {};


        /// player stats//// 
        int playerMaxHealth = 0;
        int playerhuealth = 0;
        int playStr = 0;
        int playDef = 0;
        String [] skills = {};
    ///////////////////////////////////////
    ///
    ///enime stats////
    int enemyHealth = 0;
    int enemyStr = 0;
    int enemyDef = 0;
    String enemyName = "";
    

    // Get class info based on player choice
    for (int i = 0; i < player.length; i++) {
        System.out.println(player[i]);
        int classChoice = Integer.parseInt(player[2]);
        helpClass = classInfo(classChoice);
        skills = new String[]{helpClass[0], helpClass[1]};
        playStr = Integer.parseInt(helpClass[2]);
        playDef = Integer.parseInt(helpClass[3]);  
        playerMaxHealth = Integer.parseInt(helpClass[4]);
        playerhuealth = playerMaxHealth;
        }   
        clearScreen();
        System.out.println("You wake up in a dark forest with no memory of how you got there.");
        System.out.println("As you stand up, you hear rustling in the bushes nearby.");
        System.out.println("A wild goblin jumps out, ready to attack!");
        // Get enemy info
        String [] enemyStats = enemyInfo(1); // Goblin  
        enemyName = enemyStats[0];
        enemyHealth = Integer.parseInt(enemyStats[1]);
        enemyStr = Integer.parseInt(enemyStats[2]);
        enemyDef = Integer.parseInt(enemyStats[3]);
        // Start battle
        battle(playerMaxHealth, playerhuealth, playStr, playDef, enemyHealth, enemyStr, enemyDef, enemyName, skills);
        System.out.println("Finishing the fight you see three paths ahead of you. ");
        while (a == 1) {
        
        System.err.println("\n1. A open plains to the left.\n2. A swamp straight ahead.\n3. A dense forest to the right.");
        int pathChoice = imput.nextInt();
        switch (pathChoice) {
            case 1:
                System.out.println("You chose the open plains.");
                sleep(3);
                clearScreen();
                System.out.println("As you walk through the open plains, A wild wolf appears!");
                // Get enemy info
                String [] enemyStats2 = enemyInfo(2); // wolf
                enemyName = enemyStats2[0];
                enemyHealth = Integer.parseInt(enemyStats2[1]);
                enemyStr = Integer.parseInt(enemyStats2[2]);
                enemyDef = Integer.parseInt(enemyStats2[3]);
                // Start battle
                battle(playerMaxHealth, playerhuealth, playStr, playDef, enemyHealth, enemyStr, enemyDef, enemyName, skills);
                System.out.println("As you finish the battle, You realized that you are back at the starting point of your adventure.");
                
                break;
            case 2:
                System.out.println("You chose the swamp.");
                sleep(3);
                clearScreen();
                System.out.println("as you slosh through the murky waters, A wild orc emerges from the shadows!");
                // Get enemy info
                String [] enemyStats3 = enemyInfo(3); // Orc
                enemyName = enemyStats3[0];
                enemyHealth = Integer.parseInt(enemyStats3[1]);
                enemyStr = Integer.parseInt(enemyStats3[2]);
                enemyDef = Integer.parseInt(enemyStats3[3]);
                // Start battle
                battle(playerMaxHealth, playerhuealth, playStr, playDef, enemyHealth, enemyStr, enemyDef, enemyName, skills);
                System.out.println("As you finish the battle, You realized that you are back at the starting point of your adventure.");
                break;
            case 3:
                System.out.println("You chose the dense forest.");
                sleep(3);
                clearScreen();
                System.out.println("As you navigate through the thick trees, A wild troll lumbers into your path!");
                // Get enemy info
                String [] enemyStats4 = enemyInfo(4); // Troll
                enemyName = enemyStats4[0];
                enemyHealth = Integer.parseInt(enemyStats4[1]);
                enemyStr = Integer.parseInt(enemyStats4[2]);
                enemyDef = Integer.parseInt(enemyStats4[3]);
                // Start battle
                battle(playerMaxHealth, playerhuealth, playStr, playDef, enemyHealth, enemyStr, enemyDef, enemyName, skills);
                System.out.println("As you finish the battle");
                a = 0;
                break;
            default:
                System.out.println("Invalid choice. The adventure ends here.");
                break;
            
        }
        System.out.println("As you pull away fom the Troll you see a Cave entrance ahead of you and that you when you remember your purpose was to slay the Dragon and same the princess.");
        System.out.println("You walk towards the cave to face your next challenge.");
        sleep(2);
        clearScreen();
        System.out.println("As you enter the dark cave, A fearsome Dragon descends from above, blocking your path!");
        // Get enemy info
        String [] enemyStats5 = enemyInfo(5); // Dragon
        enemyName = enemyStats5[0];
        enemyHealth = Integer.parseInt(enemyStats5[1]);             
        enemyStr = Integer.parseInt(enemyStats5[2]);
        enemyDef = Integer.parseInt(enemyStats5[3]);
        // Start battle
        battle(playerMaxHealth, playerhuealth, playStr, playDef, enemyHealth, enemyStr, enemyDef, enemyName, skills);
        System.out.println("With the Dragon defeated, you rescue the princess and complete your quest!");
        System.out.println("Congratulations, " + player[0] + "! You have completed your adventure.");
        }

        
    }



    public static void main(String[] args) {

        String [] player = charatorCreation();
        storyLine(player);

    }
    
}