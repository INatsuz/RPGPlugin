package com.inaiga.rpgplugin.skills;

public interface Skill {

    public <T> void execute(T ...args);

}