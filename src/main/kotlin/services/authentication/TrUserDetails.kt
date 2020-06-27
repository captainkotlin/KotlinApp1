package services.authentication

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class TrUserDetails(
    var user: String,
    var pass: String,
    var authoritiesList: List<GrantedAuthority>
) : UserDetails
{
    override fun getUsername(): String
    {
       return user;
    }

    override fun getPassword(): String
    {
        return pass;
    }

    override fun getAuthorities(): List<GrantedAuthority>
    {
        return authoritiesList;
    }

    override fun isEnabled(): Boolean
    {
        return true;
    }

    override fun isCredentialsNonExpired(): Boolean
    {
        return true;
    }

    override fun isAccountNonExpired(): Boolean
    {
        return true;
    }

    override fun isAccountNonLocked(): Boolean
    {
        return true;
    }
}