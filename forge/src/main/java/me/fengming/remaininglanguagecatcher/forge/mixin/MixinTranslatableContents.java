package me.fengming.remaininglanguagecatcher.forge.mixin;

import me.fengming.remaininglanguagecatcher.RemainingLanguageCatcher;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TranslatableContents.class)
public class MixinTranslatableContents {
    @Redirect(method = "decompose", at = @At(value = "INVOKE", target = "Lnet/minecraft/locale/Language;getOrDefault(Ljava/lang/String;)Ljava/lang/String;"))
    private String decompose_Language_getOrDefault_Redirected_nullValueCheck(Language instance, String s) {
        String r = instance.getOrDefault(s);
        if (r.equals(s)) RemainingLanguageCatcher.foundNullValue(s);
        return r;
    }
}
