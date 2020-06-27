import config.RepositoryConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.support.AnnotationConfigContextLoader
import repositories.TrUserRepository
import repositories.TrUserRoleRepository

@DataJpaTest
@ContextConfiguration(
    classes = [ RepositoryConfig::class, TrUserRepository::class],
    loader = AnnotationConfigContextLoader::class
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(value = ["/application.properties"])
class UserSelectionTest
{
    @Autowired
    private lateinit var userRepository: TrUserRepository;

    @Autowired
    private lateinit var userRoleRepository: TrUserRoleRepository;

    companion object
    {
        val USERNAME = "john"
        val PASSWORD = "123"
        val USER_ROLES = listOf("ROLE_read", "read")
    }

    @Test
    @DisplayName("Select default user from userRepository")
    fun test()
    {
        val trUser = userRepository.findByName(USERNAME)
        Assertions.assertEquals(USERNAME, trUser.user)
        Assertions.assertEquals(PASSWORD, trUser.pass)
    }

    @Test
    @DisplayName("Select default roles from userRoleRepository")
    fun test1()
    {
        val trUserRoles = userRoleRepository.findByName(USERNAME)
        Assertions.assertTrue(trUserRoles.size == USER_ROLES.size, "")
        val role1Index = 0
        val role2Index = 1
        Assertions.assertEquals(USER_ROLES.get(role1Index), trUserRoles[role1Index].role)
        Assertions.assertEquals(USER_ROLES.get(role2Index), trUserRoles[role2Index].role)
        userRoleRepository.findAll()
        System.out.println("Hello")
    }


}