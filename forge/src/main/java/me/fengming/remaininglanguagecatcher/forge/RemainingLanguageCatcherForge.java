package me.fengming.remaininglanguagecatcher.forge;

import dev.architectury.platform.forge.EventBuses;
import me.fengming.remaininglanguagecatcher.RemainingLanguageCatcher;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RemainingLanguageCatcher.MOD_ID)
public class RemainingLanguageCatcherForge {
    public RemainingLanguageCatcherForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(RemainingLanguageCatcher.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        RemainingLanguageCatcher.init();
    }
}