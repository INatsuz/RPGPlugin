package com.inaiga.rpgplugin.skills;

import com.inaiga.rpgplugin.classes.RPGClass;
import java.util.ArrayList;

public enum Skills {
	BACKSTAB(new Backstab(), new int[]{1, 0, 0, 0}, RPGClass.ROGUE, null),
	SNEAK(new Sneak(), new int[]{1, 0, 0, 1}, RPGClass.ROGUE, BACKSTAB),

	SWIFT(new Swift(), new int[]{1, 0, 0, 0}, RPGClass.WIZARD, null),
	HEAL(new Swift(), new int[]{1, 0, 0, 1}, RPGClass.WIZARD, SWIFT),
	BUFF(new Swift(), new int[]{1, 0, 1, 0}, RPGClass.WIZARD, HEAL),
	GUARDIAN_ANGEL(new Swift(), new int[]{1, 0, 1, 1}, RPGClass.WIZARD, BUFF),
	FIREBALL(new Swift(), new int[]{1, 1, 1, 1}, RPGClass.WIZARD, SWIFT),
	METEOR(new Swift(), new int[]{1, 1, 1, 0}, RPGClass.WIZARD, FIREBALL);

	private final Skill skill;
	private final int[] sequence;
	private final RPGClass rpgClass;
	private final Skills requiredSkill;
	private ArrayList<Skills> nextSkills = new ArrayList<>();

	Skills(Skill skill, int[] sequence, RPGClass rpgClass, Skills requiredSkill) {
		this.skill = skill;
		this.sequence = sequence;
		this.rpgClass = rpgClass;
		this.requiredSkill = requiredSkill;
		requiredSkill.addNextSkill(this);
	}

	public Skill getSkill() {
		return skill;
	}

	public int[] getSequence() {
		return sequence;
	}

	private void addNextSkill(Skills skill){
		nextSkills.add(skill);
	}

	public RPGClass getRpgClass() {
		return rpgClass;
	}

	public static <T> void executeSkill(Skills skill, T ...args){
		skill.getSkill().execute(args);
	}

}
