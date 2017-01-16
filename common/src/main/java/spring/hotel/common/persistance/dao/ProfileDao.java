package spring.hotel.common.persistance.dao;

import org.springframework.data.repository.CrudRepository;
import spring.hotel.common.persistance.to.Profile;

public interface ProfileDao extends CrudRepository<Profile, Long> {
}
