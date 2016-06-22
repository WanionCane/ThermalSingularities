package wanion.thermsingul;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.avaritia.items.ItemSingularity;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import java.util.List;

public final class ThermalSingularityItem extends ItemSingularity
{
    public final static ThermalSingularityItem instance = new ThermalSingularityItem();

    public static final int[] recipeValues = new int[]{75, 25, 100, 90, 75};
    public static final String[] thermTypes = new String[]{"shiny", "manaInfused", "signalum", "lumium", "enderium"};
    private static final int[] lighter = new int[]{0x69D6FC, 0x6499BC, 0xE55D00, 0xD8B44E, 0x107272};
    private static final int[] darker = new int[]{0x44CCFC, 0x436071, 0xA52300, 0xD4A41B, 0x093A3F};

    private ThermalSingularityItem()
    {
        super();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemstack, int renderPass)
    {
        return renderPass == 0 ? darker[getDamage(itemstack)] : lighter[getDamage(itemstack)];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item.singularity_" + thermTypes[getDamage(stack)];
    }

    @Override
    public int getDamage(ItemStack itemStack)
    {
        return MathHelper.clamp_int(super.getDamage(itemStack), 0, thermTypes.length);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        int i = 0;
        while (i < thermTypes.length)
            list.add(new ItemStack(item, 1, i++));
    }
}