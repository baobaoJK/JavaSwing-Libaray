package com.ksamar.library.tools.font;

import java.awt.*;

/**
 * 字体
 * @author KSaMar
 * @version 1.0
 */
public class Fonts {

    /**
     * 字体名称
     */
    private static final String FONT_NAME = "微软雅黑";

    /**
     * 字体设置
     */
    public static Font menu;
    public static Font title;
    public static Font subTitle;
    public static Font textField;
    public static Font button;
    public static Font loginMessage;
    public static Font menuButton;
    public static Font pane;
    public static Font time;
    public static Font comboBox;
    public static Font pageLabel;
    public static Font message;
    public static Font errorMessage;

    static {
        menu = new Font(FONT_NAME, Font.PLAIN, 12);
        title = new Font(FONT_NAME, Font.PLAIN, 28);
        subTitle = new Font(FONT_NAME, Font.PLAIN, 16);
        textField = new Font(FONT_NAME, Font.PLAIN, 16);
        button = new Font(FONT_NAME, Font.PLAIN, 14);
        loginMessage = new Font(FONT_NAME, Font.PLAIN, 16);
        menuButton = new Font(FONT_NAME, Font.PLAIN, 24);
        pane = new Font(FONT_NAME, Font.PLAIN, 36);
        time = new Font(FONT_NAME, Font.PLAIN, 24);
        comboBox = new Font(FONT_NAME, Font.PLAIN, 14);
        pageLabel = new Font(FONT_NAME, Font.PLAIN, 16);
        message = loginMessage;
        errorMessage = loginMessage;
    }
}
