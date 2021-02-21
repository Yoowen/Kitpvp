package me.goowen.kitpvp.modules.utilities;

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
import java.util.Map;

public class ItemBuilder {

    private ItemStack is;

    /**
     * maakt een nieuwe itembuilder.
     *
     * @param m de material waarmee de itembuilder word gemaakt.
     */
    public ItemBuilder(Material m) {
        this(m, 1);
    }

    /**
     * Maakt een nieuwe itembuilder.
     *
     * @param is De itemstack waarmee de itembuilder word gemaakt.
     */
    public ItemBuilder(ItemStack is) {
        this.is = is;
    }

    /**
     * Maakt een nieuwe itembuilder.
     *
     * @param m Het materiaal dat word gebruikt.
     * @param amount De hoeveelheid van het materiaal.
     */
    public ItemBuilder(Material m, int amount) {
        is = new ItemStack(m, amount);
    }

    /**
     * Maakt een nieuwe itembuilder.
     *
     * @param m          Het materiaal van het item.
     * @param amount     De Hoeveelheid van het item.
     * @param durability De durability van het item.
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder(Material m, int amount, byte durability) {
        is = new ItemStack(m, amount, durability);
    }

    /**
     * Cloont de itembuilder.
     *
     * @return
     */
    public ItemBuilder clone() {
        return new ItemBuilder(is);
    }


    /**
     * Ze de hoeveel heid van de item.
     *
     * @param amouth de hoeveelheid die gezet moet worden.
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder setAmouth(int amouth) {
        is.setAmount(amouth);
        return this;
    }

    /**
     * Ze de durability van de item.
     *
     * @param dur de durability die moet worden gezet.
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder setDurability(short dur) {
        is.setDurability(dur);
        return this;
    }

    /**
     * Zet de naam van het item.
     *
     * @param name de naam die gezet moet worden.
     */
    public ItemBuilder setName(String name) {
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }

    /**
     * Voegt een unsafe Enchantment toe op het item.
     *
     * @param ench  De enchantment die moet worden gezet.
     * @param level De level van de enchantment.
     */
    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        is.addUnsafeEnchantment(ench, level);
        return this;
    }

    /**
     * Verwijdert de enchantment van een item.
     *
     * @param ench de enchantment die moet worden verwijdert.
     */
    public ItemBuilder removeEnchantment(Enchantment ench) {
        is.removeEnchantment(ench);
        return this;
    }

    /**
     * Zet de skull owner van een item.
     *
     * @param owner De naam van de skullowner.
     */
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

    /**
     * Voegt een enchantment toe op een item.
     *
     * @param ench  de enchantment die moet worden toegevoegt.
     * @param level het level van de enchantment.
     */
    public ItemBuilder addEnchant(Enchantment ench, int level) {
        ItemMeta im = is.getItemMeta();
        im.addEnchant(ench, level, true);
        is.setItemMeta(im);
        return this;
    }

    /**
     * Zet de durability van een item naar oneindig.
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder setInfinityDurability() {
        is.setDurability(Short.MAX_VALUE);
        return this;
    }

    /**
     * Herzet de lore.
     *
     * @param lore de lore die moet worden gezet.
     */
    public ItemBuilder setLore(String... lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        return this;
    }

    /**
     * Herzet de lore.
     *
     * @param lore de lore die moet worden gezet.
     */
    public ItemBuilder setLore(List<String> lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    /**
     * verwijdert een lore regel.
     */
    public ItemBuilder removeLoreLine(String line) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if (!lore.contains(line)) return this;
        lore.remove(line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    /**
     * verwijdert een specefieke lore regel.
     *
     * @param index het nummer van welke regel moet worden verwijdert.
     */
    public ItemBuilder removeLoreLine(int index) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if (index < 0 || index > lore.size()) return this;
        lore.remove(index);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    /**
     * voegt een lore lijn toe.
     *
     * @param line de lore lijn die moet worden toegevoegt.
     */
    public ItemBuilder addLoreLine(String line) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (im.hasLore()) lore = new ArrayList<>(im.getLore());
        lore.add(line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    /**
     * voegt een lore lijn toe.
     *
     * @param line de lore lijn die moet worden toegevoegt.
     * @param pos  Het nummer van de lijn die moet worden gezet.
     */
    public ItemBuilder addLoreLine(String line, int pos) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        lore.set(pos, line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    /**
     * Herzet alle lores op een item.
     *
     * @param lores de lijst met lores.
     */
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

    /**
     * Verwijdert alle atributen van de het item.
     *
     * @param bool
     * @return
     */
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

    public ItemBuilder setCustomModelData(Integer integer)
    {
        ItemMeta itemMeta = is.getItemMeta();

        itemMeta.setCustomModelData(integer);

        is.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Zet de armor color van een item.
     *
     * @param color De kleur die moet worden gezet.
     */
    public ItemBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
            im.setColor(color);
            is.setItemMeta(im);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    /**
     * Zet de armor color van een item.
     *
     * @param color1 rood value
     * @param color2 groen value
     * @param color3 blauw value
     * @return
     */
    public ItemBuilder setLeatherArmourColorRGB(int color1, int color2, int color3) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) this.is.getItemMeta();
            im.setColor(Color.fromRGB(color1, color2, color3));
            this.is.setItemMeta(im);
        } catch (ClassCastException var5) {
        }

        return this;
    }

    /**
     * Maakt een itemstack van een itembuilder.
     *
     * @return The itemstack gemodificeert door de itembuilder.
     */
    public ItemStack toItemStack() {
        return is;
    }

}

