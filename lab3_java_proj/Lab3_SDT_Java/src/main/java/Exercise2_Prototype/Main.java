package Exercise2_Prototype;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CharacterLibrary library = new CharacterLibrary();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to RPG Character Creator!");
        library.listCharacters();

        System.out.print("Choose a character to clone or type 'custom' to create new: ");
        String choice = scanner.nextLine();

        Character playerCharacter;

        if (choice.equalsIgnoreCase("custom")) {
            playerCharacter = createCustomCharacter(scanner);
        } else {
            playerCharacter = library.getCharacter(choice);
            if (playerCharacter == null) {
                System.out.println("Invalid choice. Using Warrior as default.");
                playerCharacter = library.getCharacter("Warrior");
            }
        }

        System.out.print("Do you want to modify this character? (yes/no): ");
        String modify = scanner.nextLine();
        if (modify.equalsIgnoreCase("yes")) {
            modifyCharacter(playerCharacter, scanner);
        }

        System.out.println("\nYour final character stats:");
        playerCharacter.displayStats();
        scanner.close();
    }

    private static Character createCustomCharacter(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter class: ");
        String charClass = scanner.nextLine();
        System.out.print("Enter story: ");
        String story = scanner.nextLine();

        return new Character(
                name, charClass, story,
                getStat(scanner, "Strength"), getStat(scanner, "Constitution"),
                getStat(scanner, "Dexterity"), getStat(scanner, "Intelligence"),
                getStat(scanner, "Wisdom"), getStat(scanner, "Charisma")
        );
    }

    private static void modifyCharacter(Character character, Scanner scanner) {
        System.out.print("Enter new name or press Enter to keep [" + character.getClass() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) character.setName(name);

        character.setStrength(getStat(scanner, "Strength", character.getStrength()));
        character.setConstitution(getStat(scanner, "Constitution", character.getConstitution()));
        character.setDexterity(getStat(scanner, "Dexterity", character.getDexterity()));
        character.setIntelligence(getStat(scanner, "Intelligence", character.getIntelligence()));
        character.setWisdom(getStat(scanner, "Wisdom", character.getWisdom()));
        character.setCharisma(getStat(scanner, "Charisma", character.getCharisma()));
    }

    private static int getStat(Scanner scanner, String statName) {
        int value;
        do {
            System.out.print(statName + " (3-20): ");
            value = scanner.nextInt();
        } while (value < 3 || value > 20);
        return value;
    }

    private static int getStat(Scanner scanner, String statName, int current) {
        System.out.print(statName + " [" + current + "]: ");
        String input = scanner.nextLine();
        if (input.isEmpty()) return current;
        int value = Integer.parseInt(input);
        if (value < 3 || value > 20) return current;
        return value;
    }
}
