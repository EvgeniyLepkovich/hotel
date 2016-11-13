package spring.hotel.common.persistance.dao.impl;

import org.springframework.stereotype.Repository;
import spring.hotel.common.persistance.dao.IRoleDao;
import spring.hotel.common.persistance.to.Role;

/**
 * Created by Fene4ka_ on 13.11.2016.
 */
@Repository
public class RoleDao extends JpaDao<Role> implements IRoleDao {
}
