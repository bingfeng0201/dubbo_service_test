# dubbo_service_test
使用jmeter测试dubbo接口

1、修改zk地址和dubbo服务端接口，以及测试接口的实现类

2、使用main方法测试

3、测试成功后，clean package打包

4、将target目录下生成的jar包复制到jmeter安装路径/lib/ext目录下

5、jmeter安装路径/lib同级目录新建一个文件夹,命名lib-test

6、将本项目/target/lib目录下的所有jar包复制到jmeter安装目录/lib-test目录下

7、修改jmeter安装目录/bin/jmeter.propertes文件中
search_paths=E:/jmeter2/apache-jmeter-5.0/lib;E:/jmeter2/apache-jmeter-5.0/lib-test

8、启动jmeter，创建一个java请求，选择dubbo测试类，生成结果树。


