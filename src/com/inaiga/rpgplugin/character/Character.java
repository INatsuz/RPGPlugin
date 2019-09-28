package com.inaiga.rpgplugin.character;

import com.inaiga.rpgplugin.skills.CharacterSkill;
import com.inaiga.rpgplugin.skills.Skill;
import java.util.ArrayList;

public class Character {

    private Class characterClass;
    private int level;
    private ArrayList<Skill> skills = new ArrayList<>();

    public Character(Class characterClass, int level, ArrayList<Skill> skills) {
        this.characterClass = characterClass;
        this.level = level;
        this.skills = skills;
    }

    public Class getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(Class characterClass) {
        this.characterClass = characterClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public void useSkill(Skill skill){
        if (skills.contains(skill)){
            skill.execute();
        }
    }

}
