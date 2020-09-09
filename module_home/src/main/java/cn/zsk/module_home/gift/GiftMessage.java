package cn.zsk.module_home.gift;

/**
 * Author : ZSK
 * Date : 2020/9/8
 * Description :   礼物消息
 */
public class GiftMessage {

    private String avatarUrl;    //头像

    private String userId;    // 用户ID

    private String userName;    //用户名称

    private String title;     //礼物标题

    private String num;    //数量

    private String iconUrl;    //礼物图标

    private long updateTime = System.currentTimeMillis();   //时间

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
