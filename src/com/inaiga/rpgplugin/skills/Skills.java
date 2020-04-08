package com.inaiga.rpgplugin.skills;

import com.inaiga.rpgplugin.classes.RPGClass;

public enum Skills {
	BACKSTAB(new Backstab(), new int[]{1, 0, 0, 0}, RPGClass.ROGUE),
	SNEAK(new Sneak(), new int[]{1, 0, 0, 1}, RPGClass.ROGUE),
	SWIFT(new Swift(), new int[]{1, 1, 0, 1}, RPGClass.WIZARD);

	private final Skill skill;
	private final int[] sequence;
	private final RPGClass skillClass;

	Skills(Skill skill, int[] sequence, RPGClass skillClass) {
		this.skill = skill;
		this.sequence = sequence;
		this.skillClass = skillClass;
	}

	public Skill getSkill() {
		return skill;
	}

	public int[] getSequence() {
		return sequence;
	}

	public RPGClass getSkillClass() {
		return skillClass;
	}

	public static <T> void executeSkill(Skills skill, T ...args){
		skill.getSkill().execute(args);
	}

}
