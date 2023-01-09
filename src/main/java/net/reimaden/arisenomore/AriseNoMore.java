package net.reimaden.arisenomore;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AriseNoMore implements ModInitializer {

	public static final String MOD_ID = "arisenomore";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final int CHANCE = 100;

	@Override
	public void onInitialize() {
		LOGGER.info("Hey! Wake up!");

		// Kill the player when they wake up and if the condition is true
		EntitySleepEvents.STOP_SLEEPING.register((entity, sleepingPos) -> {
			ServerPlayerEntity player = (ServerPlayerEntity) entity;
			int random = player.getRandom().nextInt(CHANCE);

			if (random == 0 && player.getSleepTimer() >= 100) {
				player.damage(DamageSource.GENERIC, Float.MAX_VALUE);
			}
		});
	}
}