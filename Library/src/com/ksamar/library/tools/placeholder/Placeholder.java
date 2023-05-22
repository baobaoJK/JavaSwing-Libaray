package com.ksamar.library.tools.placeholder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * 提示文本工具
 * @author KSaMar
 * @version 1.0
 */
public class Placeholder {
    /**
     * 设置提示信息
     * @param jTextField 文本框
     * @param text 文本
     * @param color 颜色
     */
    public static void setPlaceholder(JTextField jTextField, String text, Color color) {
        jTextField.setText(text);
        jTextField.setForeground(color);
    }

    /**
     * 文本框提示信息事件
     * @param jTextField 文本框
     * @param text 文本
     * @return FocusListener
     */
    public static FocusListener focusListener(JTextField jTextField, String text) {
        return new FocusListener() {

            // 获取焦点
            @Override
            public void focusGained(FocusEvent e) {
                if (text.equals(jTextField.getText())) {
                    jTextField.setText("");
                    jTextField.setForeground(Color.BLACK);
                }

                if (jTextField instanceof JPasswordField) {
                    ((JPasswordField) jTextField).setEchoChar('●');
                }
            }

            // 失去焦点
            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(jTextField.getText())) {
                    jTextField.setText(text);
                    jTextField.setForeground(Color.LIGHT_GRAY);
                }

                if (jTextField instanceof JPasswordField &&
                        ("".equals(jTextField.getText()) || (text.equals(jTextField.getText())))) {
                    ((JPasswordField) jTextField).setEchoChar('\0');
                }
            }
        };
    }
}
