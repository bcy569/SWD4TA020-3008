package fi.dev.academy.vaccinationdatabase.web_controller_rest;


import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IPersonDAO;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// UserDetailsService loads user details and behind the scenes GrantedAuthority --> AccessDecisionManager checks password
@Service
public class UserDetailServiceImplemented implements UserDetailsService {

    private final IPersonDAO personRepository;

    @Autowired
    public UserDetailServiceImplemented(IPersonDAO personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        Person currentUser = personRepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username,
                currentUser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(currentUser.getRole()));
        return user;
    }
}
