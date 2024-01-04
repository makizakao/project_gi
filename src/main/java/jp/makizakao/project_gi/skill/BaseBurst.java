package jp.makizakao.project_gi.skill;

public class BaseBurst extends BaseSkill {
    protected static BaseBurst instance;
    protected int energyUsage;
    protected BaseBurst() {
    }
    public static BaseBurst getInstance() {
        return instance;
    }
}
