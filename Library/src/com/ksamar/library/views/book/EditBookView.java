package com.ksamar.library.views.book;

import com.ksamar.library.controller.book.BookController;
import com.ksamar.library.entity.Book;
import com.ksamar.library.tools.form.CheckFormTool;
import com.ksamar.library.views.message.Message;

import java.util.Vector;

/**
 * 图书窗口
 * @author KSaMar
 * @version 1.0
 */
public class EditBookView extends BookFormView {

    /**
     * 编辑图书窗口
     */
    private static final EditBookView INSTANCE = new EditBookView();

    /**
     * 编辑图书窗口
     */
    private EditBookView() {
        // 设置表单标题
        bookFormText = "编辑图书";
        bookFormTextLabel.setText(bookFormText);

        // 添加事件
        bookFormButton.setText("编辑");
        bookFormButton.addActionListener(e -> editBook());

        // 设置标题
        setTitle(bookFormText);
    }

    /**
     * 设置信息
     */
    public void setValue(Vector vector) {
        bookFormButton.setName(vector.get(0).toString());
        groupsComboBox.setSelectedItem(vector.get(1));
        bookNameField.setText(vector.get(2).toString());
        bookAuthorField.setText(vector.get(3).toString());
        bookPressField.setText(vector.get(4).toString());
        bookPriceField.setText(vector.get(5).toString());
        bookCountField.setText(vector.get(6).toString());
        bookIsbnField.setText(vector.get(7).toString());
        setVisible(true);
    }

    /**
     * 编辑图书
     */
    private void editBook() {
        if (CheckFormTool.checkBookForm(getInstance())) {
            Book book = new Book();
            book.setId(Integer.valueOf(bookFormButton.getName()));
            book.setGroups(String.valueOf(groupsComboBox.getSelectedItem()));
            book.setName(bookNameField.getText());
            book.setAuthor(bookAuthorField.getText());
            book.setPress(bookPressField.getText());
            book.setPrice(Double.valueOf(bookPriceField.getText()));
            book.setQuantity(Integer.valueOf(bookCountField.getText()));
            book.setIsbn(bookIsbnField.getText());

            int result = BookController.editBook(book);

            if (result == 0) {
                Message.showMessage("编辑失败", "错误", 0);
            }
            else if (result == 1){
                dispose();
                Message.showMessage("编辑成功", "信息", 1);
            }
            else if (result == 2){
                Message.showMessage("编辑失败，ISBN号码存在", "错误", 0);
            }
        }
    }

    /**
     * 获取编辑图书窗口
     */
    public static EditBookView getInstance() {
        return INSTANCE;
    }
}
