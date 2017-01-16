package spring.hotel.common.persistance.dao;

import org.springframework.data.repository.CrudRepository;
import spring.hotel.common.persistance.to.Role;

public interface RoleDao extends CrudRepository<Role, Long> {
}
