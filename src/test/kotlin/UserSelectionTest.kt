import config.authentication.RepositoryConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.support.AnnotationConfigContextLoader
import repository.authentication.UserRepository
import repository.authentication.UserRoleRepository

@DataJpaTest
@ContextConfiguration(
    classes = [ RepositoryConfig::class, UserRepository::class],
    loader = AnnotationConfigContextLoader::class
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(value = ["/application.properties"])
class UserSelectionTest
{
    @Autowired
    private lateinit var userRepository: UserRepository;

    @Autowired
    private lateinit var userRoleRepository: UserRoleRepository;

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
        val user = userRepository.findByName(USERNAME)
        Assertions.assertEquals(USERNAME, user.user)
        Assertions.assertEquals(PASSWORD, user.pass)
    }

    @Test
    @DisplayName("Select default roles from userRoleRepository")
    fun test1()
    {
        val userRoles = userRoleRepository.findByName(USERNAME)
        Assertions.assertTrue(userRoles.size == USER_ROLES.size, "")
        val role1Index = 0
        val role2Index = 1
        Assertions.assertEquals(USER_ROLES.get(role1Index), userRoles[role1Index].role)
        Assertions.assertEquals(USER_ROLES.get(role2Index), userRoles[role2Index].role)
        userRoleRepository.findAll()
        System.out.println("Hello")
    }


}