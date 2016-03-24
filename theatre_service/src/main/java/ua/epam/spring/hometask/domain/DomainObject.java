package ua.epam.spring.hometask.domain;

/**
 * @author Yuriy_Tkach
 */
public abstract class DomainObject {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
