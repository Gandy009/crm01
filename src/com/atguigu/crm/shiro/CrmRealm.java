package com.atguigu.crm.shiro;

import java.util.Collection;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atguigu.crm.services.UserService;
import com.atuigu.crm.entity.Authority;
import com.atuigu.crm.entity.User;

@Component
public class CrmRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		Object principal = principalCollection.getPrimaryPrincipal();
		User user = (User) principal;

		//2. 创建 SimpleAuthorizationInfo 对象
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		//3. 调用 SimpleAuthorizationInfo#addRoles(Collection<String>); 添加用户所有的权限
		Collection<String> roles = new HashSet<>();
		for(Authority authority: user.getRole().getAuthorities()){
			roles.add(authority.getName());
		}
		info.addRoles(roles);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		User user = userService.getByName(username);
		
		if(user == null){
			return null;
		}
		Object principal = user;
		Object hashedCredentials = user.getPassword();
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		String realmName = getName();
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);

		return info;
	}
	
	//相当于 init-method. 构造器被调用后, 属性被初始化完成后, 被调用
	@PostConstruct
	public void initCredentialsMatcher(){
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(1024);
		
		//设置凭证的匹配方式. 
		setCredentialsMatcher(credentialsMatcher);
	}

	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		String credentials = "123456";
		ByteSource salt = ByteSource.Util.bytes("db314a8d91bd6f83");
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		
		System.out.println(result);
	}
}
