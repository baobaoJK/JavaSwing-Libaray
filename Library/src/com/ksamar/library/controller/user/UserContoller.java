package com.ksamar.library.controller.user;

import com.ksamar.library.entity.User;
import com.ksamar.library.tools.sql.SqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制器
 * @author KSaMar
 * @version 1.0
 */
public class UserContoller {

    /**
     * 获取用户数量
     * @return 用户数量
     */
    public static int getCount() {
        int count = 0;

        // 获取用户数量语句
        String getCountSql = "select * from userlist";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(getCountSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count ++;
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
     * 获取用户信息
     * @param page 页码
     * @param size 大小
     * @return 用户信息
     */
    public static List<User> pageUser(int page, int size) {
        List<User> userArrayList = new ArrayList<>();

        // 获取用户信息语句
        String pageUserSql = "select * from userlist limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(pageUserSql);
            preparedStatement.setInt(1, (page - 1) * size);
            preparedStatement.setInt(2, size);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = setUserInfo(resultSet);
                userArrayList.add(user);
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

        return userArrayList;
    }

    /**
     * 搜索用户信息
     * @param searchType 搜索类型
     * @param text 搜索文本
     * @param page 页码
     * @param size 大小
     * @return 用户信息
     */
    public static List<User> searchUserLike(String searchType, String text, int page, int size) {
        List<User> userArrayList = new ArrayList<>();

        // 获取搜索类型
        searchType = getSearchType(searchType);

        // 搜索用户信息语句
        String searchUserLikeSql = "select * from userlist where " + searchType + " like ? limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(searchUserLikeSql);
            preparedStatement.setString(1, "%" + text + "%");
            preparedStatement.setInt(2, (page - 1) * size);
            preparedStatement.setInt(3, size);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = setUserInfo(resultSet);
                userArrayList.add(user);
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

        return userArrayList;
    }

    /**
     * 搜索用户类型长度
     * @param searchType 搜索类型
     * @param text 搜索文本
     * @return 用户类型长度
     */
    public static int searchUserLikeCount(String searchType, String text) {
        int count = 0;

        // 获取搜索类型
        searchType = getSearchType(searchType);

        // 搜索用户类型长度语句
        String searchUserLikeCountSql = "select * from userlist where " + searchType + " like ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(searchUserLikeCountSql);
            preparedStatement.setString(1, "%" + text + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count ++;
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
     * 搜索用户
     * @param username 用户名
     * @param idCard 借书卡号
     * @param phone 手机号
     * @return User
     */
    public static User searchUserByUsernameAndIdCardAndPhone(String username, String idCard, String phone) {
        User user = null;

        // 搜索用户语句
        String searchUserByUsernameAndIdCardAndPhoneSql = "select * from userlist where" +
                " username=? and id_card=? and phone=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(searchUserByUsernameAndIdCardAndPhoneSql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, idCard);
            preparedStatement.setString(3, phone);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = setUserInfo(resultSet);
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

        return user;
    }

    /**
     * 以用户名方式搜索用户
     * @param username 用户名
     * @return User
     */
    public static User searchUserByUsername(String username) {
        User user = null;

        // 搜索用户语句
        String searchUserByUsernameSql = "select * from userlist where username = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(searchUserByUsernameSql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = setUserInfo(resultSet);
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

        return user;
    }

    /**
     * 以借书卡方式搜索用户
     * @param idCard 借书卡
     * @return User
     */
    public static User searchUserByIdCard(String idCard) {
        User user = null;

        // 搜索用户语句
        String searchUserByIdCardSql = "select * from userlist where id_card = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(searchUserByIdCardSql);
            preparedStatement.setString(1, idCard);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = setUserInfo(resultSet);
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

        return user;
    }

    /**
     * 以手机号方式搜索用户
     * @param phone 手机号
     * @return User
     */
    public static User searchUserByPhone(String phone) {
        User user = null;

        // 搜索用户语句
        String searchUserByPhoneSql = "select * from userlist where phone = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(searchUserByPhoneSql);
            preparedStatement.setString(1, phone);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = setUserInfo(resultSet);
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

        return user;
    }

    /**
     * 添加用户
     * 0 - 添加失败
     * 1 - 添加成功
     * 2 - 用户名存在
     * 3 - 借书卡号存在
     * 4 - 手机号存在
     * @param user 用户
     * @return 添加状态
     */
    public static int addUser(User user) {
        int result = 0;

        // 判断用户名是否存在
        User tempUser = searchUserByUsername(user.getUsername());
        if (tempUser != null) {
            return 2;
        }

        // 判断借书卡号是否存在
        tempUser = searchUserByIdCard(user.getIdCard());
        if (tempUser != null) {
            return 3;
        }

        // 判断手机号是否存在
        tempUser = searchUserByPhone(user.getPhone());
        if (tempUser != null) {
            return 4;
        }

        // 插入用户语句
        String insertUser = "insert into userlist(groups, name, username, password, gender, id_card, phone, identity," +
                " book_count, state) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(insertUser);
            preparedStatement.setString(1, user.getGroups());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getGender());
            preparedStatement.setString(6, user.getIdCard());
            preparedStatement.setString(7, user.getPhone());
            preparedStatement.setString(8, user.getIdentity());
            preparedStatement.setInt(9, user.getBookCount());
            preparedStatement.setInt(10, user.getState());
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
     * 编辑用户
     * @param user 用户
     * @return 编辑状态
     */
    public static int editUser(User user) {
        int result = 0;

        // 管理员不可编辑
        if (user.getId() == 1) {
            return result;
        }

        // 判断用户名是否存在
        User tempUser = searchUserByUsername(user.getUsername());
        if (tempUser != null && !tempUser.getId().equals(user.getId())) {
            return 2;
        }

        // 判断借书卡号是否存在
        tempUser = searchUserByIdCard(user.getIdCard());
        if (tempUser != null && !tempUser.getId().equals(user.getId())) {
            return 3;
        }

        // 判断手机号是否存在
        tempUser = searchUserByPhone(user.getPhone());
        if (tempUser != null && !tempUser.getId().equals(user.getId())) {
            return 4;
        }

        // 更新用户语句
        String editUserSql = "update userlist set groups = ?, name = ?, username = ?, password = ?, gender = ?," +
                " id_card = ?, phone = ?, identity = ?, book_count = ?, state = ? where id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(editUserSql);
            preparedStatement.setString(1, user.getGroups());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getGender());
            preparedStatement.setString(6, user.getIdCard());
            preparedStatement.setString(7, user.getPhone());
            preparedStatement.setString(8, user.getIdentity());
            preparedStatement.setInt(9, user.getBookCount());
            preparedStatement.setInt(10, user.getState());
            preparedStatement.setInt(11, user.getId());
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
     * 删除用户
     * 0 - 删除失败
     * 1 - 删除成功
     * @param id 用户id
     * @return 删除状态
     */
    public static int deleteUser(int id) {
        int result = 0;

        // 管理员不可删除
        if (id == 1) {
            return result;
        }

        // 删除用户语句
        String deleteUser = "delete from userlist where id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(deleteUser);
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
     * 修改管理员密码
     * 0 - 修改失败
     * 1 - 修改成功
     * @return 修改状态
     */
    public static int updateAdminPassword(String password) {
        int result = 0;

        // 修改管理员密码语句
        String updateAdminPasswordSql = "update userlist set password = ? where id = 1";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(updateAdminPasswordSql);
            preparedStatement.setString(1, password);
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
     * 设置用户信息
     * @param resultSet 数据库信息
     * @return User
     * @throws SQLException SQL异常
     */
    public static User setUserInfo(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setGroups(resultSet.getString("groups"));
        user.setName(resultSet.getString("name"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setGender(resultSet.getString("gender"));
        user.setIdCard(resultSet.getString("id_card"));
        user.setPhone(resultSet.getString("phone"));
        user.setIdentity(resultSet.getString("identity"));
        user.setBookCount(resultSet.getInt("book_count"));
        user.setState(resultSet.getInt("state"));
        return user;
    }

    /**
     * 获取搜索类型
     * @param searchType 搜索类型
     * @return 搜索类型
     */
    public static String getSearchType(String searchType) {
        switch (searchType) {
            case "用户名":
                return "username";
            case "借书卡号":
                return "id_card";
            case "手机号":
                return "phone";
            default:
                return null;
        }
    }
}
