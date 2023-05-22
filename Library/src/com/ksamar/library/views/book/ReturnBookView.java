package com.ksamar.library.views.book;

import com.ksamar.library.controller.book.BookController;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.image.Images;
import com.ksamar.library.views.message.Message;

import java.util.Vector;

/**
 * 归还图书窗口
 * @author KSaMar
 * @version 1.0
 */
public class ReturnBookView extends Message {

    /**
     * 归还图书窗口
     */
    private static final ReturnBookView INSTANCE = new ReturnBookView();

    /**
     * 归还图书窗口
     */
    private ReturnBookView() {

        // 归还图书面板标题
        titleText = "归还图书";
        messageIconLabel.setIcon(Images.errorIcon);
        messageTitleLabel.setText(titleText);

        // 归还图书事件按钮
        confirmButton.setBackground(Colour.CE54D52);
        confirmButton.addActionListener(e -> returnBook());
    }

    /**
     * 设置信息
     */
    public void setValue(Vector vector) {
        setVisible(true);
        setLocationRelativeTo(null);
        messageTextLabel.setText("确认归还 " + vector.get(1).toString() + " 图书吗？");
        confirmButton.setName(vector.get(0).toString());
    }

    /**
     * 归还图书
     */
    public void returnBook() {
        dispose();
        int id = Integer.parseInt(confirmButton.getName());
        int result = BookController.returnBook(id);
        if (result == 0) {
            Message.showMessage("归还失败", "错误", 0);
        }
        else if (result == 1){
            Message.showMessage("归还成功", "信息", 1);
        }
    }

    /**
     * 获取归还图书窗口
     * @return 归还图书窗口
     */
    public static ReturnBookView getInstance() {
        return INSTANCE;
    }
}
