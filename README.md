# expenses
springboot+easyui+bootstrap支出记录

# 2017-5-31 更新
1、将jQuery、EasyUI引用部分作用common模板使用\
2、新增“支出人”字段（2017-5-31 16:02:40）\
3、完善功能（2017-5-31 23:05:41）

# 2017-6-1 更新
1、完善功能\
2、解决iframe引入滚动条问题（<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">）\
3、完善菜单样式（2017-6-1 16:53:54）

# 2017-6-2 更新
1、添加datagrid行间隔条纹\
2、优化菜单点击(2017-6-2 08:20:20)\
3、完成数据字典维护功能\
4、页面优化

# 2017-6-4 更新
1、增加所有字段本地排序sortable

# 2017-6-10 更新
1、新增日志输出到控制台、文件和数据库，以及web端日志显示\
2、完善日志记录\
3、解决多环境记录log数据源配置问题

# 2017-6-12 更新
1、优化日志只显示当前日期\

# 2017-6-13 更新
1、新增支出界面单双日期选择查询\

# 2017-6-16 更新
1、新增支出列表支持图片上传及预览\

# 2017-6-18 更新
1、集成Shiro实现登录功能\
2、新增login界面

# 2017-6-19 更新
1、实现salt\
2、实现user的增删改

# 2017-6-20 更新
1、新增rememberMe功能的实现\
2、集成ehcache\
3、实现连续密码错误5次即锁定\
4、解决doGetAuthorizationInfo不执行（加入aspectjweaver依赖）\
5、controller权限控制功能实现

# 2017-6-21 更新
1、解决“validation failed for object”问题（实体前加@Valid，后加BindingResult bindingResult）\
2、实现连续6次输错密码即永久锁定用户\
3、实现role、permission的维护功能
4、thymeleaf实现shiro标签权限控制