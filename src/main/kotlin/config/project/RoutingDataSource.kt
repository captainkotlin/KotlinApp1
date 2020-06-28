package config.project

import constant.db.DataSource
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Scope
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.stereotype.Component
import util.ResourceUtils
import javax.annotation.PostConstruct

@Component
@Scope("prototype")
class RoutingDataSource : AbstractRoutingDataSource()
{
    private var dataSources: Map<Any, Any> = emptyMap()

    override fun determineCurrentLookupKey(): Any
    {
        return dataSources.asSequence().map { it.key }.first()
    }

    @PostConstruct
    private fun init()
    {
        refresh()
    }

    fun refresh()
    {
        ResourceUtils.getResource(DataSource.configFolderPath)
            ?.let {
                dataSources = it.asSequence()
                    .associateBy({ k -> k.name }, { v -> toDataSource(ResourceUtils.readProperties(v)) })
                setTargetDataSources(dataSources)
            }
    }

    private fun toDataSource(props: Map<String, String>): javax.sql.DataSource
    {
        return DataSourceBuilder.create()
            .driverClassName(props.getOrDefault(DataSource.driverClassName, ""))
            .url(props.getOrDefault(DataSource.jdbcUrl, ""))
            .username(props.getOrDefault(DataSource.username, ""))
            .password(props.getOrDefault(DataSource.password, ""))
            .build()
    }
}