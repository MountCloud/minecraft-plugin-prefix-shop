package org.mountcloud.mcplugin.prefixshop.language;
/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月18日 下午11:01:43 
 * TODO:
 */
public enum LanguageEnum {

    BOX_TITLE("Prefix Shop"),

    MESSAGE_TITLE("&a[&ePrefix Shop&a]"),
    MESSAGE_PAY_SUCCESS("&aYou have successfully purchased prefix:{0}"),
    MESSGE_ALREADY_HAVE("&eYou already have prefixe:{0}"),
    MESSAGE_RELOAD_SUCCESS("&aReload Config Success"),
    MESSAGE_INSUFFICIENT_BALANCE("&eInsufficient balance"),

    TEXT_PUT_PREFIX("&bClick pay this prefix."),
    TEXT_PREICE("&ePrice:{0}"),

    COMMAND_USEAGE_OPEN("&7/prefixshop &b[open]  &2| Open Shop"),
    COMMAND_USEAGE_RELOAD("&7/prefixshop &breload  &2| Reload Config"),
    COMMAND_USEAGE_HELP("&7/prefixshop &bhelp  &2| Show Help'");

    private String value;
    LanguageEnum(String s) {
        this.value = s;
    }
    public String getValue(){
        return this.value;
    }
}
