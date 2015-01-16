package thermaldynamics.crafting;

import cofh.lib.util.helpers.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thermaldynamics.ThermalDynamics;
import thermaldynamics.ducts.Ducts;
import thermaldynamics.ducts.attachments.facades.FacadeHelper;

public class RecipeFacade implements IRecipe {
    @Override
    public boolean matches(InventoryCrafting craft, World p_77569_2_) {
        boolean a = false;
        boolean b = false;

        for (int i = 0; i < craft.getSizeInventory(); i++) {
            ItemStack stack = craft.getStackInSlot(i);
            if (stack == null) continue;
            if (ItemHelper.itemsEqualForCrafting(stack, Ducts.STRUCTURE.itemStack)) {
                if (a)
                    return false;
                else
                    a = true;
            } else {
                if (!(stack.getItem() instanceof ItemBlock))
                    return false;

                if (!FacadeHelper.isValid(
                        ((ItemBlock) stack.getItem()).field_150939_a,
                        stack.getItem().getMetadata(stack.getItemDamage())))
                    return false;

                if (b)
                    return false;
                else
                    b = true;

            }
        }


        return a && b;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting craft) {
        Block block;
        int meta;

        for (int i = 0; i < craft.getSizeInventory(); i++) {
            ItemStack stack = craft.getStackInSlot(i);
            if (stack == null) continue;
            if (!ItemHelper.itemsEqualForCrafting(stack, Ducts.STRUCTURE.itemStack)) {
                block = ((ItemBlock) stack.getItem()).field_150939_a;
                meta = stack.getItem().getMetadata(stack.getItemDamage());

                NBTTagCompound tag = new NBTTagCompound();
                tag.setString("Block", Block.blockRegistry.getNameForObject(block));
                tag.setByte("Meta", ((byte) meta));

                ItemStack itemStack = new ItemStack(ThermalDynamics.itemFacade, 6);
                itemStack.setTagCompound(tag);
                return itemStack;
            }
        }
        return null;
    }

    @Override
    public int getRecipeSize() {
        return 2;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return new ItemStack(ThermalDynamics.itemFacade, 6);
    }
}