package config

import app.eval
import app.evalEnd
import constants.Hibernate
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.*
import javax.annotation.PostConstruct
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


@Configuration
@EnableJpaRepositories(
    basePackages = ["repositories"],
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager"
)
class RepositoryConfig
{
    @Value(eval + Hibernate.DIALECT + evalEnd)
    private lateinit var dialect: String

    @Value(eval + Hibernate.HBM_2_DDL_AUTO + evalEnd)
    private lateinit var hbd2DdlAuto: String

    @Value(eval + Hibernate.FORMAT_SQL + evalEnd)
    private lateinit var formatSql: String

    @Value(eval + Hibernate.SHOW_SQL + evalEnd)
    private lateinit var showSql: String

    @Bean
    @ConfigurationProperties(prefix = "datasource")
    fun dataSource(): DataSource
    {
        return DataSourceBuilder.create()
            .build()
    }

    @Bean
    @ConfigurationProperties(prefix = "hibernate")
    fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean?
    {
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        entityManagerFactoryBean.dataSource = dataSource()
        entityManagerFactoryBean.jpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManagerFactoryBean.setPackagesToScan("repositories")
        val jpaProperties = Properties()
        jpaProperties.put(Hibernate.DIALECT, dialect)
        jpaProperties.put(Hibernate.HBM_2_DDL_AUTO, hbd2DdlAuto)
        jpaProperties.put(Hibernate.FORMAT_SQL, formatSql)
        jpaProperties.put(Hibernate.SHOW_SQL, showSql)
        entityManagerFactoryBean.setJpaProperties(jpaProperties)
        return entityManagerFactoryBean
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory?): JpaTransactionManager?
    {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory
        return transactionManager
    }
}