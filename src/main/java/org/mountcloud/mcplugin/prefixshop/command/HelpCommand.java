package org.mountcloud.mcplugin.prefixshop.command;

import org.bukkit.command.CommandSender;
import org.mountcloud.mcplugin.common.command.BaseCommand;
import org.mountcloud.mcplugin.common.command.BaseCommandSenderType;
import org.mountcloud.mcplugin.prefixshop.PrefixShopPluginMain;
import org.mountcloud.mcplugin.prefixshop.language.LanguageEnum;
import org.mountcloud.mcplugin.prefixshop.service.PrefixShopCommandService;
import org.mountcloud.mcplugin.prefixshop.util.LangUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月12日 上午12:59:07 
 * TODO:帮助命令
 */
public class HelpCommand extends BaseCommand{

	public HelpCommand() {
		super("help", BaseCommandSenderType.ARBITRARLIY, 0);
		setUsage(PrefixShopPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.COMMAND_USEAGE_HELP.name(), LanguageEnum.COMMAND_USEAGE_HELP.getValue()));
	}
	
	public static void showHelp(CommandSender sender) {
		String boxTitle = LangUtil.getBoxTitle();
		PrefixShopPluginMain.getInstance().getBaseMessageService().sendMessage(sender, "&5[------------ &9"+boxTitle+" &5------------]");

		List<String> helps = new ArrayList<String>();

		Map<String, BaseCommand> cmds = PrefixShopCommandService.getInstance().getCommands();
		Set<String> keys = cmds.keySet();
		for(String key : keys) {
			BaseCommand baseCommand = cmds.get(key);
			if(PrefixShopCommandService.getInstance().checkPermission(sender, baseCommand)&&PrefixShopCommandService.getInstance().checkCommandSenderType(sender, baseCommand)) {
				String uesAge = baseCommand.getUsage();
				if(!helps.contains(uesAge)) {
					helps.add(uesAge);
				}
			}
		}

		for(String help : helps) {
			PrefixShopPluginMain.getInstance().getBaseMessageService().sendMessage(sender, help);
		}
	}
	
	@Override
	public void run(CommandSender commandSender, String[] args, BaseCommandSenderType cs) {
		HelpCommand.showHelp(commandSender);
	}

}
