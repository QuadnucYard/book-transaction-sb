package team.wuse.koob.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import team.wuse.koob.entity.AdminRolePermission;

import java.util.List;

public interface AdminRolePermissionDAO extends JpaRepository<AdminRolePermission, Integer> {
    List<AdminRolePermission> findAllByRid(int rid);
    List<AdminRolePermission> findAllByRidIn(List<Integer> rids);
    void deleteAllByRid(int rid);
}
