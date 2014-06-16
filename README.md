SPIDER
======

利用Java 写的解析百度知道医生里面的信息， 例如http://zhidao.baidu.com/prof/view/888257160。 该工程实现分为两步： 第一步，解析单个网页的内容（但是百度HTML页面是Jquery 动态请求） 第二步，写个爬虫 给个入口网址，爬取合适标签〈a href=‘’〉。
做完后发现有点bug，他只能解析到一个网页里面的静态数据，而像百度网页里面的数据是动态的，是Jquery发送Ajax请求动态获取的。
只能解析静态网页中的内，比如 http://chenenguo.haodf.com/ 这样的，数据不是动态的。
