package me.fengming.remaininglanguagecatcher.fabric;

import me.fengming.remaininglanguagecatcher.RemainingLanguageCatcher;
import net.fabricmc.api.ModInitializer;

public class RemainingLanguageCatcherFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RemainingLanguageCatcher.init();
    }
}