package me.fengming.remaininglanguagecatcher.forge.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.fengming.remaininglanguagecatcher.RemainingLanguageCatcher;
import net.minecraft.client.resources.language.ClientLanguage;
import net.minecraft.client.resources.language.LanguageInfo;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

@Mixin(ClientLanguage.class)
public class MixinClientLanguage {
    private static Gson MIRROR_GSON = new Gson();
    private static Pattern MIRROR_UNSUPPORTED_FORMAT_PATTERN = Pattern.compile("%(\\d+\\$)?[\\d.]*[df]");
    private static Map<String, Boolean> checkDoublePutMap = new HashMap<>();
    private static Map<String, String> copiedMap = new HashMap<>();
    @Inject(method = "loadFrom", at = @At("TAIL"))
    private static void loadForm_TAIL_Injected_callFound(ResourceManager resourceManager, List<LanguageInfo> list, CallbackInfoReturnable<ClientLanguage> cir) {
        checkDoublePutMap.forEach((k, v) -> {
            if (!v) RemainingLanguageCatcher.foundDefaultValue(k, copiedMap.getOrDefault(k, "Null Value"));
        });
    }
    @Redirect(method = "appendFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/locale/Language;loadFromJson(Ljava/io/InputStream;Ljava/util/function/BiConsumer;)V"))
    private static void appendFrom_Language_loadFromJson_Redirected_defaultValueCheck(InputStream inputStream, BiConsumer<String, String> biConsumer) {
        JsonObject jsonObject = MIRROR_GSON.fromJson(new InputStreamReader(inputStream, StandardCharsets.UTF_8), JsonObject.class);
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String value = MIRROR_UNSUPPORTED_FORMAT_PATTERN.matcher(GsonHelper.convertToString(entry.getValue(), entry.getKey())).replaceAll("%$1s");
            String key = entry.getKey();
            checkDoublePutMap.compute(key, (k, v) -> v != null); // so fucking genius
            copiedMap.put(key, value);
            biConsumer.accept(key, value);
        }
    }
}
