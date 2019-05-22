package org.mountcloud.mcplugin.prefixshop.config;

import org.mountcloud.mcplugin.common.config.BaseConfig;
import org.mountcloud.mcplugin.prefixshop.PrefixShopPluginMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhanghaishan
 * @version 创建时间：2018年10月20日 下午11:41:46
 * TODO:默认配置
 */
public class DefaultConfig extends BaseConfig {

    public static String configName = "config.yml";

    public static String localizedName = "prefixshop-prefix-item";

    private int shopSlotNum;
    private String itemType;
    private List<PrefixShopItem> prefixShopItems;

    public DefaultConfig() {
        super(configName, PrefixShopPluginMain.getInstance());
    }

    @Override
    protected boolean createConfig() {
        getBasePlugin().getConfig();
        return true;
    }

    @Override
    public void loadConfig() {
        prefixShopItems = new ArrayList<>();
        List<Map<?, ?>> list = getConfig().getMapList("shopItems");
        if(list!=null){
            for(int i=0;i<list.size();i++){
                Map<?, ?> item = list.get(i);
                PrefixShopItem prefixShopItem = PrefixShopItem.initShopItemByMap(item);
                if(prefixShopItem!=null){
                    prefixShopItems.add(prefixShopItem);
                }
            }
        }

        shopSlotNum = getConfig().getInt("shopConfig.shopSlotNum",27);
        itemType = getConfig().getString("shopConfig.itemType","");
    }

    public static class PrefixShopItem{

        public static PrefixShopItem initShopItemByMap(Map<?,?> map){
            PrefixShopItem prefixShopItem = null;
            String prefix = null;
            Integer price = null;
            Object prefixObj = map.get("prefix");
            Object priceObj = map.get("price");

            try{
                if(prefixObj!=null&&priceObj!=null){
                    prefix = prefixObj.toString();
                    price = Integer.parseInt(priceObj.toString());
                    prefixShopItem = new PrefixShopItem();
                    prefixShopItem.setPrice(price);
                    prefixShopItem.setPrefix(prefix);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return prefixShopItem;
        }

        private String prefix;
        private Integer price;

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
    }

    public int getShopSlotNum() {
        return shopSlotNum;
    }

    public void setShopSlotNum(int shopSlotNum) {
        this.shopSlotNum = shopSlotNum;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public List<PrefixShopItem> getPrefixShopItems() {
        return prefixShopItems;
    }

    public void setPrefixShopItems(List<PrefixShopItem> prefixShopItems) {
        this.prefixShopItems = prefixShopItems;
    }
}
