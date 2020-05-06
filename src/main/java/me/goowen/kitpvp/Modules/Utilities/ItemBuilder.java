package me.goowen.kitpvp.Modules.Utilities;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
    private ItemStack is;

     // Create a new ItemBuilder from scratch.
    public ItemBuilder(Material m) {
        this(m, 1);
    }

     // Create a new ItemBuilder over an existing itemstack.
    public ItemBuilder(ItemStack is) {
        this.is = is;
    }

    // Create a new ItemBuilder from scratch.
    public ItemBuilder(Material m, int amount) {
        is = new ItemStack(m, amount);
    }

    // Create a new ItemBuilder from scratch.
    @SuppressWarnings("deprecation")
    public ItemBuilder(Material m, int amount, byte durability) {
        is = new ItemStack(m, amount, durability);
    }

    // Clone the ItemBuilder into a new one.
    public ItemBuilder clone() {
        return new ItemBuilder(is);
    }

    // Change the amouth of the item.
    @SuppressWarnings("deprecation")
    public ItemBuilder setAmouth(int amouth) {
        is.setAmount(amouth);
        return this;
    }

    //Change the durability of the item.
    @SuppressWarnings("deprecation")
    public ItemBuilder setDurability(short dur) {
        is.setDurability(dur);
        return this;
    }

    //Change the displayname of the item
    public ItemBuilder setName(String name) {
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }

    //Add an unsafe enchantment.
    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        is.addUnsafeEnchantment(ench, level);
        return this;
    }

    //Remove a certain enchant from the item.
    public ItemBuilder removeEnchantment(Enchantment ench) {
        is.removeEnchantment(ench);
        return this;
    }

    //Set the skull owner for the item. Works on skulls only.
    @SuppressWarnings("deprecation")
    public ItemBuilder setSkullOwner(String owner) {
        try {
            SkullMeta im = (SkullMeta) is.getItemMeta();
            im.setOwner(owner);
            is.setItemMeta(im);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    //Add an enchant to the item.
    public ItemBuilder addEnchant(Enchantment ench, int level) {
        ItemMeta im = is.getItemMeta();
        im.addEnchant(ench, level, true);
        is.setItemMeta(im);
        return this;
    }

    //Sets infinity durability on the item by setting the durability to Short.MAX_VALUE.
    @SuppressWarnings("deprecation")
    public ItemBuilder setInfinityDurability() {
        is.setDurability(Short.MAX_VALUE);
        return this;
    }

    //sets the lore.
    public ItemBuilder setLore(String... lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        return this;
    }

    //Re-sets the lore.
    public ItemBuilder setLore(List<String> lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    //Remove a lore line.
    public ItemBuilder removeLoreLine(String line) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if (!lore.contains(line)) return this;
        lore.remove(line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    //Remove a lore line.
    public ItemBuilder removeLoreLine(int index) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if (index < 0 || index > lore.size()) return this;
        lore.remove(index);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    //Add a lore line.
    public ItemBuilder addLoreLine(String line) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (im.hasLore()) lore = new ArrayList<>(im.getLore());
        lore.add(line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    //Add a lore line.
    public ItemBuilder addLoreLine(String line, int pos) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        lore.set(pos, line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    //Replaces all lores. with your new lore list.
    public ItemBuilder setLoreList(List<String> lores) {
        System.out.println(lores == null);

        ItemMeta itemMeta = is.getItemMeta();

        System.out.println(itemMeta == null);


        // Alle huidige lores en een anti null checker
        List<String> lore;
        if (itemMeta.hasLore()) {
            lore = new ArrayList<>(itemMeta.getLore());
        } else {
            lore = new ArrayList<>();
        }
        System.out.println(lore == null);

        // Removed alle huidige lores
        for (String s : lore) {
            lore.remove(s);
        }

        // Replaced alle lores met de nieuwe lores.
        int loreCounter = 0;
        lore.addAll(lores);
        itemMeta.setLore(lore);
        is.setItemMeta(itemMeta);
        return this;
    }


    public ItemBuilder hideAttributes(boolean bool) {
        ItemMeta itemMeta = is.getItemMeta();

        if (bool) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        } else {
            itemMeta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            itemMeta.removeItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }

        is.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder unbreakable(boolean bool) {
        ItemMeta itemMeta = is.getItemMeta();

        itemMeta.setUnbreakable(bool);

        is.setItemMeta(itemMeta);
        return this;
    }

    //Sets armorcolor
    public ItemBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
            im.setColor(color);
            is.setItemMeta(im);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    //Returns current itemstack
    public ItemStack toItemStack() {
        return is;
    }

}

