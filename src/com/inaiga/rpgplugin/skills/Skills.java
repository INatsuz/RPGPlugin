package com.inaiga.rpgplugin.skills;

public enum Skills {
	BACKSTAB(new Backstab(), new int[]{1, 0, 0, 0}),
	SNEAK(new Sneak(), new int[]{1, 0, 0, 1});

	private final Skill skill;
	private final int[] sequence;

	Skills(Skill skill, int[] sequence) {
		this.skill = skill;
		this.sequence = sequence;
	}

	public Skill getSkill() {
		return skill;
	}

	public int[] getSequence() {
		return sequence;
	}

	public static <T> void executeSkill(Skills skill, T ...args){
		skill.getSkill().execute(args);
	}

}
