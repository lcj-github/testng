	1:junit Jenkins github maven  good  --- junitgood分支上
	
	
	2:testng Jenkins github maven  good
	  2.0: 主要问题：缺少jar包。报 Failed to execute goal *****
      2.1: 从 http://repo1.maven.org/maven2/org/apache/maven/ 下载
      surefire-testng surefire-testng-utils surefire-grouper 到本地的文件夹下
      2.2: 参考
      	 http://www.cnblogs.com/zhouyalei/p/6474394.html
      	 http://blog.sina.com.cn/s/blog_90aad2c90101lce4.html
    3：IndependentTest 进行配置 log记录
    4: testng  学习参考 https://howtodoinjava.com/testng
    5：ExtentReports 结合 TestNg 生成自动化 html 报告
    6： MyListenerTest 类名前 添加 ：@Listeners标签，会在执行@Test标签方法前会执行：MyMethodInterceptor中 的方法
    7： interUsing中用到的请求方法及解析excle方法，拼接参数方法。但ContactngTest 方法与testng集成及util些方法。