package com.ksamar.library.views;

import com.ksamar.library.controller.book.BookController;
import com.ksamar.library.controller.log.LogController;
import com.ksamar.library.entity.Log;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.image.Images;
import com.ksamar.library.tools.table.LibraryTableModel;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * 仪表盘信息
 * @author KSaMar
 * @version 1.0
 */
public class HomeView extends JPanel {

    /**
     * 属性
     */
    private int bookCount = 0;
    private int borrowCount = 0;
    private int overtimeCount = 0;
    private Date dateTime = new Date();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 文字
     */
    private String bookText = "图书馆书本数量";
    private String borrowText = "借出书本数量";
    private String overtimeText = "超时归还数量";
    private String timeText = "系统时间";
    private String logText = "操作日志";

    /**
     * 面板
     */
    private JPanel bookPanel = new JPanel();
    private JPanel borrowPanel = new JPanel();
    private JPanel overtimePanel = new JPanel();
    private JPanel timePanel = new JPanel();
    private JPanel logPanel = new JPanel();
    private JScrollPane logScrollPane = new JScrollPane();

    /**
     * 标签
     */
    private JLabel bookIconLabel = new JLabel();
    private JLabel bookTextLabel = new JLabel();
    private JLabel bookCountLabel = new JLabel();
    private JLabel borrowIconLabel = new JLabel();
    private JLabel borrowTextLabel = new JLabel();
    private JLabel borrowCountLabel = new JLabel();
    private JLabel overtimeIconLabel = new JLabel();
    private JLabel overtimeTextLabel = new JLabel();
    private JLabel overtimeCountLabel = new JLabel();
    private JLabel timeIconLabel = new JLabel();
    private JLabel timeTextLabel = new JLabel();
    private JLabel timeValueLabel = new JLabel();
    private JLabel logTextLabel = new JLabel();

    /**
     * 表格
     */
    private JTable logTextTabel;
    private String[] name = {"日期/时间", "名字", "信息"};
    private Object[][] tableDate = new Object[0][0];
    private LibraryTableModel tableModel = new LibraryTableModel(tableDate, name);

    /**
     * 仪表盘窗体
     */
    public HomeView() {

        // 初始化
        init();

        // 图书数量图标
        bookIconLabel.setIcon(Images.bookPaneIcon);
        bookIconLabel.setBounds(16, 36, 128, 128);

        // 图书数量文本标签
        bookTextLabel.setText(bookText);
        bookTextLabel.setBounds(144, 56, 274, 40);
        bookTextLabel.setFont(Fonts.pane);
        bookTextLabel.setForeground(Color.WHITE);
        bookTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 图书数量标签
        bookCountLabel.setBounds(144, 108, 274, 40);
        bookCountLabel.setFont(Fonts.pane);
        bookCountLabel.setForeground(Color.WHITE);
        bookCountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 图书数量面板
        bookPanel.setLayout(null);
        bookPanel.setBounds(16, 16, 456, 200);
        bookPanel.setBackground(Colour.C3C8CE7);
        bookPanel.add(bookIconLabel);
        bookPanel.add(bookTextLabel);
        bookPanel.add(bookCountLabel);

        // 借阅图书数量图标
        borrowIconLabel.setIcon(Images.borrowPaneIcon);
        borrowIconLabel.setBounds(16, 36, 128, 128);

        // 借阅图书数量文本标签
        borrowTextLabel.setText(borrowText);
        borrowTextLabel.setBounds(144, 56, 274, 40);
        borrowTextLabel.setFont(Fonts.pane);
        borrowTextLabel.setForeground(Color.WHITE);
        borrowTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 借阅图书数量标签
        borrowCountLabel.setBounds(144, 108, 274, 40);
        borrowCountLabel.setFont(Fonts.pane);
        borrowCountLabel.setForeground(Color.WHITE);
        borrowCountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 借阅图书数量面板
        borrowPanel.setLayout(null);
        borrowPanel.setBounds(488 ,16, 456, 200);
        borrowPanel.setBackground(Colour.C5AB556);
        borrowPanel.add(borrowIconLabel);
        borrowPanel.add(borrowTextLabel);
        borrowPanel.add(borrowCountLabel);

        // 超时归还数量图标
        overtimeIconLabel.setIcon(Images.overtimePaneIcon);
        overtimeIconLabel.setBounds(16, 36, 128, 128);

        // 超时归还数量文本标签
        overtimeTextLabel.setText(overtimeText);
        overtimeTextLabel.setBounds(144, 56, 274, 40);
        overtimeTextLabel.setFont(Fonts.pane);
        overtimeTextLabel.setForeground(Color.WHITE);
        overtimeTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 超时归还数量标签
        overtimeCountLabel.setBounds(144, 108, 274, 40);
        overtimeCountLabel.setFont(Fonts.pane);
        overtimeCountLabel.setForeground(Color.WHITE);
        overtimeCountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 超时归还数量面板
        overtimePanel.setLayout(null);
        overtimePanel.setBounds(16, 232, 456, 200);
        overtimePanel.setBackground(Colour.CE54D52);
        overtimePanel.add(overtimeIconLabel);
        overtimePanel.add(overtimeTextLabel);
        overtimePanel.add(overtimeCountLabel);

        // 系统时间图标
        timeIconLabel.setIcon(Images.timePaneIcon);
        timeIconLabel.setBounds(16, 36, 128, 128);

        // 系统时间文本标签
        timeTextLabel.setText(timeText);
        timeTextLabel.setBounds(144, 56, 274, 40);
        timeTextLabel.setFont(Fonts.pane);
        timeTextLabel.setForeground(Color.WHITE);
        timeTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 系统时间标签
        timeValueLabel.setBounds(144, 108, 274, 40);
        timeValueLabel.setFont(Fonts.time);
        timeValueLabel.setForeground(Color.WHITE);
        timeValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 系统时间面板
        timePanel.setLayout(null);
        timePanel.setBounds(488, 232, 456, 200);
        timePanel.setBackground(Colour.CFAA64B);
        timePanel.add(timeIconLabel);
        timePanel.add(timeTextLabel);
        timePanel.add(timeValueLabel);

        // 日志信息文本
        logTextLabel.setText(logText);
        logTextLabel.setBounds(0, 0, 928, 32);
        logTextLabel.setFont(Fonts.subTitle);
        logTextLabel.setForeground(Color.BLACK);

        // 日志信息表格
        logTextTabel = new JTable(tableModel);
        logTextTabel.setRowHeight(32);
        logTextTabel.setBounds(0, 0, 928, 272);
        logTextTabel.getColumnModel().getColumn(0).setPreferredWidth(120);
        logTextTabel.getColumnModel().getColumn(1).setPreferredWidth(120);
        logTextTabel.getColumnModel().getColumn(2).setPreferredWidth(688);
        logTextTabel.getTableHeader().setReorderingAllowed(false);
        logTextTabel.getTableHeader().setResizingAllowed(false);

        // 日志信息面板
        logScrollPane.setViewportView(logTextTabel);
        logScrollPane.setBounds(0, 32, 928,272);

        // 日志面板
        logPanel.setLayout(null);
        logPanel.setBounds(16, 448, 928, 304);
        logPanel.setBackground(Color.WHITE);
        logPanel.add(logTextLabel);
        logPanel.add(logScrollPane);

        // 添加组件
        add(bookPanel);
        add(borrowPanel);
        add(overtimePanel);
        add(timePanel);
        add(logPanel);

        // 窗体设置
        setSize(960, 768);
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(true);
    }

    /**
     * 初始化
     */
    public void init() {
        // 面板初始化
        bookCount = BookController.getBookCount();
        borrowCount = BookController.getBorrowCount();
        overtimeCount = BookController.getOvertimeCount();
        setTime();

        // 设置标签
        bookCountLabel.setText(String.valueOf(bookCount));
        borrowCountLabel.setText(String.valueOf(borrowCount));
        overtimeCountLabel.setText(String.valueOf(overtimeCount));

        // 添加日志信息
        tableModel.setDataVector(tableDate, name);
        List<Log> logMessage = LogController.getLogMessage();
        tableModel.addLogRow(logMessage);
    }

    /**
     * 时间设置
     */
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public void setTime() {
        new Thread(() -> {
            while (true) {
                dateTime = new Date();
                timeValueLabel.setText(simpleDateFormat.format(dateTime));
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
