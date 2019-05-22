package org.mountcloud.mcplugin.prefixshop.service;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.mountcloud.mcplugin.common.command.BaseCommand;
import org.mountcloud.mcplugin.common.command.BaseCommandSenderType;
import org.mountcloud.mcplugin.common.language.BaseLanguageEnum;
import org.mountcloud.mcplugin.common.service.command.BaseCommandeSercvice;
import org.mountcloud.mcplugin.prefixshop.PrefixShopPluginMain;
import org.mountcloud.mcplugin.prefixshop.command.HelpCommand;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月18日 下午10:59:10 
 * TODO:
 */
public class PrefixShopCommandService extends BaseCommandeSercvice{

	private static PrefixShopCommandService prefixShopCommandService;

	public static PrefixShopCommandService getInstance(){
		if(prefixShopCommandService == null){
			prefixShopCommandService = new PrefixShopCommandService("prefixshop");
		}
		return prefixShopCommandService;
	}

	public PrefixShopCommandService(String execCommand) {
		super(execCommand,PrefixShopPluginMain.getInstance());
	}

	@Override
	public boolean execCommand(CommandSender sender, BaseCommand command, String[] args,
			BaseCommandSenderType commandSenderType) {
		command.run(sender, args, commandSenderType);
		return true;
	}

	@Override
	public boolean notFoundCommand(CommandSender sender, Command command, String[] args) {
		BaseLanguageEnum languageEnum = BaseLanguageEnum.COMMAND_NOT_FOUND_COMMAND;
		PrefixShopPluginMain.getInstance().getBaseMessageService().sendMessageByLanguage(sender, languageEnum.name(), languageEnum.getValue(),command.getName(),getCommandName(args));
		HelpCommand.showHelp(sender);
		return true;
	}

}
