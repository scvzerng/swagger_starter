## swagger starter

- 添加依赖

            <dependency>
                <groupId>com.zero.scvzerng</groupId>
                <artifactId>swagger-starter</artifactId>
                <version>${version}</version>
            </dependency>


- use

>application.yml

          swagger:
            email: 842494771@qq.com
            name: XXX项目
            description: XXX项目描述
            version: 1.0 
            
> 提示

- 生产环境无效
- 只有在name有值时才有效
- 只扫描标注了@Api注解的Controller


                       