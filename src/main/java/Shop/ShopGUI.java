package Shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zombiesurvival.zs.Main;

import java.util.Arrays;

public class ShopGUI implements Listener {
    private final Inventory inv;
    private Main bs = Main.getPlugin(Main.class);

    public ShopGUI() {
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 27, "Shop");

        // Put the items into the inventory
    }

    // You can call this whenever you want to put the items in
    public void initializeItems(Player p) {
        //SWORD
        if (bs.usermanager.getUser(p).getArmorLevel() == 0){
            inv.setItem(0, createGuiItem(Material.LEATHER_HELMET, "가죽갑옷", "§60코인", "§b기본적인 갑옷입니다"));
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 1){
            inv.setItem(0, createGuiItem(Material.GOLDEN_HELMET, "금갑옷", "§670코인", "§b금갑옷으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 2){
            inv.setItem(0, createGuiItem(Material.CHAINMAIL_HELMET, "사슬갑옷", "§6140코인", "§b금갑옷으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 3){
            inv.setItem(0, createGuiItem(Material.IRON_HELMET, "철갑옷", "§6210코인", "§b금갑옷으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 4){
            inv.setItem(0, createGuiItem(Material.DIAMOND_HELMET, "다이아몬드갑옷", "§6280코인", "§b금갑옷으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 5){
            inv.setItem(0, createGuiItem(Material.NETHERITE_HELMET, "네더라이트갑옷", "§6350코인", "§b금갑옷으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 6){
            inv.setItem(0, createGuiItem(Material.BARRIER, "최대강화됨", "§60코인", "§b최대강화상태입니다"));
        }
        //ARMOR
        if (bs.usermanager.getUser(p).getSwordLevel() == 0){
            inv.setItem(2, createGuiItem(Material.WOODEN_SWORD, "나무검", "§60코인", "§b기본적인 검입니다"));
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 1){
            inv.setItem(2, createGuiItem(Material.STONE_SWORD, "금검", "§650코인", "§b금검으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 2){
            inv.setItem(2, createGuiItem(Material.GOLDEN_SWORD, "돌검", "§6100코인", "§b돌검으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 3){
            inv.setItem(2, createGuiItem(Material.IRON_SWORD, "컬검", "§6150코인", "§b철검으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 4){
            inv.setItem(2, createGuiItem(Material.DIAMOND_SWORD, "다이아몬드검", "§61200코인", "§b다이아몬드검으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 5){
            inv.setItem(2, createGuiItem(Material.NETHERITE_SWORD, "네더라이트검", "§6250코인", "§b네더라이트검으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 6){
            inv.setItem(2, createGuiItem(Material.BARRIER, "최대강화됨", "§60코인", "§b최대강화상태입니다"));
        }
        if (p.getInventory().contains(Material.BOW)) {
            inv.setItem(4, createGuiItem(Material.ARROW, "화살*16", "§645코인", "§b화살 16개를 지급합니다"));
        }
        else{
            inv.setItem(4, createGuiItem(Material.BOW, "활+화살*16", "§6150코인", "§b활과 화살 16개를 지급합니다"));
        }
        inv.setItem(6, createGuiItem(Material.GOLDEN_APPLE, "황금사과", "§6200코인", "§b황금사과 1개를 지급합니다"));
        inv.setItem(8, createGuiItem(Material.COOKED_BEEF, "스테이크", "§650코인", "§b스테이크 16개를 지급합니다"));
        inv.setItem(9, createGuiItem(Material.ENCHANTED_BOOK, "검 인첸트", "§6?코인", "§b날카로움을 1만큼 추가합니다"));
        inv.setItem(11, createGuiItem(Material.ENCHANTED_BOOK, "갑옷 인첸트", "§6?코인", "§b보호를 1만큼 추가합니다"));
        inv.setItem(13, createGuiItem(Material.ENCHANTED_BOOK, "활 인첸트", "§6?코인", "§b힘을 1만큼 추가합니다"));
    }

    // Nice little method to create a gui item with a custom name, and description
    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    // You can open the inventory with this
    public void openInventory(Player p) {
        p.openInventory(inv);
        initializeItems(p);
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        final Player p = (Player) e.getWhoClicked();

        // Using slots click is a best option for your inventory click's
        if (e.getRawSlot() == 0){
            buyArmor(p, (60 * bs.usermanager.getUser(p).getSwordLevel()));
        }
        if (e.getRawSlot() == 2){
            buySword(p, (50 * bs.usermanager.getUser(p).getSwordLevel()));
        }
        if (e.getRawSlot() == 4){
            p.sendMessage("TESTING!");
        }
        if (e.getRawSlot() == 6){
            buyOtherItem(Material.GOLDEN_APPLE, 1, p, 200);
        }
        if (e.getRawSlot() == 8){
            buyOtherItem(Material.COOKED_BEEF, 16, p, 50);
        }
        openInventory((Player) e.getWhoClicked());
    }
    private Boolean buyOtherItem(Material Mt, int Count, Player p, int Price){
        if (bs.usermanager.getUser(p).getCoin() < Price){
            p.sendMessage("돈이 부족합니다");
            return false;
        }
        bs.usermanager.getUser(p).setCoin(bs.usermanager.getUser(p).getCoin() - Price);
        ItemStack item = new ItemStack(Mt, Count);
        p.getInventory().addItem(item);
        return true;
    }
    private Boolean buySword(Player p, int Price){
        if (bs.usermanager.getUser(p).getCoin() < Price){
            p.sendMessage("돈이 부족합니다");
            return false;
        }
        if(bs.usermanager.getUser(p).getSwordLevel() == 6){
            p.sendMessage("최대강화상태입니다");
            return false;
        }
        bs.usermanager.getUser(p).setCoin(bs.usermanager.getUser(p).getCoin() - Price);
        ItemStack i = new ItemStack(Material.AIR);
        if (bs.usermanager.getUser(p).getSwordLevel() == 0){
            i.setType(Material.WOODEN_SWORD);
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 1){
            i.setType(Material.GOLDEN_SWORD);
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 2){
            i.setType(Material.STONE_SWORD);
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 3){
            i.setType(Material.IRON_SWORD);
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 4){
            i.setType(Material.DIAMOND_SWORD);
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 5){
            i.setType(Material.NETHERITE_SWORD);
        }
        for(ItemStack it: p.getInventory()){
            if (it != null) {
                if (isSword(it)) {
                    p.getInventory().remove(it);
                }
            }
        }
        ItemMeta itm = i.getItemMeta();
        if (bs.usermanager.getUser(p).getSwordEnchantLevel() != 0){
            itm.addEnchant(Enchantment.DAMAGE_ALL, bs.usermanager.getUser(p).getSwordEnchantLevel(), true);
        }
        itm.setUnbreakable(true);
        i.setItemMeta(itm);
        p.getInventory().addItem(i);
        bs.usermanager.getUser(p).setSwordLevel(bs.usermanager.getUser(p).getSwordLevel() + 1);
        if (bs.usermanager.getUser(p).getSwordLevel() == 1){
            inv.setItem(2, createGuiItem(Material.STONE_SWORD, "금검", "§650코인", "§b금검으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 2){
            inv.setItem(2, createGuiItem(Material.GOLDEN_SWORD, "돌검", "§6100코인", "§b돌검으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 3){
            inv.setItem(2, createGuiItem(Material.IRON_SWORD, "컬검", "§6150코인", "§b철검으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 4){
            inv.setItem(2, createGuiItem(Material.DIAMOND_SWORD, "다이아몬드검", "§61200코인", "§b다이아몬드검으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 5){
            inv.setItem(2, createGuiItem(Material.NETHERITE_SWORD, "네더라이트검", "§6250코인", "§b네더라이트검으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getSwordLevel() == 6){
            inv.setItem(2, createGuiItem(Material.BARRIER, "최대강화됨", "§60코인", "§b최대강화상태입니다"));
        }
        return true;
    }
    private Boolean isSword(ItemStack item){
        if (item.getType().equals(Material.NETHERITE_SWORD)){
            return true;
        }
        if (item.getType().equals(Material.DIAMOND_SWORD)){
            return true;
        }
        if (item.getType().equals(Material.IRON_SWORD)){
            return true;
        }
        if (item.getType().equals(Material.STONE_SWORD)){
            return true;
        }
        if (item.getType().equals(Material.GOLDEN_SWORD)){
            return true;
        }
        if (item.getType().equals(Material.WOODEN_SWORD)){
            return true;
        }
        return false;
    }
    private Boolean buyArmor(Player p, int Price){
        if (bs.usermanager.getUser(p).getCoin() < Price){
            p.sendMessage("돈이 부족합니다");
            return false;
        }
        if(bs.usermanager.getUser(p).getArmorLevel() == 6){
            p.sendMessage("최대강화상태입니다");
            return false;
        }
        bs.usermanager.getUser(p).setCoin(bs.usermanager.getUser(p).getCoin() - Price);
        ItemStack i1 = new ItemStack(Material.AIR);
        ItemStack i2 = new ItemStack(Material.AIR);
        ItemStack i3 = new ItemStack(Material.AIR);
        ItemStack i4 = new ItemStack(Material.AIR);
        if (bs.usermanager.getUser(p).getArmorLevel() == 0){
            i1.setType(Material.LEATHER_HELMET);
            i2.setType(Material.LEATHER_CHESTPLATE);
            i3.setType(Material.LEATHER_LEGGINGS);
            i4.setType(Material.LEATHER_BOOTS);
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 1) {
            i1.setType(Material.GOLDEN_HELMET);
            i2.setType(Material.GOLDEN_CHESTPLATE);
            i3.setType(Material.GOLDEN_LEGGINGS);
            i4.setType(Material.GOLDEN_BOOTS);
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 2) {
            i1.setType(Material.CHAINMAIL_HELMET);
            i2.setType(Material.CHAINMAIL_CHESTPLATE);
            i3.setType(Material.CHAINMAIL_LEGGINGS);
            i4.setType(Material.CHAINMAIL_BOOTS);
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 3) {
            i1.setType(Material.IRON_HELMET);
            i2.setType(Material.IRON_CHESTPLATE);
            i3.setType(Material.IRON_LEGGINGS);
            i4.setType(Material.IRON_BOOTS);
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 4) {
            i1.setType(Material.DIAMOND_HELMET);
            i2.setType(Material.DIAMOND_CHESTPLATE);
            i3.setType(Material.DIAMOND_LEGGINGS);
            i4.setType(Material.DIAMOND_BOOTS);
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 5) {
            i1.setType(Material.NETHERITE_HELMET);
            i2.setType(Material.NETHERITE_CHESTPLATE);
            i3.setType(Material.NETHERITE_LEGGINGS);
            i4.setType(Material.NETHERITE_BOOTS);
        }
        ItemMeta itm = i1.getItemMeta();
        if (bs.usermanager.getUser(p).getSwordEnchantLevel() != 0){
            itm.addEnchant(Enchantment.DAMAGE_ALL, bs.usermanager.getUser(p).getSwordEnchantLevel(), true);
        }
        itm.setUnbreakable(true);
        i1.setItemMeta(itm);
        i2.setItemMeta(itm);
        i3.setItemMeta(itm);
        i4.setItemMeta(itm);
        p.getInventory().setHelmet(i1);
        p.getInventory().setChestplate(i2);
        p.getInventory().setLeggings(i3);
        p.getInventory().setBoots(i4);
        bs.usermanager.getUser(p).setArmorLevel(bs.usermanager.getUser(p).getArmorLevel() + 1);
        if (bs.usermanager.getUser(p).getArmorLevel() == 1){
            inv.setItem(0, createGuiItem(Material.GOLDEN_HELMET, "금갑옷", "§670코인", "§b금갑옷으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 2){
            inv.setItem(0, createGuiItem(Material.CHAINMAIL_HELMET, "사슬갑옷", "§6140코인", "§b금갑옷으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 3){
            inv.setItem(0, createGuiItem(Material.IRON_HELMET, "철갑옷", "§6210코인", "§b금갑옷으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 4){
            inv.setItem(0, createGuiItem(Material.DIAMOND_HELMET, "다이아몬드갑옷", "§6280코인", "§b금갑옷으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 5){
            inv.setItem(0, createGuiItem(Material.NETHERITE_HELMET, "네더라이트갑옷", "§6350코인", "§b금갑옷으로 업그레이드합니다"));
        }
        if (bs.usermanager.getUser(p).getArmorLevel() == 6){
            inv.setItem(0, createGuiItem(Material.BARRIER, "최대강화됨", "§60코인", "§b최대강화상태입니다"));
        }
        return true;
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }
}