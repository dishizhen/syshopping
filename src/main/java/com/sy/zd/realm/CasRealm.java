package com.sy.zd.realm;

import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jPrincipal;
import io.buji.pac4j.token.Pac4jToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CasRealm extends Pac4jRealm {
    @Autowired
    private DataSource dataSource;

    /**
     * CAS返回ticket认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        final Pac4jToken pac4jToken = (Pac4jToken) authenticationToken;
        final List<CommonProfile> commonProfileList = pac4jToken.getProfiles();
        final CommonProfile commonProfile = commonProfileList.get(0);
        System.out.println("单点登录返回的信息=====>" + commonProfile.toString());
        System.out.println("用户名为********"+commonProfile.getId());
        final Pac4jPrincipal principal = new Pac4jPrincipal(commonProfileList, getPrincipalNameAttribute());
        final PrincipalCollection principalCollection = new SimplePrincipalCollection(principal, getName());
        return new SimpleAuthenticationInfo(principalCollection, commonProfileList.hashCode());
    }

    /**
     * 授权,使用shiro的授权方式
     * 应该获取CAS返回用户信息,去数据库中查询相应权限信息,权限管理交由shiro
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        if (principals ==null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        } else {
            String username = (String) this.getAvailablePrincipal(principals);
            Connection connection = null;
            Set<String> roleNames = new HashSet<>();
            Set permissions = new HashSet();
            try {
                connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("select rolecode from au_role where id = (select roleid from au_user where username=?)");
                statement.setString(1,username);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String rolename = resultSet.getString("rolecode");
                    roleNames.add(rolename);
                }
                statement = connection.prepareStatement("SELECT funcUrl FROM au_function WHERE id " +
                        "IN(SELECT functionid FROM au_authority WHERE roleid =(" +
                        "SELECT roleid FROM au_user WHERE username = ?))");
                statement.setString(1, username);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String permission = resultSet.getString("funcUrl");
                    System.out.println(permission);
                    permissions.add(permission);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
            info.setStringPermissions(permissions);
            return info;
        }
    }
}
