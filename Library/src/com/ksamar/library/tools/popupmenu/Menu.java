package com.ksamar.library.tools.popupmenu;

import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.image.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 任务栏图标菜单
 * @author KSaMar
 * @version 1.0
 */
public class Menu {

    /**
     * 文字
     */
    private static String toolTipText = "图书管理系统";
    private static String closeText = "退出系统";
    private static String openText = "打开主面板";

    /**
     * 定义弹出菜单
     */
    private static JPopupMenu popupMenu = new JPopupMenu();

    /**
     * 定义弹出菜单项
     */
    private static JMenuItem openMenu = new JMenuItem(openText);
    private static JMenuItem closeMenu = new JMenuItem(closeText);

    /**
     * 图标图片
     */
    private static ImageIcon systemIcon;

    /**
     * 获取系统托盘
     */
    private static SystemTray systemTray;

    /**
     * 创建带指定图像、工具提示和弹出菜单的 SystemTrayIcon
     */
    private static SystemTrayIcon trayIcon;

    /**
     * 设置任务栏图标
     * @param frame 窗体
     */
    public static void setIcon(JFrame frame) {

        // 设置任务栏图标
        systemIcon = new ImageIcon(Images.systemImage);
        systemTray = SystemTray.getSystemTray();
        trayIcon = new SystemTrayIcon(systemIcon.getImage(),toolTipText, popupMenu);

        //为JPopupMenu设置UI
        popupMenu.setUI(new SystemPopupMenuUI());

        // 设置字体
        openMenu.setFont(Fonts.menu);
        closeMenu.setFont(Fonts.menu);

        //添加弹出菜单项到弹出菜单
        popupMenu.add(openMenu);
        popupMenu.add(closeMenu);

        //设置单击击系统托盘图标显示主窗口
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //鼠标左键点击,设置窗体状态，正常显示
                if (e.getButton() == MouseEvent.BUTTON1) {
                    frame.setExtendedState(Frame.NORMAL);
                    frame.setVisible(true);
                }
            }
        });

        //为弹出菜单项添加监听器
        openMenu.addActionListener(menuListen(frame));
        closeMenu.addActionListener(menuListen(frame));

        //将TrayIcon添加到系统托盘
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 菜单侦听器
     * @param frame 窗体
     * @return ActionListener
     */
    public static ActionListener menuListen(JFrame frame) {
        return e -> {
            if (e.getActionCommand().equals(closeText)) {
                systemTray.remove(trayIcon);
                System.exit(0);
            }
            else if(e.getActionCommand().equals(openText)){
                frame.setExtendedState(Frame.NORMAL);
                frame.setVisible(true);
            }
        };
    }
}
