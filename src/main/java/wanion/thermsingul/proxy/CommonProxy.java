package wanion.thermsingul.proxy;

import com.google.common.collect.ImmutableSet;
import cpw.mods.fml.common.registry.GameRegistry;
import fox.spiteful.avaritia.Config;
import fox.spiteful.avaritia.crafting.CompressorManager;
import fox.spiteful.avaritia.crafting.Grinder;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import wanion.thermsingul.ThermalSingularityItem;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.io.File.separatorChar;

public class CommonProxy
{
    private final Set<String> allowed;

    public CommonProxy()
    {
        Configuration config = new Configuration(new File("." + separatorChar + "config" + separatorChar + "ThermalSingularities.cfg"));
        allowed = ImmutableSet.copyOf(config.getStringList("enabledTypes", Configuration.CATEGORY_GENERAL, new String[]{"signalum", "lumium", "enderium"}, "possible values:\n\"shiny, manaInfused, signalum, lumium, enderium\"."));
        if (config.hasChanged())
            config.save();
    }

    public final void preInit()
    {
        GameRegistry.registerItem(ThermalSingularityItem.instance, "Thermal Singularity");
    }


    public void init()
    {
    }

    public final void postInit()
    {
        if (!Config.craftingOnly)
            addToRecipeInput();
    }

    private void addToRecipeInput()
    {
        final List<String> oreNameList = Arrays.asList("blockPlatinum", "blockMithril", "blockSignalum", "blockLumium", "blockEnderium");
        int i = 0;
        for (final String singularityType : ThermalSingularityItem.thermTypes) {
            if (allowed.contains(singularityType)) {
                CompressorManager.addOreRecipe(new ItemStack(ThermalSingularityItem.instance, 1, i), ThermalSingularityItem.recipeValues[i], oreNameList.get(i));
                Grinder.catalyst.getInput().add(new ItemStack(ThermalSingularityItem.instance, 1, i));
            }
            i++;
        }
    }
}