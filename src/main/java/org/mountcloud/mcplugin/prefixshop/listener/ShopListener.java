package org.mountcloud.mcplugin.prefixshop.listener;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.mountcloud.mcplugin.common.listener.BaseListener;
import org.mountcloud.mcplugin.prefixapi.service.PrefixServcie;
import org.mountcloud.mcplugin.prefixshop.PrefixShopPluginMain;
import org.mountcloud.mcplugin.prefixshop.config.DefaultConfig;
import org.mountcloud.mcplugin.prefixshop.language.LanguageEnum;
import org.mountcloud.mcplugin.prefixshop.util.LangUtil;
import org.mountcloud.mcplugin.prefixshop.util.MessageUtil;

import java.util.List;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月18日 下午11:41:46 
 * TODO:
 */
public class ShopListener extends BaseListener{
	
	@EventHandler
	public void onClickItem(InventoryClickEvent event) {
		String title = LangUtil.getBoxTitle();
		if (event.getInventory().getTitle().equalsIgnoreCase(title)) {
			//直接取消所有事件
			event.setCancelled(true);

			Player player = (Player) event.getWhoClicked();

			DefaultConfig defaultConfig = PrefixShopPluginMain.getInstance().getBaseConfigService().getConfig(DefaultConfig.configName);

			Inventory inventory = event.getInventory();
			ItemStack currentItem = event.getCurrentItem();
			ItemStack cursor = event.getCursor();

			if(currentItem==null){
				player.closeInventory();
				return;
			}
			if(!currentItem.getType().equals(Material.AIR)&&currentItem.getItemMeta().getLocalizedName().equals(DefaultConfig.localizedName)){
				DefaultConfig.PrefixShopItem item = null;

				List<DefaultConfig.PrefixShopItem> items = defaultConfig.getPrefixShopItems();
				for(int i=0;i<items.size();i++){
					DefaultConfig.PrefixShopItem tempItem = items.get(i);
					String colorPrefix = ChatColor.translateAlternateColorCodes('&',tempItem.getPrefix());
					if(colorPrefix.equals(currentItem.getItemMeta().getDisplayName())){
						item = tempItem;
						break;
					}
				}
				if(item!=null){
					RegisteredServiceProvider<PrefixServcie> prefixServiceReg = PrefixShopPluginMain.getInstance().getServer().getServicesManager().getRegistration(PrefixServcie.class);
					RegisteredServiceProvider<Economy> economyReg = PrefixShopPluginMain.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
					if(prefixServiceReg!=null&&economyReg!=null){
						List<String> playerPrefixs = prefixServiceReg.getProvider().getPlayerPrefixs(player);

						//已经有了该称号
						if(playerPrefixs.contains(item.getPrefix())){
							MessageUtil.sendMessage(event.getWhoClicked(),LanguageEnum.MESSGE_ALREADY_HAVE,ChatColor.translateAlternateColorCodes('&',item.getPrefix()));
							player.closeInventory();
							return;
						}
						//免费称号
						if(item.getPrice().equals(0)){
							prefixServiceReg.getProvider().addPrefix(player,item.getPrefix());
							MessageUtil.sendMessage(player,LanguageEnum.MESSAGE_PAY_SUCCESS,ChatColor.translateAlternateColorCodes('&',item.getPrefix()));
							player.closeInventory();
							return;
						}
						//余额不足
						if(economyReg.getProvider().getBalance(player)<item.getPrice()){
							MessageUtil.sendMessage(player,LanguageEnum.MESSAGE_INSUFFICIENT_BALANCE);
							player.closeInventory();
							return;
						}
						//完成支付
						if(economyReg.getProvider().withdrawPlayer(player,item.getPrice()).transactionSuccess()){
							prefixServiceReg.getProvider().addPrefix(player,item.getPrefix());
							MessageUtil.sendMessage(player,LanguageEnum.MESSAGE_PAY_SUCCESS,ChatColor.translateAlternateColorCodes('&',item.getPrefix()));
							player.closeInventory();
							return;
						}

					}
				}
			}

		}


//		String inventoryTitle = inventory != null ? inventory.getTitle() : "null";
//		if(currentItem!=null){
//			PrefixShopPluginMain.getInstance().getBaseLogService().info(inventoryTitle+":"+currentItem.toString());
//		}else{
//			PrefixShopPluginMain.getInstance().getBaseLogService().info("currenItem is null");
//		}
//
//		if(cursor!=null){
//			PrefixShopPluginMain.getInstance().getBaseLogService().info(inventoryTitle+":"+cursor.toString());
//		}else{
//			PrefixShopPluginMain.getInstance().getBaseLogService().info("cursor is null");
//		}


		
	}

}
