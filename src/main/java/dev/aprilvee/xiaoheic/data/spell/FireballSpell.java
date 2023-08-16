package dev.aprilvee.xiaoheic.data.spell;

import dev.aprilvee.xiaoheic.capability.SpiritCap;
import dev.aprilvee.xiaoheic.capability.SpiritProvider;
import dev.aprilvee.xiaoheic.data.Datalist;
import dev.aprilvee.xiaoheic.data.datatype.*;
import dev.aprilvee.xiaoheic.entity.BasicSpell;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class FireballSpell implements ICastable, IProjectileSpell {
    public int index = 1;
    public Component name = null;
    public String id = "fireball";
    public int qiCost = 300;
    public float perQiCost = 0.02f;

    @Override
    public void basicProjTick(BasicSpell spell) {
        if(spell.lifetime > 400){
            spell.discard();
        }
        Vec3 pos = spell.position();
        Vec3 velocity = spell.getDeltaMovement();
        Vec3 newpos = pos.add(velocity);
        Vec3 hitpos = newpos;
        Random rand = new Random();
        spell.level().addParticle(ParticleTypes.FLAME, hitpos.x, hitpos.y, hitpos.z, velocity.x/20, velocity.y/20, velocity.z/20);
        spell.level().addParticle(ParticleTypes.SMALL_FLAME, hitpos.x, hitpos.y, hitpos.z, velocity.x/40 + (rand.nextFloat()-0.5f)/10, velocity.y/40 + (rand.nextFloat()-0.5f)/10, velocity.z/40 + (rand.nextFloat()-0.5f)/10);
        spell.level().addParticle(ParticleTypes.SMALL_FLAME, hitpos.x, hitpos.y, hitpos.z, velocity.x/40 + (rand.nextFloat()-0.5f)/10, velocity.y/40 + (rand.nextFloat()-0.5f)/10, velocity.z/40 + (rand.nextFloat()-0.5f)/10);
        spell.level().addParticle(ParticleTypes.SMALL_FLAME, hitpos.x, hitpos.y, hitpos.z, velocity.x/40 + (rand.nextFloat()-0.5f)/10, velocity.y/40 + (rand.nextFloat()-0.5f)/10, velocity.z/40 + (rand.nextFloat()-0.5f)/10);

    }

    @Override
    public void entityHit(Entity target, Entity caster, BasicSpell spell) {
        target.setSecondsOnFire(3);
        target.hurt(target.damageSources().onFire(),5F);
        Random rand = new Random();

        spell.level().addParticle(ParticleTypes.FLAME, spell.position().x, spell.position().y, spell.position().z, (rand.nextFloat()-0.5f)/10, (rand.nextFloat()-0.5f)/10,(rand.nextFloat()-0.5f)/10);
        spell.level().addParticle(ParticleTypes.FLAME, spell.position().x, spell.position().y, spell.position().z, (rand.nextFloat()-0.5f)/10, (rand.nextFloat()-0.5f)/10,(rand.nextFloat()-0.5f)/10);
        spell.level().addParticle(ParticleTypes.FLAME, spell.position().x, spell.position().y, spell.position().z, (rand.nextFloat()-0.5f)/10, (rand.nextFloat()-0.5f)/10,(rand.nextFloat()-0.5f)/10);
        spell.level().addParticle(ParticleTypes.FLAME, spell.position().x, spell.position().y, spell.position().z, (rand.nextFloat()-0.5f)/10, (rand.nextFloat()-0.5f)/10,(rand.nextFloat()-0.5f)/10);
        spell.level().addParticle(ParticleTypes.FLAME, spell.position().x, spell.position().y, spell.position().z, (rand.nextFloat()-0.5f)/10, (rand.nextFloat()-0.5f)/10,(rand.nextFloat()-0.5f)/10);

    }

    @Override
    public void blockHit(BlockPos pos, Entity caster, BasicSpell spell) {
    }

    @Override
    public void castSpell(Player player) {
        BasicSpell spell = new BasicSpell(player.level(), player.getEyePosition(), this.getIndex());
        spell.setOwner(player);
        spell.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5f, 2);
        player.level().addFreshEntity(spell);
    }

    @Override
    public CastType getCasttype() {
        return CastType.activespell;
    }
    public Element element() {
        return null;
    }
    public SType type() {
        return null;
    }
    @Override
    public boolean keybindable() {
        return true;
    }

    @Override
    public boolean isSpell() {
        return true;
    }

    @Override
    public int getQiCost() {
        return qiCost;
    }

    @Override
    public float getPerQiCost() {
        return perQiCost;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Component getName() {
        return name;
    }
}
