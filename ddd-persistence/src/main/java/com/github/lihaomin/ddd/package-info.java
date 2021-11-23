/**
 * Infrastructure模块包含了Persistence、Messaging、External等模块。
 * Persistence模块包含数据库DAO的实现，包含Data Object、ORM Mapper、Entity到DO的转化类等。
 * Persistence模块要依赖具体的ORM类库，比如MyBatis。
 * 如果需要用Spring-Mybatis提供的注解方案，则需要依赖Spring。
 */
package com.github.lihaomin.ddd;