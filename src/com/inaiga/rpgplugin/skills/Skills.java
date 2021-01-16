package com.inaiga.rpgplugin.skills;

import com.inaiga.rpgplugin.characters.RPGCharacter;
import com.inaiga.rpgplugin.classes.RPGClass;

import com.inaiga.rpgplugin.player.PlayerManager;
import java.util.ArrayList;
import org.bukkit.entity.Player;

public enum Skills {
	BACKSTAB(new Backstab(), 5, new int[]{1, 0, 0, 0}, RPGClass.ROGUE, null), //TODO - Develop Backstab skill
	SNEAK(new Sneak(), 7, new int[]{1, 0, 0, 1}, RPGClass.ROGUE, BACKSTAB),

	SWIFT(new Swift(), 10, new int[]{1, 0, 0, 0}, RPGClass.WIZARD, null),
	HEAL(new Heal(), 10, new int[]{1, 0, 0, 1}, RPGClass.WIZARD, SWIFT), //TODO - Develop Heal skill
	BUFF(new Buff(), 30, new int[]{1, 0, 1, 0}, RPGClass.WIZARD, HEAL), //TODO - Develop Buff skill
	GUARDIAN_ANGEL(new GuardianAngel(), 60, new int[]{1, 0, 1, 1}, RPGClass.WIZARD, BUFF), //TODO - Develop Guardian Angel skill
	FIREBALL(new Swift(), 15, new int[]{1, 1, 1, 1}, RPGClass.WIZARD, SWIFT), //TODO - Develop Fireball skill
	METEOR(new Meteor(), 10, new int[]{1, 1, 1, 0}, RPGClass.WIZARD, FIREBALL);

	private final Skill skill;
	private final int[] sequence;
	private final RPGClass rpgClass;
	private final int cooldown;

	private final Skills requiredSkill;
	private final ArrayList<Skills> nextSkills = new ArrayList<>();

	Skills(Skill skill, int cooldown, int[] sequence, RPGClass rpgClass, Skills requiredSkill) {
		this.skill = skill;
		this.sequence = sequence;
		this.rpgClass = rpgClass;
		this.cooldown = cooldown;

		this.requiredSkill = requiredSkill;
		if (requiredSkill != null) {
			requiredSkill.addNextSkill(this);
		}
	}

	private void addNextSkill(Skills skill) {
		nextSkills.add(skill);
	}

	public Skill getSkill() {
		return skill;
	}

	public int[] getSequence() {
		return sequence;
	}

	public RPGClass getRpgClass() {
		return rpgClass;
	}

	public int getCooldown() {
		return cooldown;
	}

	public Skills getRequiredSkill() {
		return requiredSkill;
	}

	public ArrayList<Skills> getNextSkills() {
		return nextSkills;
	}
	
	@SafeVarargs
	public static <T> void executeSkill(Skills skill, Player player, T... args) {
		RPGCharacter character = PlayerManager.getRPGPlayerFromPlayer(player).getActiveRPGCharacter();
		if (!character.isOnCooldown(skill)) {
			skill.getSkill().execute(player, args);
			player.sendMessage("Executing skill: " + skill);

			character.putOnCooldown(skill);
		} else {
			player.sendMessage("Skill (" + skill.name() + ") is on cooldown");
		}
	}

	@Override
	public String toString() {
		return this.name().replaceAll("_", " ");
	}
}
