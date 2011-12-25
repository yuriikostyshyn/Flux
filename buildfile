require 'dependencies'
Project.local_task :deploy
Project.local_task :unix_deploy

repositories.remote << 'http://mirrors.ibiblio.org/maven2/'

define 'Flux' do

define 'connection' do
	project.version = '1.0.0'
	project.group = 'com.flux'
	package :jar
end

define 'dbs' do
	project.version = '1.0.0'
	project.group = 'com.flux'
	package :jar
end

define 'domain' do
	project.version = '1.0.0'
	project.group = 'com.flux'
	package :jar
end

define 'persistence' do
	project.version = '1.0.0'
	project.group = 'com.flux'
	package :jar
end

define 'webapp' do
	project.version = FLUX_VERSION
	project.group = 'com.flux'
	compile.with COMMONS_LOGGING, MOCKITO, SPRING, LOG4J, projects('domain')
	package :war
	task :deploy => :compile do
		system 'COPY "webapp\target\*.war" "%JBOSS_HOME%\standalone\deployments\"'
	end	
	task :unix_deploy => :compile do
		system 'cp webapp/target/*.war $JBOSS_HOME/standalone/deployments'
	end	
	
end

end
