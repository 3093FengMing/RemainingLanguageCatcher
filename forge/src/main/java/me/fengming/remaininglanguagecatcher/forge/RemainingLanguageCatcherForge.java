package me.fengming.remaininglanguagecatcher.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.architectury.utils.GameInstance;
import me.fengming.remaininglanguagecatcher.RemainingLanguageCatcher;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RemainingLanguageCatcher.MOD_ID)
public class RemainingLanguageCatcherForge {
    public RemainingLanguageCatcherForge() {
        EventBuses.registerModEventBus(RemainingLanguageCatcher.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        RemainingLanguageCatcher.init(GameInstance.getClient().gameDirectory);
    }
}