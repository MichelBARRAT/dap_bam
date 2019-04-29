package fr.hoc.dap.server.data;

import org.springframework.data.repository.CrudRepository;

/**
 * Manage access for DaP user in database.
 *
 * @author Michette & Thomas
 */
public interface DapUserRepository extends CrudRepository<DapUser, Long> {

    // DapUser findDapUser(String userKey);
}
