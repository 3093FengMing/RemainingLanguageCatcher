package me.fengming.remaininglanguagecatcher.fabric;

import dev.architectury.utils.GameInstance;
import me.fengming.remaininglanguagecatcher.RemainingLanguageCatcher;
import net.fabricmc.api.ModInitializer;

public class RemainingLanguageCatcherFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RemainingLanguageCatcher.init(GameInstance.getClient().gameDirectory);
    }
}