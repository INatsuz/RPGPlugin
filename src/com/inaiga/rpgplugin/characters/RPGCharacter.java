package com.inaiga.rpgplugin.characters;

import com.inaiga.rpgplugin.classes.RPGClass;
import com.inaiga.rpgplugin.skills.Skill;
import java.util.ArrayList;

public class RPGCharacter {

    //Character properties
    private RPGClass characterRPGClass;
    private int level;
    private ArrayList<Skill> skills = new ArrayList<>();

    /**
     * Creates an RPG Character
     * @param characterRPGClass A value from the {@link RPGClass} enum
     * @param level {@link com.inaiga.rpgplugin.characters.RPGCharacter} level
     * @param skills {@link java.util.ArrayList} of the {@link com.inaiga.rpgplugin.characters.RPGCharacter} {@link com.inaiga.rpgplugin.skills.Skill}
     * */
    public RPGCharacter(RPGClass characterRPGClass, int level, ArrayList<Skill> skills) {
        this.characterRPGClass = characterRPGClass;
        this.level = level;
        this.skills = skills;
    }

    /**
     * Creates an RPG Character
     * @param characterRPGClass Value from the {@link RPGClass} enum
     * @param level {@link com.inaiga.rpgplugin.characters.RPGCharacter} level
     * */
    public RPGCharacter(RPGClass characterRPGClass, int level) {
        this.characterRPGClass = characterRPGClass;
        this.level = level;
    }

    /**
     * Returns the Character RPGClass
     * @return Value from the {@link RPGClass} enum
     * */
    public RPGClass getCharacterRPGClass() {
        return characterRPGClass;
    }

    /**
     * Sets the Character RPGClass
     * @param characterRPGClass Value from the {@link RPGClass} enum
     * */
    public void setCharacterRPGClass(RPGClass characterRPGClass) {
        this.characterRPGClass = characterRPGClass;
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