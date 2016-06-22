package wanion.thermsingul.proxy;

import fox.spiteful.avaritia.render.FancyHaloRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import wanion.thermsingul.ThermalSingularityItem;

public final class ClientProxy extends CommonProxy
{
    @Override
    public void init()
    {
        MinecraftForgeClient.registerItemRenderer(ThermalSingularityItem.instance, new FancyHaloRenderer());
    }
}
