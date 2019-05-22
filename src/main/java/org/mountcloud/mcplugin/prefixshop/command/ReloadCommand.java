package org.mountcloud.mcplugin.prefixshop.command;

import org.bukkit.command.CommandSender;
import org.mountcloud.mcplugin.common.command.BaseCommand;
import org.mountcloud.mcplugin.common.command.BaseCommandSenderType;
import org.mountcloud.mcplugin.prefixshop.PrefixShopPluginMain;
import org.mountcloud.mcplugin.prefixshop.language.LanguageEnum;
import org.mountcloud.mcplugin.prefixshop.permission.PermissionEnum;
import org.mountcloud.mcplugin.prefixshop.util.MessageUtil;

import java.util.Arrays;

/**
 * @author zhanghaishan
 * @version 创建时间：2018/10/20 19:01
 * TODO: 重载配置
 */
public class ReloadCommand extends BaseCommand {
    public ReloadCommand() {
        super("reload", BaseCommandSenderType.ARBITRARLIY, 0);
        setUsage(PrefixShopPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.COMMAND_USEAGE_RELOAD.name(), LanguageEnum.COMMAND_USEAGE_RELOAD.getValue()));
        setHasPermissions(Arrays.asList(PermissionEnum.PREFIXSHOP_ADMIN.getValue()));
    }

    @Override
    public void run(CommandSender commandSender, String[] strings, BaseCommandSenderType baseCommandSenderType) {
        PrefixShopPluginMain.getInstance().getBaseConfigService().reloadAllConfig();
        MessageUtil.sendMessage(commandSender,LanguageEnum.MESSAGE_RELOAD_SUCCESS);
    }
}
