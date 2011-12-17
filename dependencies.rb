require 'dependencies_versions'

COMMONS_LOGGING = "commons-logging:commons-logging:jar:#{COMMONS_LOGGING_VERSION}"
FLUX_GROUP = 'com.flux'
FLUX_DOMAIN = "#{FLUX_GROUP}:Flux-domain:jar:#{FLUX_VERSION}"
MOCKITO = "org.mockito:mockito-all:jar:#{MOCKITO_VERSION}"
SPRING = struct(
	:asm =>"org.springframework:spring-asm:jar:#{SPRING_VERSION}",
	:beans =>"org.springframework:spring-beans:jar:#{SPRING_VERSION}",
	:context =>"org.springframework:spring-context:jar:#{SPRING_VERSION}",
	:core =>"org.springframework:spring-core:jar:#{SPRING_VERSION}",
	:expression =>"org.springframework:spring-expression:jar:#{SPRING_VERSION}",
	:web =>"org.springframework:spring-web:jar:#{SPRING_VERSION}",
	:webmvc =>"org.springframework:spring-webmvc:jar:#{SPRING_VERSION}"
	)