package org.mountcloud.mcplugin.prefixshop.util;

import org.mountcloud.mcplugin.prefixshop.PrefixShopPluginMain;
import org.mountcloud.mcplugin.prefixshop.language.LanguageEnum;

/**
 * @author zhanghaishan
 * @version 创建时间：2018/10/20 17:36
 * TODO:
 */
public class LangUtil {

    public static String getBoxTitle(){
        String pluginTitle = PrefixShopPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.BOX_TITLE.name(), LanguageEnum.BOX_TITLE.getValue());
        return pluginTitle;
    }

}
