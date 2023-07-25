package dev.aprilvee.xiaoheic.capability;

import dev.aprilvee.xiaoheic.spell.SpellType;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AutoRegisterCapability
public class SpiritCap {
    private int qi;
    private int maxqi;

    private float qiregen = 1; //these 5 are multipliers, be careful with them! strong!
    private float maxqiX = 1;
    private float spelldamage = 1;
    private float spellresist = 1;
    private float spellcost = 1;

    private String affinity = "none";
    private String affinity2 = "none";
    private String type = "none";

    private float cultivation;
    private float metalattunement;
    private float waterattunement;
    private float woodattunement;
    private float fireattunement;
    private float earthattunement;

    public Set<SpellType> unlockedspells = new HashSet<>();

    private float elementlimit = 250;
    private final int MIN_QI_VALUE = 0;
    private final int MAX_QI_VALUE = Integer.MAX_VALUE;

    public int getQi(){
        return qi;
    }
    public int getMaxqi(){return maxqi;}
    public float getMaxqiX(){return maxqiX;}
    public float getQiregen(){return qiregen;}
    public float getSpelldamage(){return spelldamage;}
    public float getSpellresist(){return spellresist;}
    public float getSpellcost(){return spellcost;}

    public String getAffinty(){return affinity;}
    public String getAffinty2(){return affinity2;}
    public String getType(){return type;}
    public float getCultivation(){return cultivation;}
    public float getMetal(){return metalattunement;}
    public float getWater(){return waterattunement;}
    public float getWood(){return woodattunement;}
    public float getFire(){return fireattunement;}
    public float getEarth(){return earthattunement;}
    public float getElementlimit(){return elementlimit;}

    public void setQi(int set){
        this.qi = set;
    }
    public void addQi(int add){
        this.qi = Math.min(qi + add, Integer.MAX_VALUE);
    }
    public void subQi(int sub){
        this.qi = Math.max(qi - sub, 0);
    }

    public void setMaxqi(int set){
        this.maxqi = set;
    }
    public void addMaxQi(int add){
        this.maxqi = Math.min(qi + add, Integer.MAX_VALUE);
    }
    public void subMaxQi(int sub){
        this.maxqi = Math.max(qi - sub, 0);
    }

    public void setMaxqiX(float set){this.maxqiX = set;}
    public void setQiregen(float set){this.qiregen = set;}
    public void setSpelldamage(float set){this.spelldamage = set;}
    public void setSpellresist(float set){this.spellresist = set;}
    public void setSpellcost(float set){this.spellcost = set;}

    public void setAffinity(String affinity){
        this.affinity = affinity;
    }
    public void setAffinity2(String affinity){
        this.affinity2 = affinity2;
    }

    public void setType(String type){
        this.type = type;
    }
    public boolean isType(String type){return this.type == type;}

    public void setSpells(Collection<SpellType> spells){
        this.unlockedspells = new HashSet<>(spells);
    }
    public void addSpell(SpellType spell){ this.unlockedspells.add(spell);}
    public boolean hasSpell(SpellType spell){return this.unlockedspells.contains(spell);}

    public void setCultivation(float set){
        this.cultivation = set;
    }
    public void addCultivation(float add){
        this.cultivation = Math.min(cultivation + add, Float.MAX_VALUE);
    }
    public void subCultivation(float sub){
        this.cultivation = Math.max(cultivation - sub, 0);
    }

    public void setMetal(float set){
        this.metalattunement = set;
    }
    public void addMetal(float add){
        this.metalattunement = Math.min(metalattunement + add, elementlimit);
    }
    public void subMetal(float sub){
        this.metalattunement = Math.max(metalattunement - sub, 0);
    }

    public void setWater(float set){
        this.waterattunement = set;
    }
    public void addWater(float add){
        this.waterattunement = Math.min( + add, elementlimit);
    }
    public void subWater(float sub){
        this.waterattunement = Math.max( - sub, 0);
    }

    public void setWood(float set){
        this.woodattunement = set;
    }
    public void addWood(float add){
        this.woodattunement = Math.min( + add, elementlimit);
    }
    public void subWood(float sub){
        this.woodattunement = Math.max( - sub, 0);
    }

    public void setFire(float set){
        this.fireattunement = set;
    }
    public void addFire(float add){
        this.fireattunement = Math.min( + add, elementlimit);
    }
    public void subFire(float sub){
        this.fireattunement = Math.max( - sub, 0);
    }

    public void setEarth(float set){
        this.earthattunement = set;
    }
    public void addEarth(float add){
        this.earthattunement = Math.min( + add, elementlimit);
    }
    public void subEarth(float sub){
        this.earthattunement = Math.max( - sub, 0);
    }

    public void setElementlimit(float set){
        this.elementlimit = set;
    }
    public void addElementLimit(float add){
        this.elementlimit = Math.min( + add, 1000);
    }
    public void subElementLimit(float sub){
        this.elementlimit = Math.max( - sub, 0);
    }


    public void copyFrom(SpiritCap source){
        this.qi = source.qi;
        this.maxqi = source.maxqi;
        this.maxqiX = source.maxqiX;
        this.qiregen = source.qiregen;
        this.spelldamage = source.spelldamage;
        this.spellresist = source.spellresist;
        this.spellcost = source.spellcost;
        this.cultivation = source.cultivation;
        this.metalattunement = source.metalattunement;
        this.waterattunement = source.waterattunement;
        this.woodattunement = source.woodattunement;
        this.fireattunement = source.fireattunement;
        this.earthattunement = source.earthattunement;
        this.elementlimit = source.elementlimit;
        this.affinity = source.affinity;
        this.affinity2 = source.affinity2;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("qi", qi);
        nbt.putInt("maxqi", maxqi);
        nbt.putFloat("maxqiX", maxqiX);
        nbt.putFloat("qiregen", qiregen);
        nbt.putFloat("spelldamage", spelldamage);
        nbt.putFloat("spellresist", spellresist);
        nbt.putFloat("spellcost", spellcost);
        nbt.putFloat("cultivation", cultivation);
        nbt.putFloat("metalattunement", metalattunement);
        nbt.putFloat("waterattunement", waterattunement);
        nbt.putFloat("woodattunement", woodattunement);
        nbt.putFloat("fireattunement", fireattunement);
        nbt.putFloat("earthattunement", earthattunement);
        nbt.putFloat("elementlimit", elementlimit);
        nbt.putString("affinity", affinity);
        nbt.putString("affinity2", affinity2);
        nbt.putString("type", type);
    }

    public void loadNBTData(CompoundTag nbt){
        qi = nbt.getInt("qi");
        maxqi = nbt.getInt("maxqi");
        maxqiX = nbt.getFloat("maxqiX");
        qiregen = nbt.getFloat("qiregen");
        spelldamage = nbt.getFloat("spelldamage");
        spellresist = nbt.getFloat("spellresist");
        spellcost = nbt.getFloat("spellcost");
        cultivation = nbt.getFloat("cultivation");
        metalattunement = nbt.getFloat("metalattunement");
        waterattunement = nbt.getFloat("waterattunement");
        woodattunement = nbt.getFloat("woodattunement");
        fireattunement = nbt.getFloat("fireattunement");
        earthattunement = nbt.getFloat("earthattunement");
        elementlimit = nbt.getFloat("elementlimit");
        affinity = nbt.getString("affinity");
        affinity2 = nbt.getString("affinity2");
        type = nbt.getString("type");
    }
}
