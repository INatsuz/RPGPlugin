package com.inaiga.rpgplugin.skills;

public enum Skills {
	BACKSTAB(new Backstab()),
	SNEAK(new Sneak());

	private final Skill skill;

	Skills(Skill skill) {
		this.skill = skill;
	}

	public Skill getSkill() {
		return skill;
	}

	public static <T> void executeSkill(Skills skill, T ...args){
		skill.getSkill().execute(args);
	}

}
