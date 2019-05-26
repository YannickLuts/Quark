package vazkii.quark.automation.feature;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.quark.base.module.Feature;

import java.util.Comparator;
import java.util.List;

public class AnimalsEatFloorFood extends Feature {

	@SubscribeEvent
	public void onEntityTick(LivingUpdateEvent event) {
		if(event.getEntityLiving() instanceof EntityAnimal) {
			EntityAnimal animal = (EntityAnimal) event.getEntityLiving();
			if(animal.getGrowingAge() == 0 && animal.ticksExisted % 20 == 0 && !animal.isInLove() && !animal.isDead) {
				double range = 2;
				List<EntityItem> nearbyFood = animal.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, animal.getEntityBoundingBox().expand(range, 0, range),
						(EntityItem i) -> i != null && !i.getItem().isEmpty() && !i.isDead && animal.isBreedingItem(i.getItem()) && i.getItem().getItem() != Items.ROTTEN_FLESH);
				
				if(!nearbyFood.isEmpty()) {
					nearbyFood.sort(Comparator.comparingDouble(ent -> ent.getDistanceSq(animal)));
					EntityItem e = nearbyFood.get(0);
					
					ItemStack stack = e.getItem();
					stack.shrink(1);
					e.setItem(stack);
					if(stack.isEmpty())
						e.setDead();
					
					animal.setInLove(null);
				}
			}
		}
	}
	
	@Override
	public String[] getIncompatibleMods() {
		return new String[] { "betterwithmods", "easybreeding", "animania" };
	}
	
	@Override
	public boolean hasSubscriptions() {
		return true;
	}
	
}
