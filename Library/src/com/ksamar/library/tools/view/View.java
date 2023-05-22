package com.ksamar.library.tools.view;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * 窗体工具
 * @author KSaMar
 * @version 1.0
 */
public class View {

    /**
     * 坐标信息
     */
    private static int x = 0;
    private static int y = 0;

    /**
     * 无边框窗体移动
     * @param frame 窗体
     */
    public static void setMoveFrame(JFrame frame) {
        // 添加鼠标监听
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 获取鼠标位置
                x = e.getX();
                y = e.getY();
            }
        });
        // 添加鼠标移动监听
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // 设置窗体位置
                int left = frame.getLocation().x;
                int top = frame.getLocation().y;
                frame.setLocation(left + e.getX() - x, top + e.getY() - y);
            }
        });
    }
}
