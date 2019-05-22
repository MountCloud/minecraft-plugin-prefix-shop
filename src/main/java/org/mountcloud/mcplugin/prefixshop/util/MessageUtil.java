package org.mountcloud.mcplugin.prefixshop.util;

import org.bukkit.command.CommandSender;
import org.mountcloud.mcplugin.common.language.BaseLanguageEnum;
import org.mountcloud.mcplugin.prefixshop.PrefixShopPluginMain;
import org.mountcloud.mcplugin.prefixshop.language.LanguageEnum;

/**
 * @author zhanghaishan
 * @version 创建时间：2018/10/20 17:32
 * TODO:
 */
public class MessageUtil {

    /**
     * 提供消息
     * @param messageEnum 消息类型
     * @return 消息
     */
    public static String getMessage(LanguageEnum messageEnum, String...prms) {
        String title = PrefixShopPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.MESSAGE_TITLE.name(), LanguageEnum.MESSAGE_TITLE.getValue());
        String message = PrefixShopPluginMain.getInstance().getBaseLanguageService().getLanguage(messageEnum.name(), messageEnum.getValue(),prms);
        return title+" "+message;
    }

    /**
     * 提供消息
     * @param messageEnum 消息类型
     * @return 消息
     */
    public static String getMessage(BaseLanguageEnum messageEnum, String...prms) {
        String title = PrefixShopPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.MESSAGE_TITLE.name(), LanguageEnum.MESSAGE_TITLE.getValue());
        String message = PrefixShopPluginMain.getInstance().getBaseLanguageService().getLanguage(messageEnum.name(), messageEnum.getValue(),prms);
        return title+" "+message;
    }

    /**
     * 发送message
     * @param sender 接收message的人
     * @param messageEnum 消息枚举
     * @param prms 消息参数
     */
    public static void sendMessage(CommandSender sender, LanguageEnum messageEnum, String...prms) {
        String message = getMessage(messageEnum,prms);
        PrefixShopPluginMain.getInstance().getBaseMessageService().sendMessage(sender, message);
    }

    /**
     * 发送message
     * @param sender 接收message的人
     * @param messageEnum 消息枚举
     * @param prms 消息参数
     */
    public static void sendMessage(CommandSender sender,BaseLanguageEnum messageEnum,String...prms) {
        String message = getMessage(messageEnum,prms);
        PrefixShopPluginMain.getInstance().getBaseMessageService().sendMessage(sender, message);
    }

}
