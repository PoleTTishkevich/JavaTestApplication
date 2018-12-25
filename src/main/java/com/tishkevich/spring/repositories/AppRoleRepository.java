package com.tishkevich.spring.repositories;

import com.tishkevich.spring.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class AppRoleRepository {

    private final EntityManager entityManager;

    @Autowired
    public AppRoleRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List getRoleNames(Long userId) {
        String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " //
                + " where ur.user.id = :userId ";

        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

}
