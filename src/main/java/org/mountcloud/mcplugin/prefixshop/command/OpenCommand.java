package org.mountcloud.mcplugin.prefixshop.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.mountcloud.mcplugin.common.command.BaseCommand;
import org.mountcloud.mcplugin.common.command.BaseCommandSenderType;
import org.mountcloud.mcplugin.prefixapi.service.PrefixServcie;
import org.mountcloud.mcplugin.prefixshop.PrefixShopPluginMain;
import org.mountcloud.mcplugin.prefixshop.config.DefaultConfig;
import org.mountcloud.mcplugin.prefixshop.language.LanguageEnum;
import org.mountcloud.mcplugin.prefixshop.permission.PermissionEnum;
import org.mountcloud.mcplugin.prefixshop.util.LangUtil;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月18日 下午10:58:17 
 * TODO: 开启商店
 */
public class OpenCommand extends BaseCommand{

	public OpenCommand() {
		super("open", BaseCommandSenderType.PLAYER, 0);
		setUsage(PrefixShopPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.COMMAND_USEAGE_OPEN.name(), LanguageEnum.COMMAND_USEAGE_OPEN.getValue()));
		setHasPermissions(Arrays.asList(PermissionEnum.PREFIXSHOP_USE.getValue(),PermissionEnum.PREFIXSHOP_ADMIN.getValue()));
	}

	@Override
	public void run(CommandSender commandSender, String[] args, BaseCommandSenderType commandSenderType) {

		DefaultConfig defaultConfig = PrefixShopPluginMain.getInstance().getBaseConfigService().getConfig(DefaultConfig.configName);
		String boxTitle = LangUtil.getBoxTitle();
		String itemType = defaultConfig.getItemType();
		Integer itemNum = defaultConfig.getShopSlotNum();
		List<DefaultConfig.PrefixShopItem> prefixShopItems = defaultConfig.getPrefixShopItems();

		Inventory shopInv = Bukkit.createInventory(null,itemNum , boxTitle);

		if(prefixShopItems!=null&&prefixShopItems.size()>0){
			for(int i=0;i<prefixShopItems.size();i++){
				DefaultConfig.PrefixShopItem item = prefixShopItems.get(i);

				ItemStack itemStack = new ItemStack(Material.getMaterial(itemType), 1);
				ItemMeta itemMeta = itemStack.getItemMeta();

				String prefix = getColorStr(item.getPrefix());
				itemMeta.setDisplayName(prefix);

				itemMeta.setLocalizedName(DefaultConfig.localizedName);

				String priceStr = PrefixShopPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.TEXT_PREICE.name(),LanguageEnum.TEXT_PREICE.getValue(),String.valueOf(item.getPrice()));
				String putMsg = PrefixShopPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.TEXT_PUT_PREFIX.name(),LanguageEnum.TEXT_PUT_PREFIX.getValue());

				priceStr = getColorStr(priceStr);
				putMsg = getColorStr(putMsg);

				itemMeta.setLore(Arrays.asList(priceStr,putMsg));
				itemStack.setItemMeta(itemMeta);

				shopInv.addItem(itemStack);
			}
		}

		Player player = (Player) commandSender;
		player.openInventory(shopInv);


	}

	/**
	 * 转颜色字符串
	 * @param str
	 * @return
	 */
	private String getColorStr(String str){
		return ChatColor.translateAlternateColorCodes('&',str);
	}

}
