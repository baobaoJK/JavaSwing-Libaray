package com.ksamar.library.tools.pagination;

import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;

import javax.swing.*;
import java.awt.*;

/**
 * 分页面板
 * @author KSaMar
 * @version 1.0
 */
public class Pagination extends JPanel {

    /**
     * 属性
     */
    public int pageNumber;
    public int pageTotal;

    /**
     * 标签
     */
    public JLabel pageLabel = new JLabel();

    /**
     * 按钮
     */
    public JButton prePageButton = new JButton();
    public JButton nextPageButton = new JButton();

    /**
     * 分页面板
     */
    public Pagination(int pageNumber, int pageTotal) {
        this.pageNumber = pageNumber;
        this.pageTotal = pageTotal;

        // 上一页按钮
        prePageButton.setText("上一页");
        prePageButton.setBounds(0, 0, 100, 32);
        prePageButton.setHorizontalAlignment(SwingConstants.CENTER);
        prePageButton.setForeground(Color.WHITE);
        prePageButton.setFont(Fonts.button);
        prePageButton.setBackground(Colour.C3C8CE7);
        prePageButton.setFocusPainted(false);
        prePageButton.setBorder(null);
        prePageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // 页码标签
        pageLabel.setText("当前为第 " + pageNumber + " 页，总 " + pageTotal + " 页");
        pageLabel.setBounds(100, 0, 728, 32);
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pageLabel.setFont(Fonts.pageLabel);

        // 下一页按钮
        nextPageButton.setText("下一页");
        nextPageButton.setBounds(828, 0, 100, 32);
        nextPageButton.setHorizontalAlignment(SwingConstants.CENTER);
        nextPageButton.setForeground(Color.WHITE);
        nextPageButton.setFont(Fonts.button);
        nextPageButton.setBackground(Colour.C3C8CE7);
        nextPageButton.setFocusPainted(false);
        nextPageButton.setBorder(null);
        nextPageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // 分页面板
        setLayout(null);
        setBackground(Color.WHITE);
        add(prePageButton);
        add(pageLabel);
        add(nextPageButton);
    }

    /**
     * 设置分页栏
     */
    public void setPagination(int pageNumber, int pageTotal) {
        this.pageNumber = pageNumber;
        this.pageTotal = pageTotal;
        pageLabel.setText("当前为第 " + pageNumber + " 页，总 " + pageTotal + " 页");
    }
}
