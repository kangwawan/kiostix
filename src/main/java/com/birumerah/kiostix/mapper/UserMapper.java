package com.birumerah.kiostix.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

//import com.birumerah.app.rest.model.UserMenu;
import com.birumerah.kiostix.model.User;
import com.birumerah.kiostix.model.UserMenu;

@Mapper
public interface UserMapper{

	@Select("SELECT USER_ID userid, USERNAME username, PASSWORD password, NAME name, ROLE_ID role, ISACTIVE enabled from userapp where ISACTIVE = 1 AND USERNAME = #{username}")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	User findOneByUsername(@Param("username") String username);
	
	@Select("SELECT rm.ROLE_ID roleId, rm.MENU_ID menuId, r.ROLE_NAME roleName, m.MENU_NAME menuName, m.URL url FROM rolemenu AS rm, role AS r, menu m, userapp u WHERE rm.ROLE_ID = r.ROLE_ID AND m.MENU_ID = rm.MENU_ID AND u.ROLE_ID = r.ROLE_ID AND u.USERNAME = #{username} ORDER BY m.URL")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	List<UserMenu> findMenuByUsername(@Param("username") String username);
	
	@Select("SELECT ROLE_ID FROM role WHERE ROLE_NAME = #{roleName}")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	Integer getRoleIdByRoleName(@Param("roleName") String roleName);
	
	@Insert("INSERT INTO userapp (USERNAME,PASSWORD,NAME,ROLE_ID,MARKETPLACE,STORE_ID,ISACTIVE,CREATED_DATE) VALUES (#{username},#{password},#{name},#{roleId},#{marketplaceGroupId},#{storeId},1,now())")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="retur_id", before=false, resultType=Integer.class)
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	Integer insertUser(@Param("username") String username, @Param("password") String password, @Param("name") String name,@Param("roleId") Integer roleId,@Param("marketplaceGroupId") Integer marketplaceGroupId,@Param("storeId") Integer storeId);
	
	@Update("UPDATE userapp SET USERNAME = #{username}, PASSWORD = #{password}, NAME = #{name}, ROLE_ID = #{roleId}, MARKETPLACE = #{marketplaceGroupId}, STORE_ID = #{storeId}, ISACTIVE = #{isActive} WHERE ID = #{id}")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	Integer updateUser(@Param("username") String username, @Param("password") String password, @Param("name") String name,@Param("roleId") Integer roleId,@Param("marketplaceGroupId") Integer marketplaceGroupId,@Param("storeId") Integer storeId, @Param("isActive") Integer isActive, @Param("id") Integer id);

	@Delete("DELETE FROM userapp WHERE ID = #{id}")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	Integer deleteUser(@Param("id") Integer id);
	
}
