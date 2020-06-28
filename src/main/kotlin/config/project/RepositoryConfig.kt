package config.project

import app.eval
import app.evalEnd
import constant.db.Hibernate
import constant.db.Packages
import constant.db.project.BeanNames
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.*
import javax.persistence.EntityManagerFactory

@Configuration(value = BeanNames.repositoryConfig)
@EnableJpaRepositories(
    basePackages = [Packages.repository_project, Packages.repository_entity],
    entityManagerFactoryRef = BeanNames.entityManagerFactory,
    transactionManagerRef = BeanNames.transactionManager
)
//@EnableTransactionManagement
class RepositoryConfig
{
    @Value(eval + Hibernate.dialect + evalEnd)
    private lateinit var dialect: String

    @Value(eval + Hibernate.hbm2ddl_auto + evalEnd)
    private lateinit var hbd2DdlAuto: String

    @Value(eval + Hibernate.format_sql + evalEnd)
    private lateinit var formatSql: String

    @Value(eval + Hibernate.show_sql + evalEnd)
    private lateinit var showSql: String

    @Autowired
    private lateinit var routingDataSource: RoutingDataSource

    @Bean(name = [ BeanNames.entityManagerFactory ])
    fun entityManagerFactory(): EntityManagerFactory
    {
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        entityManagerFactoryBean.dataSource = routingDataSource
        entityManagerFactoryBean.jpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManagerFactoryBean.setPackagesToScan(Packages.repository_authentication, Packages.repository_entity)
        val jpaProperties = Properties()
        jpaProperties.put(Hibernate.dialect, dialect)
        jpaProperties.put(Hibernate.hbm2ddl_auto, hbd2DdlAuto)
        jpaProperties.put(Hibernate.format_sql, formatSql)
        jpaProperties.put(Hibernate.show_sql, showSql)
        entityManagerFactoryBean.setJpaProperties(jpaProperties)
        entityManagerFactoryBean.afterPropertiesSet()
        return entityManagerFactoryBean.`object`
    }

    @Bean(name = [ BeanNames.transactionManager ])
    fun transactionManager(): JpaTransactionManager?
    {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory()
        return transactionManager
    }
}