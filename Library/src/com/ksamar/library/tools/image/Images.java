package com.ksamar.library.tools.image;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * 图片
 * @author KSaMar
 * @version 1.0
 */
public class Images {
    /**
     * 图片地址
     */
    public static String url = "/com/ksamar/library/images/";

    /**
     * 图片声明
     */
    public static Image systemImage;
    public static Image closeButtonImage;
    public static ImageIcon closeButtonIcon;
    public static Image closeButtonHoverImage;
    public static ImageIcon closeButtonHoverIcon;
    public static Image hideButtonImage;
    public static ImageIcon hideButtonIcon;
    public static Image hideButtonHoverImage;
    public static ImageIcon hideButtonHoverIcon;
    public static ImageIcon homeIcon;
    public static ImageIcon homeFocusIcon;
    public static ImageIcon bookIcon;
    public static ImageIcon bookFocusIcon;
    public static ImageIcon borrowIcon;
    public static ImageIcon borrowFocusIcon;
    public static ImageIcon returnIcon;
    public static ImageIcon returnFocusIcon;
    public static ImageIcon overtimeIcon;
    public static ImageIcon overtimeFocusIcon;
    public static ImageIcon userIcon;
    public static ImageIcon userFocusIcon;
    public static ImageIcon systemIcon;
    public static ImageIcon systemFocusIcon;
    public static ImageIcon bookPaneIcon;
    public static ImageIcon borrowPaneIcon;
    public static ImageIcon overtimePaneIcon;
    public static ImageIcon timePaneIcon;
    public static ImageIcon errorIcon;
    public static ImageIcon successIcon;
    public static Image userImage;
    public static ImageIcon userImageIcon;

    /**
     * 图片设置
     */
    static {

        systemImage = new ImageIcon(Objects.requireNonNull(Image.class.getResource(url + "system.png"))).getImage();

        closeButtonImage = new ImageIcon(Objects.requireNonNull(Image.class.getResource(url + "close_button.png"))).getImage();
        closeButtonIcon = new ImageIcon(closeButtonImage.getScaledInstance(20, 20 ,Image.SCALE_DEFAULT));

        closeButtonHoverImage = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "close_button_hover.png"))).getImage();
        closeButtonHoverIcon = new ImageIcon(closeButtonHoverImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        hideButtonImage = new ImageIcon(Objects.requireNonNull(Image.class.getResource(url + "hide_button.png"))).getImage();
        hideButtonIcon = new ImageIcon(hideButtonImage.getScaledInstance(20, 20 ,Image.SCALE_DEFAULT));

        hideButtonHoverImage = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "hide_button_hover.png"))).getImage();
        hideButtonHoverIcon = new ImageIcon(hideButtonHoverImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        homeIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "home.png")));
        homeFocusIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "home_focus.png")));

        bookIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "book.png")));
        bookFocusIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "book_focus.png")));

        borrowIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "borrow.png")));
        borrowFocusIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "borrow_focus.png")));

        returnIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "return.png")));
        returnFocusIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "return_focus.png")));

        overtimeIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "overtime.png")));
        overtimeFocusIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "overtime_focus.png")));

        userIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "user.png")));
        userFocusIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "user_focus.png")));

        systemIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "setting.png")));
        systemFocusIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "setting_focus.png")));

        bookPaneIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "book_pane.png")));
        borrowPaneIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "borrow_pane.png")));
        overtimePaneIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "overtime_pane.png")));
        timePaneIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "time_pane.png")));

        errorIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "error.png")));
        successIcon = new ImageIcon(Objects.requireNonNull(Images.class.getResource(url + "success.png")));

        userImage = new ImageIcon(Objects.requireNonNull(Image.class.getResource(url + "user_img.png"))).getImage();
        userImageIcon = new ImageIcon(userImage.getScaledInstance(150, 150 ,Image.SCALE_DEFAULT));
    }
}
