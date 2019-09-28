package com.inaiga.rpgplugin.skills;

public enum CharacterSkill implements Skill {
	BACKSTAB {
		@Override
		public void execute() {
			Backstab.backstab();
		}
	},

	SNEAK {
		@Override
		public void execute() {
			System.out.println("I'm so sneakyyy!");
		}
	}
}
