require 'dependencies_versions'

AOPALLIANCE = "aopalliance:aopalliance:jar:#{AOPALLIANCE_VERSION}"
APACHE_ASM = "org.apache.servicemix.bundles:org.apache.servicemix.bundles.asm:jar:#{APACHE_ASM_VERSION}"
CGLIB = "cglib:cglib:jar:#{CGLIB_VERSION}"
COMMONS_BEANUTILS = "commons-beanutils:commons-beanutils:jar:#{COMMONS_BEANUTILS_VERSION}"
COMMONS_LANG = "commons-lang:commons-lang:jar:#{COMMONS_LANG_VERSION}"
COMMONS_LOGGING = "commons-logging:commons-logging:jar:#{COMMONS_LOGGING_VERSION}"
DOZER = "net.sf.dozer:dozer:jar:#{DOZER_VERSION}"
FLUX_GROUP = 'com.flux'
FLUX_DOMAIN = "#{FLUX_GROUP}:Flux-domain:jar:#{FLUX_VERSION}"
HIBERNATE = "org.hibernate:hibernate-core:jar:#{HIBERNATE_VERSION}"
LOG4J = "log4j:log4j:jar:#{LOG4J_VERSION}" 
MOCKITO = "org.mockito:mockito-all:jar:#{MOCKITO_VERSION}"
PERSISTENCE_API = "javax.persistence:persistence-api:jar:#{PERSISTENCE_API_VERSION}"
SERVLET_API = "javax.servlet:servlet-api:jar:#{SERVLET_API_VERSION}"
SLF4J="org.slf4j:slf4j-api:jar:#{SLF4J_VERSION}"
SPRING = struct(
	:aop =>"org.springframework:spring-aop:jar:#{SPRING_VERSION}",
	:asm =>"org.springframework:spring-asm:jar:#{SPRING_VERSION}",
	:beans =>"org.springframework:spring-beans:jar:#{SPRING_VERSION}",
	:context =>"org.springframework:spring-context:jar:#{SPRING_VERSION}",
	:core =>"org.springframework:spring-core:jar:#{SPRING_VERSION}",
	:expression =>"org.springframework:spring-expression:jar:#{SPRING_VERSION}",
	:jdbc =>"org.springframework:spring-jdbc:jar:#{SPRING_VERSION}",
	:orm =>"org.springframework:spring-orm:jar:#{SPRING_VERSION}",
	:transaction =>"org.springframework:spring-tx:jar:#{SPRING_VERSION}",
	:web =>"org.springframework:spring-web:jar:#{SPRING_VERSION}",
	:webmvc =>"org.springframework:spring-webmvc:jar:#{SPRING_VERSION}"
	)
