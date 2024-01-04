package jp.makizakao.project_gi.registries;

import jp.makizakao.project_gi.skill.BaseSkill;
import jp.makizakao.project_gi.skill.TravellerWindBurst;
import jp.makizakao.project_gi.skill.TravellerWindSkill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectGISkills {
    private static int id = 0;
    public static List<BaseSkill> skillList = Collections.synchronizedList(new ArrayList<>());
    public static void register() {
        skillList.add(TravellerWindSkill.getInstance(id++));
        skillList.add(TravellerWindBurst.getInstance(id++));
    }

    public enum ElementType {
        Anemo,
        Geo,
        Electro,
        Dendro,
        Hydro,
        Pyro,
        Cryo,
    }
}
