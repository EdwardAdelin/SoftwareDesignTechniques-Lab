package Exercise2_Prototype;

import java.util.HashMap;
import java.util.Map;

public class CharacterLibrary {
    private Map<String, Character> characters = new HashMap<>();

    public CharacterLibrary() {
        // Predefined characters
        characters.put("Warrior", new Character("Default Warrior", "Warrior",
                "A brave fighter", 18, 16, 12, 8, 10, 14));
        characters.put("Wizard", new Character("Default Wizard", "Wizard",
                "A wise spellcaster", 8, 10, 12, 18, 16, 12));
        characters.put("Rogue", new Character("Default Rogue", "Rogue",
                "A sneaky thief", 12, 12, 18, 10, 12, 14));
    }

    public Character getCharacter(String type) {
        Character character = characters.get(type);
        if (character != null) {
            return character.clone();
        }
        return null;
    }

    public void listCharacters() {
        System.out.println("Available characters:");
        for (String key : characters.keySet()) {
            System.out.println("- " + key);
        }
    }
}
