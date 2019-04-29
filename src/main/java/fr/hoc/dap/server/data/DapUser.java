package fr.hoc.dap.server.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Contains all attributes for a DaP user.
 *
 * @author Michette & Thomas
 */
@Entity
public class DapUser {
    /** DaP User Id. */
    @Id
    @GeneratedValue
    private Long id;
    /** DaP UserKey (to store in Google Credentials. */
    @Column(nullable = false, unique = true)
    private String userKey;
    /** DaP User login name. */
    @Column(nullable = false)
    private String loginName;

    /**
     * @return DaP user Id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param newUserId DaP user id
     */
    public void setId(final Long newUserId) {
        this.id = newUserId;
    }

    /**
     * @return userKey DaP userKey
     */
    public String getUserKey() {
        return userKey;
    }

    /**
     * @param newUserKey DaP userKey
     */
    public void setUserKey(final String newUserKey) {
        this.userKey = newUserKey;
    }

    /**
     * @return DaP login name
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param newLoginName DaP login name
     */
    public void setLoginName(final String newLoginName) {
        this.loginName = newLoginName;
    }
}
