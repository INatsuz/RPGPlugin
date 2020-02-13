package com.inaiga.rpgplugin.characters;

import com.inaiga.rpgplugin.skills.Skill;
import com.inaiga.rpgplugin.classes.Class;
import java.util.ArrayList;

public class RPGCharacter {

    //Character properties
    private Class characterClass;
    private int level;
    private ArrayList<Skill> skills = new ArrayList<>();

    /**
     * Creates an RPG Character
     * @param characterClass A value from the {@link com.inaiga.rpgplugin.classes.Class} enum
     * @param level {@link com.inaiga.rpgplugin.characters.RPGCharacter} level
     * @param skills {@link java.util.ArrayList} of the {@link com.inaiga.rpgplugin.characters.RPGCharacter} {@link com.inaiga.rpgplugin.skills.Skill}
     * */
    public RPGCharacter(Class characterClass, int level, ArrayList<Skill> skills) {
        this.characterClass = characterClass;
        this.level = level;
        this.skills = skills;
    }

    /**
     * Creates an RPG Character
     * @param characterClass Value from the {@link com.inaiga.rpgplugin.classes.Class} enum
     * @param level {@link com.inaiga.rpgplugin.characters.RPGCharacter} level
     * */
    public RPGCharacter(Class characterClass, int level) {
        this.characterClass = characterClass;
        this.level = level;
    }

    /**
     * Returns the Character Class
     * @return Value from the {@link com.inaiga.rpgplugin.classes.Class} enum
     * */
    public Class getCharacterClass() {
        return characterClass;
    }

    /**
     * Sets the Character Class
     * @param characterClass Value from the {@link com.inaiga.rpgplugin.classes.Class} enum
     * */
    public void setCharacterClass(Class characterClass) {
        this.characterClass = characterClass;
    }

    /**
     * Returns Character level
     * @return The {@link com.inaiga.rpgplugin.characters.RPGCharacter} level as an int
     * */
    public int getLevel() {
        return level;
    }

    /**
     * Sets Character level
     * @param level The {@link com.inaiga.rpgplugin.characters.RPGCharacter} level as an int
     * */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Returns the Character Skills
     * @return {@link java.util.ArrayList} of {@link com.inaiga.rpgplugin.characters.RPGCharacter} {@link com.inaiga.rpgplugin.skills.Skill}
     * */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Sets the Character Skills
     * @param skills  {@link java.util.ArrayList} of {@link com.inaiga.rpgplugin.characters.RPGCharacter} {@link com.inaiga.rpgplugin.skills.Skill}
     * */
    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Use the selected Skill
     * @param skill {@link com.inaiga.rpgplugin.skills.Skill} to be used by the {@link com.inaiga.rpgplugin.characters.RPGCharacter}
     * */
    public void useSkill(Skill skill){
        if (skills.contains(skill)){
            skill.execute();
        }
    }

}