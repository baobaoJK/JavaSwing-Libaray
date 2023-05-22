package com.ksamar.library.tools.popupmenu;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 任务栏图标类
 * @author KSaMar
 * @version 1.0
 */
public class SystemTrayIcon extends TrayIcon {

    /**
     * 信息框
     */
    private JDialog dialog;

    /**
     * 创建带指定图像、工具提示和弹出菜单的 SystemTrayIcon
     * @param image 显示在系统托盘的图标
     * @param text	鼠标移动到系统托盘图标上的提示信息
     * @param popupMenu	弹出菜单
     */
    public SystemTrayIcon(Image image, String text, JPopupMenu popupMenu) {
        super(image,text);

        // 初始化JDialog
        dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setAlwaysOnTop(true);

        // 设置系统图标大小为自动调整
        setImageAutoSize(true);

        // 为TrayIcon设置鼠标监听器
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {

                // 鼠标右键在组件上释放时调用，显示弹出菜单
                if (e.getButton() == MouseEvent.BUTTON3 && popupMenu != null) {

                    // 设置dialog的显示位置
                    Dimension size = popupMenu.getPreferredSize();
                    dialog.setLocation(e.getX()-size.width-3, e.getY() - size.height-3);
                    dialog.setVisible(true);

                    // 显示弹出菜单
                    popupMenu.show(dialog.getContentPane(), 0, 0);
                }
            }
        });

        // 为弹出菜单添加监听器
        popupMenu.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                dialog.setVisible(false);
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                dialog.setVisible(false);
            }
        });
    }
}
