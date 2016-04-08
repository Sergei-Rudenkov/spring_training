package ua.epam.spring.hometask.service.service_interfaces;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.service_interfaces.IAbstractDomainObjectService;

/**
 * @author Yuriy_Tkach
 */
public interface IUserService extends IAbstractDomainObjectService<User> {

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    @Nullable User getUserByEmail(@Nonnull String email);

}
