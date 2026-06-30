package team.terrafirmagreg.jellies.common.entity.jellie.base;

import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.client.ClientHelpers;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.entities.EntityHelpers;
import net.dries007.tfc.common.entities.livestock.MammalProperties;
import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.dries007.tfc.common.entities.livestock.TFCAnimalProperties;
import net.dries007.tfc.common.entities.livestock.pet.TamableMammal;
import net.dries007.tfc.config.TFCConfig;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.calendar.Calendars;
import net.dries007.tfc.util.events.AnimalProductEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.MinecraftForge;

import team.terrafirmagreg.jellies.common.data.JelliesEntityDataSerializers;
import team.terrafirmagreg.jellies.common.data.JelliesSounds;
import team.terrafirmagreg.jellies.common.data.JelliesTags;

public class JellieBase extends TamableMammal {
    public static final EntityDataAccessor<JellieBaseVariant> DATA_VARIANT;
    public static final EntityDataAccessor<Long> DATA_PRODUCED;

    static {
        DATA_VARIANT = SynchedEntityData.defineId(JellieBase.class, JelliesEntityDataSerializers.JELLIE_VARIANT.get());
        DATA_PRODUCED = SynchedEntityData.defineId(JellieBase.class, EntityHelpers.LONG_SERIALIZER);
    }

    static double familiarityCap = 1;
    static int adulthoodDays = 32;
    static int uses = 230;
    static boolean eatsRottenFood = false;
    static int produceTicks = 23500;
    static double produceFamiliarity = 0.15;
    static int childCount = 2;
    static long gestationDays = 64;

    public JellieBase(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level, JelliesSounds.JELLIE, TFCConfig.SERVER.catConfig);
    }

    // region Config Bypass
    @Override
    public float getAdultFamiliarityCap() {
        return (float) familiarityCap;
    }

    @Override
    public int getDaysToAdulthood() {
        return adulthoodDays;
    }

    @Override
    public int getUsesToElderly() {
        return uses;
    }

    @Override
    public boolean eatsRottenFood() {
        return eatsRottenFood;
    }

    @Override
    public boolean isReadyForAnimalProduct() {
        return getFamiliarity() > produceFamiliarity && hasProduct() && this.level().dimension() == this.getVariant().getDimension() && isHungry();
    }

    @Override
    public long getProductsCooldown() {
        return Math.max(0, produceTicks + getProducedTick() - Calendars.get(level()).getTicks());
    }

    @Override
    public int getChildCount() {
        return childCount;
    }

    @Override
    public long getGestationDays() {
        return gestationDays;
    }
    // endregion

    // region Data/Init Stuff
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT, JellieBaseVariant.SPRING);
        this.entityData.define(DATA_PRODUCED, 0L);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("variant", this.getVariant().getSerializedName());
        tag.putLong("produced", this.getProducedTick());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariant(JellieBaseVariant.getByName(tag.getString("variant")));
        this.setProducedTick(tag.getLong("produced"));
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        SpawnGroupData spawnData = super.finalizeSpawn(level, difficulty, type, data, tag);
        this.setGender(Gender.FEMALE);
        this.setBaby(this.random.nextFloat() < 0.1F);
        this.setVariant(this.initialVariant(level));
        return spawnData;
    }

    public JellieBaseVariant initialVariant(ServerLevelAccessor level) {
        BlockPos pos = this.blockPosition();
        ResourceKey<Level> dimension = level.getLevel().dimension();
        Holder<Biome> biome = level.getBiome(pos);

        return JellieBaseVariant.getByHabitat(dimension, biome);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0F).add(Attributes.MOVEMENT_SPEED, 0.25F).add(Attributes.ATTACK_DAMAGE, 2F);
    }

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        return level.getBiome(pos).is(JelliesTags.Biomes.JellieHabitat) && checkMobSpawnRules(type, level, spawn, pos, rand);
    }
    // endregion

    // region Variant Stuff
    public JellieBaseVariant getVariant() {
        if (!this.entityData.hasItem(DATA_VARIANT)) {
            return JellieBaseVariant.SPRING;
        }
        return this.entityData.get(DATA_VARIANT);
    }

    public void setVariant(JellieBaseVariant type) {
        this.entityData.set(DATA_VARIANT, type);
    }

    public ResourceLocation getTextureLocation() {
        return this.getVariant().getTexture();
    }

    public ResourceKey<Level> getDimension() {
        return this.getVariant().getDimension();
    }

    public TagKey<Biome> getBiome() {
        return this.getVariant().getBiome();
    }

    public Item getProduct() {
        return this.getVariant().getItem();
    }

    public float getAmbientalTemperature() {
        return switch (this.getVariant()) {
            case LAVA -> 10F;
            case ICE -> -10F;
            default -> 0F;
        };
    }
    // endregion

    // region Production Stuff
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        final ItemStack held = player.getItemInHand(hand);

        if (held.isEmpty() && player.isShiftKeyDown() && this.getOwner() != null && this.isOwnedBy(player) && !this.isOnFire()) {
            if (this.level().isClientSide) {
                ClientHelpers.openPetScreen(this);
            }

            return InteractionResult.SUCCESS;
        } else if (this.isFood(held) && this.isHungry()) {
            if (this.isReadyForAnimalProduct()) {
                AnimalProductEvent event = new AnimalProductEvent(this.level(), this.blockPosition(), player, this, this.getProduct().getDefaultInstance(), held, 1);
                if (!MinecraftForge.EVENT_BUS.post(event)) {
                    if (event.getLevel().isClientSide) {
                        return InteractionResult.PASS;
                    }

                    this.playSound(this.eatingSound(held), 1.0F, 1.0F);
                    held.shrink(1);
                    this.setProductsCooldown();
                    this.addUses(event.getUses());
                    Helpers.spawnItem(event.getLevel(), event.getPos(), event.getProduct());
                }
            } else {
                this.eatFood(held, hand, player);
            }

            return InteractionResult.SUCCESS;
        } else {
            if (this.getFamiliarity() > 0.99F && this.getOwnerUUID() == null && this.isFood(held)) {
                this.tame(player);
                held.shrink(1);

                return InteractionResult.SUCCESS;
            }

            return InteractionResult.PASS;
        }
    }

    public boolean hasProduct() {
        return (getProducedTick() <= 0 || getProductsCooldown() <= 0) && getAgeType() == Age.ADULT && this.getProduct() != null;
    }

    public void setProductsCooldown() {
        this.setProducedTick(Calendars.get(this.level()).getTicks());
    }

    public long getProducedTick() {
        return this.entityData.get(DATA_PRODUCED);
    }

    public void setProducedTick(long producedTick) {
        this.entityData.set(DATA_PRODUCED, producedTick);
    }

    public MutableComponent getProductReadyName() {
        return Component.translatable("jellies.jade.product.jellie");
    }
    // endregion

    // region Pet Stuff
    @Override
    public boolean willListenTo(Command command, boolean isClientSide) {
        if (!isClientSide && command == Command.SIT && getRandom().nextFloat() < 0.25f) {
            return false;
        } else {
            return super.willListenTo(command, isClientSide);
        }
    }

    @Override
    public void receiveCommand(ServerPlayer player, Command command) {
        if (getOwner() != null && getOwner().equals(player)) {
            playSound(SoundEvents.SLIME_SQUISH, getSoundVolume(), getVoicePitch());
        }
        super.receiveCommand(player, command);
    }

    @Override
    public boolean canAttack(LivingEntity entity) {
        return super.canAttack(entity) && (Helpers.isEntity(entity, TFCTags.Entities.HUNTED_BY_CATS) || entity instanceof Monster);
    }
    // endregion

    // region Breeding Stuff
    @Override
    public boolean canMate(Animal otherAnimal) {
        if (otherAnimal.getClass() != this.getClass()) {
            return false;
        } else {
            TFCAnimal other = (TFCAnimal) otherAnimal;
            return this.isReadyToMate() && other.isReadyToMate();
        }
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob other) {
        if (other != this && other instanceof JellieBase mate && !isFertilized() && !mate.isFertilized() && getUUID().compareTo(mate.getUUID()) < 0) {
            this.onFertilized(mate);
        } else if (other == this) {
            final Entity baby = getEntityTypeForBaby().create(level);
            if (baby instanceof TFCAnimalProperties properties && baby instanceof AgeableMob ageable) {
                setBabyTraits(properties);
                return ageable;
            }
        }

        return null;
    }

    public void onFertilized(JellieBase mate) {
        mate.setFertilized(true);
        mate.setLastFed(getLastFed() - 1L);
        this.setLastFed(getLastFed() - 1);
        this.addUses(5);

        mate.setPregnantTime(getCalendar().getTotalDays());
        CompoundTag genes = new CompoundTag();
        mate.createGenes(genes, this);
        mate.setGenes(genes.isEmpty() ? null : genes);
    }

    public void createGenes(CompoundTag tag, TFCAnimalProperties mate) {
        super.createGenes(tag, mate);
        if (mate instanceof JellieBase jellieMate) {
            JellieBaseVariant childVariant;
            if ((this.getVariant() == JellieBaseVariant.LAVA && jellieMate.getVariant() == JellieBaseVariant.PLANT)
                    || (this.getVariant() == JellieBaseVariant.PLANT && jellieMate.getVariant() == JellieBaseVariant.LAVA)) {
                childVariant = JellieBaseVariant.LATEX;
            } else {
                childVariant = random.nextBoolean() ? this.getVariant() : jellieMate.getVariant();
            }
            tag.putString("variant", childVariant.getSerializedName());
        }
    }

    public void applyGenes(CompoundTag tag, MammalProperties baby) {
        super.applyGenes(tag, baby);
        if (baby instanceof JellieBase jellie) {
            String variantName = tag.getString("variant");
            JellieBaseVariant variant = JellieBaseVariant.getByName(variantName);
            jellie.setVariant(variant);
        }
    }
    // endregion

    // region Other / Unsorted
    @Override
    public TagKey<Item> getFoodTag() {
        return JelliesTags.Items.JELLIE_FOOD;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return size.height * 0.5F;
    }

    @Override
    public boolean fireImmune() {
        return getVariant() == JellieBaseVariant.LAVA;
    }

    // endregion
}
