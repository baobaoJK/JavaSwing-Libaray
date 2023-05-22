package com.ksamar.library.controller.book;

import com.ksamar.library.controller.log.LogController;
import com.ksamar.library.controller.user.UserContoller;
import com.ksamar.library.entity.Book;
import com.ksamar.library.entity.Borrow;
import com.ksamar.library.entity.Log;
import com.ksamar.library.entity.User;
import com.ksamar.library.tools.sql.SqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 图书控制器
 * @author KSaMar
 * @version 1.0
 */
public class BookController {

    /**
     * 获取图书表数据数量
     * @return 图书表数据数量
     */
    public static int getCount() {
        int count = 0;

        // 获取图书表数据数量语句
        String getCountSql = "select * from booklist";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(getCountSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return count;
    }

    /**
     * 获取图书数量
     * @return 图书数量
     */
    public static int getBookCount() {
        int bookCount = 0;

        // 获取图书数量语句
        String getBookCountSql = "select * from booklist";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(getBookCountSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int temp = resultSet.getInt("quantity");
                bookCount += temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return bookCount;
    }

    /**
     * 获取借阅图书数量
     * @return 借阅图书数量
     */
    public static int getBorrowCount() {
        int borrowCount = 0;

        // 获取借阅图书数量语句
        String getBorrowCountSql = "select * from borrowlist";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(getBorrowCountSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                borrowCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return borrowCount;
    }

    /**
     * 获取超时图书数量
     * @return 超时图书数量
     */
    public static int getOvertimeCount() {
        int overtimeCount = 0;

        // 获取超时图书数量语句
        String getOvertimeCount = "select * from borrowlist where return_time < NOW()";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(getOvertimeCount);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                overtimeCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return overtimeCount;
    }

    /**
     * 获取图书信息
     * @param page 页码
     * @param size 大小
     * @return 图书信息列表
     */
    public static List<Book> pageBook(int page, int size) {
        ArrayList<Book> bookArrayList = new ArrayList<>();

        // 获取图书信息语句
        String pageBookSql = "select * from booklist limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(pageBookSql);
            preparedStatement.setInt(1, (page - 1) * size);
            preparedStatement.setInt(2, size);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = setBookInfo(resultSet);
                bookArrayList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return bookArrayList;
    }

    /**
     * 搜索图书信息
     * @param searchType 搜索类型
     * @param text 搜索文本
     * @param page 页码
     * @param size 大小
     */
    public static List<Book> searchBookLike(String searchType, String text, int page, int size) {
        List<Book> bookArrayList = new ArrayList<>();

        // 获取搜索类型
        searchType = getSearchType(searchType);

        // 搜索图书信息语句
        String searchBookLikeSql = "select * from booklist where " + searchType + " like ? limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(searchBookLikeSql);
            preparedStatement.setString(1, "%" + text + "%");
            preparedStatement.setInt(2, (page - 1) * size);
            preparedStatement.setInt(3, size);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = setBookInfo(resultSet);
                bookArrayList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return bookArrayList;
    }

    /**
     * 搜索图书类型长度
     * @param searchType 搜索类型
     * @param text 搜索文本
     * @return 图书类型长度
     */
    public static int searchBookLikeCount(String searchType, String text) {
        int count = 0;

        // 获取搜索类型
        searchType = getSearchType(searchType);

        // 搜索图书类型长度语句
        String searchBookLikeCountSql = "select * from booklist where " + searchType + " like ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(searchBookLikeCountSql);
            preparedStatement.setString(1, "%" + text + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return count;
    }

    /**
     * 添加图书
     * 0 - 添加失败
     * 1 - 添加成功
     * 2 - ISBN 号码存在
     * @param book 图书
     * @return 添加状态
     */
    public static int addBook(Book book) {
        int result = 0;

        // 判断图书 ISBN 号码是否存在
        Book tempBook = searchBookByIsbn(book.getIsbn());
        if (tempBook != null) {
            return 2;
        }

        // 插入图书语句
        String insertBookSql = "insert into booklist(groups, name, author, press, price, quantity, isbn) " +
                "VALUE (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(insertBookSql);
            preparedStatement.setString(1, book.getGroups());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getPress());
            preparedStatement.setDouble(5, book.getPrice());
            preparedStatement.setInt(6, book.getQuantity());
            preparedStatement.setString(7, book.getIsbn());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 编辑图书
     * 0 - 编辑失败
     * 1 - 编辑成功
     * 2 - ISBN 号码存在
     * @param book 图书
     * @return 编辑状态
     */
    public static int editBook(Book book) {
        int result = 0;

        // 判断图书 ISBN 号码是否存在
        String isbn = book.getIsbn();
        Book tempBook = searchBookByIsbn(isbn);
        if (tempBook != null && !isbn.equals(tempBook.getIsbn())) {
            return 2;
        }

        // 编辑图书语句
        String updateBookSql = "update booklist set groups = ?, name = ?, author = ?, press = ?, price = ?," +
                " quantity = ?, isbn = ? where id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(updateBookSql);
            preparedStatement.setString(1, book.getGroups());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getPress());
            preparedStatement.setDouble(5, book.getPrice());
            preparedStatement.setInt(6, book.getQuantity());
            preparedStatement.setString(7, book.getIsbn());
            preparedStatement.setInt(8, book.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 删除图书
     * 0 - 删除失败
     * 1 - 删除成功
     * @param id 图书id
     * @return 删除状态
     */
    public static int deleteBook(int id) {
        int result = 0;

        // 删除图书语句
        String deleteBookSql = "delete from booklist where id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(deleteBookSql);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 以 ISBN 号码 搜索图书
     * @param isbn ISBN 号码
     * @return Book
     */
    public static Book searchBookByIsbn(String isbn) {
        Book book = null;

        // 搜索图书语句
        String searchBookByIsbn = "select * from booklist where isbn=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(searchBookByIsbn);
            preparedStatement.setString(1, isbn);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book = setBookInfo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return book;
    }

    /**
     * 借阅图书
     * 0 - 借阅失败
     * 1 - 借阅成功
     * 2 - 图书数量为 0
     * 3 - 用户不存在
     * 4 - 用户借阅数量为 0
     * @param borrow 借阅信息
     * @return 借阅状态
     */
    public static int borrowBook(Borrow borrow) {
        int result = 0;

        // 搜索图书表
        Book book = searchBookByIsbn(borrow.getIsbn());

        // 判断图书是否存在
        if (book == null) {
            return result;
        }
        // 判断图书数量是否为0
        if (book.getQuantity() == 0) {
            return 2;
        }

        // 查询用户信息
        User user = UserContoller.searchUserByUsernameAndIdCardAndPhone(borrow.getUsername(),
                                    borrow.getIdCard(), borrow.getPhone());

        // 判断用户是否存在
        if (user == null) {
            return 3;
        }
        // 判断用户借书数量是否为0
        if (user.getBookCount() == 0) {
            return 4;
        }

        // 时间
        Calendar calendar = Calendar.getInstance();
        long borrowTimeLong = calendar.getTimeInMillis();
        long returnTimeLong = borrowTimeLong + 604800000L;
        Timestamp borrowTime = new Timestamp(borrowTimeLong);
        Timestamp returnTime = new Timestamp(returnTimeLong);

        // 借阅图书语句
        String borrowBookSql = "insert into borrowlist(book_name, isbn, username, id_card, phone" +
                ", borrow_time, return_time) VALUE (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(borrowBookSql);
            preparedStatement.setString(1, borrow.getBookName());
            preparedStatement.setString(2, borrow.getIsbn());
            preparedStatement.setString(3, borrow.getUsername());
            preparedStatement.setString(4, borrow.getIdCard());
            preparedStatement.setString(5, borrow.getPhone());
            preparedStatement.setTimestamp(6, borrowTime);
            preparedStatement.setTimestamp(7, returnTime);
            result = preparedStatement.executeUpdate();

            if (result > 0) {
                // 更新图书
                book.setQuantity(book.getQuantity() - 1);
                editBook(book);

                // 更新用户
                user.setBookCount(user.getBookCount() - 1);
                UserContoller.editUser(user);

                // 日志信息
                Log log = new Log();
                log.setTime(borrowTime);
                log.setName(user.getName());
                log.setBookName(borrow.getBookName());
                log.setInfo("借走了 " + borrow.getBookName() + " 书籍");
                LogController.addLog(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 搜索借书卡借阅的图书信息
     * @param idCard 借书卡号
     * @return 借阅卡借阅的图书信息
     */
    public static List<Borrow> searchBorrowByIdCard(String idCard) {
        List<Borrow> borrowArrayList = new ArrayList<>();

        // 搜索 idCard 借阅图书语句
        String searchBorrowByIdCardSql = "select * from borrowlist where id_card = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(searchBorrowByIdCardSql);
            preparedStatement.setString(1, idCard);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Borrow borrow = setBorrowInfo(resultSet);
                borrowArrayList.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return borrowArrayList;
    }

    /**
     * 搜索借书卡借阅的超时图书信息
     * @param idCard 借书卡号
     * @return 借书卡借阅的超时图书信息
     */
    public static List<Borrow> searchBorrowByIdCardAfterNow(String idCard) {
        List<Borrow> borrowArrayList = new ArrayList<>();

        // 搜索 idCard 借阅图书语句
        String searchBorrowByIdCardAfterNowSql = "select * from borrowlist where id_card = ? and return_time < NOW()";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(searchBorrowByIdCardAfterNowSql);
            preparedStatement.setString(1, idCard);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Borrow borrow = setBorrowInfo(resultSet);
                borrowArrayList.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return borrowArrayList;
    }

    /**
     * 以 Id 搜索借阅信息
     * @param id 借阅信息 Id
     * @return Borrow
     */
    private static Borrow searchBorrowById(int id) {
        Borrow borrow = null;

        // 搜索借阅信息语句
        String searchBorrowByIdSql = "select * from borrowlist where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(searchBorrowByIdSql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                borrow = setBorrowInfo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return borrow;
    }

    /**
     * 归还图书
     * @param id 图书ID
     * @return 归还状态
     */
    public static int returnBook(int id) {
        int result = 0;

        // 搜索借阅信息
        Borrow borrow = searchBorrowById(id);
        // 判断是否存在借阅信息
        if (borrow == null) {
            return result;
        }

        // 搜索图书表
        Book book = searchBookByIsbn(borrow.getIsbn());
        // 判断图书是否存在
        if (book == null) {
            return result;
        }

        // 查询用户信息
        User user = UserContoller.searchUserByUsernameAndIdCardAndPhone(borrow.getUsername(),
                                                                        borrow.getIdCard(), borrow.getPhone());
        // 判断用户是否存在
        if (user == null) {
            return result;
        }

        // 归还图书语句
        String returnBook = "delete from borrowlist where id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(returnBook);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();

            // 判断是否删除
            if (result > 0) {
                // 更新图书
                book.setQuantity(book.getQuantity() + 1);
                editBook(book);

                // 更新用户
                user.setBookCount(user.getBookCount() + 1);
                UserContoller.editUser(user);

                // 设置时间
                Calendar calendar = Calendar.getInstance();
                long returnTimeLong = calendar.getTimeInMillis();
                Timestamp returnTime = new Timestamp(returnTimeLong);

                // 日志信息
                Log log = new Log();
                log.setTime(returnTime);
                log.setName(user.getName());
                log.setBookName(borrow.getBookName());
                log.setInfo("归还了 " + borrow.getBookName() + " 书籍");
                LogController.addLog(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 设置图书信息
     * @param resultSet 数据库信息
     * @return 图书信息
     * @throws SQLException SQL异常
     */
    public static Book setBookInfo(ResultSet resultSet) throws SQLException {

        // 设置图书信息
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setGroups(resultSet.getString("groups"));
        book.setName(resultSet.getString("name"));
        book.setAuthor(resultSet.getString("author"));
        book.setPress(resultSet.getString("press"));
        book.setPrice(resultSet.getDouble("price"));
        book.setQuantity(resultSet.getInt("quantity"));
        book.setIsbn(resultSet.getString("isbn"));

        return book;
    }

    /**
     * 获取搜索类型
     * @param searchType 搜索类型
     * @return 搜索类型
     */
    public static String getSearchType(String searchType) {
        switch (searchType) {
            case "书名":
                return "name";
            case "作者":
                return "author";
            case "出版社":
                return "press";
            case "ISBN 号码":
                return "isbn";
            default:
                return null;
        }
    }

    /**
     * 设置借阅信息
     * @param resultSet 数据库信息
     * @return 借阅信息
     * @throws SQLException SQL异常
     */
    public static Borrow setBorrowInfo(ResultSet resultSet) throws SQLException {
        // 设置借阅信息
        Borrow borrow = new Borrow();
        borrow.setId(resultSet.getInt("id"));
        borrow.setBookName(resultSet.getString("book_name"));
        borrow.setIsbn(resultSet.getString("isbn"));
        borrow.setUsername(resultSet.getString("username"));
        borrow.setIdCard(resultSet.getString("id_card"));
        borrow.setPhone(resultSet.getString("phone"));
        borrow.setBorrowTime(resultSet.getTimestamp("borrow_time"));
        borrow.setReturnTime(resultSet.getTimestamp("return_time"));

        return borrow;
    }
}
