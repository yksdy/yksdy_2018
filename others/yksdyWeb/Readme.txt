                               光盘使用说明


                          版权所有，侵权必究

                               2009年9月


------------------------------ 软件环境 -------------------------------
Windows 2000/XP/2003/Vista；
JDK 1.5 以上。

------------------------------ 硬件环境 -------------------------------
微处理器：奔腾II300MHZ或更高；
内存：至少64MB；
硬盘空间：1GB以上；
其他为常规硬件配置，对显卡、声卡等都无特别要求。


------------------------------ 光盘内容 ---------------------------

src：该目录下为本书源代码文件，可以用Eclipse的Import | Existing Projects into Workspace功能导入到MyEclipse 中。

build：该目录下为本书涉及的二进制代码（war包），可以放到Tomcat中直接运行。

libs：该目录下为本书涉及的一些第三方jar包，例如MySQL驱动、Log4j、Yahoo工具包等。

tools：该目录下为本书涉及的一些开发工具，包括JDK、MyEclipse、Tomcat、MySQL等的下载地址（因为版权原因，无法直接提供这些开发工具）。

视频讲解（赠送）：该目录下为免费赠送的Java Web开发的相关视频讲解，请读者用暴风影音等播放器播放。



------------------------------ 数据库设置说明 -------------------------

解压tools/MySQL/mysql-noinstall-5.1.32-win32.zip，执行解压后的bin/mysqld.exe启动MySQL服务。

解压tools/MySQL/mysql-gui-tools-noinstall-5.0-r17-win32.zip，通过MySQL图形界面修改MySQL的root帐号密码为admin，默认为空。本书源代码中MySQL帐号为root，密码为admin。

本书的数据库SQL在 build/setup.sql中。在MySQL控制台输入 "\. setup.sql" 即可导入SQL数据，或者用MySQL图形界面导入。


------------------------------ 源代码使用说明 -------------------------

可以用Eclipse的Import | Existing Projects into Workspace功能，选择src文件夹，选择要导入的项目，即可将源代码导入到MyEclipse中。


------------------------------ 二进制代码使用说明 ---------------------

build目录下为war文件，将war文件复制到Tomcat的webapps下面，使用tomcat/bin/startup.bat启动Tomcat，然后访问 http://localhost:8080/book，即可演示所有的源代码。

提示：

执行某些war前需要先初始化数据库。

book.war为本书的导航，可通过菜单浏览所有的效果。

由于war文件众多，建议将除book.war之外的其余war分批放入Tomcat下执行，否则容易出现内存溢出。


------------------------------技术支持-----------------------------------

如果您使用此光盘中遇到什么问题，您可以通过以下方式与我们联系：

E-mail：bookservice2008@163.com



