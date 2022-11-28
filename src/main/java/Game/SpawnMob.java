package Game;

import org.bukkit.entity.EntityType;

public enum SpawnMob {
    Plain(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.SPIDER, EntityType.CREEPER),
    Dessert(EntityType.HUSK, EntityType.STRAY, EntityType.SPIDER, EntityType.CREEPER),
    Nether(EntityType.WITHER_SKELETON, EntityType.PIGLIN_BRUTE, EntityType.SPIDER, EntityType.CREEPER),
    Hunter(EntityType.VINDICATOR, EntityType.PILLAGER, EntityType.SPIDER, EntityType.CREEPER);

    private EntityType zombie;
    private EntityType skeleton;
    private EntityType spider;
    private EntityType creeper;
    SpawnMob(EntityType zombie, EntityType skeleton, EntityType spider, EntityType creeper) {
        this.zombie = zombie;
        this.skeleton = skeleton;
        this.spider = spider;
        this.creeper = creeper;
    }
    public EntityType getZombie(){
        return zombie;
    }
    public EntityType getSkeleton(){
        return skeleton;
    }
    public EntityType getSpider(){
        return spider;
    }
    public EntityType getCreeper(){
        return creeper;
    }
}
