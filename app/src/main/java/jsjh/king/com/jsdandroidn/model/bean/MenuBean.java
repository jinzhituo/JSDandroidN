package jsjh.king.com.jsdandroidn.model.bean;

import jsjh.king.com.jsdandroidn.base.BaseBean;

/**
 * Created by ShaoGeng on 2018/5/24.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class MenuBean extends BaseBean {
    private String menuName;
    private int menuImage;
    private int menuSelectImage;
    private Boolean isSelect;

    public MenuBean(String menuName, int menuImage ,int menuSelectImage ,Boolean isSelect) {
        this.menuName = menuName;
        this.menuImage = menuImage;
        this.menuSelectImage = menuSelectImage;
        this.isSelect = isSelect;
    }

    public int getMenuSelectImage() {
        return menuSelectImage;
    }

    public void setMenuSelectImage(int menuSelectImage) {
        this.menuSelectImage = menuSelectImage;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }

    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }
}
