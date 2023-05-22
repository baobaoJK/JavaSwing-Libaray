package com.ksamar.library.views.book;

import com.ksamar.library.controller.book.BookController;
import com.ksamar.library.entity.Book;
import com.ksamar.library.tools.form.CheckFormTool;
import com.ksamar.library.views.message.Message;

/**
 * 添加图书窗口
 * @author KSaMar
 * @version 1.0
 */
public class AddBookView extends BookFormView {

    /**
     * 添加图书窗口
     */
    private static final AddBookView INSTANCE = new AddBookView();

    /**
     * 添加图书窗口
     */
    private AddBookView() {
        // 设置表单标题
        bookFormText = "添加图书";
        bookFormTextLabel.setText(bookFormText);

        // 添加事件
        bookFormButton.setText("添加");
        bookFormButton.addActionListener(e -> addBook());

        // 设置标题
        setTitle(bookFormText);
    }

    /**
     * 添加图书
     */
    private void addBook() {
        if (CheckFormTool.checkBookForm(getInstance())) {
            Book book = new Book();
            book.setGroups(String.valueOf(groupsComboBox.getSelectedItem()));
            book.setName(bookNameField.getText());
            book.setAuthor(bookAuthorField.getText());
            book.setPress(bookPressField.getText());
            book.setPrice(Double.valueOf(bookPriceField.getText()));
            book.setQuantity(Integer.valueOf(bookCountField.getText()));
            book.setIsbn(bookIsbnField.getText());

            int result = BookController.addBook(book);

            if (result == 0) {
                Message.showMessage("添加失败", "错误", 0);
            }
            else if (result == 1){
                dispose();
                resetForm();
                Message.showMessage("添加成功", "信息", 1);
            }
            else if (result == 2){
                Message.showMessage("添加失败，ISBN号码存在", "错误", 0);
            }
        }
    }

    /**
     * 获取添加图书窗体
     * @return 添加图书窗体
     */
    public static AddBookView getInstance() {
        return INSTANCE;
    }
}
