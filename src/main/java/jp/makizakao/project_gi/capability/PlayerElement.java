package jp.makizakao.project_gi.capability;

import jp.makizakao.project_gi.capability.provider.PlayerElementProvider;
import jp.makizakao.project_gi.registry.ProjectGISkills.ElementType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Optional;
import java.util.function.BiFunction;


public class PlayerElement {
    private static final int MAX_ELEMENT_ENERGY = 200;
    private int anemoEnergy = 0;
    private int geoEnergy = 0;
    private int electroEnergy = 0;
    private int dendroEnergy = 0;
    private int hydroEnergy = 0;
    private int pyroEnergy = 0;
    private int cryoEnergy = 0;
    private ElementType elementType = ElementType.Anemo;

    private final BiFunction<Integer, Integer, Integer> getAddedEnergy =
            (now, added) -> Math.min(now + added, MAX_ELEMENT_ENERGY);

    public void printEnergy() {
        System.out.println("anemoEnergy: " + this.anemoEnergy);
    }
    public int getElementEnergy(ElementType type) {
        return switch(type) {
            case Anemo -> this.anemoEnergy;
            case Geo -> this.geoEnergy;
            case Electro -> this.electroEnergy;
            case Dendro -> this.dendroEnergy;
            case Hydro -> this.hydroEnergy;
            case Pyro -> this.pyroEnergy;
            case Cryo -> this.cryoEnergy;
        };
    }

    public ElementType getElementType() {
        return this.elementType;
    }

    public void addElementEnergy(int energy, ElementType type) {
        switch(type) {
            case Anemo -> this.anemoEnergy = getAddedEnergy.apply(this.anemoEnergy, energy);
            case Geo -> this.geoEnergy = getAddedEnergy.apply(this.geoEnergy, energy);
            case Electro -> this.electroEnergy = getAddedEnergy.apply(this.electroEnergy, energy);
            case Dendro -> this.dendroEnergy = getAddedEnergy.apply(this.dendroEnergy, energy);
            case Hydro -> this.hydroEnergy = getAddedEnergy.apply(this.hydroEnergy, energy);
            case Pyro -> this.pyroEnergy = getAddedEnergy.apply(this.pyroEnergy, energy);
            case Cryo -> this.cryoEnergy = getAddedEnergy.apply(this.cryoEnergy, energy);
        }
    }

    public void consumeElementEnergy(ElementType type) {
        switch(type) {
            case Anemo -> this.anemoEnergy = 0;
            case Geo -> this.geoEnergy = 0;
            case Electro -> this.electroEnergy = 0;
            case Dendro -> this.dendroEnergy = 0;
            case Hydro -> this.hydroEnergy = 0;
            case Pyro -> this.pyroEnergy = 0;
            case Cryo -> this.cryoEnergy = 0;
        }
    }

    public void setElementType(ElementType type) {
        this.elementType = type;
    }

    public static Optional<PlayerElement> getElementOptional(Player player) {
        return Optional.ofNullable(player)
                .map(p -> p.getCapability(PlayerElementProvider.PLAYER_ELEMENT_CAPABILITY))
                .flatMap(LazyOptional::resolve);
    }

    public void saveNBTTags(CompoundTag tag) {
        tag.putInt("anemoEnergy", this.anemoEnergy);
        tag.putInt("geoEnergy", this.geoEnergy);
        tag.putInt("electroEnergy", this.electroEnergy);
        tag.putInt("dendroEnergy", this.dendroEnergy);
        tag.putInt("hydroEnergy", this.hydroEnergy);
        tag.putInt("pyroEnergy", this.pyroEnergy);
        tag.putInt("cryoEnergy", this.cryoEnergy);
        tag.putInt("elementType", this.elementType.ordinal());
    }
    public void loadNBTTags(CompoundTag tag) {
        this.anemoEnergy = tag.getInt("anemoEnergy");
        this.geoEnergy = tag.getInt("geoEnergy");
        this.electroEnergy = tag.getInt("electroEnergy");
        this.dendroEnergy = tag.getInt("dendroEnergy");
        this.hydroEnergy = tag.getInt("hydroEnergy");
        this.pyroEnergy = tag.getInt("pyroEnergy");
        this.cryoEnergy = tag.getInt("cryoEnergy");
        this.elementType = ElementType.getElement(tag.getInt("elementType"));
    }
}
