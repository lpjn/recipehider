package com.recipehider.jei;

import com.recipehider.RecipeHider;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@JeiPlugin
public class JEIHiderPlugin implements IModPlugin {
    private static final Logger LOG = LoggerFactory.getLogger(JEIHiderPlugin.class);

    public static final ResourceLocation PLUGIN_UID =
        ResourceLocation.fromNamespaceAndPath(RecipeHider.MODID, "jei");

    private static final Set<String> HIDE_ALL = Set.of("chipped", "rechiseledcreate");
    private static final String RECHISELED = "rechiseled";
    private static final ResourceLocation RECHISELED_CHISEL =
        ResourceLocation.fromNamespaceAndPath(RECHISELED, "chisel");

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        List<ItemStack> toHide = new ArrayList<>();
        for (Item item : BuiltInRegistries.ITEM) {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            if (id == null) continue;
            String ns = id.getNamespace();
            if (HIDE_ALL.contains(ns)) {
                toHide.add(new ItemStack(item));
            } else if (ns.equals(RECHISELED) && !id.equals(RECHISELED_CHISEL)) {
                toHide.add(new ItemStack(item));
            }
        }

        if (toHide.isEmpty()) {
            LOG.info("[RecipeHider] Nothing to hide (target mods not installed?).");
            return;
        }

        runtime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, toHide);
        LOG.info("[RecipeHider] Hid {} JEI items.", toHide.size());
    }
}
