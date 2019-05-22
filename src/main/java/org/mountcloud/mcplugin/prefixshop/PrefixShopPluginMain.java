package org.mountcloud.mcplugin.prefixshop;

import org.mountcloud.mcplugin.common.BasePlugin;
import org.mountcloud.mcplugin.common.config.BaseLanguageConfig;
import org.mountcloud.mcplugin.prefixshop.command.HelpCommand;
import org.mountcloud.mcplugin.prefixshop.command.OpenCommand;
import org.mountcloud.mcplugin.prefixshop.command.ReloadCommand;
import org.mountcloud.mcplugin.prefixshop.config.DefaultConfig;
import org.mountcloud.mcplugin.prefixshop.listener.ShopListener;
import org.mountcloud.mcplugin.prefixshop.service.PrefixShopCommandService;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月16日 上午12:33:36 
 * TODO:
 */
public class PrefixShopPluginMain extends BasePlugin{
	
	private static PrefixShopPluginMain prefixShopPluginMain;
	
	public static PrefixShopPluginMain getInstance() {
		return prefixShopPluginMain;
	}

	@Override
	public boolean enable() {
		prefixShopPluginMain = this;
		getBaseLogService().info("init Config");
		initConfig();
		getBaseLogService().info("init command");
		initCommand();
		getBaseLogService().info("init listener");
		initListener();
		return true;
	}
	
	private void initCommand() {
		PrefixShopCommandService commandService = PrefixShopCommandService.getInstance();
		
		OpenCommand openCommand = new OpenCommand();
		commandService.registerCommand(openCommand);
		commandService.registerCommand("", openCommand);

		HelpCommand helpCommand = new HelpCommand();
		commandService.registerCommand(helpCommand);

		ReloadCommand reloadCommand = new ReloadCommand();
		commandService.registerCommand(reloadCommand);
		
		this.registerCommand(commandService);
	}
	
	private void initListener() {
		this.registerListener(new ShopListener());
	}

	private void initConfig(){

		BaseLanguageConfig languageConfig = new BaseLanguageConfig(this);
		getBaseConfigService().registerConfig(languageConfig);

		DefaultConfig defaultConfig = new DefaultConfig();
		getBaseConfigService().registerConfig(defaultConfig);
	}
}
