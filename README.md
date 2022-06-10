# gulimall
谷粒商城

# 更新日志  
2022年5月30日 开始项目，安装vagrant虚拟机，配置开发环境  

2022年5月31日 解决相关renren-fast-vue相关问题，node.js版本为10.X，node-sass应为4.14.通过代码生成器生成相关模块基本代码，并对其进行数据库、端口的配置，初步了解nacos和feign远程调用  

2022年6月1日 nacos配置中心和命名空间，gateway网关的创建，相关前端技术（ES6、Vue）的学习

2022年6月5日 完成后台管理模块商品添加，参数添加等功能

2022年6月9日 完成第一阶段分布式基础的工作，包含商品添加模块，库存模块等

## 1. 分布式基础概念
   微服务、注册中心、配置中心、远程调用、Feign、网关
## 2. 基础开发
  SpringBoot2.0、SpringCloud、Mybatis-Plus、Vue、阿里云存储
## 3. 环境
  Vagrant、Linux、Docker、MySQL、Redis、逆向工程
## 4. 开发规范
  数据校验JSR303、全局异常处理、全局统一返回、全局跨域处理、枚举状态、业务状态码、VO与TO、PO、逻辑删除、Lombok的使用
    
### 分布式基础篇技术难点
1. 访问路径的错误。 前台系统中所有的请求都是由http://localhost:88/api 进行转发的，需要在gateway模块中添加路由规则：` - id: admin_route
          uri: lb://renren-fast
          predicates:
            - Path=/api/**`  
在访问前台系统的登陆界面时，同时发现验证码不再显示，由于路由规则的设置，原先的访问路径中包含"api"，所以需要对请求路径进行重写。此时又发现访问被拒绝了，是因为CORS头中缺少'Access-Control-Allow-Origin'，访问的域名、端口和之前的请求不同，请求就会被限制。需要在网关中定义CorsConfiguration类，用于过滤，允许所有的请求跨域  

`@Configuration
public class GulimallCorsConfiguration {
 
    @Bean
    public CorsWebFilter corsWebFilter(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowCredentials(true);
        
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(source);
    }
}`
