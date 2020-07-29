package com.inaiga.rpgplugin.characters;

import com.inaiga.rpgplugin.classes.RPGClass;
import com.inaiga.rpgplugin.skills.Skills;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public class RPGCharacter {

	//Character properties
	private RPGClass characterRPGClass;
	private int level;
	private ArrayList<Skills> skills = new ArrayList<>();
	private HashSet<Skills> cooldowns = new HashSet<>();

	/**
	 * Creates an RPG Character
	 *
	 * @param characterRPGClass A value from the {@link RPGClass} enum
	 * @param level             {@link com.inaiga.rpgplugin.characters.RPGCharacter} level
	 * @param skills            {@link java.util.ArrayList} of the {@link com.inaiga.rpgplugin.characters.RPGCharacter} {@link com.inaiga.rpgplugin.skills.Skill}
	 */
	public RPGCharacter(RPGClass characterRPGClass, int level, Skills... skills) {
		this.characterRPGClass = characterRPGClass;
		this.level = level;
		this.skills = new ArrayList<>(Arrays.asList(skills));
	}

	public RPGCharacter(RPGClass characterRPGClass, int level, ArrayList<Skills> skills) {
		this.characterRPGClass = characterRPGClass;
		this.level = level;
		this.skills = skills;
	}

	/**
	 * Creates an RPG Character
	 *
	 * @param characterRPGClass Value from the {@link RPGClass} enum
	 * @param level             {@link com.inaiga.rpgplugin.characters.RPGCharacter} level
	 */
	public RPGCharacter(RPGClass characterRPGClass, int level) {
		this.characterRPGClass = characterRPGClass;
		this.level = level;
	}

	/**
	 * Returns the Character RPGClass
	 *
	 * @return Value from the {@link RPGClass} enum
	 */
	public RPGClass getCharacterRPGClass() {
		return characterRPGClass;
	}

	/**
	 * Sets the Character RPGClass
	 *
	 * @param characterRPGClass Value from the {@link RPGClass} enum
	 */
	public void setCharacterRPGClass(RPGClass characterRPGClass) {
		this.characterRPGClass = characterRPGClass;
	}

	/**
	 * Returns Character level
	 *
	 * @return The {@link com.inaiga.rpgplugin.characters.RPGCharacter} level as an int
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets Character level
	 *
	 * @param level The {@link com.inaiga.rpgplugin.characters.RPGCharacter} level as an int
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Returns the Character Skills
	 *
	 * @return {@link java.util.ArrayList} of {@link com.inaiga.rpgplugin.characters.RPGCharacter} {@link com.inaiga.rpgplugin.skills.Skill}
	 */
	public ArrayList<Skills> getSkills() {
		return skills;
	}

	/**
	 * Sets the Character Skills
	 *
	 * @param skills {@link java.util.ArrayList} of {@link com.inaiga.rpgplugin.characters.RPGCharacter} {@link com.inaiga.rpgplugin.skills.Skill}
	 */
	public void setSkills(ArrayList<Skills> skills) {
		this.skills = skills;
	}

	public void addSkill(Skills skill) {
		skills.add(skill);
	}

	public boolean unlockSkill(Skills skill) {
		if (skills.contains(skill) || (!skills.contains(skill.getRequiredSkill()) && skill.getRequiredSkill() != null) || skill.getRpgClass() != characterRPGClass) {
			return false;
		}
		skills.add(skill);
		return true;
	}

	public HashSet<Skills> getCooldowns() {
		return cooldowns;
	}

	public void putOnCooldown(Skills skill) {
		cooldowns.add(skill);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				cooldowns.remove(skill);
			}
		}, skill.getCooldown() * 1000);
	}

	public boolean isOnCooldown(Skills skill) {
		return cooldowns.contains(skill);
	}

}