learn-shop-base-email 依赖 learn-shop-base-jpa（自动添加）,此组件用于发送邮件。

支持freemarker，thymeleaf，html，markdown模板邮件，可以指定参数，指定运行SQL（${}用于替换文本参数，#{}用于替换SQL参数）。

有接口提供自定义配置（具体API请参考 learn-shop-admin-system 下的 MailTemplateApi 类，ui请参考 learn-shop-ui-admin 下
的 MailTemplateList.vue，MailTemplateEdit.vue）。

系统会自动新建一张 sys_mail_template 的表，此表用于保存邮件模板信息。


使用方法：

1.application.yml 中需要配置：
````yaml
spring:
  mail:
    host: smtp.exmail.qq.com
    port: 465
    username: SSSSSSSS # 邮箱用户名
    password: SSSSSSSS # 邮箱密码
    default-encoding: utf-8
    properties:
      mail:
        smtp:
          socketFactory:
            class: javax.net.ssl.SSLSocketFactory
          auth: true
    thymeleaf:
      mode: HTML5
      cache: false
      prefix: classpath:/templates/ # 邮件模板的位置，一定要配置
````

2. 由于 MailTemplatePo 在包 com.billow.email.pojo.po 下，可能你的系统的 po 文件可能在 com.xx.xx.cc 下
所以需要在启动类上添加以下注解:
```java
@EntityScan(basePackages = {"com.billow.email", "com.xx.xx.cc"})
@EnableJpaRepositories(basePackages = {"com.billow.email", "com.xx.xx.cc"})
```

3.pom.xml
```xml
    <dependency>
        <groupId>com.billow</groupId>
        <artifactId>learn-shop-base-email</artifactId>
        <version>last version</version>
    </dependency>
```

**注意：** 混合模式时，指定参数优先

**附录：**
```java
/**
 * 邮件模板
 *
 * @author liuyongtao
 * @create 2019-08-20 20:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_mail_template")
public class MailTemplatePo extends BasePo implements Serializable {

    public MailTemplatePo() {
    }

    public MailTemplatePo(String mailCode, String mailType, String dataSources, String descritpion) {
        this.mailCode = mailCode;
        this.mailType = mailType;
        this.dataSources = dataSources;
        this.descritpion = descritpion;
    }

    // 邮件标识，唯一
    private String mailCode;

    // 邮件类型，1-普通邮件，2-html邮件,4-FreeMarker 模板邮件,5-Thymeleaf 模板邮件
    private String mailType;

    // 数据来源，1-固定内容，2-SQL查询，3-参数设置,4-混合（2、3都有）
    private String dataSources;

    @Lob
    @Type(type = "text")
    // 数据来源为2-SQL查询时，sql 不能为空
    private String runSql;

    @Lob
    @Type(type = "text")
    // 邮件模板
    private String mailTemp;

    @Lob
    @Type(type = "text")
    // 邮件Markdown模板
    private String mailMarkdown;

    // 邮件模板描述
    private String descritpion;

    // 收件人邮箱，多个邮箱以“;”分隔
    private String toEmails;

    // 邮件主题
    private String subject;

    // 使用 Thymeleaf 或者 Freemarker 时,需要指定模板路径
    private String templateName;

    // 使用 Thymeleaf 或者 Freemarker 时，sql 结果集是否单行，true-单行，false-多行
    private Boolean singleResult;

    // 是否带附件，true-单，false-不带
    private Boolean attachment;
}
```

```sql
DROP TABLE IF EXISTS `sys_mail_template`;
CREATE TABLE `sys_mail_template`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mail_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mail_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_sources` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `to_emails` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mail_temp` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `mail_markdown` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `run_sql` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `single_result` bit(1) NULL DEFAULT NULL,
  `template_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `descritpion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `valid_ind` bit(1) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `creator_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `updater_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attachment` bit(1) NULL DEFAULT NULL,
  `template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

```