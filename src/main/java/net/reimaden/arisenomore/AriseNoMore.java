package net.reimaden.arisenomore;

import com.mojang.logging.LogUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(AriseNoMore.MOD_ID)
public class AriseNoMore {

    public static final String MOD_ID = "arisenomore";
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int CHANCE = 100;

    public AriseNoMore() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Hey! Wake up!");
    }

    @Mod.EventBusSubscriber(modid = AriseNoMore.MOD_ID)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onPlayerWakeUp(PlayerWakeUpEvent event) {
            Player entity = event.getEntity();
            ServerPlayer player = (ServerPlayer) entity;
            int random = player.getRandom().nextInt(CHANCE);

            if (random == 0 && player.getSleepTimer() >= 100) {
                player.hurt(DamageSource.GENERIC, Float.MAX_VALUE);
            }
        }
    }
}
