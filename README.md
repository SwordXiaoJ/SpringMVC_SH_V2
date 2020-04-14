# SpringMVC_SH_V2
按照要求，使用Ioc管理类，同时，将JDBC类注册为bean
并且，重构了代码的结构，将JAVA类按照service,pojo,dao,controller四层进行了重构，使得代码的目录更加清晰，更加便于修改。

时间4月14日
对V2进行了进一步优化
（1）首先是采用了module管理，将Data和Web层分开，Data module放入了dao层，pojo层，service层。Web module中放入了controller层和JSP页面。
（2）参考课上同学的代码，使用了lombok，简化了pojo层的代码。
