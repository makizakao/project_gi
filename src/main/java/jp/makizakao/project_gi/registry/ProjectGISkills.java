package jp.makizakao.project_gi.registry;

import jp.makizakao.project_gi.skill.BaseSkill;
import jp.makizakao.project_gi.skill.TravelerWindBurst;
import jp.makizakao.project_gi.skill.TravelerWindSkill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProjectGISkills {
    private static int id = 0;
    public static int nextId() {
        return id++;
    }
    public static List<BaseSkill> skillList = Collections.synchronizedList(new ArrayList<>());
    public static void register() {
        skillList.add(TravelerWindSkill.getInstance());
        skillList.add(TravelerWindBurst.getInstance());
    }

    public enum ElementType {
        Anemo,
        Geo,
        Electro,
        Dendro,
        Hydro,
        Pyro,
        Cryo;

        public static ElementType getElement(int typeInt) {
            return Arrays.stream(ElementType.values())
                    .filter(type -> type.ordinal() == typeInt)
                    .findFirst()
                    .orElse(Anemo);
        }
    }
}
