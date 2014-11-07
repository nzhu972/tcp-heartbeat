HQ Heart Beat Project
===========================

Summary
-----------------------------------------
Heartbeat interval value can be set as a dynamic parameter during the build.

    mvn clean install -Dheartbeat.internval=<value> 
    (value is in millisec. Default is set to 300000 ms if not specified)
    
Patient Request is defined in application.properties
    
Prerequisite
-----------------------------------------

Ensure your development environment is configured to access Trinity's Nexus repo.
For instruction on how to provision, please refer to the document on Trinity's Phabricator WIKI.
The doc name is 'Trinity IDE Environment Setup Guide'

Building and installing the project into a local Standalone Fuse ESB (non-fabric mode)
-----------------------------------------
1. From the base directory of this project (i.e., where this README file is
located), the Maven pom.xml file can be used to build 

   mvn clean install -P <profile>
  (profile can be local/dev/stage/prod. For deploying to local Fuse ESB installation, use 'local')
  
  (OPTIONAL) To skip Junit test case
  
       mvn clean install -P local -Dmaven.test.skip=true
  
  (OPTIONAL) To run the Junit test case individually
  
        mvn test -Dtest=<TestCaseName> such as mvn test -Dtest=HeartbeatTest
  
2. Install the bundle into the Fuse ESB container. In the Fuse Command Console, issue the following command.

  osgi:install -s file:C:/<location>/target/heartbeat-hq-0.0.1-SNAPSHOT.jar
  
Building and installing the project into a local Standalone Fuse ESB (Fabric mode)
-----------------------------------------  
1. Create a Fabric Profile for deployment

    fabric:profile-create --version <version-number> <profile-name>
    (version-number is the version that the container is on. It's show when running fabric:container-list.
     profile-name is the name that you can provide)
    *Note* if --version is ommitted, then the base version 1.0 will be used.  
    
ie. 
    fabric:profile-create --1.1 heartbeat
         

2. To install the bundle, use the following command in Fuse Command Console

   fabric:profile-edit --bundles mvn:org.cheth.esb.cpi/cpisearch-empi/1.0.0-SNAPSHOT cpisearch 1.1

(OPTIONAL) To remove a bundle from profile

    fabric:profile-edit --delete --bundles mvn:org.cheth.esb.heartbeat/heartbeat-hq/0.0.1-SNAPSHOT heartbeat 1.1
    
3. apply the profile to the container. This step will provision the bundle onto the root container. Root container is the 
   default container when Fabric Fuse ESB is first created.

   fabric:container-add-profile <container-name> <profile-name>
   
   ie.
   
   fabric:container-add-profile root heartbeat
   
(OPTIONAL) To remove profile from container

   fabric:container-remove-profile root heartbeat
   
       
