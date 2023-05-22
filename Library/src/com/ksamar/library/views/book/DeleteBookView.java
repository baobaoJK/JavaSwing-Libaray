package com.ksamar.library.views.book;

import com.ksamar.library.controller.book.BookController;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.image.Images;
import com.ksamar.library.views.message.Message;

import java.util.Vector;

/**
 * 删除图书窗口
 * @author KSaMar
 * @version 1.0
 */
public class DeleteBookView extends Message {

    /**
     * 删除图书窗口
     */
    private static final DeleteBookView INSTANCE  = new DeleteBookView();

    /**
     * 删除图书窗口
     */
    private DeleteBookView() {
        // 删除图书面板标题
        titleText = "删除图书";
        messageIconLabel.setIcon(Images.errorIcon);
        messageTitleLabel.setText(titleText);

        // 删除图书事件按钮
        confirmButton.setBackground(Colour.CE54D52);
        confirmButton.addActionListener(e -> deleteBook());
    }

    /**
     * 设置信息
     */
    public void setValue(Vector vector) {
        setVisible(true);
        setLocationRelativeTo(null);
        messageTextLabel.setText("确认删除 " + vector.get(2).toString() + " 图书吗？");
        confirmButton.setName(vector.get(0).toString());
    }

    /**
     * 删除图书
     */
    private void deleteBook() {
        dispose();
        int id = Integer.parseInt(confirmButton.getName());
        int result = BookController.deleteBook(id);
        if (result == 0) {
            Message.showMessage("删除失败", "错误", 0);
        }
        else if (result == 1) {
            Message.showMessage("删除成功", "信息", 1);
        }
    }

    /**
     * 获取删除图书窗口
     * @return 删除图书窗口
     */
    public static DeleteBookView getInstance() {
        return INSTANCE;
    }
}
